package model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.map.Field;
import model.map.Location;
import model.map.Randomizer;
import model.virus.Virus;

public class Human extends AbstractEntity implements SeDeplace {

    protected static final Virus VIRUS_PAR_DEFAUT = Virus.OK;
    protected static final double TAUX_ACTIVITE_DEFAUT = 0.5;
    protected static final double TAUX_ACTIVITE_MALADE_DEFAUT = 0.6;
    protected static final double TAUX_SOCIAL_DEFAUT = 0.9;
    protected static final ArrayList<Virus> VIRUS_POSSIBLE = new ArrayList<Virus>() {
        {
            add(Virus.H1N1);
            add(Virus.H5N1);
        }
    };

    private static final Random rand = Randomizer.getRandom();

    private double activityRate;
    private int recoveringTime;
    private int contagionTime;

    public Human(Location location, Field field) {
        this(location, field, VIRUS_PAR_DEFAUT, TAUX_ACTIVITE_DEFAUT, TAUX_SOCIAL_DEFAUT, VIRUS_POSSIBLE);
    }

    public Human(Location location, Field field, Virus disease, double activityRate, double socialRate, List<Virus> avaibleDisease) {
        super(location, field, disease);
        this.name = Person.HUMAN;
        this.recoveringTime = 0;
        this.contagionTime = 0;
        this.activityRate = activityRate;
        this.setSocialRate(socialRate);
        this.getPotentialVirus().addAll(avaibleDisease);

    }

    @SuppressWarnings("static-access")
    @Override
    public void action() {
        if (isAlive()) {
            if (this.rand.nextDouble() <= this.activityRate) {
                if (this.getState().equals(State.HEALTHY) || rand.nextDouble() <= TAUX_ACTIVITE_MALADE_DEFAUT) {
                    seDeplace();
                }
            }
            interagit();
        }

    }

    @Override
    protected void interagit() {
        List<Location> lesVoisins = field.getBusyAdjacentLocations(getLocation());

        switch (getState()) {
            case HEALTHY:
                expose(lesVoisins);
                break;
            case SICK:
                becomeContagious();
                break;
            case CONTAGIOUS:
                infecteVoisins(lesVoisins);
                verifyRecovering();
                break;
            case RECOVERY:
                verifyDead();
                break;
            default:
                throw new IllegalArgumentException("State unknow");
        }

    }

    private void verifyRecovering() {
        contagionTime--;
        if (contagionTime <= 0) {
            setState(State.RECOVERY);
            recoveringTime = this.getVirus().getRecoveringTime();
        }
    }

    @Override
    protected void expose(List<Location> voisins) {
        for (Location neighbor : voisins) {
            if (rand.nextDouble() <= this.getSocialRate()) {
                continue;
            }
            AbstractEntity entite = (AbstractEntity) getField().getObjectAt(neighbor);
            if (entite.infecte(getLocation())) {
                break;
            }
        }
    }

    @Override
    protected boolean infecte(Location location) {
        if (rand.nextDouble() <= this.getVirus().getInfectionRate()) {
            AbstractEntity e = (AbstractEntity) getField().getObjectAt(location);
            if (e.name.equals(Person.HUMAN)) {
                e.setDisease(this.getVirus());
                e.setState(State.SICK);
                e.setIncubationTime(this.getVirus().getIncubationTime());
                return true;
            }
        }
        return false;
    }

    @Override
    public void seDeplace() {
        Location newLocation = getField().freeAdjacentLocation(getLocation());
        if (newLocation != null) {
            setLocation(newLocation);
        }
    }

    @Override
    public void setLocation(Location newLocation) {
        if (this.location != null) {
            this.field.clear(getLocation());
        }
        this.location = newLocation;
        this.field.place(this, newLocation);
    }

    @Override
    protected void becomeContagious() {
        this.incubationTime--;
        if (getIncubationTime() <= 0) {
            this.setState(State.CONTAGIOUS);
            this.contagionTime = this.getVirus().getContagionTime();
        }
    }

    @Override
    protected void verifyDead() {
        this.recoveringTime--;
        if (rand.nextDouble() <= this.getVirus().getDeathRate()) {
            this.setAlive(false);
            if (location != null) {
                field.clear(location);
                location = null;
                field = null;
            }
        } else if (this.recoveringTime <= 0) {
            this.setState(State.HEALTHY);
        }
    }

}
