package FluGraph.core;

import FluGraph.ui.SimulatorView;
import FluGraph.ui.GraphView;
import FluGraph.ui.GridView;
import FluGraph.Animal.Rabbit;
import FluGraph.Animal.Fox;
import java.util.Random;


import plague.inc.Entity.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;
import plague.inc.map.Coordinates;

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
    // The probability that a fox will be created in any given grid position.
    private static final double FOX_CREATION_PROBABILITY = 0.02;
    // The probability that a rabbit will be created in any given grid position.
    private static final double RABBIT_CREATION_PROBABILITY = 0.08;

    // List of entities in the field.
    private List<AbstractEntity> entities;
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
        this(DEFAULT_DEPTH, DEFAULT_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     * 
     * @param depth
     *            Depth of the field. Must be greater than zero.
     * @param width
     *            Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        if (width <= 0 || depth <= 0) {
            System.out.println("The dimensions must be greater than zero.");
            System.out.println("Using default values.");
            depth = DEFAULT_DEPTH;
            width = DEFAULT_WIDTH;
        }

        entities = new ArrayList<>();
        field = new Field(depth, width);

        views = new ArrayList<>();

        SimulatorView view = new GridView(depth, width);
        //bleu clair --> Homme
        view.setColor(Person.class, new Color(36, 191, 200));
        //Orange clair --> Poulet
        view.setColor(Chicken.class,new Color(255,128,0) );
        views.add(view);

        view = new GraphView(500, 150, 500);
        //rose clair --> Cochon
        view.setColor(Pig.class,Color.ORANGE);
        //jaune clair --> Canard
        view.setColor(Duck.class, Color.YELLOW);
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
        }
    }

    /**
     * Run the simulation from its current state for a single step. Iterate over
     * the whole field updating the state of each fox and rabbit.
     */
    public void simulateOneStep() {
        step++;

        // Provide space for newborn entities.
        List<AbstractEntity> newentities = new ArrayList<>();
        // Let all rabbits act.
        for (Iterator<AbstractEntity> it = entities.iterator(); it.hasNext();) {
            AbstractEntity entity = it.next();
            entity.act(newentities);
            if (!entity.isAlive()) {
                it.remove();
            }
        }

        // Add the newly born foxes and rabbits to the main lists.
        entities.addAll(newentities);

        updateViews();
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        step = 0;
        entities.clear();
        for (SimulatorView view : views) {
            view.reset();
        }

        populate();
        updateViews();
    }

    /**
     * Update all existing views.
     */
    private void updateViews() {
        for (SimulatorView view : views) {
            view.showStatus(step, field);
        }
    }

    /**
     * Randomly populate the field with foxes and rabbits.
     */
    private void populate() {
        Random rand = Randomizer.getRandom();
        field.clear();
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                if (rand.nextDouble() <= FOX_CREATION_PROBABILITY) {
                    Coordinates location = new Coordinates(row, col);
                    Fox fox = new Fox(true, field, location);
                    entities.add(fox);
                } else if (rand.nextDouble() <= RABBIT_CREATION_PROBABILITY) {
                    Coordinates location = new Coordinates(row, col);
                    Rabbit rabbit = new Rabbit(true, field, location);
                    entities.add(rabbit);
                }
                // else leave the location empty.
            }
        }
    }
}
