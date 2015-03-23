package finaltwo.ants;

import finaltwo.Main;

/**
 * Created by Sebastian on 19-Mar-15
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class SportyAnt extends Ant {

    /**
     * Construcs a new Sporty Ant
     * @param direction Initial direction of the Ant
     * @param name Name of the Ant
     */
    public SportyAnt(int direction, char name) {
        super(direction, name);
    }

    /**
     * Moves the Ant
     */
    public void move() {
        for (int i = 0; i < Main.getSpeed(); i++) {
            step();
        }
    }
}
