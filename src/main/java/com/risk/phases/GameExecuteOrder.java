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

                    l_order.execute();
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

        String l_winner = d_Map.getCountryListOfMap().get(0).getD_Player().getD_PlayerName();

        for(Country l_country : d_Map.getCountryListOfMap())
        {
            if(!l_country.getD_Player().getD_PlayerName().equals(l_winner))
            {
                return "";
            }
        }
        return l_winner;
    }

    /**
     * To return current phase name in string
     *
     * @return Current phase
     */
    public String currentPhase() {
        return "GameExecuteOrder";
    }

    public void loadGame(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public void saveGame(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    /**
     * To set next phase
     */
    public void next() {
        d_gameEngine.setPhase(new GameIssueOrder(d_gameEngine));
        d_gameEngine.getPhase().reinforce();
    }
}
