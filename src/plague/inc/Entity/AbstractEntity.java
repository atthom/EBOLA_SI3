/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc.Entity;

import java.util.Objects;
import plague.inc.Coordinates;
import plague.inc.Map;
import plague.inc.State;
import plague.inc.Viruses;

/**
 *
 * @author user
 */
public abstract class AbstractEntity {

    protected State state;
    protected Viruses virus;
    protected Coordinates coord;
    protected int StatusTime = 0;

    public AbstractEntity() {
        state = State.HEALTHY;
        virus = null;
        coord = new Coordinates(0, 0);
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

    public void setCoord(Coordinates c) {
        this.coord = c;
    }

    public void move(Map map) {
        coord = map.move(coord, coord.randomize());
    }

    protected Coordinates getCoord() {
        return coord;
    }

    protected int getStatusTime() {
        return StatusTime;
    }

    public abstract void Contact(AbstractEntity abs);

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
        if (this.StatusTime != other.StatusTime) {
            return false;
        }
        if (this.state != other.state) {
            return false;
        }
        if (this.virus != other.virus) {
            return false;
        }
        if (!Objects.equals(this.coord, other.coord)) {
            return false;
        }
        return true;
    }

}
