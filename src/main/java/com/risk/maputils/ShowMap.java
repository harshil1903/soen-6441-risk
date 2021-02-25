package com.risk.maputils;

import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;
import com.risk.models.Player;

import static com.risk.main.Main.d_PlayerList;

/**
 * This Class is used for displaying the loaded map as well
 * as displaying the game map.
 */
public class ShowMap {

    /**
     * This method is used to display the Contents of the map i.e the continents,
     * countries and the adjacent countries.
     *
     * @param p_map Loaded MAP
     */
    public static void displayEditorMap(Map p_map) {

        System.out.println("DISPLAYING MAP");
        for (Continent l_continent : p_map.getD_Continents()) {
            System.out.println("Continent Name : " + l_continent.getD_ContinentName());
            System.out.println("Countries in the Continent : \n");
            for (Country l_country : l_continent.getD_Countries()) {
                System.out.println("\t" + l_country.getD_CountryName());
                System.out.print("\tAdjacent Countries : ");
                try {
                    for (Country l_adjCountry : l_country.getD_AdjacentCountries()) {
                        System.out.print("\t\t" + l_adjCountry.getD_CountryName() + ", ");
                    }
                } catch (Exception e) {
                }

                System.out.println("\n");
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
            System.out.println("Continent Name : " + l_continent.getD_ContinentName());
            System.out.println("Countries in the Continent : \n");

            for (Country l_country : l_continent.getD_Countries()) {
                try {
                    System.out.println("\t" + l_country.getD_CountryName() + " \n\tOwned by: " + l_country.getD_Player().getD_PlayerName() + " \tArmies: " + l_country.getD_NumberOfArmies());
                } catch (Exception e) {
                    System.out.println("\t" + l_country.getD_CountryName() + " \n\tOwned by: " + l_country.getD_Player() + " \tArmies: " + l_country.getD_NumberOfArmies());

                }
                System.out.print("\tAdjacent Countries : ");
                for (Country l_adjCountry : l_country.getD_AdjacentCountries()) {
                    System.out.print("\t\t" + l_adjCountry.getD_CountryName() + ", ");
                }
                System.out.println("\n");
            }
        }

        System.out.println("\nPLAYER INFO");
        for (Player l_player : d_PlayerList) {
            System.out.println("\nPlayer " + l_player.getD_PlayerName().toUpperCase());
            System.out.println("You own the following Countries along with army count in it.");
            for (Country l_country : l_player.getD_AssignedCountries()) {
                System.out.println("\t" + l_country.getD_CountryName() + " \tArmies : " + l_country.getD_NumberOfArmies());
            }
        }
    }
}
