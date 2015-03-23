package finaltwo;

import edu.kit.informatik.Terminal;
import finaltwo.ants.Ant;
import finaltwo.ants.LazyAnt;
import finaltwo.ants.SportyAnt;
import finaltwo.ants.StandardAnt;
import finaltwo.messages.Output;

/**
 * Created by Sebastian on 20-Mar-15
 * Contains all methods for the interactive Interface
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class Interactive {

    /**
     * Waits for Input and calls appropriate Methods
     */
    public static void getInput() {
        String[] input = Terminal.readLine().split(" ");

        if (input.length == 1) {
            if (input[0].equals("quit")) {
                System.exit(0);
            } else {
                if (input[0].equals("print")) {
                    Main.printGame();
                } else {
                    if (input[0].equals("ant")) {
                        Main.printAnts();
                    } else {
                        Output.printMessage("Interactive.1", true, false);
                        Output.printMessage("HELP", true, false);
                    }
                }
            }
        } else {
            if (input.length == 2) {
                if (input[0].equals("move")) {
                    int moves;
                    try {
                        moves = Integer.parseInt(input[1]);
                        Main.move(moves);
                    } catch (NumberFormatException e) {
                        Output.printMessage("Interactive.2", true, false);
                    }
                } else {
                    if (input[0].equals("position")) {
                        position(input[1]);
                    } else {
                        if (input[0].equals("field")) {
                            Field field = Main.getField(input[1]);
                            if (field != null) {
                                Terminal.printLine(field.toString());
                            }
                        } else {
                            if (input[0].equals("direction")) {
                                direction(input[1]);
                            } else {
                                if (input[0].equals("create")) {
                                    create(input[1]);
                                } else {
                                    if (input[0].equals("escape")) {
                                        Ant ant = Main.getAnt(input[1].toLowerCase());
                                        if (ant != null) {
                                            Main.deleteAnt(ant);
                                        }
                                    } else {
                                        Output.printMessage("Interactive.1", true, false);
                                        Output.printMessage("HELP", true, false);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        getInput();
    }

    /**
     * Gets and prints the Position of an Ant
     * @param arg Ant's name from Terminal
     */
    private static void position(String arg) {
        if (arg.length() != 1) {
            Output.printMessage("Interactive.3", true, false);
        } else {
            Ant ant = Main.getAnt(arg.toLowerCase());
            if (ant != null) {
                Field pos = ant.getPosition();

                Terminal.printLine(pos.getCoordinates()[0] + "," + pos.getCoordinates()[1]);
            }
        }
    }

    /**
     * Gets and prints the direction of an Ant
     * @param arg Ant's name
     */
    private static void direction(String arg) {
        if (arg.length() != 1) {
            Output.printMessage("Interactive.3", true, false);
        } else {
            Ant ant = Main.getAnt(arg.toLowerCase());
            if (ant != null) {
                Terminal.printLine(ant.getHumanDirection());
            }
        }
    }

    /**
     * Creates an Ant
     * @param arg <i>Ant's name</i>,<i>line</i>,<i>row</i>
     */
    private static void create(String arg) {
        String[] args = arg.split(",");
        if (args.length != 3 || args[0].length() != 1) {
            Output.printMessage("Interactive.4", true, false);
            return;
        }
        char descr = args[0].charAt(0);
        if (!(('a' <= descr && descr <= 'z') || ('A' <= descr && descr <= 'Z'))) {
            Output.printMessage("Interactive.4", true, false);
            return;
        }
        if (Main.getStringAnts().contains(args[0])) {
            Output.printMessage("Interactive.7", true, false);
            return;
        }
        Field putOn = Main.getField(args[1] + "," + args[2]);
        if (putOn == null) return;


        if ('a' <= descr && 'h' >= descr) {
            StandardAnt newAnt = new StandardAnt(180, descr);
            Main.addAnt(newAnt);
            newAnt.setPosition(putOn);
            putOn.setOccupiedBy(newAnt);
        }
        if ('i' <= descr && 'q' >= descr) {
            SportyAnt newAnt = new SportyAnt(180, descr);
            Main.addAnt(newAnt);
            newAnt.setPosition(putOn);
            putOn.setOccupiedBy(newAnt);
        }
        if ('r' <= descr && 'z' >= descr) {
            LazyAnt newAnt = new LazyAnt(180, descr);
            Main.addAnt(newAnt);
            newAnt.setPosition(putOn);
            putOn.setOccupiedBy(newAnt);
        }
        if ('A' <= descr && 'H' >= descr) {
            StandardAnt newAnt = new StandardAnt(0, descr);
            Main.addAnt(newAnt);
            newAnt.setPosition(putOn);
            putOn.setOccupiedBy(newAnt);
        }
        if ('I' <= descr && 'Q' >= descr) {
            SportyAnt newAnt = new SportyAnt(0, descr);
            Main.addAnt(newAnt);
            newAnt.setPosition(putOn);
            putOn.setOccupiedBy(newAnt);
        }
        if ('R' <= descr && 'Z' >= descr) {
            LazyAnt newAnt = new LazyAnt(0, descr);
            Main.addAnt(newAnt);
            newAnt.setPosition(putOn);
            putOn.setOccupiedBy(newAnt);
        }
    }
}
