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
import plague.inc.Viruses;

/**
 *
 * @author user
 */
public abstract class AbstractEntity {
    private State state;
    private Viruses virus;
    private Coordinates coord;
    private int StatusTime = 0 ;
    
    public AbstractEntity() {
        state = State.Healty;
    }

    public State getS() {
        return state;
    }
    public void setS(State s) {
        this.state = s;
    }
      
    protected Viruses getVirus() {
		return virus;
	}

	protected Coordinates getT() {
		return coord;
	}

	protected int getStatusTime() {
		return StatusTime;
	}

	public void move(Mapppp map) {
        int rand = (int) (Math.random() * 4);        
    }
       
    public abstract void Contact(AbstractEntity abs);

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
        return this.state == other.state;
    }
    
    /**
	 * Un individu sain devient malade
	 */
	protected abstract void becomeSick(Viruses v);
	
	/**
	 * Un invidu devient contagieux
	 */
	protected abstract void becomeContagious();
	
	/**
	 * Un individu meurt
	 */
	protected abstract void die();
	
    
    
}
