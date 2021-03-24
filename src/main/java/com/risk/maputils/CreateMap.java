package com.risk.maputils;

import com.risk.models.Map;
/**
 * Creates a new map from scratch if not present
 */
public class CreateMap {
    /**
     * This Method Creates a new map if map is not present.
     */
    public static Map createMap(Map p_Map) {
        p_Map = new Map();
        System.out.println("Map file not presented will be created from scratch");
        p_Map.d_isEmpty = true;
        return p_Map;
    }
}
