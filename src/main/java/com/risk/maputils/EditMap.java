package com.risk.maputils;

import com.risk.models.Map;

/**
* This class loads a map from an existing domination map file,
* or if file doesn't exits creates a new map from scratch.
* @author Chirag
*/

public class EditMap {
    // Object map that will be returned after processing.
    private Map c_Map;

    /**
     * Default constructor
     */
    public EditMap(){}

    /**
     * Parameterized constructor
     * @param p_map provides the map for loading
     */
    public EditMap(Map p_map) {
        this.c_Map = p_map;
    }

    /**
     * return map object after loading or creating if doesn't exist.
     * @return the map
     */
    private Map GetMap(){
        return c_Map;
    }
}
















