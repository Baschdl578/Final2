package finaltwo.ants;

import finaltwo.Main;

/**
 * Created by Sebastian on 19-Mar-15
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class LazyAnt extends Ant {
    private int turnSinceLastMove;

    /**
     * Constructs a new Lazy Ant
     * @param direction Initial direction o the Ant
     * @param name Name of the Ant
     */
    public LazyAnt(int direction, char name) {
        super(direction, name);
        turnSinceLastMove = Main.getSpeed();
    }

    /**
     * Moves the Ant
     */
    public void move() {
        if (Main.getSpeed() == turnSinceLastMove) {
            turnSinceLastMove = 1;
            step();
        } else {
            turnSinceLastMove++;
        }
    }
}
