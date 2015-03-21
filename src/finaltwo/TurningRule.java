package finaltwo;


import finaltwo.messages.Output;

/**
 * Created by Sebastian on 19-Mar-15
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class TurningRule {
    private int[] turningRule = new int[5];

    /**
     * Creates a new set of rules and exits on invalid Input
     * @param rules Error checked String for console arguments
     */
    public TurningRule(String rules) {
        String[] rule = rules.split("-");
        int[] turns = new int[5];

        if (rule.length != 5) {
            Output.printMessage("Rules.1", true, true);
            System.exit(1);
        }

        for (int i = 0; i < 5; i++) {
            try {
                turns[i] = Integer.parseInt(rule[i]);
                if (turns[i] != 45 && turns[i] != 90 && turns[i] != 270 && turns[i] != 315) {
                    Output.printMessage("Rules.1", true, true);
                    System.exit(1);
                }
            } catch (NumberFormatException e) {
                Output.printMessage("Rules.1", true, true);
                System.exit(1);
            }
        }
    }

    /**
     * Returns the new Direction of an Ant
     * @param color Color the Ant is standing on
     * @param oldRot old Direction
     * @return new Direction
     */
    public int getNewDirection(int color, int oldRot) {
        int newRot = oldRot + turningRule[color];
        if (newRot >= 360) {
            newRot -= 360;
        }
        return newRot;
    }
}
