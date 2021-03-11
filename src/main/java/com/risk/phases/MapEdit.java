package com.risk.phases;

import com.risk.main.MapEngine;

public abstract class MapEdit extends Phase {

    MapEdit(MapEngine p_mapEngine){
        super(p_mapEngine);
    }


    public void loadMap() {
        printInvalidCommandMessage();
    }

    public void showGameMap() {
        printInvalidCommandMessage();
    }

    public void addPlayer() {
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
