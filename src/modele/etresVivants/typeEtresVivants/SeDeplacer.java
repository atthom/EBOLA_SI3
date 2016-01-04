package modele.etresVivants.typeEtresVivants;

import modele.carte.Location;

/**
 * Created by Adrien Prestini on 02/01/2016.
 * Interface qui permet d'autoriser le déplacement sur la grille
 */
public interface SeDeplacer {

    void bouge();
    void setLocation(Location newLocation);
}
