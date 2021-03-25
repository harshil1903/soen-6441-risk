package com.risk.phases;

import com.risk.main.GameEngine;

import java.util.List;

import static com.risk.main.Main.d_Log;

/**
 * Phase Class is the parent class for the State Pattern implementation
 *
 * @author Harshil
 */
public abstract class Phase {

    GameEngine d_gameEngine;

    /**
     * Instantiates a new Phase.
     *
     * @param p_gameEngine the p game engine
     */
    Phase(GameEngine p_gameEngine){
        d_gameEngine = p_gameEngine;
    }

    /**
     * Edit map boolean.
     *
     * @param p_argumentTokens the p argument tokens
     * @return the boolean
     */
    abstract public boolean editMap(List<String> p_argumentTokens);

    /**
     * Validate map.
     *
     * @param p_argumentTokens the p argument tokens
     */
    abstract public void validateMap(List<String> p_argumentTokens);

    /**
     * Show map.
     *
     * @param p_argumentTokens the p argument tokens
     */
    abstract public void showMap(List<String> p_argumentTokens);

    /**
     * Save map.
     *
     * @param p_argumentTokens the p argument tokens
     */
    abstract public void saveMap(List<String> p_argumentTokens);

    /**
     * Edit country.
     *
     * @param p_argumentTokens the p argument tokens
     */
    abstract public void editCountry(List<String> p_argumentTokens);

    /**
     * Edit continent.
     *
     * @param p_argumentTokens the p argument tokens
     */
    abstract public void editContinent(List<String> p_argumentTokens);

    /**
     * Edit neighbor.
     *
     * @param p_argumentTokens the p argument tokens
     */
    abstract public void editNeighbor(List<String> p_argumentTokens);


    /**
     * Load map boolean.
     *
     * @param p_argumentList the p argument list
     * @return the boolean
     */
    abstract public boolean loadMap(List<String> p_argumentList);

    /**
     * Add player.
     *
     * @param p_argumentTokens the p argument tokens
     */
    abstract public void addPlayer(List<String> p_argumentTokens);

    /**
     * Assign countries boolean.
     *
     * @return the boolean
     */
    abstract public boolean assignCountries();

    /**
     * Reinforce.
     */
    // reinforcement commands
    abstract public void reinforce();

    /**
     * Issue order.
     */
    abstract public void issueOrder();

    /**
     * Execute order.
     */
    abstract public void executeOrder();

    /**
     * End game.
     */
    abstract public void endGame();

    /**
     * Next.
     */
    abstract public void next();

    /**
     * Signature of currentPhase for classes which extends it.
     * @return current value of the phase
     */
    abstract public String currentPhase();

    /**
     * Common method to all States.
     */
    public void printInvalidCommandMessage() {
        System.out.println("Invalid command in state " + this.getClass().getSimpleName() );
        d_Log.notify("Invalid command in state" + this.getClass().getSimpleName());
    }
}
