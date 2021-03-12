package com.risk.phases;

import com.risk.controller.GameCommands;
import com.risk.gameutils.AssignCountries;
import com.risk.main.GameEngine;
import com.risk.main.GameEngineNew;
import com.risk.models.Player;

import java.util.ArrayList;
import java.util.List;

import static com.risk.main.Main.d_PlayerList;

public class GameSetup extends Game {

    public GameSetup(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }

    public GameSetup(GameEngineNew p_gameEngine) {
        super(p_gameEngine);
    }


    public void loadMap(List<String> p_argumentList) {

        //Call Loadmap Operation from here.
        try {
            boolean d_gameLoaded = GameCommands.loadMapCommand(p_argumentList);
        }
        catch (Exception e){}

    }

    public void addPlayer(List<String> p_argumentTokens) {

        //Call addplayer Operation from here.
        GameCommands.gamePlayerCommand(p_argumentTokens);

    }

    public boolean assignCountries() {
        //Call assigncountries Operation from here.

        if (d_PlayerList.isEmpty()) {
            System.out.println("No players added, add players first");
            return false;
        } else if (d_PlayerList.size() == 1) {
            System.out.println("Cant play the game with 1 player, add atleast 1 more player");
            return false;
        }

        try {
            AssignCountries.assignCountries();
            System.out.println("Countries have been successfully assigned to all the players");
        }
        catch (Exception e) {
        }

        return true;
    }

    public void reinforce() {
        printInvalidCommandMessage();
    }

    public void issueOrder(String p_action, String p_arguments) {
        printInvalidCommandMessage();
    }

    public void executeOrder() {
        printInvalidCommandMessage();
    }

    public void endGame() {
        printInvalidCommandMessage();
    }

    public void next() {
        d_gameEngineNew.setPhase(new MainGame(d_gameEngineNew));
    }
}
