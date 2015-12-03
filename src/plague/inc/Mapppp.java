/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc;

import java.util.ArrayList;
import plague.inc.Entity.AbstractEntity;

/**
 *
 * @author user
 */
public class Mapppp {
    ArrayList<ArrayList<Tile>> map;
    
    public Mapppp(int longeur, int largeur) {
       map = new ArrayList<>();
       for(int i =0; i <= longeur;i++) {
           map.add(new ArrayList<>(largeur));
           for(int j=0; j<= largeur;j++) {
               map.get(i).add(new Tile());
           }
       }        
    }
    
    public Tile getTile(int longeur, int largeur) {
        
        if(longeur<map.size() && largeur < map.get(0).size()) {
            return map.get(longeur).get(largeur);
        }
        throw new PlagueException("Problem : OutOfBounds (largeur ou longeur) !");
    }
                
    
    public ArrayList<AbstractEntity> getSurrounded(int longeur, int largeur) {
       ArrayList<AbstractEntity> g = new ArrayList<>();
        
       //if()
       
        return g;
    }
    
    
    
       
    public void print() {
        String s = "";
        for(int i = 0; i < map.size();i++) {
            s = s + " _";
        }
        System.out.println(s);
        for (ArrayList<Tile> map1 : map) {
            s = "|";
            for (Tile tile : map1) {
                if(tile.is_empty()) {
                    s = s + " ";
                } else {
                    s = s + "$";
                }    
            }
            System.out.println(s + "|");         
        }
        
        s = "";
        for(int i = 0; i < map.size();i++) {
            s = s + " -";
        }
        System.out.println(s);
    }
       
    
    
    
}
