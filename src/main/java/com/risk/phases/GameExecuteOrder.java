package com.risk.phases;

import com.risk.main.GameEngineNew;

import java.util.List;

public class GameExecuteOrder extends Game{
    GameExecuteOrder(GameEngineNew p_gameEngine) {
        super(p_gameEngine);
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
        printInvalidCommandMessage();
    }

    public void issueOrder(String p_action, String p_arguments) {
        printInvalidCommandMessage();
    }

    public void executeOrder() {

    }

    public void endGame() {

    }

    public void next() {
        d_gameEngineNew.setPhase(new GameIssueOrder(d_gameEngineNew));
    }
}
