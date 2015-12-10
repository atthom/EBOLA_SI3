/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc.map;

/**
 * Classe coordonée 2D
 * @author user
 */
public class Coordinates {
    private int x,y;

    /**
     *
     * @param x
     * @param y
     */
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return y;
    }
    
    /**
     * Permet de calculer la distance manhatan entre deux coordonnées
     * @param a la seconde coordonnée
     * @return
     */
    public int distance(Coordinates a) {
        return Math.abs(a.x - this.x) + Math.abs(a.y - this.y);
    }
    
    
    public int distance() {
        return Math.abs(this.x) + Math.abs(this.y);
    }
    
    public Coordinates randomize() {
        return new Coordinates(
                (int) ((Math.random() * 3) - 1 + this.x),
                (int) ((Math.random() * 3) - 1 + this.y));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordinates other = (Coordinates) obj;
        if (this.x != other.x) {
            return false;
        }
        return this.y == other.y;
    }

    private void setX(int x) {
        this.x = x;
    }

    private void setY(int y) {
        this.y = y;
    }
      
    
}
