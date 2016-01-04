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
public class Cochon extends Animaux{

    private static final double pourcentageContaminationHumain = 0.2;
    private static final double pourcentageContaminationCongenaires = 0.7;
    private static final Random rand = Randomizer.getRandom();

    public double getPourcentageContaminationHumain() {
        return pourcentageContaminationHumain;
    }

    public double getPourcentageContaminationCongenaires() {
        return pourcentageContaminationCongenaires;
    }
    public Cochon(){
        super();
    }

    public Cochon(EtatEtreVivant healthLivingBeing, int healthTime, Location position, Field fields, Virus virus) {
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
                    if (vivants.getEtat().equals(EtatEtreVivant.SAIN)) {
                        if (vivants.getClass().getSimpleName().equals("Cochon") || vivants.getClass().getSimpleName().equals("Humain"))
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
            if(Cochon.pourcentageContaminationHumain < rand.nextDouble())
                living.devientMalade(Virus.H1N1Humain);
        }
        else{
            if(Cochon.pourcentageContaminationCongenaires < rand.nextDouble())
                living.devientMalade(Virus.H1N1Cochon);
        }
    }
}

