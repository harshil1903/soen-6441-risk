package com.risk.orders;

import com.risk.models.Player;

import static com.risk.main.Main.d_PlayerList;

/**
 * Type Diplomacy
 */
public class Diplomacy implements Order {
    Player d_player, d_otherPlayer;
    String d_playerName;

    /**
     * constructor for the diplomacy class
     * @param p_player Current player object
     * @param p_otherPlayer Other player Id
     */
    public Diplomacy(Player p_player, String p_otherPlayer){
        d_player = p_player;
        d_otherPlayer=new Player();

        for(Player l_player : d_PlayerList)
        {
            if(l_player.getD_PlayerName().equals(p_otherPlayer))
                d_otherPlayer = l_player;
        }
    }
    /**
     * Valid boolean.
     *
     * @return the boolean
     */
    public boolean valid() {
        if(!d_player.getD_PlayerName().equals(d_otherPlayer.getD_PlayerName()))
        {
            return true;
        }
        else{
            System.out.println("Both player name can not be same");
            return false;
        }
    }


    /**
     * Execute.
     */
    public void execute() {
        d_player.addPlayerToDiplomacyList(d_otherPlayer.getPlayerFromPlayerName(d_playerName));
        d_otherPlayer.addPlayerToDiplomacyList(d_player);
    }

}
