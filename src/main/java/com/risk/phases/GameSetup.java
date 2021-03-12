package com.risk.phases;

import com.risk.controller.GameCommands;
import com.risk.gameutils.AssignCountries;
import com.risk.main.GameEngine;
import com.risk.models.Player;

import java.util.ArrayList;
import java.util.List;

import static com.risk.main.Main.d_PlayerList;

public class GameSetup extends Game {

    public GameSetup(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }

    public void loadMap(List<String> p_argumentList) {
        //Call Loadmap Operation from here.
        try {
            boolean d_gameLoaded = GameCommands.loadMapCommand(p_argumentList);
        }
        catch (Exception e){}
    }
    public void showGameMap() {
        //Call ShowGamemap Operation from here.
        GameCommands.showMapCommand(new ArrayList<>());
    }

    public void addPlayer(List<String> p_argumentTokens) {
        //Call addplayer Operation from here.
        String l_playerName;

        for (int i = 0; i < p_argumentTokens.size(); i++) {
            if (p_argumentTokens.get(i).equals("-add")) {
                try {
                    l_playerName = p_argumentTokens.get(++i);
                    System.out.println("Player Name: " + l_playerName);

                    Player l_player = new Player(l_playerName);
                    d_PlayerList.add(l_player);


                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. add option has 1 arguments");
                    return;
                }
            } else if (p_argumentTokens.get(i).equals("-remove")) {
                try {
                    l_playerName = p_argumentTokens.get(++i);
                    System.out.println("Player Name: " + l_playerName);

                    for (Player l_player : d_PlayerList) {
                        if (l_player.getD_PlayerName().equals(l_playerName)) {
                            d_PlayerList.remove(l_player);
                        }
                    }


                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. remove option has 1 argument");
                    e.printStackTrace();
                    return;
                }
            } else {
                System.out.println("Invalid option. gameplayer has -add and -remove options only");
            }

        }
    }

    public void assignCountries() {
        //Call assigncountries Operation from here.

        if (d_PlayerList.isEmpty()) {
            System.out.println("No players added, add players first");
            return;
        } else if (d_PlayerList.size() == 1) {
            System.out.println("Cant play the game with 1 player, add atleast 1 more player");
            return;
        }

        try {
            AssignCountries.assignCountries();
            System.out.println("Countries have been successfully assigned to all the players");
        } catch (Exception e) {
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

    public void endGame() {
        printInvalidCommandMessage();
    }

    public void next() {

    }
}
