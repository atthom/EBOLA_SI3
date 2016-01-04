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
public class Humain extends EtreVivant {


    private static final double pourcentageContaminationHumain = 0.4;
    private int tempsRecup = 2;
    private static final double pourcentageSocial = 0.2;
  

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
                    if (vivants.getEtat().equals(EtatEtreVivant.SAIN)) {
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
            
            if(this.etat.equals(EtatEtreVivant.MALADE)) {
//                if (rand.nextFloat() <= this.getPeutBougerMalade()) {
//                    bouge();
//                }
            } else {
                bouge();
            }
            
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

    


   

    public double getPourcentageContaminationHumain() {
        return pourcentageContaminationHumain;
    }

    public double getPourcentageSocial() {
        return pourcentageSocial;
    }

    public int getTempsRecup() {
        return tempsRecup;
    }

    

    /***
     * L'Homme est le seul EtreVivant à pouvoir guerrir de son infection
     */
    public void guerit() {
        if (this.etat.equals(EtatEtreVivant.GUERI)) {
            if (this.tempsRecup == 0) {
                tempsRecup = 2;
                this.etat = EtatEtreVivant.SAIN;
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
