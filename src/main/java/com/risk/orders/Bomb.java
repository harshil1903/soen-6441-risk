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
     * @param p_player      current player object
     * @param p_countryName Enemy country Id
     */

    public Bomb(Player p_player, String p_countryName) {
        this.d_countryName = p_countryName;
        this.d_player = p_player;
        d_country = new Country();
        d_country = d_country.getCountryFromCountryName(p_countryName);
    }

    /**
     * Valid boolean.
     *
     * @return the boolean
     */
    public boolean valid() {

        //here we have to check whether both country is adjecent or not?
        //here firstly check if player have a bomb card or not after chirag make cardlist of each player
        if(d_player.getD_cardList().contains("bomb")){
            if (d_country.getD_NumberOfArmies() > 0) {
                ArrayList<String> l_countriesOwnedList = new ArrayList<>();
                ArrayList<String> l_adjacentList = new ArrayList<>();
                for (Country l_country : d_player.getD_AssignedCountries()) {
                    l_countriesOwnedList.add(l_country.getD_CountryName());
                }

                for (Country l_country : d_player.getD_AssignedCountries()) {
                    for (Country l_adjCountry : l_country.getD_AdjacentCountries()) {
                        l_adjacentList.add(l_adjCountry.getD_CountryName());
                    }
                }
                    if (!l_adjacentList.contains(d_countryName)) {
                        System.out.println(d_countryName + " not adjacent with " + d_player.getD_PlayerName() + "countries");
                        return false;
                    }

                    if (l_countriesOwnedList.contains(d_countryName)) {
                        System.out.println(d_player.getD_PlayerName() + " can not attack bomb on your own country");
                        return false;
                    }

                    return true;
            } else {
                System.out.println(d_countryName + " has a 0 army so you can not apply bomb order there");
                return false;
            }


        }else{
            System.out.println("Player does not contain bomb card");
            return false;
        }
    }

    /**
     * Execute.
     */
    public void execute(){
        if (valid()) {
            int l_previousArmy = d_country.getCountryFromCountryName(d_countryName).getD_NumberOfArmies();
            System.out.println("Before Bomb Card number of army in "+d_countryName+" is : "+d_country.getD_NumberOfArmies());
            d_country.getCountryFromCountryName(d_countryName).setD_NumberOfArmies(Math.round(l_previousArmy / 2));
            System.out.println(d_player.getD_PlayerName()+" applied Bomb Card successfully");
            System.out.println("After Bomb Card number of army in "+d_countryName+" is : "+d_country.getD_NumberOfArmies());
            d_player.removeCard("bomb");
        }
    }
}
