package com.risk.maputils;

import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static com.risk.main.Main.d_Log;

/**
 * The class MapWriter will write the data in map file when the user creates a map
 *
 * @author Rishabh
 */

public class MapWriter {

    /**
     * This method writes the map details to map file
     *
     * @param p_map  object of the map which is being processed.
     * @param p_file object of file to store map data.
     */

    public static void writeMapFile(Map p_map, File p_file) {

        FileWriter l_fileWriter;
        try {
            if (p_map == null) {
                System.out.println("Map object is NULL! ");
                d_Log.notify("Map object is NULL! ");
            }

            String l_content = parseMapAndReturnString(p_map);
            l_fileWriter = new FileWriter(p_file, false);
            l_fileWriter.write(l_content);
            l_fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * This method processes the map by calling three different methods and makes a string to be written in map file.
     *
     * @param p_Map object of the map which is processed
     * @return String to be written in the map file
     */
    private static String parseMapAndReturnString(Map p_Map) {

        StringBuilder l_content = new StringBuilder();
        l_content.append(processContinent(p_Map));
        l_content.append(processCountries(p_Map));
        l_content.append(processAdjacentCountries(p_Map));
        return l_content.toString();
    }


    /**
     * This method processes the continents.
     *
     * @param p_Map object of the map which is being processed
     * @return a string that contains details of the continents that will eventually be written in the map file.
     */
    private static StringBuilder processContinent(Map p_Map) {
        StringBuilder l_continentData = new StringBuilder();
        l_continentData.append("[continents]");
        l_continentData.append("\n");
        for (Continent l_continent : p_Map.getD_Continents()) {
            l_continentData.append(l_continent.getD_ContinentName() + " " + l_continent.getD_ContinentValue());
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
    private static StringBuilder processCountries(Map p_Map) {
        StringBuilder l_countryData = new StringBuilder();
        l_countryData.append("\n");
        l_countryData.append("[countries]");
        l_countryData.append("\n");

        for (Continent l_continent : p_Map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                l_countryData.append(l_country.getD_CountryID() + " " + l_country.getD_CountryName() + " " + l_continent.getD_ContinentID());
                l_countryData.append("\n");
            }
        }
        return l_countryData;
    }

    /**
     * This method is for processing adjacent countries.
     *
     * @param p_Map object of map that is being processed.
     * @return a string that contains adjacent countries and that will be written in map file.
     */
    private static StringBuilder processAdjacentCountries(Map p_Map) {
        StringBuilder l_countryData = new StringBuilder();
        l_countryData.append("\n");
        l_countryData.append("[borders]");
        l_countryData.append("\n");
        for (Continent l_continent : p_Map.getD_Continents()) {
            List<Country> l_countryList = l_continent.getD_Countries();
            if (l_countryList != null) {
                for (Country l_country : l_countryList) {
                    l_countryData.append(l_country.getD_CountryID());
                    for (Country l_adjacentCountries : l_country.getD_AdjacentCountries()) {
                        l_countryData.append(" ");
                        l_countryData.append(l_adjacentCountries.getD_CountryID());
                    }
                    l_countryData.append("\n");
                }
            }
        }
        return l_countryData;
    }
}