package com.risk.phases;

import com.risk.main.GameEngine;

import java.util.List;

import static com.risk.main.Main.d_Log;

/**
 * Abstract class for Map Edit phase
 *
 * @author Harshil
 */
public abstract class MapEdit extends Phase {

    /**
     * Instantiates a new Map edit.
     *
     * @param p_gameEngine the p game engine
     */
    MapEdit(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }

    /**
     * loadmap Command to load the map
     *
     * @param p_argumentTokens command parameters
     */
    public boolean loadMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
        return false;
    }

    /**
     * gameplayer Command to add/remove players
     *
     * @param p_argumentTokens command parameters
     */
    public void addPlayer(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    /**
     * assigncountries Command to assign countries amoong players
     */
    public boolean assignCountries() {
        printInvalidCommandMessage();
        return false;
    }

    /**
     * To reinforce players with their reinforcement armies
     */
    public void reinforce() {
        printInvalidCommandMessage();
    }

    /**
     * Issue orders as per player's choices
     */
    public void issueOrder() {
        printInvalidCommandMessage();
    }

    /**
     * Execute orders once all players have issued their orders
     */
    public void executeOrder() {
        printInvalidCommandMessage();
    }

    /**
     * end Command to leave phase
     */
    public void endGame() {
        System.out.println("Leaving Phase");
        d_Log.notify("Leaving Phase");
        return;
    }


    /**
     * To set next phase
     */
    public void next() {

    }
}
