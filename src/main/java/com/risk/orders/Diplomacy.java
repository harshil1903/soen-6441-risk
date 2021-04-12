package com.risk.orders;

import com.risk.models.Player;

import static com.risk.main.Main.d_Log;
import static com.risk.main.Main.d_PlayerList;

/**
 * Type Diplomacy
 */
public class Diplomacy implements Order {
    Player d_player, d_otherPlayer;
    String d_playerName;

    /**
     * constructor for the diplomacy class
     *
     * @param p_player      Current player object
     * @param p_otherPlayer Other player Id
     */
    public Diplomacy(Player p_player, String p_otherPlayer) {
        d_player = p_player;
        d_otherPlayer = new Player();

        for (Player l_player : d_PlayerList) {
            if (l_player.getD_PlayerName().equals(p_otherPlayer))
                d_otherPlayer = l_player;
        }
    }

    /**
     * Valid boolean for check players name are not same
     *
     * @return the boolean
     */
    public boolean valid() {
        if (d_player.getD_cardList().contains("negotiate")) {

            if (!d_player.getD_PlayerName().equals(d_otherPlayer.getD_PlayerName())) {
                return true;
            } else {
                System.out.println("Both player name cannot be same");
                d_Log.notify("Both player name cannot be same");
                return false;
            }
        } else {
            System.out.println("Player does not contain diplomacy card");
            d_Log.notify("Player does not contain diplomacy card");
            return false;
        }
    }


    /**
     * Execute method to run diplomacy card
     */
    public void execute() {
        printOrder();
        if (valid()) {
            d_player.addPlayerToDiplomacyList(d_otherPlayer.getPlayerFromPlayerName(d_playerName));
            d_otherPlayer.addPlayerToDiplomacyList(d_player);
            d_player.removeCard("negotiate");
            System.out.println("Order Executed Successfully\n");
        }
        else {
            System.out.println("Invalid Order, not executed\n");
            d_Log.notify("Invalid Order, not executed\n");
        }
    }

    /*
     * Print order.
     */
    public void printOrder() {
        System.out.println("Order Type : Diplomacy \nPlayer : " + d_player.getD_PlayerName() + " Opponent Player : " + d_otherPlayer.getD_PlayerName());
        d_Log.notify("Order Type : Diplomacy \nPlayer : " + d_player.getD_PlayerName() + " Opponent Player : " + d_otherPlayer.getD_PlayerName());
    }
}
