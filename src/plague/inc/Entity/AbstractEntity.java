/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc.Entity;

import java.util.List;
import FluGraph.core.Field;
import java.util.Objects;
import plague.inc.map.Coordinates;
import plague.inc.virus.Viruses;

/**
 *
 * @author user
 */
public abstract class AbstractEntity {
    //ajout des attributs inspir�s de Fox&Rabbits

    protected boolean alive;
    protected Field field;
    protected int StatusTime = 0;

    protected State state;
    protected Viruses virus;
    protected Coordinates coord;

    public AbstractEntity() {
        state = State.HEALTHY;
        virus = null;
        coord = new Coordinates(0, 0);
    }

    public boolean isAlive() {
        return alive;
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

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates c) {
        this.coord = c;
    }

    public Coordinates move() {
        return coord.randomize();
    }

    protected int getStatusTime() {
        return StatusTime;
    }
// a revoir

    public abstract void contact(AbstractEntity abs);

    /**
     * Make this animal act - that is: make it do whatever it wants/needs to do.
     *
     * @param newAnimals A list to receive newly born animals.
     */
    abstract public void act(List<AbstractEntity> newEntities);

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
    protected void die() {
        alive = false;
        if (coord != null) {
            field.clear(coord);
            coord = null;
            field = null;
        }
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

    /**
     * Place the animal at the new location in the given field.
     *
     * @param newLocation The animal's new location.
     */
    protected void setCoordinates(Coordinates newLocation) {
        if (coord != null) {
            field.clear(coord);
        }
        coord = newLocation;
        field.place(this, newLocation);
    }

    protected abstract void incrementStatusTime();

    protected abstract void StateChange();

}
