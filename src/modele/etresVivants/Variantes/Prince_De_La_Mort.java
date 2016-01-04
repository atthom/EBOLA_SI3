package modele.etresVivants.Variantes;

import modele.Virus.Virus;
import modele.carte.Field;
import modele.carte.Location;
import modele.etresVivants.EtatEtreVivant;
import modele.etresVivants.EtreVivant;
import modele.etresVivants.typeEtresVivants.SeDeplacer;

import java.util.ArrayList;

/**
 * Created by Adrien on 04/01/2016.
 * Le Prince de La Mort ne tue que les Etres Humains
 */
public class Prince_De_La_Mort extends EtreVivant implements SeDeplacer {
    public Prince_De_La_Mort(EtatEtreVivant healthLivingBeing, int healthTime, Location position, Field fields, Virus virus, int nombreVois) {
        super(healthLivingBeing, healthTime, position, fields, virus, nombreVois);
    }


    @Override
    public void action() {
        bouge();
        interagit();
    }

    @Override
    public void interagit() {
        ArrayList<EtreVivant> cibles = this.ciblesPotentiellesAdjacentes(this.getPosition(),this.nombreVoisins);
        for (EtreVivant vivants : cibles) {
                if (vivants.getClass().getSimpleName().equals("Humain"))
                    this.infecte(vivants);

        }
    }


    @Override
    public void infecte(EtreVivant vivants) {
        //Le prince de La Mort ne peut pas tomber Malade Il tue ses victimes
        vivants.meurt();
    }

    @Override
    public void bouge() {
        Location newLocation = getChamp().freeAdjacentLocation(getPosition());
        if (newLocation != null) {
            setLocation(newLocation);
        }

    }

    @Override
    public void setLocation(Location newLocation) {
        if (this.position != null) {
            this.champ.clear(getPosition());
        }
        this.position = newLocation;
        this.champ.place(this, newLocation);
    }
}
