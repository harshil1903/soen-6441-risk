package com.risk.phases;

import com.risk.gameutils.Reinforce;
import com.risk.main.GameEngine;
import com.risk.models.Country;
import com.risk.models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.risk.main.Main.d_Log;
import static com.risk.main.Main.d_PlayerList;

/**
 * Game issue order allows players to issue their orders
 *
 * @author Harshil
 */
public class GameIssueOrder extends Game{
    /**
     * Instantiates a new Game issue order.
     *
     * @param p_gameEngine the p game engine
     */
    public GameIssueOrder(GameEngine p_gameEngine) {

        super(p_gameEngine);
        reinforce();

    }

    /**
     *  To reinforce players with their reinforcement armies
     */
    public void reinforce() {

        System.out.println("\nReinforcing Armies");
        d_Log.notify("\nReinforcing Armies");
        Reinforce.assignReinforcementArmies();
        System.out.println("\nArmies have been successfully reinforced among players");
        d_Log.notify("\nArmies have been successfully reinforced among players");
        issueOrder();

    }

    /**
     *  Issue orders as per player's choices
     */
    public void issueOrder() {
        for(Player l_Player : d_PlayerList)
        {
            l_Player.setD_noOrdersLeft(false);
        }
        Scanner l_scanner = new Scanner(System.in);
        String l_command;

        System.out.println("\nISSUE ORDER PHASE");
        d_Log.notify("\nISSUE ORDER PHASE");

        while (noOrdersLeftToIssue()){

            for (Player l_player : d_PlayerList) {

                if(!l_player.isD_noOrdersLeft()){
                    System.out.println("\nPlayer " + l_player.getD_PlayerName().toUpperCase() + "'s turn to issue order. ");
                    System.out.println("You have " + l_player.getD_Armies() + " number of reinforcement armies");
                    System.out.println("You own the following Countries along with their adjacent countries");

                    System.out.printf("\t%-15s:\t%-15s%n","COUNTRY","NEIGHBOR COUNTRIES");

                    for (Country l_country : l_player.getD_AssignedCountries()) {

                        System.out.printf("\t%-15s:", l_country.getD_CountryName());

                        for (Country l_adjCountry : l_country.getD_AdjacentCountries()) {
                            System.out.printf("\t%-15s ", l_adjCountry.getD_CountryName());
                        }
                        System.out.println();
                    }

                    System.out.println("\n\nEnter command: ");
                    l_command = l_scanner.nextLine();

                    if(l_command.equals("end")){
                        return;
                    }

                    if(l_command.equals("showmap")){
                        showMap(new ArrayList<>());
                        System.out.println("\n\nEnter command: ");
                        l_command = l_scanner.nextLine();
                    }

                    String l_action = l_command.split(" ")[0];
                    String l_arguments = l_command.substring(l_action.length());

                    l_player.setOrderValues(l_action, l_arguments);
                    l_player.issueOrder();

                    System.out.println("Do you have more orders left? (y/n)");
                    l_command = l_scanner.nextLine();

                    if (l_command.equals("n"))
                        l_player.setD_noOrdersLeft(true);
                }
                else{
                    System.out.println("Player " + l_player.getD_PlayerName() + "'s turn is skipped because they have no orders left");
                }
            }
        }

        System.out.println("\nAll orders for the current turn have been issued.\n");
        d_Log.notify("\nAll orders for the current turn have been issued.\n");
        next();

    }


    //to be removed
    public void issueDeployOrder(String p_command){
        String l_action = p_command.split(" ")[0];
        String l_arguments = p_command.substring(l_action.length());


    }

    /**
     *  To set next phase
     */
    public void next() {
        d_gameEngine.setPhase(new GameExecuteOrder(d_gameEngine));
    }

    /**
     * No orders left to issue boolean.
     *
     * @return the boolean
     */
    public boolean noOrdersLeftToIssue(){
        for(Player l_Player : d_PlayerList)
        {
            if(!l_Player.isD_noOrdersLeft())
                return true;
        }

        return false;
    }



    /**
     * To return current phase name in string
     *
     * @return Current phase
     */
    public String currentPhase(){
        return "GameIssueOrder";
    }



    /**
     *  loadmap Command to load the map
     *
     * @param p_argumentTokens command parameters
     */
    public boolean loadMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
        return false;
    }

    /**
     *  assigncountries Command to assign countries amoong players
     */
    public boolean assignCountries() {
        printInvalidCommandMessage();
        return false;
    }


    /**
     *  gameplayer Command  to add/remove players
     *
     * @param p_argumentTokens command parameters
     */
    public void addPlayer(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    /**
     *  execute orders once all players have issued their orders
     */
    public void executeOrder() {
        printInvalidCommandMessage();
    }
}
