package modele.etresVivants;

import modele.Virus.Virus;
import modele.carte.Field;
import modele.carte.Location;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import modele.carte.Randomizer;
import modele.etresVivants.typeEtresVivants.SeDeplacer;


/**
 * Created by Adrien Prestini on 18/12/2015.
 */
public abstract class EtreVivant implements SeDeplacer{

    protected EtatEtreVivant etat;
    protected static final double peutBouger = 0.8;
    protected static final double peutBougerMalade = 0.3;
    protected int tempsEtat;
    protected Location position;
    protected Field champ;
    protected Virus virus;
    protected static final Random rand = Randomizer.getRandom();

    /**
     * Constructeur par defaut d'un etre vivant
     */
    public EtreVivant() {
        this.etat = EtatEtreVivant.SAIN;
        this.tempsEtat = 0;
        this.position = null;
        this.champ = null;
        this.virus = null;
    }

    /**
     * Constructeur normal d'un etre vivant
     * @param healthLivingBeing
     * @param healthTime
     * @param position
     * @param fields
     * @param virus
     */
    public EtreVivant(EtatEtreVivant healthLivingBeing, int healthTime, Location position, Field fields,  Virus virus) {
        this.champ = fields;
        this.etat = healthLivingBeing;
        this.tempsEtat = healthTime;
        setPosition(position);
        this.virus = virus;
    }

    /**
     * L'etre vivant devient malade avec le virus v
     * @param v
     */
    public void devientMalade(Virus v) {
        etat = EtatEtreVivant.MALADE;
        tempsEtat = v.getTempsMalade();
        virus = v;
    }

    /**
     * L'etre vivant devient contagieux
     */
    protected void devientContagieux() {
        etat = EtatEtreVivant.CONTAGIEUX;
        tempsEtat = virus.getTempsContagion();
    }

    /***
     * Positionne les etres vivants sur la grille graphique
     * @param location
     */
    public void setPosition(Location location) {
        if (this.position != null) {
            this.champ.clear(position);
        }
        this.position = location;
        this.champ.place(this, location);
    }

    public abstract void action();

    /***
     * L'etre vivant meurt et disparait de la grille graphique
     */
    protected void meurt(){
        this.tempsEtat--;
        etat = EtatEtreVivant.MORT;
        if (position != null) {
            champ.clear(position);
            position = null;
            champ = null;
        }

    }

    /***
     * Methode qui recherche les cibles adjacentes potentielles
     * @param positionDonnee
     * @return une liste d'etre vivants adjacents et victimes potentielles
     */
    public ArrayList<EtreVivant> ciblesPotentiellesAdjacentes(Location positionDonnee/*,int option4ou8*/){
        List<Location> positionCibles = champ.adjacentLocations(positionDonnee);
        ArrayList<EtreVivant> res = new ArrayList<>();
        for(Location o : positionCibles){
            if(champ.getObjectAt(o) != null)
                res.add((EtreVivant)champ.getObjectAt(o));
        }
        return res;
    }

    public abstract void interagit();

    public abstract void infecte(EtreVivant vivants);


//-------------------------------------------------------GETTERS--------------------------------------------------------
    public int getTempsEtat() {
        return tempsEtat;
    }

    public EtatEtreVivant getEtat() {
        return etat;
    }

    public Location getPosition() {
        return position;
    }

    public Field getChamp() {
        return champ;
    }

    public Virus getVirus() {
        return virus;
    }

    public boolean estVivant(){
        return !this.getEtat().equals(EtatEtreVivant.MORT);
    }
    
    /***
     * Méthode permettant le déplacement de l'Homme
     */
    @Override
    public void bouge() {
        Location newLocation = getChamp().freeAdjacentLocation(getPosition());
        if(this.etat.equals(EtatEtreVivant.SAIN)){
            if(rand.nextDouble() >= EtreVivant.peutBouger) {
                if (newLocation != null) {
                    setLocation(newLocation);
                }
            }
        }else {
            if (rand.nextDouble() >= EtreVivant.peutBougerMalade) {
                if (newLocation != null) {
                    setLocation(newLocation);
                }
            }
        }
    }
    
     public double getPeutBouger() {
        return peutBouger;
    }
    
    public double getPeutBougerMalade() {
        return peutBougerMalade;
    }

    /***
     * Changement de position sur la grille graphique
     * @param newLocation
     */
    @Override
    public void setLocation(Location newLocation) {
        if (this.position != null) {
            this.champ.clear(getPosition());
        }
        this.position = newLocation;
        this.champ.place(this, newLocation);
    }

}
