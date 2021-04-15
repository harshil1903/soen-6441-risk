package com.risk.phases;

import com.risk.controller.GameCommands;
import com.risk.gameutils.AssignCountries;
import com.risk.gameutils.LoadGame;
import com.risk.gameutils.SaveGame;
import com.risk.main.GameEngine;
import com.risk.models.Player;

import java.util.ArrayList;
import java.util.List;

import static com.risk.main.Main.*;

/**
 * Game startup allows map loading, assigning players and countries functionalities
 *
 * @author Harshil
 */
public class GameStartup extends Game {

    static boolean d_mapLoaded = false;

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
        System.out.println(d_mapLoaded);
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

    public void loadGame(List<String> p_argumentTokens) {

        LoadGame.loadGame(p_argumentTokens.get(0));
        d_mapLoaded = true;

        //System.out.println(d_mapLoaded);
        d_Map.clearMapData();
        d_PlayerList.clear();

        d_Map = LoadGame.getD_Map();

        ArrayList<Player> l_players = LoadGame.getD_PlayerList();

        for (Player l_player : l_players) {
            System.out.println("Player name : " + l_player.getD_PlayerName());
            d_PlayerList.add(l_player);
        }

        String l_gamePhase = LoadGame.getD_GamePhase();

        switch (l_gamePhase) {
            case "GameStartup":
                d_gameEngine.setPhase(new GameStartup(d_gameEngine));
                break;

            case "GameIssueOrder":
                d_gameEngine.setPhase(new GameIssueOrder(d_gameEngine));
                d_gameEngine.getPhase().issueOrder();
                break;
        }
    }

    public void saveGame(List<String> p_argumentTokens) {
        SaveGame l_game = new SaveGame();
        l_game.saveGame(d_Map, d_gameEngine.getPhase(), d_PlayerList, p_argumentTokens.get(0));
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
