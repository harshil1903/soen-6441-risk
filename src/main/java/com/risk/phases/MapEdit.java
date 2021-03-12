package com.risk.phases;

import com.risk.main.GameEngine;
import com.risk.main.MapEngine;

import java.util.List;

public abstract class MapEdit extends Phase {

    MapEdit(MapEngine p_mapEngine){
        super(p_mapEngine);
    }

    MapEdit(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }

    public void loadMap(List<String> p_argumentList) {
        printInvalidCommandMessage();
    }

    public void showGameMap() {
        printInvalidCommandMessage();
    }

    public void addPlayer(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public void assignCountries() {
        printInvalidCommandMessage();
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
