package com.risk.maputils;
import com.risk.models.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import com.risk.models.Continent;
import com.risk.models.Country;
import java.io.FileInputStream;


/**
* This class loads a map from an existing domination map file,
* or if file doesn't exits creates a new map from scratch.
* @author Chirag
*/

public class EditMap {
    // Object map that will be returned after processing.
    private Map d_Map;

    /**
     * Default constructor
     */
    public EditMap() {
    }

    /**
     * Parameterized constructor
     *
     * @param p_map provides the map for loading
     */
    public EditMap(Map p_map) {
        this.d_Map = p_map;
    }

    /**
     * return map object after loading or creating if doesn't exist.
     *
     * @return the map
     */
    private Map GetMap() {
        return d_Map;
    }
}
