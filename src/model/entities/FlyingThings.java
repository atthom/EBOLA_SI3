package model.entities;

import model.map.Field;
import model.map.Location;
import model.virus.Virus;

/**
 * Created by tibo on 17/12/15.
 */
public abstract class FlyingThings extends Animal {

    public FlyingThings(Location location, Field field, Virus v){
        super(location, field, v);
    }

}
