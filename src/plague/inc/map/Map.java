/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc.map;

import Exceptions.PlagueRuntimeException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import plague.inc.Entity.AbstractEntity;

/**
 *
 * @author user
 */
public class Map {

    private final HashMap<Coordinates, AbstractEntity> map;
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

    private void maj(Coordinates current, Coordinates newone) {
        for (Iterator<Coordinates> iterator = coords.iterator(); iterator.hasNext();) {
            Coordinates coord = iterator.next();
            if (coord.equals(current)) {
                coords.add(newone);
                map.put(newone, map.get(coord));
                map.remove(coord);
                coords.remove(coord);
               
                break;
            }
        }
    }

    public void add(ArrayList<AbstractEntity> a) {
        if (a.size() >= yaxis * xaxis) {
            throw new PlagueRuntimeException("Pas assez de  place pour les entitées !");
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

    private boolean can_move(Coordinates togo) {
        return (!is_OutOfBound(togo)) ? coords.stream().noneMatch((c) -> (c.equals(togo))) : false;
    }

    public void move() {
        for (Coordinates c : coords) {
            AbstractEntity entity = map.get(c);
            Coordinates togo = entity.move();
            if (can_move(togo)) { 
                maj(c, togo);
                break;
            }
        }
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
