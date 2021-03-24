package com.risk.maputils;

import java.util.*;

import com.risk.exception.InvalidMapException;
import com.risk.models.Map;
import com.risk.models.Continent;
import com.risk.models.Country;

import static com.risk.main.Main.d_Log;

/**
 * This class performs validation of map correctness, also after loading
 * and saving any new map the map should be validated automatically.
 *
 * @author Harsh
 */


public class MapValidator {

    static String d_alertMessage = "";
    public static boolean d_isValid = false;

    /**
     * This method is used for validation of map.
     *
     * @param p_map refers to the map object for verification.
     * @throws InvalidMapException is used for the situation where map has certain errors.
     */

    public static void validateMap(Map p_map) throws InvalidMapException {
        if (p_map == null) {
            throw new InvalidMapException("The Map is not valid, it does not contain any content.");
        } else {
            if (p_map.getD_Continents().size() < 1) {
                throw new InvalidMapException("There should be atleast one Continent in the graph.");
            } else {

                validateCountryBelongsToOneContinent(p_map);

                validateContinents(p_map);

                if (!isMapConnectedGraph(p_map)) {
                    throw new InvalidMapException("The map is not a connected graph i.e Continent is not a subgraph in the map. A map should be connected graph formed by continents.");
                } else {
                    System.out.println("The map is connected");
                    d_Log.notify("The map is connected");
                }

            }

        }
        d_isValid = true;

    }

    /**
     * This method is used to validate the continents.
     * <p>
     * A map must be subgraph of continents and if it is true then the continents must be subgraph of countries.
     * This method will validate whether the map is subgraph of continents or not.
     *
     * @param p_map map refers to map object to validate the continents.
     * @throws InvalidMapException throws exception if map has any continent related errors.
     */

    public static void validateContinents(Map p_map) throws InvalidMapException {

        for (Continent l_continent : p_map.getD_Continents()) {
            if (l_continent.getD_Countries().size() < 1) {
                throw new InvalidMapException("There should be atleast one country in any continent.");
            }

            for (Country l_country : l_continent.getD_Countries()) {
                validateCountry(l_country, p_map);
            }

            if (!isContinentConnectedGraph(l_continent, p_map)) {
                throw new InvalidMapException("The Continent:- " + l_continent.getD_ContinentName() + " is not connected by its neighbouring countries.A Continent must be connected graph formed by its neighbouring countries in the map.");
            }
        }

        for (Continent l_continent : p_map.getD_Continents()) {

            if(!checkSubGraphConnectivityForContinent(l_continent)){
                throw new InvalidMapException("The Continent:-" + l_continent.getD_ContinentName() + " failed subgraph connectivity");
            }
        }


    }


    /**
     * This method is used for validating that whether the continents are connected or not.
     *
     * @param p_continent is the continent to be validated.
     * @param p_map       is the current map object.
     * @return will return true if the continent is connected in the map.
     */

    public static boolean isContinentConnectedGraph(Continent p_continent, Map p_map) {
        bfsTraversalCountry(p_continent.getD_Countries().get(0), p_map);
        boolean l_returnValue = true;
        for (Country l_country : p_continent.getD_Countries()) {
            if (l_country.isD_IsProcessed() == false) {
                l_country.setD_Processed(false);
                d_alertMessage = l_country.getD_CountryName() + "country is not forming connected graph in the continent" + p_continent.getD_ContinentName() + ".";
                l_returnValue = false;
                break;
            }
        }

        for (Country l_country : p_continent.getD_Countries()) {
            l_country.setD_Processed(false);
        }
        return l_returnValue;
    }

    /**
     * This method performs the traversal of the countries in BFS manner.
     *
     * @param p_country refers to the country that is to be traversed.
     * @param p_map     refers to the map object.
     */

    public static void bfsTraversalCountry(Country p_country, Map p_map) {
        if (p_country.isD_IsProcessed() == true) {
            return;
        }

        p_country.setD_Processed(true);

        for (Country l_country : p_country.getD_AdjacentCountries()) {
            if ((l_country.getD_BelongToContinent() == p_country.getD_BelongToContinent()) && l_country.isD_IsProcessed() == false)
                bfsTraversalCountry(l_country, p_map);
        }

    }

    /**
     * This method is used to validate that the country is connected or not.
     *
     * @param p_country refers to the country that is to be verified.
     * @param p_map     refers to the map object.
     * @throws InvalidMapException throws exception if the map is not valid.
     */

    public static void validateCountry(Country p_country, Map p_map) throws InvalidMapException {
        List<Country> l_adjCountryList = p_country.getD_AdjacentCountries();
        List<Country> l_adjCountryListBelongToSameContinent = p_country.getD_AdjacentCountries();

        if ((l_adjCountryList == null) && (l_adjCountryList.size() < 1)) {
            throw new InvalidMapException("Country: " + p_country.getD_CountryName() + " must have atleast one adjacent country.");
        } else {
            for (Country l_adjCountry : l_adjCountryList) {
                if (!l_adjCountry.getD_AdjacentCountries().contains(p_country)) {
                    l_adjCountry.getD_AdjacentCountries().add(p_country);
                }
            }
        }

//        for(Country l_adjCountryBelongToSameContinent : l_adjCountryListBelongToSameContinent){
//             List<Country> l_adj = l_adjCountryBelongToSameContinent.getD_AdjacentCountries(); //main list after removing other continents countries
//             Continent l_cont = p_country.getD_BelongToContinent();
//
//             for(Country l_countryToBeRemoved : l_adj){
//                 if(!l_cont.getD_Countries().contains(l_countryToBeRemoved));
//                 l_adj.remove(l_countryToBeRemoved);
//             }
//
//             if(!l_adj.contains(l_cont.getD_Countries())){
//                 throw new InvalidMapException("Country: " + p_country.getD_CountryName() + " is not adjacent with the countries in the" + l_cont.getD_ContinentName() + "continent.");
//             }
//             //l_adj.remove(l_adjCountryBelongToSameContinent.getD_AdjacentCountries());
//        }


    }


