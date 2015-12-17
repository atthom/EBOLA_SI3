package model.entities;


import java.util.List;

import model.map.Field;
import model.map.Location;
import model.virus.Virus;

/**
 * Created by tibo on 17/12/15.
 */
public class Chicken extends FlyingThings{
	 private static final Virus DEFAULT_VIRUS = Virus.OK;
	 private static final double DEFAULT_HUMAN_SOCIAL_RATE = 0.3;
	 private static final double DEFAULT_SOCIAL_RATE = 0.9;

	public Chicken(Location location, Field field, Virus v) {
		this(location, field, v, DEFAULT_HUMAN_SOCIAL_RATE, DEFAULT_SOCIAL_RATE,FlyingThings.DEFAULT_AVAIBLE_VIRUS);
	}
	public Chicken(Location location, Field field) {
		this(location, field, DEFAULT_VIRUS, DEFAULT_HUMAN_SOCIAL_RATE, DEFAULT_SOCIAL_RATE,FlyingThings.DEFAULT_AVAIBLE_VIRUS);
	}

	public Chicken(Location location, Field field, Virus v, double humanSocialRate, double socialRate, List<Virus> avaibleDisease) {
	        super(location, field, v);
	        this.name=Person.CHICKEN;
	        this.setTauxSocialHumain(humanSocialRate);
	        this.setSocialRate(socialRate);
	        this.getPotentialVirus().addAll(avaibleDisease);
	}

}
