package finaltwo.ants;

/**
 * Created by Sebastian on 19-Mar-15
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class LazyAnt extends Ant {
    private int speed;
    private int turnSinceLastMove;


    public LazyAnt(int direction, char name, int speed) {
        super(direction, name);
        this.speed = speed;
        turnSinceLastMove = speed;
    }

    /**
     * Moves the Ant
     */
    public void move() {
        if (speed == turnSinceLastMove) {
            turnSinceLastMove = 1;
            step();
        } else {
            turnSinceLastMove++;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
