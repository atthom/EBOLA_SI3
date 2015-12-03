/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc;

import java.util.ArrayList;
import java.util.HashMap;
import plague.inc.Entity.AbstractEntity;

/**
 *
 * @author user
 */
public class Map {

    private HashMap<Coordinates, AbstractEntity> map;
    private ArrayList<Coordinates> coords;
    private int longeur, largeur;

    public Map(int longeur, int largeur) {
        map = new HashMap<>(largeur * longeur);
        this.longeur = longeur;
        this.largeur = largeur;
        coords = new ArrayList<>();
    }

    public void add(ArrayList<AbstractEntity> a) {
        if (a.size() >= longeur * largeur) {
            throw new PlagueException("Pas assez de  place pour les entitées !");
        }

        for (AbstractEntity a1 : a) {
            int randx = (int) (Math.random() * longeur);
            int randy = (int) (Math.random() * largeur);

            while (map.containsKey(new Coordinates(randx, randy))) {
                randx = (int) (Math.random() * longeur);
                randy = (int) (Math.random() * largeur);
            }
            Coordinates c = new Coordinates(randx, randy);
            coords.add(c);
            map.put(c, a1);
        }
    }

    public void move() {
        ArrayList<Coordinates> coordsbis = new ArrayList<>(coords.size());
        int i = 0;
        while(coords.size()>1) {
            int randx = (int) (Math.random() * 2) - 1 + coords.get(i).getX();
            int randy = (int) (Math.random() * 2) - 1 + coords.get(i).getY();

            Coordinates c = new Coordinates(randx, randy);
            while ((randx > 0 && randx < longeur) && (randy > 0 && randy < largeur) && coords.contains(c) && coordsbis.contains(c)) {
                randx = (int) (Math.random() * 2) - 1 + coords.get(i).getX();
                randy = (int) (Math.random() * 2) - 1 + coords.get(i).getY();
                c = new Coordinates(randx, randy);
            }

            coordsbis.add(c);
            map.put(c, map.get(coords.get(i)));
            map.remove(coords.get(i));
            coords.remove(i);
           i++;
        }
       
        coords.addAll(coordsbis);

    }

    public void print() {
        String s = "";
        for (int i = 0; i <= longeur; i++) {
            s = s + " _";
        }
        System.out.println(s);
        for (int i = 0; i < longeur; i++) {
            s = "|";
            for (int j = 0; j < largeur; j++) {
                if (coords.contains(new Coordinates(i, j))) {
                    s = s + "$";
                } else {
                    s = s + " ";
                }
            }
            System.out.println(s + " |");
        }
        s = "";
        for (int i = 0; i <= longeur; i++) {
            s = s + " -";
        }
        System.out.println(s );
    }

}
