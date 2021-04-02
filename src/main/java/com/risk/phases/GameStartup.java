package com.risk.phases;

import com.risk.controller.GameCommands;
import com.risk.gameutils.AssignCountries;
import com.risk.main.GameEngine;

import java.util.List;

import static com.risk.main.Main.d_Log;
import static com.risk.main.Main.d_PlayerList;

/**
 * Game startup allows map loading, assigning players and countries functionalities
 *
 * @author Harshil
 */
public class GameStartup extends Game {

    boolean d_mapLoaded = false;

    /**
     * Instantiates a new Game startup.
     *
     * @param p_gameEngine the p game engine
     */
    public GameStartup(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }


    public boolean loadMap(List<String> p_argumentList) {


        try {
            d_mapLoaded = GameCommands.loadMapCommand(p_argumentList);
        } catch (Exception e) {
        }

        return d_mapLoaded;

    }

    public void addPlayer(List<String> p_argumentTokens) {
        if (d_mapLoaded)
            GameCommands.gamePlayerCommand(p_argumentTokens);
        else {
            System.out.println("Map is not loaded");
            d_Log.notify("Map is not loaded");
        }
    }

    public boolean assignCountries() {
        if (!d_mapLoaded) {
            System.out.println("Map is not loaded");
            d_Log.notify("Map is not loaded");
            return false;
        } else {
            if (d_PlayerList.isEmpty()) {
                System.out.println("No players added, add players first");
                d_Log.notify("No players added, add players first");
                return false;
            } else if (d_PlayerList.size() == 1) {
                System.out.println("Cant play the game with 1 player, add atleast 1 more player");
                d_Log.notify("Cant play the game with 1 player, add atleast 1 more player");
                return false;
            }

            try {
                AssignCountries.assignCountries();
                System.out.println("Countries have been successfully assigned to all the players");
                d_Log.notify("Countries have been successfully assigned to all the players");
            } catch (Exception e) {
            }

            return true;
        }
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

    public String currentPhase() {
        return "GameStartup";
    }

    public void next() {
        d_gameEngine.setPhase(new GameIssueOrder(d_gameEngine));
    }
}
