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

/**
 *
 * @author user
 */
public abstract class AbstractEntity {

    private State s;
    private Coordinates coord;

    public AbstractEntity() {
        s = State.Healty;
        coord = null;
    }

    public void setCoord(Coordinates c) {
        this.coord = c;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public State getS() {
        return s;
    }

    public void setS(State s) {
        this.s = s;
    }

    public void move(Map map) {
        coord = map.move(coord, coord.randomize());
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
        if (this.s != other.s) {
            return false;
        }
        if (!Objects.equals(this.coord, other.coord)) {
            return false;
        }
        return true;
    }

}
