/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc.Entity;

import java.util.List;

import plague.inc.State;
import plague.inc.Viruses;

/**
 *
 * @author user
 */
public class Person extends AbstractEntity {

    public Person() {
    	super();
    }
    
    
    
    @Override
    public void Contact(AbstractEntity abs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



	@Override
	protected void becomeSick(Viruses v)throws IllegalArgumentException {
		if(this.state.equals(State.HEALTHY)){
			this.state = State.SICK;
			virus = v;
		}else
			throw new IllegalArgumentException();
	}



	@Override
	protected void becomeContagious()throws IllegalArgumentException {
		if(this.state.equals(State.SICK)){
			this.state = State.CONTAGIOUS;
		}else
			throw new IllegalArgumentException();
	}



	public void recovery()throws IllegalArgumentException {
		if(this.state.equals(State.CONTAGIOUS)){
			this.state = State.HEALTHY;
		}else
			throw new IllegalArgumentException();
	}

	
	@Override
	protected void die()throws IllegalArgumentException {
		if(this.state.equals(State.CONTAGIOUS)){
			this.state = State.HE_S_DEAD_MOTHAFUCKA;
		}else
			throw new IllegalArgumentException();
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
    
}
