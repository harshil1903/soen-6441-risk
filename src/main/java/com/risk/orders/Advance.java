package com.risk.orders;

import com.risk.models.Country;
import com.risk.models.Player;

import java.util.ArrayList;

/**
 * The type advance
 *
 * @author Parth
 * @author Harsh
 */

public class Advance implements Order {

    String d_sourceCountryName;
    String d_targetCountryName;

    Country d_sourceCountry;
    Country d_targetCountry;
    Player d_player;
    int d_numberOfArmies;

    /**
     * constructor for advance class
     *
     * @param p_sourceCountryName Source Country Name
     * @param p_targetCountryName Target Country Name
     * @param p_player            Player Id
     * @param p_numberOfArmies    Number of armies
     */

    public Advance(Player p_player, String p_sourceCountryName, String p_targetCountryName, int p_numberOfArmies) {
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
        //first check player has a advance card or not in card list

        if (d_sourceCountry.getD_NumberOfArmies() > d_numberOfArmies) {
            return true;

        } else {
            System.out.println(d_player.getD_PlayerName() + " does not have sufficient armies to be attack to the target country");
            return false;
        }
    }

    /**
     * Execute.
     */
    public void execute() {
        ArrayList<String> l_countriesOwnedList = new ArrayList<>();
        for (Country l_country : d_player.getD_AssignedCountries()) {
            l_countriesOwnedList.add(l_country.getD_CountryName());
        }
        if (l_countriesOwnedList.contains(d_targetCountryName)) {
            //just move army from onr country to another country

            //first remove number of army from source country
            d_sourceCountry.setD_NumberOfArmies(d_sourceCountry.getD_NumberOfArmies() - d_numberOfArmies);

            //then fetch number of army in target country
            int l_previousArmies = d_targetCountry.getCountryFromCountryName(d_targetCountryName).getD_NumberOfArmies();

            //then add source army to target army
            d_targetCountry.getCountryFromCountryName(d_targetCountryName).setD_NumberOfArmies(d_numberOfArmies + l_previousArmies);

        } else {
            //logic for the battle
            int l_attacker = (int) (d_numberOfArmies * 0.6);
            int l_defender = (int) (d_targetCountry.getCountryFromCountryName(d_targetCountryName).getD_NumberOfArmies() * 0.7);

            if (l_attacker > l_defender) {
                d_sourceCountry.getCountryFromCountryName(d_sourceCountryName).setD_NumberOfArmies(d_sourceCountry.getD_NumberOfArmies() - d_numberOfArmies);
                d_targetCountry.getCountryFromCountryName(d_targetCountryName).setD_NumberOfArmies(l_attacker);
                d_player.addCountryToAssignedCountries(d_targetCountry);
            } else if (l_attacker < l_defender) {
                d_sourceCountry.getCountryFromCountryName(d_sourceCountryName).setD_NumberOfArmies(d_sourceCountry.getD_NumberOfArmies() - d_numberOfArmies);
                d_targetCountry.getCountryFromCountryName(d_targetCountryName).setD_NumberOfArmies(l_defender);
            }

        }

    }

}
