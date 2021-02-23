package com.risk.maputils;

import com.risk.exception.InvalidMapException;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

import java.util.ArrayList;
import java.util.List;

import static com.risk.main.Main.d_Map;

/**
 * This class defined different operation on class such as
 * continent add and remove for adding and remove continent from continent list
 * country add and remove for adding and remove country from country list
 * neighbor add and remove for adding and remove adjacent countries from list
 * @author Parth Navsariwala
 */

public class MapOperations {

    /**
     * Add continent to map with details such as ID and Value
     *
     * @param p_Map            current map object
     * @param p_ContinentName    ID(Name) of the continent
     * @param p_ContinentValue Value of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void addContinent(Map p_Map, String p_ContinentName, int p_ContinentValue) throws InvalidMapException {
        Continent l_continent = new Continent();
        l_continent.setD_ContinentID(d_Map.getD_Continents().size() + 1);
        l_continent.setD_ContinentValue(p_ContinentValue);
        l_continent.setD_ContinentName(p_ContinentName);

        if (p_Map.isContinentPresentInContinentList(p_ContinentName)) {
            throw new InvalidMapException("The Continent " + p_ContinentName + " Already Exist");
        } else {
            p_Map.addContinentToContinentList(l_continent);
            System.out.println("The Continent "+p_ContinentName +" Added Successfully");
        }
    }

    /**
     * Remove continent to map with details such as ID and Value
     * @param p_Map         current map object
     * @param p_ContinentName ID(Name) of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void removeContinent(Map p_Map, String p_ContinentName) throws InvalidMapException {

        for(Continent l_Continent : d_Map.getD_Continents())
        {
            if(l_Continent.getD_ContinentName().equals(p_ContinentName))
            {
                if(!l_Continent.getD_Countries().isEmpty())
                {
                    System.out.println("Remove Countries from the continent first");
                    return;
                }
            }
        }
        p_Map.removeContinentFromContinentList(p_ContinentName);
        System.out.println("Continent "+p_ContinentName+" removed Successfully");
    }

    /**
     * Add country to map with details such as Country Name and Continent Name
     *
     * @param p_Map         current map object
     * @param p_CountryName   Name of the country
     * @param p_ContinentName Name of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void addCountry(Map p_Map, String p_CountryName, String p_ContinentName) throws InvalidMapException {
        Country l_country = new Country();
        l_country.setD_ContinentName(p_ContinentName);
        l_country.setD_CountryName(p_CountryName);
        for (Continent l_continent : p_Map.getD_Continents())
        {
            if (l_continent.getD_ContinentName().equals(p_ContinentName))
            {
                l_country.setD_CountryID(l_continent.getD_Countries().size() + 1);
                l_country.setD_ContinentID(l_continent.getD_ContinentID());
                l_continent.addCountryToCountryList(l_country);
                System.out.println("The Country "+l_country +" Added Successfully");
            }
        }

    }

    /**
     * Remove continent to map with details such as ID and Value
     *
     * @param p_Map       current map object
     * @param p_CountryName ID of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void removeCountry(Map p_Map, String p_CountryName) throws InvalidMapException {
        for (Continent l_continent : p_Map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                for (Country l_adjCountry : l_country.getD_AdjacentCountries()) {
                    if (l_adjCountry.getD_CountryName().equals(p_CountryName)) {
                        l_country.removeCountryFromAdjacentCountries(p_CountryName);
                        System.out.println("Country "+p_CountryName+" remove successfully");
                    }
                }
            }
            if (l_continent.isCountryPresentInCountryList(p_CountryName)) {
                l_continent.removeCountryFromCountryList(p_CountryName);
            }
        }
    }

    /**
     * Add neighbor country to Country
     *
     * @param p_Map        current map object
     * @param p_NeighborName Name of the neighbor country
     * @param p_CountryName  Name of the country
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void addNeighborCountry(Map p_Map, String p_NeighborName, String p_CountryName) throws InvalidMapException {
        for (Continent l_continent : p_Map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                if (l_country.getD_CountryName().equals(p_CountryName)) {
                    Country l_tempCountry = l_country.getCountryFromCountryName(p_NeighborName);
                    l_country.addCountryToAdjacentCountries(l_tempCountry);
                    l_tempCountry.addCountryToAdjacentCountries(l_country);
                    System.out.println("Neighbor Country "+l_tempCountry +" Added Successfully");
                }
            }
        }

    }

    /**
     * Remove neighbor country from Country
     *
     * @param p_Map        current map object
     * @param p_NeighborName Name of the neighbor country
     * @param p_CountryName  Name of the country
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void removeNeighborCountry(Map p_Map, String p_NeighborName, String p_CountryName) throws InvalidMapException {
        for (Continent l_continent : p_Map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                if (l_country.getD_CountryName().equals(p_CountryName)) {
                    Country l_tempCountry = l_country.getCountryFromCountryName(p_NeighborName);
                    l_country.removeCountryFromAdjacentCountries(p_NeighborName);
                    l_tempCountry.removeCountryFromAdjacentCountries(p_CountryName);
                    System.out.println("Neighbor country "+l_tempCountry+" remove successfully");
                }
            }
        }
    }
}
