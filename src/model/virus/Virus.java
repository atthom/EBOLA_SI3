package model.virus;

public enum Virus {
	 	OK(0,0,0,0,0),
	    H1N1(0.6,0.02,2,7,8),
	    H5N1(0.6,0.02,2,7,8);

	    private double infectionRate;
	    private double deathRate;
	    private int incubationTime;
	    private int contagionTime;
	    private int recoveringTime;

	    Virus(double infectionRate, double deathRate, int incubationTime, int contagionTime,int recoveringTime) {
	        this.infectionRate = infectionRate;
	        this.deathRate = deathRate;
	        this.incubationTime = incubationTime;
	        this.contagionTime = contagionTime;
	        this.recoveringTime=recoveringTime;
	    }

	    public double getInfectionRate() {
	        return infectionRate;
	    }

	    public double getDeathRate() {
	        return deathRate;
	    }

	    public int getIncubationTime() {
	        return incubationTime;
	    }

	    public int getContagionTime() {
	        return contagionTime;
	    }

	    public int getRecoveringTime() {
	        return recoveringTime;
	    }
}
