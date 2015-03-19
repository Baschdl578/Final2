package finaltwo;


/**
 * Created by Sebastian on 19-Mar-15
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class Ant {
    private int direction; //0 is facing north
    private Field position;
    private int speed;
    private boolean isSlow;
    private int timeSinceMove;

    /**
     * Constructor for manual creation with speed set
     * @param direction direction in which the Ant points
     * @param position Position of the Ant
     * @param speed speed factor of the Ant
     * @param isSlow true if lazy Ant, otherwise sporty is assumed, normal is created by speed = 1
     */
    public Ant(int direction, Field position, int speed, boolean isSlow) {
        this.direction = direction;
        this.position = position;
        this.speed = speed;
        this.isSlow = isSlow;
        this.timeSinceMove = this.speed;

    }

    /**
     * Moves the Ant
     */
    public void move() {
        if (isSlow && timeSinceMove != speed) {
            timeSinceMove++;
        } else {
            int reps = speed;
            if (isSlow) {
                timeSinceMove = 1;
                reps = 1;
            }
            for (int i = 0; i < reps; i++) {
                step();
            }

        }
    }

    private void step() {
        Field next = getNextField();
        if (next != null && next != position) {
            position.setOccupiedBy(null);
            next.setOccupiedBy(this);
            this.position = next;
            this.direction = Main.getTurningRules().getNewDirection(this.position.getColor(), this.direction);
            this.position.setColor((4 * this.position.getColor() + 23) % 5);
            return;
        }
        if (next == null) { //This means the Ant is off the board
            Main.deleteAnt(this);
            return;
        }
        this.direction = Main.getTurningRules().getNewDirection(this.position.getColor(), this.direction);
    }

    private Field getNextField() {
        Field next;
        switch (direction) {
            case 0:
                next = position.getNorth();
                break;
            case 45:
                next = position.getNortheast();
                break;
            case 90:
                next = position.getEast();
                break;
            case 135:
                next = position.getSoutheast();
                break;
            case 180:
                next = position.getSouth();
                break;
            case 225:
                next = position.getSouthwest();
                break;
            case 270:
                next = position.getWest();
                break;
            case 315:
                next = position.getNortwest();
                break;
            default:
                next = position;
        }

        if (!next.canEnter()) {
            next = position;
        }
        return next;
    }




//GETTER AND SETTER:


    public Field getPosition() {
        return position;
    }

    public void setPosition(Field position) {
        this.position = position;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isSlow() {
        return isSlow;
    }

    public void setSlow(boolean isSlow) {
        this.isSlow = isSlow;
    }
}
