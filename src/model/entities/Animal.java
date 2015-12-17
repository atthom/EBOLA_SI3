package model.entities;

import java.util.List;
import java.util.Random;
import model.map.Field;
import model.map.Location;
import model.map.Randomizer;
import model.virus.Virus;

public abstract class Animal extends AbstractEntity{
	protected double tauxSocialHumain;
	
	private static final Random rand = Randomizer.getRandom();
	
    public Animal(Location location, Field field, Virus v ){
        super(location, field, v);
    }
    
	@Override
	public void action() {
		if(isAlive())
			interagit();
	}

	@Override
	protected void interagit() {
		List<Location> voisins = field.getBusyAdjacentLocations(location);
		switch (this.getState()) {
		
		case HEALTHY :
			expose(voisins);
			break;
		
		case SICK :
			this.becomeContagious();
			break;
		
		case CONTAGIOUS : 
			infecteVoisins(voisins);
			verifyDead();
		default:
			throw new IllegalArgumentException("État de l'entite inconnu");
		}
	}
/**
 * On vérifie s'il peut mourir et si c'est le cas il disparait de la carte
 */
	@Override
	protected void verifyDead() {
        if (rand.nextDouble() <= this.getVirus().getDeathRate()) {
            this.setAlive(false);
            if (location != null) {
                field.clear(location);
                location = null;
                field = null;
            }
        }
	}

	@Override
	protected void becomeContagious() {
		this.incubationTime--;
		if(this.incubationTime <= 0);
			this.setState(State.CONTAGIOUS);
	}



	@Override
	protected void expose(List<Location> voisins) {
        for (Location neighbor : voisins) {
            if (rand.nextDouble() <= this.getSocialRate())
                continue;	
            AbstractEntity entite = (AbstractEntity) getField().getObjectAt(neighbor);
            if (entite.infecte(getLocation())) break;
        }
		
	}

	@SuppressWarnings("static-access")
	@Override
	protected boolean infecte(Location location) {
		//si il infecte
		if(this.rand.nextDouble() <= this.getVirus().getInfectionRate()){
			AbstractEntity entite = (AbstractEntity) getField().getObjectAt(location);
			
			switch (entite.getName()) {
			case HUMAN :
                if (rand.nextDouble() <= getTauxSocialHumain() && isAvaibleToDisease(entite)) {
                    infectEntity(entite);
                    return true;
                }
                break;
                
                
			default :
				if (isAvaibleToDisease(entite)) {
                    infectEntity(entite);
                    return true;
                }
				break;
			}
		}
		return false;
	}
	protected double getTauxSocialHumain() {
		return tauxSocialHumain;
	}

    public void setTauxSocialHumain(double humanSocialRate) {
        this.tauxSocialHumain = humanSocialRate;
    }

}
