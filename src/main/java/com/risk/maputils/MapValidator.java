package com.risk.maputils;

import java.util.*;

import com.risk.exception.InvalidMapException;
import com.risk.models.Map;
import com.risk.models.Continent;
import com.risk.models.Country;
import jdk.jfr.internal.tool.PrettyWriter;

import javax.smartcardio.TerminalFactory;


/**
 * This class performs validation of map correctness, also after loading
 * and saving any new map the map should be validated automatically.
 * @author HARSH
 */


public class MapValidator {

    static String AlertMessage ="";

    /**
     * This methodis used for validaion of map.
     * @param map refers to the map object for verification.
     * @throws InvalidMapException is used for the situation where map has certain errors.
     */

    public static void validateMap(Map map) throws InvalidMapException {
        if(map==null){
            throw new InvalidMapException("The Map is not valid, it does not contain any content.");
        } else{
            if(map.getD_Continents().size()<1){
                throw new InvalidMapException("There should be atleast one Continent in the graph.");
            } else{
                //A map must be subgraph of continents and if it is true then the continents must be subgraph of countries.
                //This method will validate whether the map is subgraph of continents or not.
                validateContinents(map);

                //This method will check whether if the map is subgraph of continents.
                if(!isMapConnectedGraph(map)){
                    throw new InvalidMapException("The map is not a connected graph i.e Continent is not a subgraph in the map. A map should be connected graph formed by continents.");
                }

                //validate whether the country belongs to only one continent.
                validateCountryBelongsToOneContinent(map);
            }

        }

    }

    /**
     * This method is used to validate the continents.
     * @param map map refers to map object to validate the continents.
     * @throws InvalidMapException throws exception if map has any continent related errors.
     */

    public static void validateContinents(Map map) throws InvalidMapException{

        for(Continent continent : map.getD_Continents()){
            if(continent.getD_Countries().size() < 1){
                throw new InvalidMapException("There should be atleast one country in any continent.")
            }
            //At this point it is validated that map is subgraph of continents, now validate whether continent is subgraph of countries.
            for(Country country : continent.getD_Countries()){
                validateCountry(country,map);
            }

            //This method will check whether if the continent is a subgraph of countries.
            if(!isContinentConnectedGraph(continent,map)){
                throw new InvalidMapException(message+"The Continent:- "+continent.getD_ContinentName()+" is not connected by its neighbouring countries.A Continent must be connected graph formed by its neighbouring countries in the map.");
            }
        }
    }


    /**
     * This method is used for validating that whether the continents are connected or not.
     * @param continent is the continent to be validated.
     * @map is the current map object.
     * @return will return true if the continent is connected in the map.
     */

    public static boolean isContinentConnectedGraph(Continent continent,Map map){
        bfsTraversalCountry(continent.getD_Countries().get(0),map);
        boolean d_returnValue = true;
        for(Country c : continent.getD_Countries()){
            if(c.isD_IsProcessed() == false){
                c.setD_Processed(false);
                AlertMessage = c.getD_CountryName()+"country is not forming connected graph in the continent"+continent.getD_ContinentName()+".";
                d_returnValue = false;
                break;
            }
        }

        for(Country c : continent.getD_Countries()){
            c.setD_Processed(false);
        }
        return d_returnValue;
    }

    /**
     *This method performs the traversal of the countries in BFS manner.
     * @param country refers to the country that is to be travversed.
     * @map refers to the map object.
     */

    public static void bfsTraversalCountry(Country country,Map map){
        if(country.isD_IsProcessed() == true){
            return;
        }

        country.setD_Processed(true);

        for(Country c : country.getD_AdjacentCountries()){
            if((c.getD_BelongToContinent() == country.getD_BelongToContinent()) && c.isD_IsProcessed() == false)
                bfsTraversalCountry(c,map);
        }

    }

    /**
     * This method is used to validate that the country is connected or not.
     * @param country refers to the country that is to be verified.
     * @map refers to the map object.
     * @throws InvalidMapException throws exception if the map is not valid.
     */

    public static void validateCountry(Country country,Map map) throws new InvalidMapException{
        List<Country> adjCountryList = country.getD_AdjacentCountries();

        if((adjCountryList == null) && (adjCountryList.size()<1)){
            throw new InvalidMapException("Country: "+country.getD_CountryName()+" must have atleast one adjacent country.");
        }
        else{
            for(Country adjCountry : adjCountryList){
                if(!adjCountry.getD_AdjacentCountries().contains(country)){
                    //add the passed country to the list of adjacent countries if it is not connected to its adjacent country
                    adjCountry.getD_AdjacentCountries().add(country);
                }
            }
        }

    }

    /**
     * This method is used to validate that whether the continents in the map form a graph or not.
     * @param map refers to the map object
     * @return returns true if it the map is connected graph.
     */

    public static boolean isMapConnectedGraph(Map map){
        System.out.println("The map is connected");
        if(map.getD_Continents().size()<2){
            return false;
        }

        bfsTraversalContinent(map.getD_Continents().get(0),map);

        boolean d_returnvalue = true;
        for(Continent continent : map.getD_Continents()){
            if(continent.isD_IsVisited() == false){
                System.out.println(continent.getD_ContinentName()+"XXXXXX");
                d_returnvalue = false;
                break;
            }
        }

        for(Continent continent : map.getD_Continents()){
            continent.setD_Visited(false);
        }
        return d_returnvalue;
    }

    /**
     * This method performs the traversal of continents in BFS manner.
     * @param continent refers to the continent to be traversed.
     * @map refers to the map object
     */

    public static void bfsTraversalContinent(Continent continent,Map map){
        if(continent.isD_IsVisited()==true){
            return;
        }

        continent.setD_Visited(true);

        System.out.println("continent in bfs"+continent.getD_ContinentName());
        for(Continent c : getAdjacentContinents(continent,map)){
            System.out.println("Control is inside the adjacent continents loop");
            if(c.isD_IsVisited() == false)
                bfsTraversalContinent(c,map);
        }

    }

    /**
     * This method is used to return the adjacent continents as a list for any particular continent.
     * @param continent refers to the continent whose adjacent continents are to be found.
     * @param map refers to the map object.
     * @return will return the list of adjacent continents of the current continent.
     */

    public static List<Continent> getAdjacentContinents(Continent continent, Map map){
        List<Continent> adjacentContinents = new ArrayList<>();

        HashSet<Country> adjCountryMainSet = new HashSet<>();
        for(Country country : continent.getD_Countries()){
            adjCountryMainSet.addAll(country.getD_AdjacentCountries());
        }

        System.out.println(adjCountryMainSet);

        for(Continent remainingContinent : map.getD_Continents()){
            if(!continent.equals(remainingContinent)){
                //this will be processed if there is any relation between two continents.
                //also it will return true ony if both the continents are different.
                if(!Collections.disjoint(adjCountryMainSet,remainingContinent.getD_Countries())){
                    System.out.println ("Inside the disjoint method");
                    //There are some countries which are common.
                    adjacentContinents.add(remainingContinent);

                }
            }
        }
        return adjacentContinents;
    }

    



}