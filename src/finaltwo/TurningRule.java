package finaltwo;


/**
 * Created by Sebastian on 19-Mar-15
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class TurningRule {
    private int[] turningRule = new int[5];

    /**
     * Creates a new set of rules;
     * @param rules Error checked String for console arguments
     */
    public TurningRule(String rules) {
        String[] numbers = rules.split("-");
        for (int i = 0; i < 5; i++) {
            turningRule[i] = Integer.parseInt(numbers[i]);
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
        if (newRot > 360) {
            newRot -= 360;
        }
        return newRot;
    }
}
