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

    private HashMap<String, String> d_mapData;
    private List<Continent> d_continents;
    private HashMap<String, Continent> d_continentMap;

    /**
     * Default Constructor
     */
    public Map() {
        d_mapData = new HashMap<String, String>();
        d_continents = new ArrayList<Continent>();
        d_continentMap = new HashMap<String, Continent>();
    }


    /**
     * Gets map data in a HashMap format.
     *
     * @return map data
     */
    public HashMap<String, String> getD_mapData() {
        return d_mapData;
    }

    /**
     * Sets map data in a HashMap format
     *
     * @param p_mapData map data
     */
    public void setD_mapData(HashMap<String, String> p_mapData) {
        d_mapData = p_mapData;
    }

    /**
     * Gets a list of continents of the map.
     *
     * @return continent list
     */
    public List<Continent> getD_continents() {
        return d_continents;
    }

    /**
     * Sets a list of continent to the map.
     *
     * @param p_continents continent list
     */
    public void setD_continents(List<Continent> p_continents) {
        d_continents = p_continents;
    }

    /**
     * Gets a HashMap of continent and map.
     *
     * @return continentMap HashMap
     */
    public HashMap<String, Continent> getD_continentMap() {
        return d_continentMap;
    }

    /**
     * Sets a HashMap of continent and map
     *
     * @param p_continentMap continentMap HasHMap
     */
    public void setD_continentMap(HashMap<String, Continent> p_continentMap) {
        d_continentMap = p_continentMap;
    }
}