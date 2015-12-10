package plague.inc;

import java.math.BigInteger;

/**
 * 
 * @author Adrien Prestini
 * Enum qui présente tous les virus possibles présents sur la carte 
 * Un coefficient de mortalité et un coefficient de contagion
 *
 */
public enum Viruses {

	H5N1(2,1),
	H1N1(2,1),
	NOPROBLEM(1000000,1000000);

	private float coeff_mortality;
	private float coeff_contagious;
	
	private int humanIncubationTime;
	private int animalIncubationTime;
	
	Viruses(int tH, int tA){
		this.humanIncubationTime = tH;
		this.animalIncubationTime = tA;
		
	}
	public int getHumanIncubationTime() {
		return humanIncubationTime;
	}

	public int getAnimalIncubationTime() {
		return animalIncubationTime;
	}
}
