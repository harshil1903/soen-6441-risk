package com.risk.phases;

import com.risk.controller.GameCommands;
import com.risk.main.GameEngine;
import com.risk.main.GameEngineNew;

import java.util.ArrayList;
import java.util.List;

public abstract class Game extends Phase {

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
}
