/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class VirusTest {
    Virus v;
    public VirusTest() {
    }
    
    @Before
    public void setUp() {
         v = new Virus("H1N1");
        
        v = new Virus("H1N4", 0.99f, 0.99f);
    }

    /**
     * Test of contamiate method, of class Virus.
     */
    @Test
    public void testContamiate() {
    }
    
}
