package com.risk.maputils;

import com.risk.exception.InvalidMapException;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

import static com.risk.main.Main.d_Map;

/**
 * This class defined different operation on class such as
 * continent add and remove for adding and remove continent from continent list
 * country add and remove for adding and remove country from country list
 * neighbor add and remove for adding and remove adjacent countries from list
 *
 * @author Parth
 */

public class MapOperations {

    /**
     * Add continent to map with details such as ID and Value
     *
     * @param p_map            current map object
     * @param p_continentName  ID(Name) of the continent
     * @param p_continentValue Value of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void addContinent(Map p_map, String p_continentName, int p_continentValue) throws InvalidMapException {
        Continent l_continent = new Continent();
        l_continent.setD_ContinentID(d_Map.getD_Continents().size() + 1);
        l_continent.setD_ContinentValue(p_continentValue);
        l_continent.setD_ContinentName(p_continentName);

        if (p_map.isContinentPresentInContinentList(p_continentName)) {
            throw new InvalidMapException("The Continent " + p_continentName + " Already Exist");
        } else {
            p_map.addContinentToContinentList(l_continent);
            System.out.println("The Continent " + p_continentName + " Added Successfully");
        }
    }

    /**
     * Remove continent to map with details such as ID and Value
     *
     * @param p_map           current map object
     * @param p_continentName ID(Name) of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void removeContinent(Map p_map, String p_continentName) throws InvalidMapException {

        for (Continent l_continent : d_Map.getD_Continents()) {
            if (l_continent.getD_ContinentName().equals(p_continentName)) {
                if (!l_continent.getD_Countries().isEmpty()) {
                    System.out.println("Remove Countries from the continent first");
                    return;
                }
            }
        }
        p_map.removeContinentFromContinentList(p_continentName);
        System.out.println("Continent " + p_continentName + " removed Successfully");
    }

    /**
     * Add country to map with details such as Country Name and Continent Name
     *
     * @param p_map           current map object
     * @param p_countryName   Name of the country
     * @param p_continentName Name of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void addCountry(Map p_map, String p_countryName, String p_continentName) throws InvalidMapException {
        Country l_country = new Country();
        l_country.setD_ContinentName(p_continentName);
        l_country.setD_CountryName(p_countryName);
        for (Continent l_continent : p_map.getD_Continents()) {
            if (l_continent.getD_ContinentName().equals(p_continentName)) {
                l_country.setD_ContinentID(l_continent.getD_ContinentID());
                l_continent.addCountryToCountryList(l_country);
                System.out.println("The Country " + p_countryName + " Added Successfully");
            }
        }

        int l_countryID = 0;
        for (Continent l_continent : p_map.getD_Continents()) {
            l_countryID += l_continent.getD_Countries().size();
        }
        l_country.setD_CountryID(l_countryID);

    }

    /**
     * Remove continent to map with details such as ID and Value
     *
     * @param p_map         current map object
     * @param p_countryName ID of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void removeCountry(Map p_map, String p_countryName) throws InvalidMapException {
        for (Continent l_continent : p_map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                for (Country l_adjCountry : l_country.getD_AdjacentCountries()) {
                    if (l_adjCountry.getD_CountryName().equals(p_countryName)) {
                        l_country.removeCountryFromAdjacentCountries(p_countryName);
                        System.out.println("Country " + p_countryName + " remove successfully");
                    }
                }
            }
            if (l_continent.isCountryPresentInCountryList(p_countryName)) {
                l_continent.removeCountryFromCountryList(p_countryName);
            }
        }
    }

    /**
     * Add neighbor country to Country
     *
     * @param p_map          current map object
     * @param p_neighborName Name of the neighbor country
     * @param p_countryName  Name of the country
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void addNeighborCountry(Map p_map, String p_neighborName, String p_countryName) throws InvalidMapException {
        for (Continent l_continent : p_map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                if (l_country.getD_CountryName().equals(p_countryName)) {
                    Country l_tempCountry = l_country.getCountryFromCountryName(p_neighborName);
                    l_country.addCountryToAdjacentCountries(l_tempCountry);
                    l_tempCountry.addCountryToAdjacentCountries(l_country);
                    System.out.println("Neighbor Country " + p_neighborName + " Added Successfully");
                }
            }
        }

    }

    /**
     * Remove neighbor country from Country
     *
     * @param p_map          current map object
     * @param p_neighborName Name of the neighbor country
     * @param p_countryName  Name of the country
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void removeNeighborCountry(Map p_map, String p_neighborName, String p_countryName) throws InvalidMapException {
        for (Continent l_continent : p_map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                if (l_country.getD_CountryName().equals(p_countryName)) {
                    Country l_tempCountry = l_country.getCountryFromCountryName(p_neighborName);
                    l_country.removeCountryFromAdjacentCountries(p_neighborName);
                    l_tempCountry.removeCountryFromAdjacentCountries(p_countryName);
                    System.out.println("Neighbor country " + p_neighborName + " remove successfully");
                }
            }
        }
    }
}
