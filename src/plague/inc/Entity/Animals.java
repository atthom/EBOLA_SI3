package plague.inc.Entity;

import FluGraph.Field;
import plague.inc.map.Coordinates;

public abstract class Animals extends AbstractEntity {
	
	public Animals(Field field, Coordinates coord){
		this.alive = true;
		this.field = field;
		setCoordinates(coord);
	}
	
	
	
	
	
}
