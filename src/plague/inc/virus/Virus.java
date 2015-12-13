/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc.virus;

/**
 *
 * @author user
 */
public class Virus {
    float coeff_mortality, coeff_contagious;
    String name;
    
    public Virus(String name, float mortality, float contagious) {
        this.name = name;
        coeff_mortality = mortality;
        coeff_contagious = contagious;
    }

    public Virus(String name) {
        this.name = name;
        coeff_mortality = 0.3f;
        coeff_contagious = 0.7f;
    }
    
    public void contamiate() {
        
   }
    
    
    
    
    
}
