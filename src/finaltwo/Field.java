package finaltwo;

import finaltwo.ants.Ant;

/**
 * Created by Sebastian on 19-Mar-15
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class Field {
    private Field north;
    private Field east;
    private Field south;
    private Field west;
    private int[] coordinates = new int[2];

    private int color = 0;
    private boolean isObstacle;
    private boolean isOccupied;
    private Ant occupiedBy = null;

    /**
     * Constructs a new Field. DOES NOT SET NEIGHBORS
     * @param color color
     * @param obstacle isObstacle
     * @param occupied isOccupied
     * @param occupiedBy if occupied, else null;
     * @param line line (for coordinates)
     * @param position position (for coordinates)
     */
    public Field(int color, boolean obstacle, boolean occupied, Ant occupiedBy, int line, int position) {
        this.color = color;
        this.isObstacle = obstacle;
        this.isOccupied = occupied;
        this.coordinates[0] = line;
        this.coordinates[1] = position;

        if (this.isOccupied) {
            this.occupiedBy = occupiedBy;
        }
    }


    /**
     * Returns whether an Ant can enter this Field
     * @return True if unoccupied and no obstacle
     */
    public boolean canEnter() {
        return (!isObstacle && !isOccupied);
    }

    /**
     * Applies the new color to the Field when entered
     */
    public void reColor() {
        this.color = (4 * this.color + 23) % 5;
    }

    /**
     * Returns a String representation of the Field
     * @return Ant if this is occupied, "*" if obstacle or color if empty
     */
    public String toString() {
        if (isObstacle) return "*";
        if (isOccupied) return occupiedBy.toString();

        return Integer.toString(color);
    }


//GETTER AND SETTER:
    /**
     * Puts a new Ant on this Field
     * @param occupiedBy Ant to put here
     */
    public void setOccupiedBy(Ant occupiedBy) {
        this.isOccupied = (occupiedBy != null);
        this.occupiedBy = occupiedBy;
    }

    /**
     * Gets the Color of the Field
     * @return Color as integer
     */
    public int getColor() {
        return color;
    }

    /**
     * Returns the Field north of this
     * @return Field north of this
     */
    public Field getNorth() {
        return north;
    }

    /**
     * Returns the Field north of this
     * @param north Field north of this
     */
    public void setNorth(Field north) {
        this.north = north;
    }

    /**
     * Returns the Field northeast of this
     * @return Field northeast of this
     */
    public Field getNortheast() {
        if (this.north == null) return null;
        return north.getEast();
    }

    /**
     * Returns the Field east of this
     * @return Field east of this
     */
    public Field getEast() {
        return east;
    }

    /**
     * Sets the Field east of this
     * @param east Field east of this
     */
    public void setEast(Field east) {
        this.east = east;
    }

    /**
     * Returns the Field southeast of this
     * @return Field southeast of this
     */
    public Field getSoutheast() {
        if (this.south == null) return null;
        return south.getEast();
    }

    /**
     * Returns the Field south of this
     * @return Field south of this
     */
    public Field getSouth() {
        return south;
    }

    /**
     * Sets the Field south of this
     * @param south Field south of this
     */
    public void setSouth(Field south) {
        this.south = south;
    }

    /**
     * Returns the Field southwest of this
     * @return Field southwest of this
     */
    public Field getSouthwest() {
        if (this.south == null) return null;
        return south.getWest();
    }

    /**
     * Returns the Field west of this
     * @return Field west of this
     */
    public Field getWest() {
        return west;
    }

    /**
     * Sets the Field west of this
     * @param west Field west of this
     */
    public void setWest(Field west) {
        this.west = west;
    }

    /**
     * Returns the Field northwest of this
     * @return Field northwest of this
     */
    public Field getNortwest() {
        if (this.north == null) return null;
        return north.getWest();
    }

    /**
     * Gets the coordinates of this field
     * @return Coordinates as an int Array int[row][column]
     */
    public int[] getCoordinates() {
        return coordinates;
    }
}
