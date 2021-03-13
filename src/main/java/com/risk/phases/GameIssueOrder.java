package com.risk.phases;

import com.risk.gameutils.Reinforce;
import com.risk.main.GameEngineNew;
import com.risk.models.Country;
import com.risk.models.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.risk.main.Main.d_PlayerList;

public class GameIssueOrder extends Game{
    GameIssueOrder(GameEngineNew p_gameEngine) {

        super(p_gameEngine);
        reinforce();

    }

    public boolean loadMap(List<String> p_argumentList) {

        printInvalidCommandMessage();
        return false;

    }


    public boolean assignCountries() {

        printInvalidCommandMessage();
        return false;
    }

    public void reinforce() {

        System.out.println("\nReinforcing Armies\n");
        Reinforce.assignReinforcementArmies();
        System.out.println("\nArmies have been successfully reinforced among players\n");
        issueOrder();

    }

    public void issueOrder() {
        for(Player l_Player : d_PlayerList)
        {
            l_Player.setD_noOrdersLeft(false);
        }
        Scanner l_scanner = new Scanner(System.in);
        String l_command;


        System.out.println("\nISSUE ORDER PHASE\n");
        while (noOrdersLeftToIssue()){
            for (Player l_player : d_PlayerList) {

                if(!l_player.isD_noOrdersLeft()){
                    System.out.println("\n\nPlayer " + l_player.getD_PlayerName().toUpperCase() + "'s turn to issue order. ");
                    System.out.println("You have " + l_player.getD_Armies() + " number of reinforcement armies");
                    System.out.println("You own the following Countries");
                    for (Country l_country : l_player.getD_AssignedCountries()) {
                        System.out.print("\n\t\t" + l_country.getD_CountryName() + ", ");
                        System.out.print("\n\tAdjacent Countries : ");
                        for (Country l_adjCountry : l_country.getD_AdjacentCountries()) {
                            System.out.print("\t\t" + l_adjCountry.getD_CountryName() + ", ");
                        }
                    }

                    System.out.println("\n Enter command: ");
                    l_command = l_scanner.nextLine();

                    if(l_command.equals("end")){
                        return;
                    }

                    String l_action = l_command.split(" ")[0];
                    String l_arguments = l_command.substring(l_action.length());


                    System.out.println("Issue Command : " + l_action + l_arguments);

                    l_player.issue_Order(l_action, l_arguments);

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

        System.out.println("All orders for the current turn have been issued.");
        next();

    }

    public void endGame() {

    }

    public void next() {
        d_gameEngineNew.setPhase(new GameExecuteOrder(d_gameEngineNew));
    }

    public boolean noOrdersLeftToIssue(){
        for(Player l_Player : d_PlayerList)
        {
            if(!l_Player.isD_noOrdersLeft())
                return true;
        }

        return false;
    }

    public void addPlayer(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public void executeOrder() {
        printInvalidCommandMessage();
    }

}
