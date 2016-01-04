package modele.etresVivants;

import modele.Virus.Virus;
import modele.carte.Field;
import modele.carte.Location;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Adrien Prestini on 18/12/2015.
 */
public abstract class EtreVivant{

    protected EtatEtreVivant etat;
    protected int tempsEtat;
    protected Location position;
    protected Field champ;
    protected Virus virus;
    protected int nombreVoisins;


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
    public EtreVivant(EtatEtreVivant healthLivingBeing, int healthTime, Location position, Field fields,  Virus virus, int nombreVoisins) {
        this.champ = fields;
        this.etat = healthLivingBeing;
        this.tempsEtat = healthTime;
        setPosition(position);
        this.virus = virus;
        this.nombreVoisins = nombreVoisins;
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
    public void meurt(){
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
    public ArrayList<EtreVivant> ciblesPotentiellesAdjacentes(Location positionDonnee,int option4ou8){
        List<Location> positionCibles;
        if(option4ou8 == 4) {
            positionCibles = champ.crossLocation(positionDonnee);

        }else {

            positionCibles = champ.adjacentLocations(positionDonnee);
        }

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

    public void setVirus(Virus v){this.virus = virus;}

    public void changeEtat(EtatEtreVivant etat){this.etat = etat;}
}
