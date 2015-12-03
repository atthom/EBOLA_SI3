/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc;

import Exceptions.PlagueRuntimeException;
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
    private int yaxis, xaxis;

    public Map(int longeur, int largeur) {
        map = new HashMap<>(largeur * longeur);
        this.yaxis = longeur;
        this.xaxis = largeur;
        coords = new ArrayList<>();
    }

    private void put(Coordinates c, AbstractEntity a) {
        a.setCoord(c);
        coords.add(c);
        map.put(c, a);
    }
    
    private void remove(Coordinates c) {
        for(Coordinates cc : coords) {
            if(cc.equals(c)) {
                map.remove(cc);
            }
        }
    }

    public void add(ArrayList<AbstractEntity> a) {
        if (a.size() >= yaxis * xaxis) {
            throw new PlagueException("Pas assez de  place pour les entitées !");
        }

        for (AbstractEntity a1 : a) {
            int randx = (int) (Math.random() * yaxis);
            int randy = (int) (Math.random() * xaxis);

            while (map.containsKey(new Coordinates(randx, randy))) {
                randx = (int) (Math.random() * yaxis);
                randy = (int) (Math.random() * xaxis);
            }
            Coordinates c = new Coordinates(randx, randy);
            put(c, a1);
        }
    }

    private boolean is_OutOfBound(Coordinates c) {
        if (c.getX() > xaxis || c.getX() < 0) {
            return true;
        }
        if (c.getY() > yaxis || c.getY() < 0) {
            return true;
        }
        return false;
    }

    public AbstractEntity getEntity(Coordinates c) {
        for (Coordinates coord : coords) {
            if (coord.equals(c)) {
                return map.get(coord);
            }
        }
        throw new PlagueRuntimeException("Il y a déja quelqu'un ici !");
    }

    public Coordinates move(Coordinates current, Coordinates togo) {
        if (is_OutOfBound(togo)) {
            return current;
        } else {
            for (Coordinates cc : coords) {
                if (cc.equals(togo)) {
                    return current;
                }
            }
            for(Coordinates cc : coords) {
                if(cc.equals(current)) {
                    AbstractEntity a = map.get(cc);
                    map.remove(cc);
                    put(togo, a);
                    return togo;
                }
            } 
            return current;
        }      
    }

    public void move() {
//        for(Coordinates c : coords) {
//            map.get(c).move(this);
//            // this is fucked up, i know... :(
//        }
        
        
//        ArrayList<Coordinates> coordsbis = new ArrayList<>(coords.size());
//        int i = 0;
//        while (coords.size() > 1) {
//            int randx = (int) (Math.random() * 2) - 1 + coords.get(i).getX();
//            int randy = (int) (Math.random() * 2) - 1 + coords.get(i).getY();
//
//            Coordinates c = new Coordinates(randx, randy);
//            while ((randx > 0 && randx < yaxis) && (randy > 0 && randy < xaxis) && coords.contains(c) && coordsbis.contains(c)) {
//                randx = (int) (Math.random() * 2) - 1 + coords.get(i).getX();
//                randy = (int) (Math.random() * 2) - 1 + coords.get(i).getY();
//                c = new Coordinates(randx, randy);
//            }
//
//            coordsbis.add(c);
//            map.put(c, map.get(coords.get(i)));
//            map.remove(coords.get(i));
//            coords.remove(i);
//            i++;
//        }
//
//        coords.addAll(coordsbis);

    }

    public void print() {
        String s = "";
        for (int i = 0; i <= yaxis; i++) {
            s = s + " _";
        }
        System.out.println(s);
        for (int i = 0; i < yaxis; i++) {
            s = "|";
            for (int j = 0; j < xaxis; j++) {
                if (coords.contains(new Coordinates(i, j))) {
                    s = s + "$";
                } else {
                    s = s + " ";
                }
            }
            System.out.println(s + " |");
        }
        s = "";
        for (int i = 0; i <= yaxis; i++) {
            s = s + " -";
        }
        System.out.println(s);
    }

}
