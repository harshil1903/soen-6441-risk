package com.risk.main;

import com.risk.controller.GameCommands;
import com.risk.gameutils.AssignCountries;
import com.risk.models.Continent;
import com.risk.models.Country;
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
 * and main game loop
 *
 * @author Harshil
 */
public class GameEngineOld {

    /**
     * Contains true if game is loaded otherwise false.
     */
    public static boolean d_gameLoaded = false;

    /**
     * Game play
     * Main Game Loop begins from here
     */
    public static void GamePlay() {

        boolean l_continueMainGameLoop = true;
        while (l_continueMainGameLoop) {

            //REINFORCEMENT PHASE
            assignReinforcementArmies();


            //ISSUE ORDERS PHASE
            //call a function which scanes for order commands then create an order of that type
            //Add that order to that Player
            //call issueOrder() of player which will then add that order's valid method to check
            // amd then perform necessary operations and add that order to orderlist of player
            while (!checkReinforcementArmiesCount()) {
                for (Player l_player : d_PlayerList) {
                    if (l_player.getD_Armies() != 0) {
                        System.out.println("\n\nPlayer " + l_player.getD_PlayerName().toUpperCase() + "'s turn to issue order. ");
                        System.out.println("You have " + l_player.getD_Armies() + " number of reinforcement armies");
                        System.out.println("You own the following Countries");
                        for (Country l_country : l_player.getD_AssignedCountries()) {
                            System.out.print("\t\t" + l_country.getD_CountryName() + ", ");
                        }
                        l_player.issueOrder();
                    } else {
                        System.out.println("Player " + l_player.getD_PlayerName() + "'s turn is skipped due to no reinforcement armies left");
                    }
                }
            }
            System.out.println();

            //EXECUTE ORDERS PHASE
            int l_noOrdersPlayerCount = 0;
            Scanner l_scanner = new Scanner(System.in);
            String l_choice;
            while (l_noOrdersPlayerCount <= d_PlayerList.size()) {

                for (Player l_player : d_PlayerList) {
                    Orders l_order = l_player.nextOrder();
                    if (l_order != null) {
                        l_order.execute();
                        System.out.println("Order : " + l_order.getD_CountryName() + " has " + l_order.getD_NumberOfArmies() + " armies.");
                    } else {
                        ++l_noOrdersPlayerCount;
                        System.out.println("Number of Players with No Orders : " + l_noOrdersPlayerCount);
                    }
                }

            }
            GameCommands.showMapCommand(new ArrayList<>());

            System.out.print("Continue Main Game Loop? (y/n) : ");
            System.out.println("\nEnter command: ");
            l_choice = l_scanner.nextLine();

            if (l_choice.equals("n")) {
                l_continueMainGameLoop = false;
            }

        }

    }

    /**
     * Check if reinforcement armies are left to be assigned for any player
     *
     * @return whether to continue issue orders or not
     */
    public static boolean checkReinforcementArmiesCount() {
        for (Player l_player : d_PlayerList) {
            if (l_player.getD_Armies() != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * Assign reinforcement armies to each player in the beginning of each turn.
     */
    public static void assignReinforcementArmies() {
        for (Player l_player : d_PlayerList) {
            int l_countriesOwned = l_player.getD_AssignedCountries().size();
            int l_reinforcementCount, l_flag;
            String l_playerName = l_player.getD_PlayerName();

            l_reinforcementCount = Math.max((l_countriesOwned / 3), 3);

            for (Continent l_continent : d_Map.getD_Continents()) {
                l_flag = 0;
                for (Country l_country : l_continent.getD_Countries()) {
                    if (!l_country.getD_Player().getD_PlayerName().equals(l_playerName)) {
                        l_flag = 1;
                        break;
                    }
                }
                if (l_flag == 0) {
                    l_reinforcementCount += l_continent.getD_ContinentValue();
                }
            }

            l_player.setD_Armies(l_reinforcementCount);
        }
    }

    /**
     * Assign Countries randomly among players
     */
    public static void assignCountries() {
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

        GamePlay();

    }


    /**
     * Parses GAME related commands and calls the appropriate Game Command method.
     *
     * @param p_action    command name
     * @param p_arguments command options/arguments (if any)
     */
    public static void gameCommandParser(String p_action, String p_arguments) {
        String[] l_argumentTokens = p_arguments.split(" ");
        List<String> l_argumentList = new ArrayList<>(Arrays.asList(l_argumentTokens.clone()));

        if (!l_argumentList.isEmpty()) {
            l_argumentList.remove(0);
        }

        switch (p_action) {
            case "loadmap":
                try {
                    d_gameLoaded = GameCommands.loadMapCommand(l_argumentList);
                } catch (Exception e) {
                    System.out.println("Could not load the requested map.");
                }

                break;

            case "gameplayer":
                if (d_gameLoaded) {
                    GameCommands.gamePlayerCommand(l_argumentList);
                    break;
                }
                break;

            case "assigncountries":
                if (d_gameLoaded) {
                    assignCountries();
                    break;
                }
                break;

            case "showmap":
                if (d_gameLoaded) {
                    GameCommands.showMapCommand(l_argumentList);
                    break;
                }
                break;

            default:
                System.out.println("Invalid Command \nAllowed Commands are : loadmap, gameplayer, assigncountries, showmap");
                break;
        }

        if (!d_gameLoaded) {
            System.out.println("No Map is loaded yet, use loadmap command to load map");
        }

    }


    /**
     * Scan and parse Game related Commands Only
     *
     * @param p_action    command name
     * @param p_arguments command options/arguments (if any)
     */
    public static void checkNextGameCommands(String p_action, String p_arguments) {

        gameCommandParser(p_action, p_arguments);

        Scanner l_scanner = new Scanner(System.in);
        String l_command;

        System.out.println("Enter command: ");
        l_command = l_scanner.nextLine();

        while (!l_command.equals("EXITGAME")) {
            String l_action = l_command.split(" ")[0];

            String l_arguments = l_command.substring(l_action.length());

            gameCommandParser(l_action, l_arguments);

            System.out.println("Enter command: ");
            l_command = l_scanner.nextLine();
        }
    }


}
