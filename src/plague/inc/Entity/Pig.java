/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc.Entity;

import FluGraph.Field;
import java.util.List;
import plague.inc.map.Coordinates;
import plague.inc.virus.Viruses;

/**
 *
 * @author user
 */
public class Pig extends Animals {

    public Pig(Field field, Coordinates coord) {
        super(field, coord);
    }

    @Override
    protected void becomeSick(Viruses v) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void becomeContagious() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void die() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isAlive() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void act(List<AbstractEntity> newEntities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void contact(AbstractEntity abs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void incrementStatusTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void StateChange() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
