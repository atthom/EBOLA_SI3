package plague.inc;


/**
 * 
 * @author Adrien Prestini
 * Enum qui pr�sente tous les virus possibles pr�sents sur la carte 
 * Un coefficient de mortalit� et un coefficient de contagion
 *
 */
public enum Viruses {

	H5N1(2,1),
	H1N1(2,1);

	private float coeff_mortality;
	private float coeff_contagious;
	
	private int humanIncubationTime;
	private int animalIncubationTime;
	
	Viruses(int tH, int tA){
		this.humanIncubationTime = tH;
		this.animalIncubationTime = tA;
		
	}
	protected int getHumanIncubationTime() {
		return humanIncubationTime;
	}

	protected int getAnimalIncubationTime() {
		return animalIncubationTime;
	}
}
