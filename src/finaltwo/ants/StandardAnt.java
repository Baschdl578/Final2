package finaltwo.ants;

import finaltwo.Field;

/**
 * Created by Sebastian on 19-Mar-15
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class StandardAnt extends Ant {
    private int direction; //0 is facing north
    private Field position;

    /**
     * Constructor for manual creation with speed set
     * @param direction direction in which the Ant points
     * @param name Name of the ant
     */
    public StandardAnt(int direction, char name) {
        super(direction, name);
    }

    /**
     * Moves the Ant
     */
    public void move() {
        step();
    }

}
