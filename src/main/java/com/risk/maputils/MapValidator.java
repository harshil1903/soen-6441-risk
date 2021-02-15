package com.risk.maputils;

import java.util.*;

import com.risk.exception.InvalidMapException;
import com.risk.models.Map;
import com.risk.models.Continent;
import com.risk.models.Country;



/**
 * This class performs validation of map correctness, also after loading
 * and saving any new map the map should be validated automatically.
 * @author HARSH
 */


public class MapValidator {

    static String d_AlertMessage ="";

    /**
     * This methodis used for validaion of map.
     * @param p_Map refers to the map object for verification.
     * @throws InvalidMapException is used for the situation where map has certain errors.
     */

    public static void validateMap(Map p_Map) throws InvalidMapException {
        if(p_Map==null){
            throw new InvalidMapException("The Map is not valid, it does not contain any content.");
        } else{
            if(p_Map.getD_Continents().size()<1){
                throw new InvalidMapException("There should be atleast one Continent in the graph.");
            } else{
                //A map must be subgraph of continents and if it is true then the continents must be subgraph of countries.
                //This method will validate whether the map is subgraph of continents or not.
                validateContinents(p_Map);

                //This method will check whether if the map is subgraph of continents.
                if(!isMapConnectedGraph(p_Map)){
                    throw new InvalidMapException("The map is not a connected graph i.e Continent is not a subgraph in the map. A map should be connected graph formed by continents.");
                }

                //validate whether the country belongs to only one continent.
                validateCountryBelongsToOneContinent(p_Map);
            }

        }

    }

    /**
     * This method is used to validate the continents.
     * @param p_Map map refers to map object to validate the continents.
     * @throws InvalidMapException throws exception if map has any continent related errors.
     */

    public static void validateContinents(Map p_Map) throws InvalidMapException{

        for(Continent l_continent : p_Map.getD_Continents()){
            if(l_continent.getD_Countries().size() < 1){
                throw new InvalidMapException("There should be atleast one country in any continent.");
            }
            //At this point it is validated that map is subgraph of continents, now validate whether continent is subgraph of countries.
            for(Country l_country : l_continent.getD_Countries()){
                validateCountry(l_country,p_Map);
            }

            //This method will check whether if the continent is a subgraph of countries.
            if(!isContinentConnectedGraph(l_continent,p_Map)){
                throw new InvalidMapException("The Continent:- "+l_continent.getD_ContinentName()+" is not connected by its neighbouring countries.A Continent must be connected graph formed by its neighbouring countries in the map.");
            }
        }
    }


    /**
     * This method is used for validating that whether the continents are connected or not.
     * @param p_continent is the continent to be validated.
     * @map is the current map object.
     * @return will return true if the continent is connected in the map.
     */

    public static boolean isContinentConnectedGraph(Continent p_Continent,Map p_Map){
        bfsTraversalCountry(p_Continent.getD_Countries().get(0),p_Map);
        boolean l_returnValue = true;
        for(Country c : p_Continent.getD_Countries()){
            if(c.isD_IsProcessed() == false){
                c.setD_Processed(false);    //The Continent won't be connected only when any of the country is not being visited.
                d_AlertMessage = c.getD_CountryName()+"country is not forming connected graph in the continent"+p_Continent.getD_ContinentName()+".";
                l_returnValue = false;
                break;
            }
        }

        //This loop sets the value setD_Processed for all the countries to false for future usage of validation

        for(Country c : p_Continent.getD_Countries()){
            c.setD_Processed(false);
        }
        return l_returnValue;
    }

    /**
     *This method performs the traversal of the countries in BFS manner.
     * @param country refers to the country that is to be travversed.
     * @map refers to the map object.
     */

    public static void bfsTraversalCountry(Country p_Country,Map p_Map){
        if(p_Country.isD_IsProcessed() == true){
            return;
        }

        p_Country.setD_Processed(true);


        for(Country c : p_Country.getD_AdjacentCountries()){
            if((c.getD_BelongToContinent() == p_Country.getD_BelongToContinent()) && c.isD_IsProcessed() == false)
                bfsTraversalCountry(c,p_Map);
        }

    }

    /**
     * This method is used to validate that the country is connected or not.
     * @param country refers to the country that is to be verified.
     * @map refers to the map object.
     * @throws InvalidMapException throws exception if the map is not valid.
     */

