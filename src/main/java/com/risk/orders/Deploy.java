package com.risk.orders;

import com.risk.models.Country;
import com.risk.models.Player;

import java.util.ArrayList;

import static com.risk.main.Main.d_Log;

/**
 * The Command Pattern Class for Deploy Order
 *
 * @author Harshil
 */
public class Deploy implements Order {
    String d_countryName;
    Country d_country;
    int d_numberOfArmies;
    Player d_player;

    /**
     * constructor for the Orders class
     *
     * @param p_player         player Id
     * @param p_countryName    Country Id
     * @param p_numberOfArmies The total number of armies
     */
    public Deploy(Player p_player, String p_countryName, int p_numberOfArmies) {
        d_player = p_player;
        d_countryName = p_countryName;
        d_numberOfArmies = p_numberOfArmies;
        d_country = new Country();
    }

    /**
     * Validate the Deploy command by checking its requirements
     *
     * @return the boolean
     */
    public boolean valid() {
        if (d_player.getD_Armies() >= d_numberOfArmies) {

            ArrayList<String> l_countriesOwnedList = new ArrayList<>();
            for (Country l_country : d_player.getD_AssignedCountries()) {
                l_countriesOwnedList.add(l_country.getD_CountryName());
            }
            if (!l_countriesOwnedList.contains(d_countryName)) {
                System.out.println("You do not own this country. Try Again with a country name that has been assigned to you.");
                d_Log.notify("You do not own this country. Try Again with a country name that has been assigned to you");
                return false;
            }

            return true;

        } else {
            System.out.println("You are trying to deploy more armies than you have. Try Again in your next turn.");
            d_Log.notify("You are trying to deploy more armies than you have. Try Again in your next turn.");
            System.out.println("You currently have " + d_player.getD_Armies() + " number of reinforcement armies left.");
            d_Log.notify("You currently have " + d_player.getD_Armies() + " number of reinforcement armies left.");
            return false;
        }
    }

    /**
     * Execute the deploy order after checking it's validity in Execute Order State
     */
    public void execute() {
        printOrder();
        if (valid()) {
            d_player.setD_Armies(d_player.getD_Armies() - d_numberOfArmies);
            int l_previousArmies = d_country.getCountryFromCountryName(d_countryName).getD_NumberOfArmies();
            d_country.getCountryFromCountryName(d_countryName).setD_NumberOfArmies(d_numberOfArmies + l_previousArmies);
            System.out.println("Order Executed Successfully\n");
        } else {
            System.out.println("Invalid Order, not executed\n");
            d_Log.notify("Invalid Order, not executed\n");
        }
    }

    /*
     * Print order.
     */
    public void printOrder() {
        System.out.println("Order Type : Deploy \nPlayer : " + d_player.getD_PlayerName() + " Country : " + d_countryName
                + " Number Of Armies : " + d_numberOfArmies);
        d_Log.notify("Order Type : Deploy \nPlayer : " + d_player.getD_PlayerName() + " Country : " + d_countryName
                + " Number Of Armies : " + d_numberOfArmies);
    }
}
