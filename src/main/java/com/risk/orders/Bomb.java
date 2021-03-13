package com.risk.orders;

import com.risk.models.Country;
import com.risk.models.Player;

import java.util.ArrayList;

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
        //here firstly check if player have a bomb card or not after chirag make cardlist of each player
        ArrayList<String> l_countriesOwnedList = new ArrayList<>();
        for (Country l_country : d_player.getD_AssignedCountries()) {
            l_countriesOwnedList.add(l_country.getD_CountryName());
        }
        if (l_countriesOwnedList.contains(d_countryName)) {
            System.out.println(d_player.getD_PlayerName() + " can not attack bomb on your own country");
            return false;
        }
        return true;
    }

    /**
     * Execute.
     */
    public void execute() {
        int l_previousArmy = d_country.getCountryFromCountryName(d_countryName).getD_NumberOfArmies();
        d_country.getCountryFromCountryName(d_countryName).setD_NumberOfArmies((l_previousArmy / 2));
    }
}