    public static void validateCountry(Country p_Country,Map p_Map) throws InvalidMapException{
        List<Country> l_adjCountryList = p_Country.getD_AdjacentCountries();

        if((l_adjCountryList == null) && (l_adjCountryList.size()<1)){
            throw new InvalidMapException("Country: "+p_Country.getD_CountryName()+" must have atleast one adjacent country.");
        }
        else{
            for(Country l_adjCountry : l_adjCountryList){
                if(!l_adjCountry.getD_AdjacentCountries().contains(p_Country)){
                    //add the passed country to the list of adjacent countries if it is not connected to its adjacent country
                    l_adjCountry.getD_AdjacentCountries().add(p_Country);
                }
            }
        }

    }

    /**
     * This method is used to validate that whether the continents in the map form a graph or not.
     * @param map refers to the map object
     * @return returns true if it the map is connected graph.
     */

    public static boolean isMapConnectedGraph(Map p_Map){
        System.out.println("The map is connected");
        if(p_Map.getD_Continents().size()<2){
            return false;
        }

        bfsTraversalContinent(p_Map.getD_Continents().get(0),p_Map);

        boolean l_returnvalue = true;
        for(Continent l_continent : p_Map.getD_Continents()){
            if(l_continent.isD_IsVisited() == false){
                System.out.println(l_continent.getD_ContinentName()+"XXXXXX");
                l_returnvalue = false;
                break;
            }
        }

        for(Continent l_continent : p_Map.getD_Continents()){
            l_continent.setD_Visited(false);
        }
        return l_returnvalue;
    }

    /**
     * This method performs the traversal of continents in BFS manner.
     * @param continent refers to the continent to be traversed.
     * @map refers to the map object
     */

    public static void bfsTraversalContinent(Continent p_Continent,Map p_Map){
        if(p_Continent.isD_IsVisited()==true){
            return;
        }

        p_Continent.setD_Visited(true);

        System.out.println("continent in bfs"+p_Continent.getD_ContinentName());
        for(Continent c : getAdjacentContinents(p_Continent,p_Map)){
            System.out.println("Control is inside the adjacent continents loop");
            if(c.isD_IsVisited() == false)
                bfsTraversalContinent(c,p_Map);
        }

    }

    /**
     * This method is used to return the adjacent continents as a list for any particular continent.
     * @param continent refers to the continent whose adjacent continents are to be found.
     * @param map refers to the map object.
     * @return will return the list of adjacent continents of the current continent.
     */

    public static List<Continent> getAdjacentContinents(Continent p_Continent, Map p_Map){
        List<Continent> l_adjacentContinents = new ArrayList<>();

        HashSet<Country> l_adjCountryMainSet = new HashSet<>(); ////hash set allows unique value only
        for(Country l_country : p_Continent.getD_Countries()){
            l_adjCountryMainSet.addAll(l_country.getD_AdjacentCountries()); //all unique adjacent countries of entire continent will be added to this list
        }

        System.out.println(l_adjCountryMainSet);

        for(Continent l_remainingContinent : p_Map.getD_Continents()){
            if(!p_Continent.equals(l_remainingContinent)){
                //this will be processed if there is any relation between two continents.
                //also it will return true ony if both the continents are different.
                //if there are some countries common between two continents i.e they are connected i.e disjoint set is not formed then that continent is adjacent continent.
                if(!Collections.disjoint(l_adjCountryMainSet,l_remainingContinent.getD_Countries())){
                    System.out.println ("Inside the disjoint method");
                    //There are some countries which are common.
                    l_adjacentContinents.add(l_remainingContinent);

                }
            }
        }
        return l_adjacentContinents;
    }

    /**
     * This method is used to check whether the country is belonging to only one continent or not.
     * @param map refers to map object.
     * @throws InvalidMapException throws InvalidMapException if map is not valid.
     */
    public static void validateCountryBelongsToOneContinent(Map p_Map) throws InvalidMapException{
        HashMap<Country, Integer> l_countryBelongsToContinentCount = new HashMap<>();

        for(Continent l_continent : p_Map.getD_Continents()){
            for(Country l_country : l_continent.getD_Countries()){
                if(!l_countryBelongsToContinentCount.containsKey(l_country)){
                    l_countryBelongsToContinentCount.put(l_country,1);
                }else{
                    throw new InvalidMapException("Country:"+l_country.getD_CountryName()+"must belong to only one continent.");
                }
            }
        }
    }




}