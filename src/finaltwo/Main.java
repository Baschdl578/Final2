package finaltwo;

import edu.kit.informatik.Terminal;
import finaltwo.ants.Ant;
import finaltwo.messages.Output;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Sebastian on 19-Mar-15
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class Main {
    private static TurningRule rules = null;
    private static LinkedList<Ant> ants = new LinkedList<Ant>(); //Sorted by alphabet
    private static int speed = 2;
    private static Field topLeft;

    /**
     * Gets the turning rules
     * @return Turning rules as TurningRule object
     */
    public static TurningRule getTurningRules() {
        return rules;
    }

    /**
     * Deletes an Ant, exits if no Ants are in the game
     * @param ant Ant to delete
     */
    public static void deleteAnt(Ant ant) {
        if (!ants.contains(ant)) {
            Output.printMessage("Main.6", true, false);
            return;
        }
        ants.remove(ant);
        ant.getPosition().setOccupiedBy(null);
        if (ants.size() == 0) {
            System.exit(0);
        }
    }

    /**
     * Gets an Ant from it's name
     * @param ant Name of the Ant
     * @return Ant
     */
    public static Ant getAnt(String ant) {
        char name = ant.charAt(0);
        ListIterator<Ant> iter = ants.listIterator();
        Ant current;
        while (iter.hasNext()) {
            current = iter.next();
            if (current.getName() == name) {
                return current;
            }
        }
        Output.printMessage("Main.6", true, false);
        return null;
    }

    /**
     * Adds an Ant to the List
     * @param a Ant to add
     */
    public static void addAnt(Ant a) {
        ants.addFirst(a);
        Collections.sort(ants); //Just inserting and sorting afterwards should not create much performance loss
                                //We're just doing this 26 times MAX
    }

    /**
     * Returns the Speedup factor of this game
     * @return Speedup factor  as integer
     */
    public static int getSpeed() {
        return speed;
    }

    /**
     * Returns a Field from its coordinates
     * @param coord coordinates in this format: <i>line</i>,<i>row</i>
     * @return Field with the given coordinates or null if invalid coordinates
     */
    public static Field getField(String coord) {
        int[] coordinates = new int[2];
        try {
            String[] tmp = coord.split(",");
            if (tmp.length != 2) {
                Output.printMessage("Interactive.5", true, false);
                return null;
            }
            coordinates[0] = Integer.parseInt(tmp[0]);
            coordinates[1] = Integer.parseInt(tmp[1]);
        } catch (NumberFormatException e) {
            Output.printMessage("Interactive.5", true, false);
            return null;
        }
        Field out = topLeft;
        for (int i = 0; i < coordinates[0]; i++) {
            out = out.getSouth();
            if (out == null) {
                Output.printMessage("Interactive.6", true, false);
                return null;
            }
        }
        for (int i = 0; i < coordinates[1]; i++) {
            out = out.getEast();
            if (out == null) {
                Output.printMessage("Interactive.6", true, false);
                return null;
            }
        }

        return out;
    }

    /**
     * Performs the given number of moves for every Ant
     * @param moves Number of moves
     */
    public static void move(int moves) {
        for (int i = 0; i < moves; i++) {
            for (Ant a: ants) {
                a.move();
            }
        }
    }

    /**
     * Returns a String representation of every Ant, seperated by a comma
     * @return String with every Ant's name
     */
    public static String getStringAnts() {
        ListIterator<Ant> iter = ants.listIterator();
        String out = "";

        while (iter.hasNext()) {
            if (!out.isEmpty()) out += ",";
            out += iter.next().toString();
        }
        return out;
    }

    /**
     * Prints every Ant's name, seperated by a comma
     */
    public static void printAnts() {
        Terminal.printLine(getStringAnts());
    }

    /**
     * Prints the game board
     */
    public static void printGame() {
        String out = "";

        Field current = topLeft;

        while (current != null) {
            out += current.toString();

            Field nextInLine = current;
            while (nextInLine.getEast() != null) {
                nextInLine = nextInLine.getEast();
                out += nextInLine.toString();
            }
            if (current.getSouth() != null) {
                out += "\n";
            }
            current = current.getSouth();
        }
        Terminal.printLine(out);
    }

    /**
     * Main method
     * Checks arguments, sets everything up and starts interactive interface
     * @param args Arguments from command line
     */
    public static void main(String[] args) {
        String rule = "270-90-315-45-90";

        if (args.length == 0) {
            Output.printMessage("Main.1", true, true);
            System.exit(1);
        }
        if (args.length > 2) {
            Output.printMessage("Main.4", true, true);
        }


        File f = new File(args[0]);
        if (!(f.exists() && !f.isDirectory())) {
            Output.printMessage("Main.2", true, false);
            System.exit(1);
        }
        if (args.length > 1) {
            if (args[1].startsWith("speedup=")) {
                try {
                    speed = Integer.parseInt(args[1].substring(8));
                } catch (NumberFormatException e) {
                    Output.printMessage("Main.3", true, false);
                    System.exit(1);
                }
                if (args.length == 3) {
                    if (args[2].startsWith("rule=")) {
                        rule = args[2].substring(5);
                    } else {
                        Output.printMessage("Main.5", true, true);
                        System.exit(1);
                    }
                }

            } else {
                if (args[1].startsWith("rule=")) {
                    rule = args[1].substring(5);
                    if (args.length == 3) {
                        if (args[2].startsWith("speedup=")) {
                            try {
                                speed = Integer.parseInt(args[2].substring(8));
                            } catch (NumberFormatException e) {
                                Output.printMessage("Main.3", true, false);
                                System.exit(1);
                            }
                        } else {
                            Output.printMessage("Main.5", true, true);
                            System.exit(1);
                        }
                    }
                } else {
                    Output.printMessage("Main.5", true, true);
                    System.exit(1);
                }
            }
        }
        rules = new TurningRule(rule);
        topLeft = Setup.setup(args[0]);
        Interactive.getInput();
    }
}
