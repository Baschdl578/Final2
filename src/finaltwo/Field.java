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

    public void reColor() {
        this.color = (4 * this.color + 23) % 5;
    }


    public int getColor() {
        return color;
    }

    /**
     * Sets the initial color
     * @param color initial color
     */
    public void setColor(int color) {
        this.color = color;
    }

    public String toString() {
        if (isObstacle) return "*";
        if (isOccupied) return occupiedBy.toString();

        return Integer.toString(color);
    }

    public Ant getOccupiedBy() {
        return occupiedBy;
    }

    public void setOccupiedBy(Ant occupiedBy) {
        this.isOccupied = (occupiedBy != null);
        this.occupiedBy = occupiedBy;
    }

    public Field getNorth() {
        return north;
    }

    public void setNorth(Field north) {
        this.north = north;
    }

    public Field getNortheast() {
        return north.getEast();
    }

    public Field getEast() {
        return east;
    }

    public void setEast(Field east) {
        this.east = east;
    }

    public Field getSoutheast() {
        return south.getEast();
    }

    public Field getSouth() {
        return south;
    }

    public void setSouth(Field south) {
        this.south = south;
    }

    public Field getSouthwest() {
        return south.getWest();
    }

    public Field getWest() {
        return west;
    }

    public void setWest(Field west) {
        this.west = west;
    }

    public Field getNortwest() {
        return north.getWest();
    }

    public int[] getCoordinates() {
        return coordinates;
    }
}
