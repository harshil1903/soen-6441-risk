package com.risk.orders;

import com.risk.models.Country;
import com.risk.models.Player;

import java.util.ArrayList;

/**
 * The type Airlift.
 *
 * @author Harsh
 * @author Parth
 */

public class Airlift implements Order {

    String d_sourceCountryName;
    String d_targetCountryName;

    Country d_sourceCountry;
    Country d_targetCountry;

    int d_numberOfArmies;
    Player d_player;

    /**
     * @param p_player            Player ID
     * @param p_sourceCountryName Source country ID
     * @param p_targetCountryName Target country ID
     * @param p_numberOfArmies    Number of armies to be placed from source to target country
     */

    public Airlift(Player p_player, String p_sourceCountryName, String p_targetCountryName, int p_numberOfArmies) {
        this.d_sourceCountryName = p_sourceCountryName;
        this.d_targetCountryName = p_targetCountryName;
        this.d_numberOfArmies = p_numberOfArmies;
        this.d_player = p_player;

        d_sourceCountry = new Country();
        d_targetCountry = new Country();

    }

    /**
     * Valid boolean.
     *
     * @return the boolean
     */

    public boolean valid() {
        //check whether the player has airlift card or not

        ArrayList<String> l_countriesOwnedList = new ArrayList<>();
        for (Country l_country : d_player.getD_AssignedCountries()) {
            l_countriesOwnedList.add(l_country.getD_CountryName());
        }

        if (!l_countriesOwnedList.contains(d_sourceCountryName)) {
            System.out.println(d_player.getD_PlayerName() + "can not use Airlift card as source country is not owned");
            return false;
        } else if (!l_countriesOwnedList.contains(d_targetCountryName)) {
            System.out.println(d_player.getD_PlayerName() + "can not use Airlift card on target country is not owned");
            return false;
        } else if (d_targetCountry.getCountryFromCountryName(d_targetCountryName).getD_NumberOfArmies() < d_numberOfArmies) {
            System.out.println(d_player.getD_PlayerName() + "does not have sufficient armies to be airlifted to the target country");
            return false;
        }


        return true;
    }


    /**
     * Execute.
     */
    public void execute() {

        d_sourceCountry.setD_NumberOfArmies(d_sourceCountry.getD_NumberOfArmies() - d_numberOfArmies);
        int l_previousArmy = d_targetCountry.getCountryFromCountryName(d_targetCountryName).getD_NumberOfArmies();
        d_targetCountry.getCountryFromCountryName(d_targetCountryName).setD_NumberOfArmies(l_previousArmy + d_numberOfArmies);
    }

}
