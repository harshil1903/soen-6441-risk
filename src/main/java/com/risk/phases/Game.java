package com.risk.phases;

import com.risk.controller.GameCommands;
import com.risk.main.GameEngine;
import com.risk.main.GameEngineNew;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Class for Game Phase
 *
 * @author Harshil
 */
public abstract class Game extends Phase {

    /**
     * Instantiates a new Game.
     *
     * @param p_gameEngine the p game engine
     */
    Game(GameEngineNew p_gameEngine) {
        super(p_gameEngine);
    }
    //PreMapLoad
    public boolean editMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
        return false;
    }

    //PostMopLoad commands
    public void validateMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public void showMap(List<String> p_argumentTokens) {
        GameCommands.showMapCommand(new ArrayList<>());
    }

    public void saveMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public void editCountry(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public void editContinent(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public void editNeighbor(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    @Override
    public void endGame() {
        System.out.println("Ending Game");
        return;
    }
}
