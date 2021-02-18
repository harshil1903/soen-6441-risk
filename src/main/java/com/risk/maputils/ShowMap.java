package com.risk.maputils;

import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

/**
 * This Class is used for displaying the loaded map as well
 * as displaying the game map.
 */


public class ShowMap {

    /**
     * This method is used to display the Contents of the map i.e the continents,
     * countries and the adjacent countries.
     * @param p_Map Loaded MAP
     */
    public static void displayEditorMap(Map p_Map)
    {

        System.out.println("DISPLAYING MAP");
        for (Continent l_continent : p_Map.getD_Continents())
        {
            System.out.println("Continent Name : " + l_continent.getD_ContinentName());
            System.out.println("Countries in the Continent : \n");
            for (Country l_country : l_continent.getD_Countries())
            {
                System.out.println("\t" + l_country.getD_CountryName());
                System.out.println("\tAdjacent Countries : ");
                for (Country l_adjCountry : l_country.getD_AdjacentCountries())
                {
                    System.out.print("\t\t" + l_adjCountry.getD_CountryName() + ", ");
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }
    }

    public static void displayGameMap(Map p_Map)
    {
        System.out.println("DISPLAYING GAME MAP");
        for (Continent l_continent : p_Map.getD_Continents())
        {
            System.out.println("Continent Name : " + l_continent.getD_ContinentName());
            System.out.println("Countries in the Continent : \n");

            for (Country l_country : l_continent.getD_Countries())
            {
                System.out.println("\t" + l_country.getD_CountryName() + "\nOwned by : " + l_country.getD_Player() + "\nNumber of Armies " + l_country.getD_NumberOfArmies());
                System.out.println("\tAdjacent Countries : ");

                for (Country l_adjCountry : l_country.getD_AdjacentCountries())
                {
                    System.out.print("\t\t" + l_adjCountry.getD_CountryName() + ", ");
                }
                System.out.println("\n");
            }
            System.out.println("\n");
        }
    }
}
