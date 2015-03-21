package finaltwo.ants;


import finaltwo.Field;
import finaltwo.Main;

/**
 * Created by Sebastian on 19-Mar-15
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public abstract class Ant implements Comparable<Ant> {
    private char name;
    private int direction; //0 is facing north
    private Field position;

    /**
     * Constructor for manual creation with speed set
     * @param direction direction in which the Ant points
     * @param name Name of the ant
     */
    public Ant(int direction, char name) {
        this.direction = direction;
        char lowerName = Character.toLowerCase(name);
        this.name = lowerName;
    }

    /**
     * Compares this Ant with the specified Ant for order.
     * Returns a negative integer, zero, or a positive integer as this Ant's name is less than, equal to, or greater
     * than that of the specified Ant.
     * @param a Ant to be compared to
     * @return a negative integer, zero, or a positive integer
     */
    public int compareTo(Ant a) {
        Character charA = new Character(a.getName());
        Character charB = new Character(this.getName());

        return charB.compareTo(charA);
    }

    /**
     * Moves the Ant
     */
    public abstract void move();

    /**
     * Performs actual movement of the Ant
     * Call move() for type specific movement
     */
    void step() {
        Field next = getNextField();
        if (next != null && next != position) {
            position.setOccupiedBy(null);
            next.setOccupiedBy(this);
            this.position = next;
            this.direction = Main.getTurningRules().getNewDirection(this.position.getColor(), this.direction);
            this.position.reColor();
            return;
        }
        if (next == null) { //This means the Ant is off the board
            Main.deleteAnt(this);
            return;
        }
        this.direction = Main.getTurningRules().getNewDirection(this.position.getColor(), this.direction);
        this.position.reColor();
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

        if (next == null) {
            return null;
        }

        if (!next.canEnter()) {
            next = position;
        }
        return next;
    }

    /**
     * Gets the direction in an human-readable Format
     * @return N, NO, O, SO, S, SW, W, or NW
     */
    public String getHumanDirection() {
        if (direction == 0) return "N";
        if (direction == 45) return "NO";
        if (direction == 90) return "O";
        if (direction == 135) return "SO";
        if (direction == 180) return "S";
        if (direction == 225) return "SW";
        if (direction == 270) return "W";
        if (direction == 315) return "NW";
        return "-1";
    }

    public String toString() {
        return "" + this.name;
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

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }
}
