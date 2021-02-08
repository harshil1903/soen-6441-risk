package com.risk.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;


/**
 * This class defines Map and its list of continents.
 * @author Harshil
 * @see Country
 * @see Continent
 */
public class Map extends Observable {

    private HashMap<String, String> d_mapData;
    private List<Continent> d_continents;
    private HashMap<String, Continent> d_continentMap;

    public Map() {
        d_mapData = new HashMap<String, String>();
        d_continents = new ArrayList<Continent>();
        d_continentMap = new HashMap<String, Continent>();
    }
}