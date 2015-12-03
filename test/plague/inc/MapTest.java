/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import plague.inc.Entity.AbstractEntity;
import plague.inc.Entity.Person;

/**
 *
 * @author user
 */
public class MapTest {
    Map map;
    public MapTest() {
    }
    
    @Before
    public void setUp() {
        
        map = new Map(5, 10);
    }

    /**
     * Test of add method, of class Map.
     */
    @Test
    public void testAdd() {
        ArrayList<AbstractEntity> abs= new ArrayList<>();
        abs.add(new Person());
        
    }

    /**
     * Test of move method, of class Map.
     */
    @Test
    public void testMove() {
        ArrayList<AbstractEntity> abs= new ArrayList<>();
        abs.add(new Person());
        abs.add(new Person());
        abs.add(new Person());
        abs.add(new Person());
        map.add(abs);
        map.print();
        
        map.move();
        map.print();map.move();
        map.print();map.move();
        map.print();map.move();
        map.print();map.move();
        map.print();
    
    }

    /**
     * Test of print method, of class Map.
     */
    @Ignore
    public void testPrint() {
        map.print();
        ArrayList<AbstractEntity> abs= new ArrayList<>();
        abs.add(new Person());
        map.add(abs);
        map.print();
    }

}
