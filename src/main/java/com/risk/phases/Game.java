package com.risk.phases;

import com.risk.controller.GameCommands;
import com.risk.main.GameEngine;

import java.util.ArrayList;
import java.util.List;

public abstract class Game extends Phase {
    Game(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }

    //PreMapLoad
    public void editMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    //PostMopLoad commands
    public void validateMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public void showMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
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

    public void showGameMap() {
        //Call ShowGamemap Operation from here.
        GameCommands.showMapCommand(new ArrayList<>());

    }
}
