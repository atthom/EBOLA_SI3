/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc;

import java.util.Objects;
import plague.inc.Entity.AbstractEntity;

/**
 *
 * @author user
 */
public class Tile {
    AbstractEntity entity;
    
    public Tile() {
        entity = null;
    }

    public void setEntity(AbstractEntity entity) {
        this.entity = entity;
   //     this.entity.setT(this);
    }
    

    public void swap_entities(Tile t) throws PlagueException {
        if(t.is_empty()) {
            t.setEntity(entity);
            this.entity = null;
        } else {
            throw new PlagueException("La place est prise !");
        }
    }
    
    public boolean is_empty() {
        return entity == null;
    }

    public AbstractEntity getEntity() {
        return entity;
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
        final Tile other = (Tile) obj;
        return Objects.equals(this.entity, other.entity);
    }
    
    
    
    
    
    
    
}
