/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plague.inc.Entity;

import plague.inc.Viruses;

/**
 *
 * @author user
 */
public class Pig extends Animals {

    public Pig() {
    }

    @Override
    public void Contact(AbstractEntity abs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	protected void becomeSick(Viruses v) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void becomeContagious() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void die() {
		// TODO Auto-generated method stub
		
	}
    
}
