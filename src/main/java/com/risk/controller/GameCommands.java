package com.risk.controller;

import com.risk.exception.InvalidMapException;
import com.risk.maputils.*;
import com.risk.models.Player;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.risk.main.Main.*;

/**
 * The Game Commands class performs various Game related commands.
 * It verifies whether the arguments provided for the commands are valid or not.
 *
 * @author Harshil
 */
public class GameCommands {
    /**
     * enum of player strategy to be used in the game.
     */
    public enum d_playerStrategyType {
        /**
         * enum for human strategy
         */
        human,
        /**
         * enum for aggressive strategy
         */
        aggressive,
        /**
         * enum for benevolent strategy
         */
        benevolent,
        /**
         * enum for cheater strategy
         */
        cheater,
        /**
         * enum for random strategy
         */
        random}


    /**
     * Check map type
     *
     * @param p_fileName  file name
     * @return whether it is Conquest or Domination File
     */
    public static int checkMapType(String p_fileName){
        String l_path = "src/main/resources/";
        String l_fileName = p_fileName + ".map";
        File l_map = new File(l_path + l_fileName);

        if (!l_map.exists()){
            return 1;
        }
        else {
            Scanner l_mapReader = null;
            try {
                l_mapReader = new Scanner(l_map);
                while (l_mapReader.hasNextLine()) {
                    String l_line = l_mapReader.nextLine();
                    if (l_line.equals("[Continents]")) {
                        l_line = l_mapReader.nextLine();
                        if(l_line.contains("=")){
                            return 2;
                        }
                        else {
                            return 1;
                        }
                    }
                }
            }
            catch (FileNotFoundException e){}
        }
        return 1;
    }


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

        d_Map.clearMapData();

        //Test for the type of Map
        if(checkMapType(p_argumentTokens.get(0)) == 1)
        {
            try {
                d_Map = new EditMap().editMap(p_argumentTokens.get(0));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                d_Log.notify(e.getMessage());
                throw new InvalidMapException(e.getMessage());

            }
        }
        else{
            try {
                d_Map = new EditMapAdapter(new EditConquestMap()).editMap(p_argumentTokens.get(0));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                d_Log.notify(e.getMessage());
                throw new InvalidMapException(e.getMessage());

            }
        }
        if (d_Map.d_isEmpty) {
            return true;
        }
        try {
            MapValidator.validateMap(d_Map);
        } catch (Exception e) {
            System.out.println("Map Validation Failed \nMap data has been cleared, use editmap to load a map again");
            d_Log.notify("Map Validation Failed \nMap data has been cleared, use editmap to load a map again");
            System.out.println(e.getMessage());
            d_Log.notify(e.getMessage());
            d_Map.clearMapData();
            return false;
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

        String l_playerName, l_playerStrategy;
        int l_flag, l_flag1;

        for (int i = 0; i < p_argumentTokens.size(); i++) {
            l_flag = 0;
            l_flag1 = 0;

            if (p_argumentTokens.get(i).equals("-add")) {
                try {
                    l_playerName = p_argumentTokens.get(++i);
                    l_playerStrategy = p_argumentTokens.get(++i);

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

                    for(d_playerStrategyType j : d_playerStrategyType.values())
                    {
                        if(j.name().equals(l_playerStrategy)){
                            l_flag1 = 1;
                            break;
                        }
                    }

                    if(l_flag1 == 0){
                        System.out.println("Invalid Strategy Type, Player " + l_playerName + " not added. Allowed Types of Strategy are :" +
                                "human, aggressive, benevolent, cheater, random");
                        d_Log.notify("Invalid Strategy Type, Player " + l_playerName + " not added. Allowed Types of Strategy are :" +
                                "human, aggressive, benevolent, cheater, random");
                        break;
                    }
                    System.out.println("Player Name: " + l_playerName + " has joined the Game");

                    d_Log.notify("Player Name: " + l_playerName + " has joined the Game");
                    Player l_player = new Player(l_playerName,l_playerStrategy);
                    d_PlayerList.add(l_player);


                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. add option has 2 arguments");
                    d_Log.notify("Wrong number of Arguments provided. add option has 2 arguments");
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
                            break;
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
