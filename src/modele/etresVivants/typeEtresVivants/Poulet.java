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
 * Created by Adrien on 18/12/2015.
 */
public class Poulet extends Animaux {
    private static final double pourcentageContaminationHumain = 0.2;
    private static final double pourcentageContaminationCongenaires = 0.7;
    private static final Random rand = Randomizer.getRandom();

    public double getPourcentageContaminationHumain() {
        return pourcentageContaminationHumain;
    }

    public double getPourcentageContaminationCongenaires() {
        return pourcentageContaminationCongenaires;
    }
    public Poulet(){
        super();
    }

    public Poulet(EtatEtreVivant healthLivingBeing, int healthTime, Location position, Field fields, Virus virus) {
        super(healthLivingBeing, healthTime, position, fields, virus);
    }
    /***
     * intéraction avec les cases adjacentes
     */
    @Override
    public void interagit(){

        if(this.pourcentageSocial <= rand.nextDouble()) {
            if (this.getEtat().equals(EtatEtreVivant.CONTAGIEUX)) {
                ArrayList<EtreVivant> cibles = this.ciblesPotentiellesAdjacentes(this.getPosition());
                for (EtreVivant vivants : cibles) {
                    if (vivants.getEtat().equals(EtatEtreVivant.SAINT)) {
                        if (vivants.getClass().getSimpleName().equals("Humain") || vivants.getClass().getSimpleName().equals("Poulet") || vivants.getClass().getSimpleName().equals("Canard"))
                            this.infecte(vivants);
                    }
                }
            }
        }
    }
    /**
     * Le canard infecte les possibles victimes autour de lui
     * @param living
     */
    @Override
    public void infecte(EtreVivant living){

        if(living.getClass().getSimpleName().equals("Humain")){

            if(Poulet.pourcentageContaminationHumain < rand.nextDouble())
                living.devientMalade(Virus.H5N1Humain);
        }
        else{
            if(living.getClass().getSimpleName().equals("Canard")){
                if (Poulet.pourcentageContaminationCongenaires < rand.nextDouble())
                    living.devientMalade(Virus.H5N1Canard);
            }else{
                if(Poulet.pourcentageContaminationCongenaires < rand.nextDouble())
                    living.devientMalade(Virus.H5N1Poulet);
            }
        }
    }
}
