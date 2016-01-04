package controleur;

import java.util.Random;

import modele.etresVivants.EtatEtreVivant;
import modele.etresVivants.EtreVivant;
import modele.etresVivants.typeEtresVivants.Animaux;
import modele.etresVivants.typeEtresVivants.Poulet;
import modele.etresVivants.typeEtresVivants.Canard;
import modele.etresVivants.typeEtresVivants.Humain;
import modele.etresVivants.typeEtresVivants.Cochon;
import modele.carte.Field;
import modele.carte.Location;
import modele.carte.Randomizer;
import modele.Virus.Virus;
import vue.GraphView;
import vue.GridView;
import vue.SimulatorView;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

/**
 * A simple predator-prey simulator, based on a rectangular field containing
 * rabbits and foxes.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class Simulator {
    // Constants representing configuration information for the simulation.
    // The default width for the grid.
    private static final int DEFAULT_WIDTH = 120;
    // The default depth of the grid.
    private static final int DEFAULT_DEPTH = 80;

    private static final int DEFAULT_NEIGHBOURHOOD = 4;

    // The probability that a fox will be created in any given grid position.
    private static final double PIG_CREATION_PROBABILITY = 0.06;
    // The probability that a rabbit will be created in any given grid position.
    private static final double CHICKEN_CREATION_PROBABILITY = 0.06;
    // The probability that a duck will be created in any given grid position.
    private static final double DUCK_CREATION_PROBABILITY = 0.06;
    // The probability that a human will be created in any given grid position.
    private static final double HUMAN_CREATION_PROBABILITY = 0.06;

    // List of animals and humans in the field.
    private List<EtreVivant> livingBeings;
    // The current state of the field.
    private Field field;
    // The current step of the simulation.
    private int step;
    // A graphical view of the simulation.
    private List<SimulatorView> views;

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(DEFAULT_DEPTH, DEFAULT_WIDTH,DEFAULT_NEIGHBOURHOOD);
    }

    public Simulator(int neighbourhood){

        this(DEFAULT_DEPTH,DEFAULT_WIDTH,neighbourhood);

    }

    /**
     * Create a simulation field with the given size.
     *
     * @param depth
     *            Depth of the field. Must be greater than zero.
     * @param width
     *            Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width, int neighbourhood) {
        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        livingBeings = new ArrayList<>();

        field = new Field(depth, width, neighbourhood);

        views = new ArrayList<>();

        SimulatorView view = new GridView(depth, width);
        view.setColor(Humain.class, Color.GREEN);
        view.setColor(Cochon.class, Color.PINK);
        view.setColor(Canard.class, Color.YELLOW);
        view.setColor(Poulet.class, Color.RED);
        views.add(view);

        view = new GraphView(500, 150, 500);
        view.setColor(Humain.class, Color.GREEN);
        view.setColor(Cochon.class, Color.PINK);
        view.setColor(Canard.class, Color.YELLOW);
        view.setColor(Poulet.class, Color.RED);
        views.add(view);

        // Setup a valid starting point.
        reset();
    }

    /**
     * Run the simulation from its current state for a reasonably long period,
     * (4000 steps).
     */
    public void runLongSimulation() {
        simulate(4000);
    }

    /**
     * Run the simulation from its current state for the given number of steps.
     * Stop before the given number of steps if it ceases to be viable.
     *
     * @param numSteps
     *            The number of steps to run for.
     */
    public void simulate(int numSteps) {
        for (int step = 1; step <= numSteps && views.get(0).isViable(field); step++) {
            simulateOneStep();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void simulate() {

        while(!checkAllState()){
            simulateOneStep();
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean checkAllState(){
        return livingBeings.stream().noneMatch((vivants) -> (vivants.estVivant() && !vivants.getEtat().equals(EtatEtreVivant.SAINT)));
    }

    /**
     * Run the simulation from its current state for a single step. Iterate over
     * the whole field updating the state of each fox and rabbit.
     */
    public void simulateOneStep() {
        step++;

        for (Iterator<EtreVivant> it = livingBeings.iterator(); it.hasNext();) {
            EtreVivant vivant = it.next();
            vivant.action();
            if (!vivant.estVivant()) {
                it.remove();
            }
        }

        updateViews();
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        step = 0;
        livingBeings.clear();
        views.stream().forEach((view) -> {
            view.reset();
        });

        populate();
        updateViews();
    }

    /**
     * Update all existing views.
     */
    private void updateViews() {
        views.stream().forEach((view) -> {
            view.showStatus(step, field);
        });
    }

    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate() {
        Random randome2;
        Random r = new Random();
        randome2 = r;
        field.clear();
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                if (randome2.nextDouble() <= HUMAN_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Humain human;
                    if(randome2.nextInt()%3==0) {
                        human = new Humain(EtatEtreVivant.MALADE, Virus.H1N1Humain.getTempsMalade(), location, field, Virus.H1N1Humain);
                    }else if(randome2.nextInt()%2==0) {
                        human = new Humain(EtatEtreVivant.MALADE, Virus.H5N1Humain.getTempsMalade(), location, field, Virus.H5N1Humain);
                    }else{
                         human = new Humain(EtatEtreVivant.SAINT, 0, location, field, Virus.Rien);
                    }
                    livingBeings.add(human);
                }
                else if (randome2.nextDouble() <= DUCK_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Canard duck;
                    if(randome2.nextInt()%2==0) {
                        duck = new Canard(EtatEtreVivant.MALADE,Virus.H5N1Canard.getTempsMalade(), location, field, Virus.H5N1Canard);
                    }else{
                        duck = new Canard(EtatEtreVivant.SAINT, 0, location, field, Virus.Rien);
                    }
                    livingBeings.add(duck);
                }
                else if (randome2.nextDouble() <= CHICKEN_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Poulet chicken;
                    if(randome2.nextInt()%2==0) {
                        chicken = new Poulet(EtatEtreVivant.MALADE, Virus.H5N1Poulet.getTempsMalade(), location, field, Virus.H5N1Poulet);
                    }else{
                        chicken = new Poulet(EtatEtreVivant.SAINT, 0, location, field, Virus.Rien);
                    }
                    livingBeings.add(chicken);
                }
                else if (randome2.nextDouble() <= PIG_CREATION_PROBABILITY) {
                    Location location = new Location(row, col);
                    Cochon pig;
                    if(randome2.nextInt()%2==0) {
                        pig = new Cochon(EtatEtreVivant.MALADE, Virus.H1N1Cochon.getTempsMalade(), location, field, Virus.H1N1Cochon);
                    }else{
                        pig = new Cochon(EtatEtreVivant.SAINT, 0, location, field, Virus.Rien);
                    }
                    livingBeings.add(pig);
                }
                // else leave the location empty.

            }
        }
    }
}