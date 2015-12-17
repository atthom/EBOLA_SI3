package model.entities;

import model.map.Field;
import model.map.Location;
import model.virus.Virus;

/**
 * Created by tibo on 17/12/15.
 */
public class Pig extends Animal {

    public Pig(Location location, Field field, Virus v){
        super(location, field, v);
    }
}
