package com.risk.phases;

import com.risk.gameutils.Reinforce;
import com.risk.main.GameEngine;

import java.util.List;

public abstract class MainGame extends Game {

    MainGame(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }

    public void loadmap(List<String> p_argumentTokens){
        printInvalidCommandMessage();
    }

    public void addPlayer(){
        printInvalidCommandMessage();
    }

    public void assignCountries(){
        printInvalidCommandMessage();
    }

    public void reinforce() {
        Reinforce.assignReinforcementArmies();
    }

    public void issueOrder() {
    }

    public void executeOrder() {
    }

    public void endGame() {
        printInvalidCommandMessage();
    }

}