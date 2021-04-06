package com.risk.maputils;


import com.risk.models.Map;

/**
 * Adapter class for LoadMap that helps in accepting conquest file format map.
 *
 * @author Chirag
 */
public class LoadMapAdapter extends LoadMap {
    public static Map d_map = new Map();

    private LoadConquestMap d_loadConquestMap;

    /**
     * Parameterized constructor for LoadMapAdapter.
     *
     * @param p_loadConquestMap object of LoadConquestMap
     */
    public LoadMapAdapter(LoadConquestMap p_loadConquestMap) {
        this.d_loadConquestMap = p_loadConquestMap;
    }


}
