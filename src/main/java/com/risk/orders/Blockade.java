package com.risk.orders;

import com.risk.models.Country;
import com.risk.models.Player;

/**
 * The type Blockade
 *
 * @author Parth
 * @author Harsh
 */

public class Blockade implements Order {

    String d_countryName;
    Country d_country;
    Player d_player;

    /**
     * constructor of Blockade class
     *
     * @param p_player      Player Id
     * @param p_countryName Country Id
     */

    public Blockade(Player p_player, String p_countryName) {
        this.d_countryName = p_countryName;
        this.d_player = p_player;
        d_country = new Country();
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
