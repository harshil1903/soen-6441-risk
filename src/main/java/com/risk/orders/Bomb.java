package com.risk.orders;

import com.risk.models.Country;
import com.risk.models.Player;

/**
 * The type Bomb
 * @author Parth
 * @author Harsh
 */
public class Bomb implements Order{
    String d_countryName;
    Country d_country;
    Player d_player;

    /**
     * constructor for Bomb class
     * @param d_countryName Country Id
     */

    public Bomb(String d_countryName) {
        this.d_countryName = d_countryName;
    }

    /**
     * Valid boolean.
     *
     * @return the boolean
     */
    public boolean valid(){
        return false;
    };

    /**
     * Execute.
     */
    public void execute(){

    };
}
