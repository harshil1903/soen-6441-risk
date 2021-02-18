package com.risk.controller;

import com.risk.exception.InvalidMapException;
import com.risk.maputils.*;
import com.risk.models.Continent;
import com.risk.models.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;

/**
 * The Game Commands class performs various Game related commands.
 * It verifies whether the arguments provided for the commands are valid or not.
 * @author Harshil
 */
public class GameCommands
{
    /**
     * Validates the command arguments and then loads the map.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     * @return true if map is successfully loaded into the Game
     */
    public static boolean loadMapCommand(List<String> p_ArgumentTokens) throws InvalidMapException
    {
        if(p_ArgumentTokens.stream().count() != 1)
        {
            System.out.println("Wrong Number of Arguments provided. editmap command has only one argument.");
            return false;
        }

        ArrayList<String> l_ArgumentTokens = new ArrayList<>();

        //Remember to change return type of map validator to stop map from being loaded
        //new EditMap.EditMap(d_Map);       Change Method Name, its same as class name

        try {
            d_Map = new EditMap().EditMap();
        }
        catch (Exception e)
        {
            throw new InvalidMapException(e.getMessage());
        }

        MapCommands.validateMapCommand(l_ArgumentTokens);

        //System.out.println("Reached Edit Map Command. Argument List : " + p_ArgumentTokens);
        return true;
    }

    /**
     * Validates the command arguments and then displays the map.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void showMapCommand(List<String> p_ArgumentTokens)
    {
        if(p_ArgumentTokens.stream().count() != 0)
        {
            System.out.println("Wrong Number of Arguments provided. showmap command has no argument.");
        }


        //Call MAP Display method.
        ShowMap.displayGameMap(d_Map);

        //System.out.println("Reached Show Map Command. Argument List : " + p_ArgumentTokens);
    }


    /**
     * Validates the command arguments and then edits the Player information accordingly.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void gamePlayerCommand(List<String> p_ArgumentTokens)
    {

        String l_PlayerName;

        for(int i = 0; i < p_ArgumentTokens.size() ; i++)
        {
            if(p_ArgumentTokens.get(i).equals("-add"))
            {
                try
                {
                    l_PlayerName = p_ArgumentTokens.get(++i);
                    System.out.println("Player Name: " + l_PlayerName);



                    //CALL ADD Player FUNCTION
                    Player l_Player = new Player(l_PlayerName);
                    d_PlayerList.add(l_Player);


                }
                catch (Exception e)
                {
                    System.out.println("Wrong number of Arguments provided. add option has 1 arguments");
                    return;
                }
            }

            else if(p_ArgumentTokens.get(i).equals("-remove"))
            {
                try
                {
                    l_PlayerName = p_ArgumentTokens.get(++i);
                    System.out.println("Player Name: " + l_PlayerName);



                    //CALL REMOVE Player FUNCTION
                    for(Player l_Player : d_PlayerList)
                    {
                        if(l_Player.getD_PlayerName().equals(l_PlayerName))
                        {
                            d_PlayerList.remove(l_Player);
                        }
                    }




                }
                catch (Exception e)
                {
                    System.out.println("Wrong number of Arguments provided. remove option has 1 argument");
                    e.printStackTrace();
                    return;
                }
            }

            else
            {
                System.out.println("Invalid option. gameplayer has -add and -remove options only");
            }

        }

    }
}
