package com.risk.maputils;

import com.risk.exception.InvalidMapException;
import com.risk.models.Map;

import java.io.File;

/**
 * This class loads a map from an existing domination map file,
 * or if file doesn't exits creates a new map from scratch.
 *
 * @author Chirag
 */
public class EditMap {

    /**
     * Map variable that will hold map data when map is loaded.
     */
    public static Map d_Map = new Map();

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
        d_Map = p_map;
    }

    /**
     * return map object after loading or creating if doesn't exist.
     *
     * @return the map
     */
    private Map getMap() {
        return d_Map;
    }


    /**
     * This Method loads map and process it accordingly.
     *
     * @param p_fileName Filename to be loaded if already present or to be created if not.
     * @return the Map object with loaded values.
     * @throws InvalidMapException if map is not valid.
     */
    public static Map editMap(String p_fileName) throws InvalidMapException {
        String l_path = "src/main/resources/";
        String l_fileName = p_fileName + ".map";
        d_Map.d_mapName=p_fileName;
        File l_map = new File(l_path + l_fileName);
        if (l_map.exists())
            return LoadMap.loadMap(l_map, d_Map);
        return CreateMap.createMap(d_Map);
    }
}