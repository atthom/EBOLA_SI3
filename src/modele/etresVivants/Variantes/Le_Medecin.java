package modele.etresVivants.Variantes;

import modele.Virus.Virus;
import modele.carte.Field;
import modele.carte.Location;
import modele.etresVivants.EtatEtreVivant;
import modele.etresVivants.EtreVivant;
import modele.etresVivants.typeEtresVivants.SeDeplacer;

import java.util.ArrayList;
import modele.etresVivants.typeEtresVivants.Humain;

/**
 * Created by Adrien on 04/01/2016.
 * C'est une classe qui décrit un medecin sauvant les animaux et les humains de la maladie
 * il intervient uniquement lorsque les cibles sont malades et ignorent les contagieux
 */
public class Le_Medecin extends Humain implements SeDeplacer{
  private static final long serialVersionUID = 1L;

    public Le_Medecin(EtatEtreVivant healthLivingBeing, int healthTime, Location position, Field fields, Virus virus, int nombreVois) {
        super(healthLivingBeing, healthTime, position, fields, virus, nombreVois);
    }
    
    /**
     * Le medecin ne traite que les malades
     */
    @Override
    public void interagit() {
      super.interagit();
        ArrayList<EtreVivant> cibles = this.ciblesPotentiellesAdjacentes(this.getPosition(),this.nombreVoisins);
        cibles.stream().filter((vivants) -> (vivants.getEtat().equals(EtatEtreVivant.MALADE))).forEach((vivants) -> {
          this.soigne(vivants);
    });
    }

    /***
     * Le medecin soigne les etres vivants
     *
     * @param vivants
     */
    private void soigne(EtreVivant vivants) {
        setVirus(Virus.Rien);
        this.changeEtat(EtatEtreVivant.SAIN);
    }

}
