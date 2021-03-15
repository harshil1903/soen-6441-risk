package com.risk.phases;

import com.risk.main.GameEngine;
import com.risk.main.GameEngineNew;
import com.risk.main.MapEngine;

import java.util.List;

/**
 * The type Map edit.
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
        printInvalidCommandMessage();
    }


    public void next() {

    }
}
