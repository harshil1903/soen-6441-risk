package com.risk.main;

import com.risk.controller.GameCommands;
import com.risk.gameutils.AssignCountries;
import com.risk.models.Country;
import com.risk.models.Orders;
import com.risk.models.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
    public static void GamePlay() {
        //STARTUP PHASE

        //"loadmap filename"
        //Call editmap function to load map and run validate map function on the map

        //"gameplayer -add playername -remove playername"
        //Add or remove player from player list created in MAIN

        //"assigncountries"
        //Assign countries to players


        //MAIN GAME LOOP
        boolean l_ContinueMainGameLoop = true;
        while (l_ContinueMainGameLoop) {
            //REINFORCEMENT PHASE
            //Method to assign reinforcement armies to each player
            assignReinforcementArmies();

            //GameCommands.showMapCommand(new ArrayList<>());

            //ISSUE ORDERS PHASE
            //Scan for deploy command
            //Add the order by calling Player.issue_Order();
            //Subtract reinforcement armies from that player
            while (!checkReinforcementArmiesCount()) {
                for (Player l_Player : d_PlayerList) {
                    if (l_Player.getD_Armies() != 0) {
                        System.out.println("\n\nPlayer " + l_Player.getD_PlayerName().toUpperCase() + "'s turn to issue order. ");
                        System.out.println("You have " + l_Player.getD_Armies() + " number of reinforcement armies");
                        System.out.println("You own the following Countries");
                        for (Country l_country : l_Player.getD_AssignedCountries()) {
                            System.out.print("\t\t" + l_country.getD_CountryName() + ", ");
                        }
                        l_Player.issue_order();
                        //l_Player.issue_order();
                    } else {
                        System.out.println("Player " + l_Player.getD_PlayerName() + "'s turn is skipped due to no reinforcement armies left");
                    }
                }
            }
            System.out.println();

            //EXECUTE ORDERS PHASE
            //Call next_Order() for each player which returns Order Object
            //call Order.execute()

            //While(l_Order != null)
            int l_NoOrdersPlayerCount = 0;
            Scanner l_Scanner = new Scanner(System.in);
            String l_Choice;
            while (l_NoOrdersPlayerCount <= d_PlayerList.size()) {

                for (Player l_Player : d_PlayerList) {
                    Orders l_Order = l_Player.next_order();
                    //l_Order = l_Player.nextOrder();
                    //if(l_Order != null)
                    if (l_Order != null) {
                        //Order.execute()
                        l_Order.execute();
                        System.out.println("Order : " + l_Order.getD_countryName() + " has " + l_Order.getD_numberOfArmies() + " armies.");
                    } else {
                        ++l_NoOrdersPlayerCount;
                        System.out.println("Number of Players with No Orders : " + l_NoOrdersPlayerCount);
                    }
                }

            }

            GameCommands.showMapCommand(new ArrayList<>());


            System.out.print("Continue Main Game Loop? (y/n) : ");

            System.out.println("\nEnter command: ");
            l_Choice = l_Scanner.nextLine();

            if (l_Choice.equals("n")) {
                l_ContinueMainGameLoop = false;
            }

        }

    }

    /**
     * Check if reinforcement armies are left to be assigned for any player
     *
     * @return whether to continue issue orders or not
     */
    public static boolean checkReinforcementArmiesCount() {
        for (Player l_Player : d_PlayerList) {
            if (l_Player.getD_Armies() != 0) {
                return false;
            }

        }
        return true;
    }


    /**
     * Assign reinforcement armies to each player in the beginning of each turn.
     */
    public static void assignReinforcementArmies() {
        for (Player l_Player : d_PlayerList) {
            int l_CountriesOwned = l_Player.getD_AssignedCountries().size();

            int l_ReinforcementCount;

            l_ReinforcementCount = Math.max((l_CountriesOwned / 3), 3);

            l_Player.setD_Armies(l_ReinforcementCount);
        }
    }

    /**
     * Assign Countries randomly among players
     */
    public static void assignCountries() {
        //Add a method to randomly assign countries to every player
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

        //GameCommands.showMapCommand(new ArrayList<>());
        GamePlay();

    }


    /**
     * Parses GAME related commands and calls the appropriate Game Command method.
     *
     * @param p_Action    command name
     * @param p_Arguments command options/arguments (if any)
     */
    public static void gameCommandParser(String p_Action, String p_Arguments) {
        String[] l_ArgumentTokens = p_Arguments.split(" ");
        List<String> l_ArgumentList = new ArrayList<>(Arrays.asList(l_ArgumentTokens.clone()));

        if (!l_ArgumentList.isEmpty()) {
            l_ArgumentList.remove(0);
        }

        switch (p_Action) {
            case "loadmap":
                try {
                    d_GameLoaded = GameCommands.loadMapCommand(l_ArgumentList);
                } catch (Exception e) {
                    System.out.println("Could not load the requested map.");
                }

                break;

            case "gameplayer":
                if (d_GameLoaded) {
                    GameCommands.gamePlayerCommand(l_ArgumentList);
                    break;
                }
                break;

            case "assigncountries":
                if (d_GameLoaded) {
                    assignCountries();
                    break;
                }
                break;

            case "showmap":
                if (d_GameLoaded) {
                    GameCommands.showMapCommand(l_ArgumentList);
                    break;
                }
                break;
        }

        if (!d_GameLoaded) {
            System.out.println("No Map is loaded yet, use loadmap command to load map");
        }

    }


    /**
     * Scan and parse Game related Commands Only
     *
     * @param p_Action    command name
     * @param p_Arguments command options/arguments (if any)
     */
    public static void checkNextGameCommands(String p_Action, String p_Arguments) {

        gameCommandParser(p_Action, p_Arguments);

        Scanner l_Scanner = new Scanner(System.in);
        String l_Command;

        System.out.println("Enter command: ");
        l_Command = l_Scanner.nextLine();

        while (!l_Command.equals("EXITGAME")) {
            String l_Action = l_Command.split(" ")[0];

            String l_Arguments = l_Command.substring(l_Action.length());

            gameCommandParser(l_Action, l_Arguments);

            System.out.println("Enter command: ");
            l_Command = l_Scanner.nextLine();
        }
    }


}
