package com.risk.maputils;

import com.risk.exception.InvalidMapException;
import com.risk.models.Continent;
import com.risk.models.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defined different operation on class such as
 * continent add and remove
 * country add and remove
 * neighbor add and remove
 *
 * @author Parth
 */

public class MapOperations {

    /**
     * Add continent to map with details such as ID and Value
     *
     * @param p_map            current map object
     * @param p_ContinentID    ID of the continent
     * @param p_ContinentValue Value of the continent
     * @return Returns the new continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static Continent addContinent(Map p_map, int p_ContinentID, int p_ContinentValue) throws InvalidMapException {
        Continent d_continent = new Continent();
        d_continent.setD_ContinentID(p_ContinentID);
        d_continent.setD_ContinentValue(p_ContinentValue);

        if (p_map.getD_Continents().contains(d_continent)) {
            throw new InvalidMapException("The Continent" + p_ContinentID + "Already Exist");
        } else {
            p_map.getD_Continents().add(d_continent);
        }
        return d_continent;
    }

    /**
     * Remove continent to map with details such as ID and Value
     *
     * @param p_map         current map object
     * @param p_ContinentID ID of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void removeContinent(Map p_map, int p_ContinentID) throws InvalidMapException {
        Continent d_rcontinent = new Continent();
        if (d_rcontinent.getD_ContinentID() == p_ContinentID) {
            p_map.getD_Continents().remove(d_rcontinent);
        }

    }

    /**
     * Add country to map with details such as ID and Value
     *
     * @param p_map         current map object
     * @param p_CountryID   ID of the country
     * @param p_ContinentID Value of the country
     * @return Returns the new country
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    /*public static Country addCountry(Map p_map, int p_CountryID, int p_ContinentID) throws InvalidMapException {
        Country d_country = new Country();
        List<Country> l_TempCountry=new ArrayList<Country>();
        d_country.setD_CountryID(p_CountryID);
        List<Continent> l_Continentlist = new ArrayList<Continent>();
        l_Continentlist = p_map.getD_Continents();

        //check whether country exist or not
        for (Continent l_continent : l_Continentlist) {
            List<Country> l_countrylist=l_continent.getD_Countries();
            for(l_TempCountry:l_countrylist) {
                if (l_TempCountry.get == p_CountryID) {
                    throw new InvalidMapException("The Continent" + p_ContinentID + "Already Exist");
                }
            }
        }
        return d_country;
    }*/

    /**
     * Remove continent to map with details such as ID and Value
     *
     * @param p_map       current map object
     * @param p_CountryID ID of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void removeCountry(Map p_map, int p_CountryID) throws InvalidMapException {

    }
}
