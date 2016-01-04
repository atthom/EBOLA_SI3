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
 * Classe Abstraite pour les Animaux et dont on pourra facilement ajouter d'autres animaux
 */
public abstract class Animaux extends EtreVivant {
    //probabilité d'intéragir avec les individus adjacents
    protected double pourcentageSocial = 0.5;

    public Animaux(){
        super();
    }

    public Animaux(EtatEtreVivant healthLivingBeing, int healthTime, Location position, Field fields,  Virus virus, int nombreVois) {
        super(healthLivingBeing, healthTime, position, fields,  virus,nombreVois);
    }

    /***
     * Méthode permettant de faire une action par étape(step)
     */
    @Override
    public void action() {
        if(estVivant()) {
            interagit();
            verifieEtat();
        }
    }

    /***
     * On vérife l'état de l'animal et on modifie sa santé en fonction de son cas
     */
    protected void verifieEtat(){
        switch (this.etat){
            case MALADE:
                if(this.getTempsEtat() == 0)
                    devientContagieux();
                else
                    this.tempsEtat--;
                break;

            case CONTAGIEUX:
                if(this.getTempsEtat() == 0) {
                    Random random = new Random();
                    double test1=random.nextDouble();
                    if(test1 < this.getVirus().getProbaMourir())
                        this.meurt();
                }
                else
                    this.tempsEtat--;
                break;

            default:break;
        }
    }

}
