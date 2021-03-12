package com.risk.phases;

import com.risk.gameutils.Reinforce;
import com.risk.main.GameEngine;
import com.risk.main.GameEngineNew;

import java.util.List;

public class MainGame extends Game {

    MainGame(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }

    MainGame(GameEngineNew p_gameEngine) {
        super(p_gameEngine);
        reinforce();
    }

    public void loadMap(List<String> p_argumentTokens){
        printInvalidCommandMessage();
    }

    public void addPlayer(List<String> p_argumentTokens){
        printInvalidCommandMessage();
    }

    public boolean assignCountries(){
        printInvalidCommandMessage();
        return false;
    }

    public void reinforce() {
        Reinforce.assignReinforcementArmies();
    }

    public void issueOrder(String p_action, String p_arguments) {
        //Call issue order
    }

    public void executeOrder() {
    }

    public void endGame() {
        printInvalidCommandMessage();
    }


    public void next() {

    }
}