package com.risk.maputils;

import com.risk.exception.InvalidMapException;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defined different operation on class such as
 * continent add and remove
 * country add and remove
 * neighbor add and remove
 *
 * @author Parth Navsariwala
 */

public class MapOperations {

    /**
     * Add continent to map with details such as ID and Value
     *
     * @param p_Map            current map object
     * @param p_ContinentID    ID of the continent
     * @param p_ContinentValue Value of the continent
     * @return Returns the new continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static Continent addContinent(Map p_Map, int p_ContinentID, int p_ContinentValue) throws InvalidMapException {
        Continent l_continent = new Continent();
        l_continent.setD_ContinentID(p_ContinentID);
        l_continent.setD_ContinentValue(p_ContinentValue);

        if (p_Map.isContinentPresentInContinentList(p_ContinentID)) {
            throw new InvalidMapException("The Continent" + p_ContinentID + "Already Exist");
        } else {
            p_Map.addContinentToContinentList(p_ContinentID);
        }
        return l_continent;
    }

    /**
     * Remove continent to map with details such as ID and Valu
     *
     * @param p_Map         current map object
     * @param p_ContinentID ID of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void removeContinent(Map p_Map, int p_ContinentID) throws InvalidMapException {

        for (Continent l_tempContinent : p_Map.getD_Continents()) {
            for (Country l_tempCcountry : l_tempContinent.getD_Countries()) {
                for (Country l_tempAdjcountry : l_tempCcountry.getD_AdjacentCountries()) {
                    if (l_tempAdjcountry.getD_ContinentID() == p_ContinentID) {
                        l_tempAdjcountry.getD_AdjacentCountries().remove(l_tempAdjcountry);
                    }
                }
            }
            if (p_Map.isContinentPresentInContinentList(p_ContinentID)) {
                p_Map.removeContinentFromContinentList(p_ContinentID);
            }
        }
    }

    /**
     * Add country to map with details such as ID and Value
     *
     * @param p_Map         current map object
     * @param p_CountryID   ID of the country
     * @param p_ContinentID Value of the country
     * @return Returns the new country
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static Country addCountry(Map p_Map, int p_CountryID, int p_ContinentID) throws InvalidMapException {
        Country l_country = new Country();
        l_country.setD_CountryID(p_CountryID);
        for (Continent l_continent : p_Map.getD_Continents()) {
            if (l_continent.getD_ContinentID() == p_ContinentID) {
                l_continent.addCountryToCountryList(p_CountryID);
            }
        }
        return l_country;
    }

    /**
     * Remove continent to map with details such as ID and Value
     *
     * @param p_map       current map object
     * @param p_CountryID ID of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void removeCountry(Map p_map, int p_CountryID) throws InvalidMapException {
        for (Continent l_continent : p_map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                for (Country l_adjCountry : l_country.getD_AdjacentCountries()) {
                    if (l_adjCountry.getD_CountryID() == p_CountryID) {
                        l_adjCountry.removeCountryFromAdjacentCountries(p_CountryID);
                    }
                }
            }
            if (l_continent.isCountryPresentInCountryList(p_CountryID)) {
                l_continent.removeCountryFromCountryList(p_CountryID);
            }
        }
    }

    /**
     * Add neighbor country to map with details such as ID and Value
     * @param p_Map        current map object
     * @param p_NeighborID ID of the country
     * @param p_CountryID  Value of the country
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void addNeighborCountry(Map p_Map, int p_NeighborID, int p_CountryID) throws InvalidMapException {
        for(Continent l_continent:p_Map.getD_Continents()){
            for(Country l_country:l_continent.getD_Countries()){
                if(l_country.getD_CountryID()==p_CountryID){

                }
            }
        }

    }

    /**
     * Remove country from map with details such as ID and Value
     * @param p_map       current map object
     * @param p_CountryID ID of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void removeNeighborCountry(Map p_map, int p_CountryID) throws InvalidMapException {
        for(Continent l_continent:p_map.getD_Continents()){
            for(Country l_country:l_continent.getD_Countries()){
                if(l_country.getD_CountryID()==p_CountryID){

                }
            }
        }

    }
}
