package com.risk.controller;

import com.risk.exception.InvalidMapException;
import com.risk.maputils.*;
import com.risk.models.Player;

import java.util.ArrayList;
import java.util.List;

import static com.risk.main.Main.*;

/**
 * The Game Commands class performs various Game related commands.
 * It verifies whether the arguments provided for the commands are valid or not.
 *
 * @author Harshil
 */
public class GameCommands {
    /**
     * Validates the command arguments and then loads the map.
     *
     * @param p_argumentTokens list of arguments provided with the command
     * @return true if map is successfully loaded into the Game
     * @throws InvalidMapException Invalid Map Exception
     */
    public static boolean loadMapCommand(List<String> p_argumentTokens) throws InvalidMapException {
        if (p_argumentTokens.stream().count() != 1) {
            System.out.println("Wrong Number of Arguments provided. editmap command has only one argument.");
            d_Log.notify("Wrong Number of Arguments provided. editmap command has only one argument.");
            return false;
        }

        ArrayList<String> l_argumentTokens = new ArrayList<>();

        d_Map.getD_Continents().clear();

        try {
            d_Map = new EditMap().editMap(p_argumentTokens.get(0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new InvalidMapException(e.getMessage());
        }

        MapCommands.validateMapCommand(l_argumentTokens);
        if (MapValidator.d_isValid == false) {
            d_Map.getD_Continents().clear();
        }


        return true;
    }

    /**
     * Validates the command arguments and then displays the map.
     *
     * @param p_argumentTokens list of arguments provided with the command
     */
    public static void showMapCommand(List<String> p_argumentTokens) {
        if (p_argumentTokens.stream().count() != 0) {
            System.out.println("Wrong Number of Arguments provided. showmap command has no argument.");
            d_Log.notify("Wrong Number of Arguments provided. showmap command has no argument.");
        }

        ShowMap.displayGameMap(d_Map);

    }


    /**
     * Validates the command arguments and then edits the Player information accordingly.
     *
     * @param p_argumentTokens list of arguments provided with the command
     */
    public static void gamePlayerCommand(List<String> p_argumentTokens) {

        String l_playerName;
        int l_flag = 0;

        for (int i = 0; i < p_argumentTokens.size(); i++) {
            if (p_argumentTokens.get(i).equals("-add")) {
                try {
                    l_playerName = p_argumentTokens.get(++i);

                    for (Player l_player : d_PlayerList) {
                        if (l_player.getD_PlayerName().equals(l_playerName)) {
                            System.out.println("Player with player name " + l_playerName + " already exists. Try again with different name.");
                            d_Log.notify("Player with player name " + l_playerName + " already exists. Try again with different name.");
                            l_flag = 1;
                            break;
                        }
                    }

                    if (l_flag == 1) {
                        l_flag = 0;
                        continue;
                    }

                    System.out.println("Player Name: " + l_playerName + " has joined the Game");

                    d_Log.notify("Player Name: " + l_playerName + " has joined the Game");
                    Player l_player = new Player(l_playerName);
                    d_PlayerList.add(l_player);


                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. add option has 1 arguments");
                    d_Log.notify("Wrong number of Arguments provided. add option has 1 arguments");
                    return;
                }
            } else if (p_argumentTokens.get(i).equals("-remove")) {
                try {
                    l_playerName = p_argumentTokens.get(++i);
                    System.out.println("Player Name: " + l_playerName + " has been removed from the Game");
                    d_Log.notify("Player Name: " + l_playerName + " has been removed from the Game");
                    for (Player l_player : d_PlayerList) {
                        if (l_player.getD_PlayerName().equals(l_playerName)) {
                            d_PlayerList.remove(l_player);
                        }
                    }


                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. remove option has 1 argument");
                    d_Log.notify("Wrong number of Arguments provided. remove option has 1 argument");
                    e.printStackTrace();
                    return;
                }
            } else {
                System.out.println("Invalid option. gameplayer has -add and -remove options only");
                d_Log.notify("Invalid option. gameplayer has -add and -remove options only");
            }

        }

    }
}
