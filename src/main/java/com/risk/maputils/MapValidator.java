package com.risk.maputils;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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


}
