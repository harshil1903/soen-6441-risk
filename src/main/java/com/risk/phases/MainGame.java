package com.risk.phases;

import com.risk.main.GameEngine;

public abstract class MainGame extends GamePlay {

    MainGame(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }

    public void loadmap(){
        printInvalidCommandMessage();
    }
    public void showGameMap(){
        //Call showGameMap Operation here
    }

    public void addPlayer(){
        printInvalidCommandMessage();
    }

    public void assignCountries(){
        printInvalidCommandMessage();
    }

}