package com.risk.maputils;

import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;
import com.risk.models.Player;

import static com.risk.main.Main.d_Log;
import static com.risk.main.Main.d_PlayerList;

/**
 * This Class is used for displaying the loaded map as well
 * as displaying the game map.
 *
 * @author Harshil
 */
public class ShowMap {

    /**
     * This method is used to display the Contents of the map i.e the continents,
     * countries and the adjacent countries.
     *
     * @param p_map Loaded MAP
     */
    public static void displayEditorMap(Map p_map) {

        System.out.println("\n\nDISPLAYING MAP\n");
        for (Continent l_continent : p_map.getD_Continents()) {
            System.out.println("Continent Name : " + l_continent.getD_ContinentName());
            d_Log.notify("Continent Name : " + l_continent.getD_ContinentName());
            //System.out.println("Countries in the Continent : \n");

            System.out.printf("\t%-15s:\t%-15s%n", "COUNTRY", "NEIGHBOR COUNTRIES");

            for (Country l_country : l_continent.getD_Countries()) {

                System.out.printf("\t%-15s:", l_country.getD_CountryName());

                for (Country l_adjCountry : l_country.getD_AdjacentCountries()) {
                    System.out.printf("\t%-15s ", l_adjCountry.getD_CountryName());
                }
                System.out.println();
            }
            System.out.println("\n");
        }
    }

    /**
     * This method is used to display the Contents of the map i.e the continents,
     * countries and the adjacent countries along with which player owns the countries
     * and how many armies are present in each country
     *
     * @param p_map the p map
     */
    public static void displayGameMap(Map p_map) {
        System.out.println("\n\nDISPLAYING GAME MAP");
        for (Continent l_continent : p_map.getD_Continents()) {
            System.out.println("\n\nContinent Name : " + l_continent.getD_ContinentName());

            System.out.printf("\t%-15s:\t%-10s:\t%-10s :\t%-15s%n", "COUNTRY", "OWNER", "ARMIES", "NEIGHBOR COUNTRIES");
            for (Country l_country : l_continent.getD_Countries()) {

                System.out.printf("\t%-15s:", l_country.getD_CountryName());

                try {
                    System.out.printf("\t%-10s:\t %-10d:", l_country.getD_Player().getD_PlayerName().toUpperCase(), l_country.getD_NumberOfArmies());
                } catch (Exception e) {
                    System.out.printf("\t%-10s:\t %-10d:", l_country.getD_Player(), l_country.getD_NumberOfArmies());
                }

                for (Country l_adjCountry : l_country.getD_AdjacentCountries()) {
                    System.out.printf("\t%-15s ", l_adjCountry.getD_CountryName());
                }

                System.out.println();
            }
        }

        System.out.println("\nPLAYER INFO");
        for (Player l_player : d_PlayerList) {
            System.out.println("\nPlayer: " + l_player.getD_PlayerName().toUpperCase());
            System.out.println("You own the following Countries along with army count in it.");
            System.out.printf("\t%-15s:\t%-10s%n", "COUNTRY", "ARMIES");
            for (Country l_country : l_player.getD_AssignedCountries()) {
                System.out.printf("\t%-15s:   \t%s%n", l_country.getD_CountryName(), l_country.getD_NumberOfArmies());
            }
        }

        System.out.println("\n");
    }
}
