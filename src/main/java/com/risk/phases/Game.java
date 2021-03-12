package com.risk.phases;

import com.risk.main.GameEngine;

public abstract class Game extends Phase {
    Game(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }

    //PreMapLoad
    public void editMap() {
        printInvalidCommandMessage();
    }

    //PostMopLoad commands
    public void validateMap() {
        printInvalidCommandMessage();
    }

    public void showMap() {
        printInvalidCommandMessage();
    }

    public void saveMap() {
        printInvalidCommandMessage();
    }

    public void editCountry() {
        printInvalidCommandMessage();
    }

    public void editContinent() {
        printInvalidCommandMessage();
    }

    public void editNeighbor() {
        printInvalidCommandMessage();

    }

}
