package com.risk.phases;

import com.risk.gameutils.Reinforce;
import com.risk.main.GameEngineNew;

import java.util.List;

public class GameIssueOrder extends Game{
    GameIssueOrder(GameEngineNew p_gameEngine) {
        super(p_gameEngine);
        reinforce();
    }

    public boolean loadMap(List<String> p_argumentList) {
        printInvalidCommandMessage();
        return false;
    }

    public void addPlayer(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public boolean assignCountries() {
        printInvalidCommandMessage();
        return false;
    }

    public void reinforce() {
        Reinforce.assignReinforcementArmies();
    }

    public void issueOrder(String p_action, String p_arguments) {

    }

    public void executeOrder() {
        printInvalidCommandMessage();
    }

    public void endGame() {

    }

    public void next() {
        d_gameEngineNew.setPhase(new GameExecuteOrder(d_gameEngineNew));
    }
}
