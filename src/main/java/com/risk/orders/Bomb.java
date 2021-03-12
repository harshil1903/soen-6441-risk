package com.risk.orders;

import com.risk.models.Country;
import com.risk.models.Player;

/**
 * The type Bomb
 *
 * @author Parth
 * @author Harsh
 */
public class Bomb implements Order {
    String d_countryName;
    Country d_country;
    Player d_player;

    /**
     * constructor for Bomb class
     *
     * @param p_countryName Country Id
     */

    public Bomb(Player p_player, String p_countryName) {
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
        //here firstly add if player have a bomb card or not after chirag make cardlist of each player
        if(d_player.getD_AssignedCountries().contains(d_countryName))
        {
            System.out.println("You can not attack bomb on your own country");
        }
        return false;
    }

    /**
     * Execute.
     */
    public void execute() {
        int p_previousArmy = d_country.getCountryFromCountryName(d_countryName).getD_NumberOfArmies();
        d_country.getCountryFromCountryName(d_countryName).setD_NumberOfArmies((p_previousArmy / 2));
    }
}
