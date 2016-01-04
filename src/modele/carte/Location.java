package modele.carte;

import java.awt.*;

/**
 * Represent a location in a rectangular grid.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Location extends Point
{
    /**
     * Represent a row and column.
     * @param row The row.
     * @param col The column.
     */
    public Location(int row, int col)
    {
        this.x = row;
        this.y = col;
    }
    
    /**
     * Implement content equality.
     */
    public boolean equals(Object obj)
    {
        if(obj instanceof Location) {
            Location other = (Location) obj;
            return x == other.getRow() && y == other.getCol();
        }
        else {
            return false;
        }
    }
    
    /**
     * Return a string of the form row,column
     * @return A string representation of the location.
     */
    public String toString()
    {
        return x + "," + y;
    }
    
    /**
     * Use the top 16 bits for the row value and the bottom for
     * the column. Except for very big grids, this should give a
     * unique hash code for each (row, col) pair.
     * @return A hashcode for the location.
     */
    public int hashCode()
    {
        return (x << 16) + y;
    }
    
    /**
     * @return The row.
     */
    public int getRow()
    {
        return x;
    }
    
    /**
     * @return The column.
     */
    public int getCol()
    {
        return y;
    }
}
