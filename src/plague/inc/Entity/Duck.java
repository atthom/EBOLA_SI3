/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc.Entity;

import java.util.List;

import FluGraph.Field;
import plague.inc.Coordinates;
import plague.inc.State;
import plague.inc.Viruses;

/**
 *
 * @author user
 */
public class Duck  extends Animals {

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
	protected void incrementStatusTime() {
		// TODO Auto-generated method stub
		
	}



	@Override
	protected void StateChange() {
		// TODO Auto-generated method stub
		
	}
    
}
