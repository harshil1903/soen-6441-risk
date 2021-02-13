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
 * @author Parth Navsari
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

        if (p_Map.getD_Continents().contains(l_continent)) {
            throw new InvalidMapException("The Continent" + p_ContinentID + "Already Exist");
        } else {
            p_Map.addContinentToContinentList(p_ContinentID);
        }
        return l_continent;
    }

    /**
     * Remove continent to map with details such as ID and Valu
     * @param p_Map current map object
     * @param p_ContinentID ID of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void removeContinent(Map p_Map, int p_ContinentID) throws InvalidMapException {


        //For each continent in MAP.continents
        //  For each country in continent.countries
        //      For each country in country.adjacentcountries
        //          if(country.continentID == p_continentID
        //              country.adjacentCountries.remove(country)


        Continent l_rcontinent = new Continent();
        l_rcontinent.setD_ContinentID(p_ContinentID);
        if(p_Map.isContinentPresentInContinentList(p_ContinentID)){
            p_Map.removeContinentFromContinentList(p_ContinentID);
        }
        else{
            throw new InvalidMapException("The Continent Not Present In The List");
        }
    }

    /**
     * Add country to map with details such as ID and Value
     * @param p_Map         current map object
     * @param p_CountryID   ID of the country
     * @param p_ContinentID Value of the country
     * @return Returns the new country
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static Country addCountry(Map p_Map, int p_CountryID, int p_ContinentID) throws InvalidMapException {

        //create newcountry with countryid
        //For each continent in MAP.continents
        //  if continent.ID == p_continentID
        //      continent.addcountrytocountrylist(country)


        Country l_country = new Country();
        Continent l_continent=new Continent();
        l_country.setD_CountryID(p_CountryID);
        l_country.setD_ContinentID(p_ContinentID);
        List<Country> l_tempCountry=new ArrayList<Country>();
        return l_country;
    }

    /**
     * Remove continent to map with details such as ID and Value
     * @param p_map       current map object
     * @param p_CountryID ID of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void removeCountry(Map p_map, int p_CountryID) throws InvalidMapException {

        //For each continent in MAP.continents
        //      For each country in continent.countries
        //          For each country in country.adjacentcountries
        //              if(country.countryID == p_countryID
        //                  country.adjacentCountries.remove(country)
        //
        //      if(Continent.iscountrypresentinthiscontinent(p_CountryID))
        //          Continent.removeCountry(p_CountryID)


    }
    /**
     * Add neighbor country to map with details such as ID and Value
     * @param p_Map         current map object
     * @param p_NeighborID   ID of the country
     * @param p_CountryID Value of the country
     * @return Returns the new neighbor country
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static Country addNeighborCountry(Map p_Map, int p_NeighborID, int p_CountryID) throws InvalidMapException {
        //For each continent in MAP.continents
        //      For each country in continent.countries
        //          if(country.countryID == p_countryID)
        //              tempcountry = Country.getObjectfromID(p_NeighborID)
        //              country.addCountrytoAdjacentlist(tempCountry)



        Country l_country = new Country();
        return l_country;
    }
    /**
     * Remove country from map with details such as ID and Value
     * @param p_map       current map object
     * @param p_CountryID ID of the continent
     * @throws InvalidMapException throws IO Exception if there is any error while doing operations on map
     */

    public static void removeNeighborCountry(Map p_map, int p_CountryID) throws InvalidMapException {
        //For each continent in MAP.continents
        //      For each country in continent.countries
        //          if(country.countryID == p_countryID)
        //              tempcountry = Country.getObjectfromID(p_NeighborID)
        //              country.removeCountrytoAdjacentlist(tempCountry)

    }
}