    public static boolean checkSubGraphConnectivityForContinent(Continent p_continent) throws InvalidMapException{

        bfsTraversalSubGraphConnectivityForContinent(p_continent.getD_Countries().get(0));
        boolean l_returnValue = true;
        for (Country l_country : p_continent.getD_Countries()) {
            if (l_country.isD_IsProcessed() == false) {
                l_country.setD_Processed(false);
                d_alertMessage = l_country.getD_CountryName() + "country is not forming connected graph in the continent" + p_continent.getD_ContinentName() + ".";
                l_returnValue = false;
                break;
            }
        }

        for (Country l_country : p_continent.getD_Countries()) {
            l_country.setD_Processed(false);
        }
        return l_returnValue;
    }

    public static void bfsTraversalSubGraphConnectivityForContinent(Country p_country) throws InvalidMapException{
        ArrayList<Country> l_adjCountryListBelongToSameContinent = new ArrayList<>();
        //System.out.println("Checking Country : " + p_country.getD_CountryName());

        for (Country l_country : p_country.getD_AdjacentCountries()) {

           // System.out.println("SUB GRAPH : Country : " + l_country.getD_CountryName());
          //  System.out.println("Continents : " + p_country.getD_ContinentName() + "  " + l_country.getD_ContinentName());
            if (l_country.getD_ContinentName().equals(p_country.getD_ContinentName())) {
                l_adjCountryListBelongToSameContinent.add(l_country);
            }
        }

        //for(Country c : l_adjCountryListBelongToSameContinent)
        //    System.out.println("After removing SUB GRAPH : Country : " + c.getD_CountryName());

        if (p_country.isD_IsProcessed() == true) {
            return;
        }

        p_country.setD_Processed(true);

        for (Country l_country : l_adjCountryListBelongToSameContinent) {
            if ((l_country.getD_BelongToContinent() == p_country.getD_BelongToContinent()) && l_country.isD_IsProcessed() == false)
                bfsTraversalSubGraphConnectivityForContinent(l_country);
        }


    }


    /**
     * This method is used to validate that whether the continents in the map form a graph or not.
     *
     * @param p_map refers to the map object
     * @return returns true if it the map is connected graph.
     */

    public static boolean isMapConnectedGraph(Map p_map) {

        if (p_map.getD_Continents().size() < 2) {
            return false;
        }

        bfsTraversalContinent(p_map.getD_Continents().get(0), p_map);

        boolean l_returnValue = true;
        for (Continent l_continent : p_map.getD_Continents()) {
            if (l_continent.isD_IsVisited() == false) {
                l_returnValue = false;
                break;
            }
        }

        for (Continent l_continent : p_map.getD_Continents()) {
            l_continent.setD_Visited(false);
        }
        return l_returnValue;
    }

    /**
     * This method performs the traversal of continents in BFS manner.
     *
     * @param p_continent refers to the continent to be traversed.
     * @param p_map       refers to the map object
     */

    public static void bfsTraversalContinent(Continent p_continent, Map p_map) {
        if (p_continent.isD_IsVisited() == true) {
            return;
        }

        p_continent.setD_Visited(true);

        for (Continent l_continent : getAdjacentContinents(p_continent, p_map)) {
            if (l_continent.isD_IsVisited() == false)
                bfsTraversalContinent(l_continent, p_map);
        }

    }

    /**
     * This method is used to return the adjacent continents as a list for any particular continent.
     *
     * @param p_continent refers to the continent whose adjacent continents are to be found.
     * @param p_map       refers to the map object.
     * @return will return the list of adjacent continents of the current continent.
     */

    public static List<Continent> getAdjacentContinents(Continent p_continent, Map p_map) {
        List<Continent> l_adjacentContinents = new ArrayList<>();
        HashSet<Country> l_adjCountryMainSet = new HashSet<>();

        for (Country l_country : p_continent.getD_Countries()) {
            l_adjCountryMainSet.addAll(l_country.getD_AdjacentCountries());
        }

        for (Continent l_remainingContinent : p_map.getD_Continents()) {
            if (!p_continent.equals(l_remainingContinent)) {
                if (!Collections.disjoint(l_adjCountryMainSet, l_remainingContinent.getD_Countries())) {
                    l_adjacentContinents.add(l_remainingContinent);

                }
            }
        }
        return l_adjacentContinents;
    }

    /**
     * This method is used to check whether the country is belonging to only one continent or not.
     *
     * @param p_map refers to map object.
     * @throws InvalidMapException throws InvalidMapException if map is not valid.
     */
    public static void validateCountryBelongsToOneContinent(Map p_map) throws InvalidMapException {

        ArrayList<Country> l_countryList = new ArrayList<>();

        for (Continent l_continent : p_map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                if (!l_countryList.contains(l_country)) {
                    l_countryList.add(l_country);
                } else {
                    throw new InvalidMapException("Country:" + l_country.getD_CountryName() + " must belong to only one continent.");
                }
            }
        }
    }


}