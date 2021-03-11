package com.risk.phases;

import com.risk.main.GameEngine;

public class GameSetup extends GamePlay{

    public GameSetup(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }

    public void loadMap() {
        //Call Loadmap Operation from here.
    }

    public void showGameMap() {
        //Call ShowGamemap Operation from here.
    }

    public void addPlayer() {
        //Call addplayer Operation from here.
    }

    public void assignCountries() {
        //Call assigncountries Operation from here.
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
