/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc.Entity;

import FluGraph.core.Field;
import java.util.List;
import plague.inc.map.Coordinates;

<<<<<<< HEAD
import FluGraph.Field;
import plague.inc.Coordinates;
import plague.inc.State;
import plague.inc.Viruses;
=======
import plague.inc.virus.Viruses;
>>>>>>> fd9b0cd483cc21b99053464caa1e28313d58505a

/**
 *
 * @author user
 */
public class Duck extends Animals {

<<<<<<< HEAD
    public Duck(Field field,Coordinates coord) {
    	super(field,coord);
    	this.StatusTime = 0;
    	this.virus = Viruses.NOPROBLEM;
    }

    
    
    @Override
    public void contact(AbstractEntity abs) {
		if(this.state.equals(State.CONTAGIOUS)){
			if(abs.getClass().getTypeName().equals("Chicken") || abs.getClass().getTypeName().equals("Duck") || abs.getClass().getTypeName().equals("Person"))
				abs.becomeSick(this.virus);
		}
		else{
			if(abs.state.equals(State.CONTAGIOUS))
				abs.becomeSick(this.virus);
		}
=======
    public Duck(Field field, Coordinates coord) {
        super(field, coord);
    }

    public void Contact(AbstractEntity abs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> fd9b0cd483cc21b99053464caa1e28313d58505a
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

<<<<<<< HEAD
	@Override
	public void act(List<AbstractEntity> newEntities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void incrementStatusTime() {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected void StateChange() {
		// TODO Auto-generated method stub
		
	}
    
=======
>>>>>>> fd9b0cd483cc21b99053464caa1e28313d58505a
}
