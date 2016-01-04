package modele.etresVivants.typeEtresVivants;

import modele.Virus.Virus;
import modele.carte.Field;
import modele.carte.Location;
import modele.carte.Randomizer;
import modele.etresVivants.EtatEtreVivant;
import modele.etresVivants.EtreVivant;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Adrien Prestini on 18/12/2015.
 */

//l'homme ne peut que s'infecter entre congénaires et non avec des animaux
public class Humain extends EtreVivant implements SeDeplacer{

    private static final double peutBouger = 0.8;
    private static final double peutBougerMalade = 0.3;
    private static final double pourcentageContaminationHumain = 0.4;
    private int tempsRecup = 2;
    private static final double pourcentageSocial = 0.2;
    private static final Random rand = Randomizer.getRandom();

    public Humain(){
        super();
    }

    /***
     * Constructeur normal de l'Homme
     * @param healthLivingBeing
     * @param healthTime
     * @param position
     * @param fields
     * @param virus
     */
    public Humain(EtatEtreVivant healthLivingBeing, int healthTime, Location position, Field fields, Virus virus) {
        super(healthLivingBeing, healthTime, position, fields, virus);
    }
    /***
     * intéraction avec les cases adjacentes
     */
    @Override
    public void interagit() {
        if(Humain.pourcentageSocial <= rand.nextDouble()) {
            if (this.getEtat().equals(EtatEtreVivant.CONTAGIEUX)) {
                ArrayList<EtreVivant> cibles = this.ciblesPotentiellesAdjacentes(this.getPosition());
                for (EtreVivant vivants : cibles) {
                    if (vivants.getEtat().equals(EtatEtreVivant.SAINT)) {
                        if (vivants.getClass().getSimpleName().equals("Humain"))
                            this.infecte(vivants);
                    }
                }
            }
        }
        verifieEtat();
    }

    /***
     * A chaque étape de la pandémie, on appelle la fonction action
     */
    @Override
    public void action() {
        if(this.estVivant()) {
            bouge();
            interagit();
        }
    }
    /**
     * Le canard infecte les possibles victimes autour de lui
     * @param vivants
     */
    @Override
    public void infecte(EtreVivant vivants) {
        //Random random = Randomizer.getRandom();
        if (Humain.pourcentageContaminationHumain < rand.nextDouble()) {
            switch (this.getVirus().getNomVirus()) {
                case "H1N1":
                    vivants.devientMalade(Virus.H1N1Humain);
                    break;

                case "H5N1":
                    vivants.devientMalade(Virus.H5N1Humain);
                    break;

                default:
                    break;
            }
        }
    }

    /***
     * Méthode permettant le déplacement de l'Homme
     */
    @Override
    public void bouge() {
        Location newLocation = getChamp().freeAdjacentLocation(getPosition());
        if(this.etat.equals(EtatEtreVivant.SAINT)){
            if(rand.nextDouble() >= Humain.peutBouger) {
                if (newLocation != null) {
                    setLocation(newLocation);
                }
            }
        }else {
            if (rand.nextDouble() >= Humain.peutBougerMalade) {
                if (newLocation != null) {
                    setLocation(newLocation);
                }
            }
        }
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


    public double getPeutBouger() {
        return peutBouger;
    }

    public double getPourcentageContaminationHumain() {
        return pourcentageContaminationHumain;
    }

    public double getPourcentageSocial() {
        return pourcentageSocial;
    }

    public int getTempsRecup() {
        return tempsRecup;
    }

    public double getPeutBougerMalade() {
        return peutBougerMalade;
    }

    /***
     * L'Homme est le seul EtreVivant à pouvoir guerrir de son infection
     */
    public void guerit() {
        if (this.etat.equals(EtatEtreVivant.GUERI)) {
            if (this.tempsRecup == 0) {
                tempsRecup = 2;
                this.etat = EtatEtreVivant.SAINT;
                this.virus = Virus.Rien;
            } else
                this.tempsRecup--;
        }
        else
            this.etat = EtatEtreVivant.GUERI;
    }

    /***
     * On vérifie l'état de santé de l'Homme
     */
    public void verifieEtat(){
        switch (this.etat){
            case MALADE:
                if(this.getTempsEtat() == 0)
                    devientContagieux();
                else
                    this.tempsEtat--;
                break;

            case CONTAGIEUX:
                if(this.getTempsEtat() == 0){
                    if(rand.nextDouble() >= this.getVirus().getProbaMourir() )
                        this.guerit();
                    else
                        this.meurt();
                }
                else
                    this.tempsEtat--;
                break;

            case GUERI:
                this.guerit();
                break;

            default:break;
        }
    }

}
