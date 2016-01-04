package modele.Virus;

import java.util.ArrayList;

/**
 * Created by Adrien Prestini on 30/12/2015.
 * Enum d'écraivant les compatibilités d'infection entre les especes
 */
public enum VirusPotentiels {
    H5N1(new ArrayList<String>(){{add("Humain");}{add("Canard");}{add("Poulet");}}),
    H1N1(new ArrayList<String>(){{add("Cochon");}{add("Humain");}});


    private ArrayList<String> virusPotentiels;

    VirusPotentiels(ArrayList<String> strings) {
        virusPotentiels = strings;
    }

    public ArrayList<String> getVirusPotentiels() {
        return virusPotentiels;
    }



}
