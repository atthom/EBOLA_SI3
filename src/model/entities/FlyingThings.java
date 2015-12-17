package model.entities;

import java.util.ArrayList;
import java.util.List;

import model.map.Field;
import model.map.Location;
import model.virus.Virus;

/**
 * Created by tibo on 17/12/15.
 */
public abstract class FlyingThings extends Animal {
	protected static final List<Virus> DEFAULT_AVAIBLE_VIRUS =new ArrayList<Virus>(){{add(Virus.H5N1);}};
	
    public FlyingThings(Location location, Field field, Virus v){
        super(location, field, v);
    }

}
