
import java.util.ArrayList;
import plague.inc.Entity.AbstractEntity;
import plague.inc.Entity.Person;
import plague.inc.map.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class Run {
     public static void main(String[] args) {
         Map map = new Map(5,10);
         ArrayList<AbstractEntity> b = new ArrayList<>();
         b.add(new Person());
         b.add(new Person());
         map.add(b);
         map.print();
         map.move();
         map.print();
         map.move();
         map.print();
         
     }
}
