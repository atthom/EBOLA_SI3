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
 * Classe Canard qui est un animal
 */
public class Canard extends Animaux{
    private static final double pourcentageContaminationHumain = 0.5;
    private static final double pourcentageContaminationCongenaires = 0.5;
    private static final Random rand = Randomizer.getRandom();

    public double getPourcentageContaminationHumain() {
        return pourcentageContaminationHumain;
    }

    public double getPourcentageContaminationCongenaires() {
        return pourcentageContaminationCongenaires;
    }

    public Canard(){
        super();
    }

    public Canard(EtatEtreVivant healthLivingBeing, int healthTime, Location position, Field fields, Virus virus) {
        super(healthLivingBeing, healthTime, position, fields, virus);
    }

    /***
     * intéraction avec les cases adjacentes
     */
    @Override
    public void interagit(){
       // Random rand = Randomizer.getRandom();
        if(this.pourcentageSocial <= rand.nextDouble()) {
            if (this.getEtat().equals(EtatEtreVivant.CONTAGIEUX)) {
                ArrayList<EtreVivant> cibles = this.ciblesPotentiellesAdjacentes(this.getPosition());
                for (EtreVivant vivants : cibles) {
                    if (vivants.getEtat().equals(EtatEtreVivant.SAINT)) {
                        if (vivants.getClass().getSimpleName().equals("Poulet") || vivants.getClass().getSimpleName().equals("Humain") || vivants.getClass().getSimpleName().equals("Canard"))
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
        //Random random = Randomizer.getRandom();
        if(living.getClass().getSimpleName().equals("Humain")){
            if(Canard.pourcentageContaminationHumain < rand.nextDouble())
                living.devientMalade(Virus.H5N1Humain);
        }
        else{
            if(living.getClass().getSimpleName().equals("Poulet")){
                if (Canard. pourcentageContaminationCongenaires < rand.nextDouble())
                    living.devientMalade(Virus.H5N1Poulet);
            }else{
                if(Canard.pourcentageContaminationCongenaires < rand.nextDouble())
                    living.devientMalade(Virus.H5N1Canard);
            }
        }
    }
}
