package com.risk.phases;

import com.risk.main.GameEngine;
import com.risk.main.GameEngineNew;
import com.risk.main.MapEngine;

import java.util.List;

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
    MapEdit(GameEngineNew p_gameEngine) {
        super(p_gameEngine);
    }

    public boolean loadMap(List<String> p_argumentList) {
        printInvalidCommandMessage();
        return false;
    }

    public void addPlayer(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public boolean assignCountries() {
        printInvalidCommandMessage();
        return false;
    }

    public void reinforce() {
        printInvalidCommandMessage();
    }

    public void issueOrder() {
        printInvalidCommandMessage();
    }

    public void executeOrder() {
        printInvalidCommandMessage();
    }

    public void endGame() {
        System.out.println("Leaving Phase");
        return;
    }


    public void next() {

    }
}
