package com.risk.main;

import com.risk.controller.GameCommands;
import com.risk.controller.MapCommands;
import com.risk.exception.InvalidMapException;
import com.risk.gameutils.AssignCountries;
import com.risk.models.Orders;
import com.risk.models.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;

/**
 * Game Engine Class controls the entire flow of Gameplay including all the game phases
 */
public class GameEngine {

    public static boolean d_GameLoaded = false;

    /**
     * Game play
     * Main Game Loop begins from here
     */
    public static void GamePlay()
    {
        //STARTUP PHASE

            //"loadmap filename"
            //Call editmap function to load map and run validate map function on the map

            //"gameplayer -add playername -remove playername"
            //Add or remove player from player list created in MAIN

            //"assigncountries"
            //Assign countries to players





        //MAIN GAME LOOP
        while (true)
        {
            //REINFORCEMENT PHASE
            //Method to assign reinforcement armies to each player
            assignReinforcementArmies();


            //ISSUE ORDERS PHASE
            //Scan for deploy command
            //Add the order by calling Player.issue_Order();
            //Subtract reinforcement armies from that player
            while(!checkReinforcementArmiesCount())
            {
                for (Player l_Player : d_PlayerList)
                {
                    if(l_Player.getD_Armies() != 0)
                    {
                        System.out.println("Player " + l_Player.getD_PlayerName() + "'s turn to issue order");
                        l_Player.issue_order();
                        //l_Player.issue_order();
                    }
                }
            }


            //EXECUTE ORDERS PHASE
            //Call next_Order() for each player which returns Order Object
            //call Order.execute()


            //While(l_Order != null)
            while(true)
            {
                for (Player l_Player : d_PlayerList)
                {
                    Orders l_Order = l_Player.next_order();
                    //l_Order = l_Player.nextOrder();
                    //if(l_Order != null)
                    if(l_Order != null)
                    {
                        //Order.execute()
                        l_Order.execute();
                    }
                }
            }



        }

    }

    /**
     * Check if reinforcement armies are left to be assigned for any player
     *
     * @return whether to continue issue orders or not
     */
    public static boolean checkReinforcementArmiesCount()
    {
        for (Player l_Player : d_PlayerList)
        {
            if(l_Player.getD_Armies() != 0)
            {
                return false;
            }

        }
        return true;
    }


    /**
     * Assign reinforcement armies to each player in the beginning of each turn.
     */
    public static void assignReinforcementArmies()
    {
        for (Player l_Player : d_PlayerList)
        {
            int l_CountriesOwned = l_Player.getD_AssignedCountries().size();

            int l_ReinforcementCount;

            l_ReinforcementCount = Math.max((l_CountriesOwned/3) , 3);

            l_Player.setD_Armies(l_ReinforcementCount);
        }
    }

    /**
     * Assign Countries randomly among players
     */
    public static void assignCountries()
    {
        //Add a method to randomly assign countries to every player
        try {
            AssignCountries.assignCountries();
        }
        catch (Exception e){
        }
        GamePlay();

    }


    /**
     * Parses GAME related commands and calls the appropriate Game Command method.
     *
     * @param p_Action    command name
     * @param p_Arguments command options/arguments (if any)
     */
    public static void gameCommandParser(String p_Action, String p_Arguments)
    {
        String[] l_ArgumentTokens = p_Arguments.split(" ");
        List<String> l_ArgumentList = new ArrayList<>(Arrays.asList(l_ArgumentTokens.clone()));

        if(!l_ArgumentList.isEmpty())
        {
            l_ArgumentList.remove(0);
        }

        switch (p_Action)
        {
            case "loadmap":
                try{
                    d_GameLoaded = GameCommands.loadMapCommand(l_ArgumentList);
                }
                catch (Exception e)
                {}

                break;

            case "gameplayer":
                if(d_GameLoaded)
                {
                    GameCommands.gamePlayerCommand(l_ArgumentList);
                    break;
                }
                break;

            case "assigncountries":
                if(d_GameLoaded)
                {
                    assignCountries();
                    break;
                }
                break;

            case "showmap":
                if(d_GameLoaded)
                {
                    MapCommands.showMapCommand(l_ArgumentList);
                    break;
                }
                break;
        }

        if(!d_GameLoaded)
        {
            System.out.println("No Map is loaded yet, use loadmap command to load map");
        }

    }


    /**
     * Scan and parse Game related Commands Only
     *
     * @param p_Action    command name
     * @param p_Arguments command options/arguments (if any)
     */
    public static void checkNextGameCommands(String p_Action, String p_Arguments)
    {

        gameCommandParser(p_Action, p_Arguments);

        Scanner l_Scanner = new Scanner(System.in);
        String l_Command;

        System.out.println("Enter command: ");
        l_Command = l_Scanner.nextLine();

        while (!l_Command.equals("EXITGAME"))
        {
            String l_Action = l_Command.split(" ")[0];

            String l_Arguments = l_Command.substring(l_Action.length());

            gameCommandParser(l_Action,l_Arguments);

            System.out.println("Enter command: ");
            l_Command = l_Scanner.nextLine();
        }
    }



}
