package model.entities;


import java.util.ArrayList;
import java.util.List;

import model.map.Field;
import model.map.Location;
import model.virus.Virus;


/**
 *
 * @author Adrien Prestini
 */
public abstract class AbstractEntity {
    //ajout des attributs inspirés de Fox&Rabbits
	protected Person name;
    protected boolean alive;
    protected Field field;
    protected int incubationTime;
    protected double socialRate;
    protected List<Virus> potentialVirus;
    protected State state;
    protected Virus virus;
    protected Location location;

    public AbstractEntity(Location location, Field field){
        this(location,field,null);
    }


    public AbstractEntity(Location location, Field field, Virus v){

        this.field=field;
        this.alive=true;
        setLocation(location);
        potentialVirus=new ArrayList<>();
        incubationTime=0;
        this.virus=v;
        state = virus.equals(Virus.OK) ?State.HEALTHY : State.SICK ;
    }


    protected abstract void action();

    protected abstract void interagit();

    protected abstract void expose(List<Location> neighbors);

    protected abstract boolean infecte(Location entite);

    public boolean isAlive(){
        return alive;
    }

    protected boolean isAvaibleToVirus(AbstractEntity species){
        return species.getPotentialVirus().contains(this.getVirus());
    }

    protected void infectEntity(AbstractEntity entity){
    	entity.virus=this.virus;
    	entity.state=State.SICK;
    	entity.incubationTime = virus.getIncubationTime();
    }
    public Location getLocation(){
        return location;
    }

    public Field getField(){
        return field;
    }

    public State getState(){
        return state;
    }

    protected Virus getVirus(){
        return virus;
    }

    public List<Virus> getPotentialVirus() {
        return potentialVirus;
    }

    public double getSocialRate() {
        return socialRate;
    }

    public int getIncubationTime() {
        return incubationTime;
    }

    public Person getName(){return name;};

    public void setIncubationTime(int incubationTime) {
        this.incubationTime = incubationTime;
    }

    public void setVirus(Virus v) {
        this.virus = v;
    }

    public void setState(State state) {
        this.state = state;
    }

    protected void setLocation(Location newLocation){
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setPotentialVirus(ArrayList<Virus> potentialVirus) {
        this.potentialVirus = potentialVirus;
    }

    public void setSocialRate(double socialRate) {
        this.socialRate = socialRate;
    }
    
    protected boolean isAvaibleToDisease(AbstractEntity entite){
        return entite.getPotentialVirus().contains(this.getVirus());
    }

   protected abstract void becomeContagious();
   protected abstract void verifyDead();
   /**
    * l'animal infecte tous ces voisins 
    * @param voisins
    */
   	protected void infecteVoisins(List<Location> voisins) {
   		for(int i =0; i < voisins.size(); i++){
   			this.infecte(voisins.get(i));
   		}
   	}

}
