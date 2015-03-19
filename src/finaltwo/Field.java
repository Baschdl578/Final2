package finaltwo;

/**
 * Created by Sebastian on 19-Mar-15
 *
 * @author Sebastian Schindler
 * @version 1.0
 */
public class Field {
    private Field north;
    private Field northeast;
    private Field east;
    private Field southeast;
    private Field south;
    private Field southwest;
    private Field west;
    private Field nortwest;

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
     */
    public Field(int color, boolean obstacle, boolean occupied, Ant occupiedBy) {
        this.color = color;
        this.isObstacle = obstacle;
        this.isOccupied = occupied;

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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
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
        return northeast;
    }

    public void setNortheast(Field northeast) {
        this.northeast = northeast;
    }

    public Field getEast() {
        return east;
    }

    public void setEast(Field east) {
        this.east = east;
    }

    public Field getSoutheast() {
        return southeast;
    }

    public void setSoutheast(Field southeast) {
        this.southeast = southeast;
    }

    public Field getSouth() {
        return south;
    }

    public void setSouth(Field south) {
        this.south = south;
    }

    public Field getSouthwest() {
        return southwest;
    }

    public void setSouthwest(Field southwest) {
        this.southwest = southwest;
    }

    public Field getWest() {
        return west;
    }

    public void setWest(Field west) {
        this.west = west;
    }

    public Field getNortwest() {
        return nortwest;
    }

    public void setNortwest(Field nortwest) {
        this.nortwest = nortwest;
    }
}
