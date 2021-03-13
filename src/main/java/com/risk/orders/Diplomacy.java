package com.risk.orders;

import com.risk.models.Player;

import static com.risk.main.Main.d_PlayerList;

public class Diplomacy implements Order {
    Player d_player, d_otherPlayer;

    public Diplomacy(Player p_player, String p_otherPlayer){
        d_player = p_player;
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
        return false;
    }


    /**
     * Execute.
     */
    public void execute() {

    }

}
