package com.risk.orders;

import com.risk.models.Country;
import com.risk.models.Player;

import java.util.ArrayList;

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
        //here firstly check if player have a blockade card or not after chirag make cardlist of each player

        ArrayList<String> l_countriesOwnedList = new ArrayList<>();
        for (Country l_country : d_player.getD_AssignedCountries()) {
            l_countriesOwnedList.add(l_country.getD_CountryName());
        }
        if (!l_countriesOwnedList.contains(d_countryName)) {
            System.out.println(d_player.getD_PlayerName() + "can not use Blockade card on opponent’s country");
            return false;
        }

        return true;
    }

    /**
     * Execute.
     */
    public void execute() {
        int l_previousArmy = d_country.getCountryFromCountryName(d_countryName).getD_NumberOfArmies();
        d_country.getCountryFromCountryName(d_countryName).setD_NumberOfArmies((l_previousArmy * 3));
    }
}
