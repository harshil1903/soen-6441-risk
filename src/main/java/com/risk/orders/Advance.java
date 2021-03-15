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
     * @param p_player            Current player object
     * @param p_numberOfArmies    Number of armies
     */

    public Advance(Player p_player, String p_sourceCountryName, String p_targetCountryName, int p_numberOfArmies) {
        this.d_sourceCountryName = p_sourceCountryName;
        this.d_targetCountryName = p_targetCountryName;
        this.d_numberOfArmies = p_numberOfArmies;
        this.d_player = p_player;

        d_sourceCountry = new Country();
        d_sourceCountry = d_sourceCountry.getCountryFromCountryName(d_sourceCountryName);
        d_targetCountry = new Country();
        d_targetCountry = d_targetCountry.getCountryFromCountryName(d_targetCountryName);
    }


    /**
     * Valid boolean.
     *
     * @return the boolean
     */
    public boolean valid() {
        //first check player has a advance card or not in card list

        if (d_sourceCountry.getD_NumberOfArmies() > d_numberOfArmies) {
            if (d_sourceCountryName != d_targetCountryName) {
                if (d_sourceCountry.getD_AdjacentCountries().contains(d_targetCountry)) {
                    return true;
                }
            } else {
                System.out.println("Source country name " + d_targetCountryName + " and Target country name" + d_targetCountryName + "are same so advance order can not apply here");
                return false;
            }

        } else {
            if (d_sourceCountry.getD_NumberOfArmies() == d_numberOfArmies) {
                System.out.println(d_player.getD_PlayerName() + " can not use whole army for attack");
            }
            System.out.println(d_player.getD_PlayerName() + " does not have sufficient armies to be attack to the target country");
            return false;
        }
        return false;
    }

   /* public boolean testCheck()
    {
        if(d_player.getDeplomacyPlayer().contains(d_targetCountry.getD_Player()))
        {
            System.out.println("Player is in diplomacyList");
            return true;
        }
        else{
            System.out.println("Player is not in deplomacyList");
            return false;
        }
    }*/

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
            //
            int l_attackerPercentage = (int) Math.round(d_numberOfArmies * 0.6);
            int l_defenderPercentage = (int) Math.round(d_targetCountry.getCountryFromCountryName(d_targetCountryName).getD_NumberOfArmies() * 0.7);

            //Battle Begins....
            int l_attackerArmy = d_numberOfArmies - l_defenderPercentage;
            int l_defenderArmy = d_targetCountry.getCountryFromCountryName(d_targetCountryName).getD_NumberOfArmies() - l_attackerPercentage;

            if (l_defenderArmy <= 0) {
                //attacker will get card here...
                d_sourceCountry.getCountryFromCountryName(d_sourceCountryName).setD_NumberOfArmies(d_sourceCountry.getD_NumberOfArmies() - d_numberOfArmies);
                d_targetCountry.getCountryFromCountryName(d_targetCountryName).setD_NumberOfArmies(l_attackerArmy);
                d_player.addCountryToAssignedCountries(d_targetCountry);
                Player l_tempPlayer = d_targetCountry.getD_Player();
                l_tempPlayer.removeCountryFromAssignedCountries(d_targetCountry.getD_CountryID());
                d_targetCountry.setD_Player(d_player);
            } else {
                //here defender successfully defend
                //so attacker can not get card here

                int l_previousArmies = d_sourceCountry.getD_NumberOfArmies() - d_numberOfArmies;
                d_sourceCountry.getCountryFromCountryName(d_sourceCountryName).setD_NumberOfArmies(l_attackerArmy + l_previousArmies);
                d_targetCountry.getCountryFromCountryName(d_targetCountryName).setD_NumberOfArmies(l_defenderArmy);
            }

        }

    }

}
