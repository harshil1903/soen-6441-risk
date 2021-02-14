package com.risk.maputils;

import com.risk.exception.InvalidMapException;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * The class MapWriter will write the data in map file when the user creates a map.
 *
 * @author Rishabh
 */

public class MapWriter {

    /**
     * Below method writes the map details to map file
     *
     * @param p_Map  object of the map which is being processed..
     * @param p_file file path
     */

    public void writeMapFile(Map p_Map, File p_file) {

        FileWriter fileWriter;
        try {
            if (p_Map == null) {
                System.out.println("Map object is NULL! ");
            }

            String l_content = parseMapAndReturnString(p_Map);
            fileWriter = new FileWriter(p_file, false);
            fileWriter.write(l_content);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Below method processes the map by calling three different methods and makes a string to be written in map file.
     *
     * @param p_map object of the map which is processed
     * @return String to be written in the map file
     */

    private String parseMapAndReturnString(Map p_map) {

        StringBuilder l_content = new StringBuilder();
        l_content = processMapAttribute(p_map);
        l_content.append(processContinent(p_map));
        l_content.append(processCountries(p_map));
        return l_content.toString();
    }

    /**
     * Below method processes map attributes.
     *
     * @param p_map object of the map is being processed
     * @return a String that contains map properties.
     **/

    private StringBuilder processMapAttribute(Map p_map) {
        StringBuilder l_mapAttribute = new StringBuilder();
        l_mapAttribute.append("[Map]");
        l_mapAttribute.append("\n");

        for (java.util.Map.Entry<String, String> keymap : p_map.getD_MapData().entrySet()) {
            l_mapAttribute.append(keymap.getKey() + "=" + keymap.getValue());
            l_mapAttribute.append("\n");
        }
        return l_mapAttribute;
    }

    /**
     * Below method processes the continents.
     *
     * @param p_Map object of the map which is being processed
     * @return a string that contains details of the continents that will eventually be written in the map file.
     * great
     */

    private StringBuilder processContinent(Map p_Map) {
        StringBuilder l_continentData = new StringBuilder();
        l_continentData.append("\n");
        l_continentData.append("[Continents]");
        l_continentData.append("\n");
        for (Continent l_continent : p_Map.getD_Continents()) {
            l_continentData.append(l_continent.getD_ContinentName() + "=" + l_continent.getD_ContinentValue());
            l_continentData.append("\n");
        }
        return l_continentData;
    }

    /**
     * Below method is for processing countries.
     *
     * @param p_Map object of the map that is being processed
     * @return a string that contains details of countries that will ultimately be written in the map file.
     */

    private StringBuilder processCountries(Map p_Map) {
        StringBuilder l_countryData = new StringBuilder();
        l_countryData.append("\n");
        l_countryData.append("[Countries]");
        l_countryData.append("\n");

        for (Continent l_continent : p_Map.getD_Continents()) {
            List<Country> countryList = l_continent.getD_Countries();
            if (countryList != null) {
                for (Country l_country : countryList) {
                    l_countryData.append(l_country.getD_CountryName() + "," + l_country.getD_BelongToContinent().getD_ContinentName());
                    for (Country l_adjacentCountries : l_country.getD_AdjacentCountries()) {
                        l_countryData.append(",");
                        l_countryData.append(l_adjacentCountries.getD_CountryName());
                    }
                    l_countryData.append("\n");
                }
                l_countryData.append("\n");
            }
            l_countryData.append("\n");
        }
        return l_countryData;
    }
}