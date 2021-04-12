package com.risk.maputils;


import com.risk.exception.InvalidMapException;
import com.risk.models.Map;

import java.io.File;

/**
 * Adapter class for LoadMap that helps in accepting conquest file format map.
 *
 * @author Chirag
 */
public class EditMapAdapter extends EditMap {

    private static EditConquestMap d_editConquestMap;

    /**
     * Parameterized constructor for LoadMapAdapter.
     *
     * @param p_editConquestMap object of LoadConquestMap
     */
    public EditMapAdapter(EditConquestMap p_editConquestMap) {
        this.d_editConquestMap = p_editConquestMap;
    }

    /**
     * Overridden editMap method of EditMap class
     *
     * @param p_fileName File name of the conquest file
     * @return Map pbject to be used in the game
     * @throws InvalidMapException if Map is invalid.
     */
    public static Map editMap(String p_fileName) throws InvalidMapException {

        String l_path = "src/main/resources/";
        String l_fileName = p_fileName+".map";
        File l_map = new File(l_path + l_fileName);
        if (l_map.exists())
            return d_editConquestMap.loadConquestMap(p_fileName);
        return d_editConquestMap.createConquestMap();
    }
}
