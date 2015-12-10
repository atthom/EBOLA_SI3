package FluGraph;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import plague.inc.Coordinates;
import plague.inc.Entity.AbstractEntity;

/**
 * Represent a rectangular grid of field positions. Each position is able to
 * store a single entity.
 * 
 * @author David J. Barnes and Michael Kölling
 * @override Adrien Prestini
 * @version 2011.07.31
 * @VersionNew 2015.12.10
 */
public class Field {
    // A random number generator for providing random locations.
    private static final Random rand = Randomizer.getRandom();

    // The depth and width of the field.
    private int depth, width;
    // Storage for the entitys.
    private AbstractEntity[][] field;

    /**
     * Represent a field of the given dimensions.
     * 
     * @param depth
     *            The depth of the field.
     * @param width
     *            The width of the field.
     */
    public Field(int depth, int width) {
        this.depth = depth;
        this.width = width;
        field = new AbstractEntity[depth][width];
    }

    /**
     * Empty the field.
     */
    public void clear() {
        for (int row = 0; row < depth; row++) {
            for (int col = 0; col < width; col++) {
                field[row][col] = null;
            }
        }
    }

    /**
     * Clear the given location.
     * 
     * @param location
     *            The location to clear.
     */
    public void clear(Coordinates cord) {
        field[cord.getX()][cord.getY()] = null;
    }

    /**
     * Place an entity at the given location. If there is already an entity at
     * the location it will be lost.
     * 
     * @param entity
     *            The entity to be placed.
     * @param row
     *            Row coordinate of the location.
     * @param col
     *            Column coordinate of the location.
     */
    public void place(AbstractEntity entity, int row, int col) {
        place(entity, new Coordinates(row, col));
    }

    /**
     * Place an entity at the given location. If there is already an entity at
     * the location it will be lost.
     * 
     * @param entity
     *            The entity to be placed.
     * @param location
     *            Where to place the entity.
     */
    public void place(AbstractEntity entity, Coordinates location) {
        field[location.getX()][location.getY()] = entity;
    }

    /**
     * Return the entity at the given location, if any.
     * 
     * @param location
     *            Where in the field.
     * @return The entity at the given location, or null if there is none.
     */
    public AbstractEntity getAbstractEntitytAt(Coordinates location) {
        return getAbstractEntitytAt(location.getX(), location.getY());
    }

    /**
     * Return the entity at the given location, if any.
     * 
     * @param row
     *            The desired row.
     * @param col
     *            The desired column.
     * @return The entity at the given location, or null if there is none.
     */
    public AbstractEntity getAbstractEntitytAt(int row, int col) {
        return field[row][col];
    }

    /**
     * Generate a random location that is adjacent to the given location, or is
     * the same location. The returned location will be within the valid bounds
     * of the field.
     * 
     * @param location
     *            The location from which to generate an adjacency.
     * @return A valid location within the grid area.
     */
    public Coordinates randomAdjacentCoordinates(Coordinates location) {
        List<Coordinates> adjacent = adjacentCoordinates(location);
        return adjacent.get(0);
    }

    /**
     * Get a shuffled list of the free adjacent coordinates.
     * 
     * @param location
     *            Get locations adjacent to this.
     * @return A list of free adjacent coordinates.
     */
    public List<Coordinates> getFreeAdjacentCoordinates(Coordinates location) {
        List<Coordinates> free = new LinkedList<>();
        List<Coordinates> adjacent = adjacentCoordinates(location);
        for (Coordinates next : adjacent) {
            if (getAbstractEntitytAt(next) == null) {
                free.add(next);
            }
        }
        return free;
    }

    /**
     * Try to find a free location that is adjacent to the given location. If
     * there is none, return null. The returned location will be within the
     * valid bounds of the field.
     * 
     * @param location
     *            The location from which to generate an adjacency.
     * @return A valid location within the grid area.
     */
    public Coordinates freeAdjacentCoordinates(Coordinates location) {
        // The available free ones.
        List<Coordinates> free = getFreeAdjacentCoordinates(location);
        if (free.size() > 0) {
            return free.get(0);
        } else {
            return null;
        }
    }

    /**
     * Return a shuffled list of locations adjacent to the given one. The list
     * will not include the location itself. All locations will lie within the
     * grid.
     * 
     * @param location
     *            The location from which to generate adjacencies.
     * @return A list of locations adjacent to that given.
     */
    public List<Coordinates> adjacentCoordinates(Coordinates location) {
        assert location != null : "Null location passed to adjacentCoordinates";
        // The list of locations to be returned.
        List<Coordinates> locations = new LinkedList<>();
        if (location != null) {
            int row = location.getX();
            int col = location.getY();
            for (int roffset = -1; roffset <= 1; roffset++) {
                int nextRow = row + roffset;
                if (nextRow >= 0 && nextRow < depth) {
                    for (int coffset = -1; coffset <= 1; coffset++) {
                        int nextCol = col + coffset;
                        // Exclude invalid locations and the original location.
                        if (nextCol >= 0 && nextCol < width
                                && (roffset != 0 || coffset != 0)) {
                            locations.add(new Coordinates(nextRow, nextCol));
                        }
                    }
                }
            }

            // Shuffle the list. Several other methods rely on the list
            // being in a random order.
            Collections.shuffle(locations, rand);
        }
        return locations;
    }

    /**
     * Return the depth of the field.
     * 
     * @return The depth of the field.
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Return the width of the field.
     * 
     * @return The width of the field.
     */
    public int getWidth() {
        return width;
    }
}
