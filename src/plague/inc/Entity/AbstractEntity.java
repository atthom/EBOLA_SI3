/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc.Entity;

import plague.inc.Coordinates;
import plague.inc.Mapppp;
import plague.inc.State;
import plague.inc.Tile;

/**
 *
 * @author user
 */
public abstract class AbstractEntity {
    private State s;
    private Coordinates t;
    
    public AbstractEntity() {
        s = State.Healty;
    }

    public State getS() {
        return s;
    }
    public void setS(State s) {
        this.s = s;
    }
    
    
    public void move(Mapppp map) {
        int rand = (int) (Math.random() * 4);        
    }
       
    public abstract void Contact(AbstractEntity abs);
//    public void move(Tile t) {
//        int rand =  (int) (Math.random() * 4);
//    }
//    
//    private void swap(Tile t1, Tile t2) {
//        Tile tc = t1;
//        t1 = t2;
//        t2 = tc;
//    }

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
        final AbstractEntity other = (AbstractEntity) obj;
        return this.s == other.s;
    }
    
}
