package com.risk.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;


/**
 * This class defines Map and its list of continents.
 *
 * @author Harshil
 * @see Country
 * @see Continent
 */
public class Map extends Observable {

    private HashMap<String, String> d_MapData;
    private List<Continent> d_Continents;
    private HashMap<String, Continent> d_ContinentMap;

    /**
     * Default Constructor
     */
    public Map() {
        d_MapData = new HashMap<String, String>();
        d_Continents = new ArrayList<Continent>();
        d_ContinentMap = new HashMap<String, Continent>();
    }


    /**
     * Gets map data in a HashMap format.
     *
     * @return map data
     */
    public HashMap<String, String> getD_MapData() {
        return d_MapData;
    }

    /**
     * Sets map data in a HashMap format
     *
     * @param p_MapData map data
     */
    public void setD_MapData(HashMap<String, String> p_MapData) {
        d_MapData = p_MapData;
    }

    /**
     * Gets a list of continents of the map.
     *
     * @return continent list
     */
    public List<Continent> getD_Continents() {
        return d_Continents;
    }

    /**
     * Sets a list of continent to the map.
     *
     * @param p_Continents continent list
     */
    public void setD_Continents(List<Continent> p_Continents) {
        d_Continents = p_Continents;
    }

    /**
     * Gets a HashMap of continent and map.
     *
     * @return continentMap HashMap
     */
    public HashMap<String, Continent> getD_ContinentMap() {
        return d_ContinentMap;
    }

    /**
     * Sets a HashMap of continent and map
     *
     * @param p_ContinentMap continentMap HasHMap
     */
    public void setD_ContinentMap(HashMap<String, Continent> p_ContinentMap) {
        d_ContinentMap = p_ContinentMap;
    }
}