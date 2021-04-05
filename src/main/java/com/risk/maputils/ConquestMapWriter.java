package com.risk.maputils;


import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.risk.main.Main.d_Log;

/**
 * Helps in writing Map data back to conquest map file format
 *
 * @author Chirag
 */



public class ConquestMapWriter {

    /**
     * This method processes the map by calling three different methods and makes a string to be written in map file.
     *
     * @param p_Map object of the map which is processed
     * @return String to be written in the map file
     */
    private static String parseConquestMapAndReturnString(Map p_Map) {

        StringBuilder l_content = new StringBuilder();
        l_content.append(processConquestContinent(p_Map));
        l_content.append(processTerritories(p_Map));
        l_content.append(processAdjacentTerritories(p_Map));
        return l_content.toString();
    }


    /**
     * This method processes the continents for Conquest.
     *
     * @param p_Map object of the map which is being processed
     * @return a string that contains details of the continents that will eventually be written in the map file.
     */
    private static StringBuilder processConquestContinent(Map p_Map){
        StringBuilder l_continentData = new StringBuilder();
        l_continentData.append("[Continents]");
        l_continentData.append("\n");
        for (Continent l_continent : p_Map.getD_Continents()) {
            l_continentData.append(l_continent.getD_ContinentName() + "=" + l_continent.getD_ContinentValue());
            l_continentData.append("\n");
        }
        return l_continentData;
    }


    /**
     * This method is for processing countries.
     *
     * @param p_Map object of the map that is being processed
     * @return a string that contains details of countries that will ultimately be written in the map file.
     */
    private static StringBuilder processTerritories(Map p_Map) {

        return null;
    }


    /**
     * This method is for processing adjacent countries.
     *
     * @param p_Map object of map that is being processed.
     * @return a string that contains adjacent countries and that will be written in map file.
     */
    private static StringBuilder processAdjacentTerritories(Map p_Map) {
        return null;
    }



    /**
     * This method writes the map details to map file
     *
     * @param p_map  object of the map which is being processed.
     * @param p_file object of file to store map data.
     */
    public static void writeConquestMapFile(Map p_map, File p_file) {

        FileWriter l_fileWriter;
        try {
            if (p_map == null) {
                System.out.println("Map object is NULL! ");
            }

            String l_content = parseConquestMapAndReturnString(p_map);
            l_fileWriter = new FileWriter(p_file, false);
            l_fileWriter.write(l_content);
            l_fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }



}
