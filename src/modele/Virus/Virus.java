package modele.Virus;

/**
 * Created by Adrien on 18/12/2015.
 */
public enum Virus {

    H5N1Humain("H5N1",10,7,0.012),  H1N1Humain("H1N1",10,7,0.012),
    H5N1Canard("H5N1",10,5,0.018),
    H5N1Poulet("H5N1",10,5,0.02),
                                       H1N1Cochon("H1N1",10,5,0.018),
    Rien("Ok",0,0,0);




    private String nomVirus;
    private int tempsMalade;
    private int tempsContagion;
    private double ProbaMourir;



    // Constructeur
    Virus (String nom,  int sickTime, int mayContagiousTime,  double likelyDeath) {
        this.nomVirus = nom;
        this.tempsMalade = sickTime;
        this.tempsContagion = mayContagiousTime;
        this.ProbaMourir = likelyDeath;
    }

	/*---------------------------------------------GETTERS------------------------------------------*/

    public int getTempsMalade() {
        return tempsMalade;
    }

    public double getProbaMourir() {
        return ProbaMourir;
    }

    public int getTempsContagion() {
        return tempsContagion;
    }

    public String getNomVirus() { return nomVirus; }
}
