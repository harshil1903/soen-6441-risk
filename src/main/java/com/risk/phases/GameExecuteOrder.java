package com.risk.phases;

import com.risk.main.GameEngine;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.Order;

import java.util.ArrayList;
import java.util.List;

import static com.risk.main.Main.*;

/**
 * Game execute order executes the orders of each player in a round robin manner
 *
 * @author Harshil
 */
public class GameExecuteOrder extends Game {

    /**
     * Instantiates a new Game execute order.
     *
     * @param p_gameEngine the p game engine
     */
    GameExecuteOrder(GameEngine p_gameEngine) {
        super(p_gameEngine);
        executeOrder();
    }

    /**
     * loadmap Command to load the map
     *
     * @param p_argumentTokens command parameters
     */
    public boolean loadMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
        return false;
    }

    /**
     * gameplayer Command to add/remove players
     *
     * @param p_argumentTokens command parameters
     */
    public void addPlayer(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    /**
     * assigncountries Command to assign countries amoong players
     */
    public boolean assignCountries() {
        printInvalidCommandMessage();
        return false;
    }

    /**
     * To reinforce players with their reinforcement armies
     */
    public void reinforce() {
        printInvalidCommandMessage();
    }

    /**
     * Issue orders as per player's choices
     */
    public void issueOrder() {
        printInvalidCommandMessage();
    }

    /**
     * Execute orders once all players have issued their orders
     */
    public void executeOrder() {
        int l_noOrdersPlayerCount = 0;
        String l_playerWon;
        while (l_noOrdersPlayerCount <= d_PlayerList.size()) {

            for (Player l_player : d_PlayerList) {

                Order l_order = l_player.nextOrder();
                if (l_order != null) {

                    System.out.println("Executing Order");
                    d_Log.notify("Executing Order");
                    if (l_order.valid()) {

                        l_order.execute();
                    } else {
                        System.out.println("Invalid Order, not executed");
                        d_Log.notify("Invalid Order, not executed");
                    }
                } else {
                    ++l_noOrdersPlayerCount;
                }

            }

        }
        System.out.println("Executing Orders finished\n \n\nNEW TURN \n");
        d_Log.notify("Executing Orders finished\n \n\nNEW TURN \n");

        for (Player l_player : d_PlayerList) {
            l_player.getDiplomacyPlayer().clear();
        }

        l_playerWon = playerWon();

        if (!l_playerWon.equals("")) {
            System.out.println("Player " + l_playerWon + " has Won the Game!!!");
            d_Log.notify("Player " + l_playerWon + " has Won the Game!!!");
            showMap(new ArrayList<>());
            endGame();
        }
        next();
    }

    /**
     * To check if a player has won the game
     *
     * @return Name of player won or blank string if nobody has won
     */
    public String playerWon() {
        for (Player l_player : d_PlayerList) {

            String l_playerName = l_player.getD_PlayerName();

            for (Continent l_continent : d_Map.getD_Continents()) {

                for (Country l_country : l_continent.getD_Countries()) {

                    if (!l_country.getD_Player().getD_PlayerName().equals(l_playerName)) {
                        return "";
                    }
                }
            }

            return l_playerName;
        }
        return "";
    }

    /**
     * To return current phase name in string
     *
     * @return Current phase
     */
    public String currentPhase() {
        return "GameExecuteOrder";
    }


    /**
     * To set next phase
     */
    public void next() {
        d_gameEngine.setPhase(new GameIssueOrder(d_gameEngine));
    }
}
