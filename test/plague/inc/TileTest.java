/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import plague.inc.Entity.AbstractEntity;
import plague.inc.Entity.Person;

/**
 *
 * @author user
 */
public class TileTest {
    Tile t;
    
    public TileTest() {
    }
    
    @Before
    public void setUp() {
        t = new Tile();
    }

    /**
     * Test of is_empty method, of class Tile.
     */
    @Test
    public void testIs_empty() {
        assertTrue(t.is_empty());
    }
   
    /**
     * Test of setEntity method, of class Tile.
     */
    @Test
    public void testSetEntity() {
        t.setEntity((AbstractEntity) new Person());
        assertEquals(new Person(), t.getEntity());
    }
     
    /**
     * Test of getEntity method, of class Tile.
     */
    @Test
    public void testGetEntity() {
         t.setEntity(new Person());
        assertEquals(new Person(), t.getEntity());        
    }

    /**
     * Test of equals method, of class Tile.
     */
    @Test
    public void testEquals() {
        t.setEntity(new Person());
        Tile tt = new Tile();
        tt.setEntity(new Person());
        assertEquals(t, tt);
    }
    
}
