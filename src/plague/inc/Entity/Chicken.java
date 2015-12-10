/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc.Entity;

import java.io.IOError;
import java.util.List;
import java.util.Random;

import FluGraph.Field;
import plague.inc.Coordinates;
import plague.inc.State;

/**
 *
 * @author user
 */
public class Chicken extends Animals {

	
    public Chicken(Field field,Coordinates coord){
    	super(field,coord);
    	this.StatusTime = 0;
    	this.virus = Viruses.NOPROBLEM;
    }
    
    @Override
	protected void incrementStatusTime()
    {
        StatusTime++;
        if(this.virus.getAnimalIncubationTime() == this.StatusTime)//tester en fonction de l'évolution de la maladie de l'infecté
        	StateChange();
    }		
    
    


//A MODIFIER CAR CELA NE SUFFIT PAS !!!!
    @Override
	protected void StateChange() {
		Random rand = new Random();
	    int i = rand.nextInt(10);
		switch (this.state) {
		case SICK:
			this.becomeContagious();
			break;
		case CONTAGIOUS:
			//soit il meurt sois il guérit
			if(i%3 == 0)
				this.die();
			break;		
		default: return;
		}
		
	}

	@Override
	protected void becomeSick(Viruses v) {
		if(!this.isAlive())
			throw new IOError(new Throwable("Le poulet est mort et ne peut changer d'état ! Erreur !"));
		if(this.state.equals(State.HEALTHY)){
			this.virus = v;
			this.state = State.SICK;
		}	
		this.StatusTime = 0;
	}



	@Override
	protected void becomeContagious() {
		if(!this.isAlive())
			throw new IOError(new Throwable("Le poulet est mort et ne peut changer d'état ! Erreur !"));
		if(this.state.equals(State.SICK)){
			this.state = State.CONTAGIOUS;
		}	
		this.StatusTime = 0;
		
	}



	@Override
	protected void die() {
		if(!this.isAlive())
			throw new IOError(new Throwable("Le poulet est mort et ne peut pas mourir ! Erreur !"));
		if(this.state.equals(State.CONTAGIOUS) && this.StatusTime == this.virus.getAnimalIncubationTime()){
			this.die();
		}			
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
		//TODO : Tester tester tester
		if(this.state.equals(State.CONTAGIOUS)){
			if(abs.getClass().getTypeName().equals("Chicken") || abs.getClass().getTypeName().equals("Duck") || abs.getClass().getTypeName().equals("Person"))
				abs.becomeSick(this.virus);
		}
		else{
			if(abs.state.equals(State.CONTAGIOUS))
				abs.becomeSick(this.virus);
		}
	}
    
}
