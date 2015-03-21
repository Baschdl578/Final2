package finaltwo.ants;

/**
 * Created by Sebastian on 19-Mar-15
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class SportyAnt extends Ant {
    private int speed;

    public SportyAnt(int direction, char name, int speed) {
        super(direction, name);
        this.speed = speed;
    }

    /**
     * Moves the Ant
     */
    public void move() {
        for (int i = 0; i < speed; i++) {
            step();
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
