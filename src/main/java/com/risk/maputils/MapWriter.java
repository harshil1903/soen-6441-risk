package com.risk.maputils;

import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * The class MapWriter will write the data in map file when the user creates a map
 * @author Rishabh
 */

public class MapWriter {

    /**
     * This method writes the map details to map file
     *
     * @param p_Map  object of the map which is being processed.
     * @param p_file file path
     */

    public void writeMapFile(Map p_Map, File p_file) {

        FileWriter fileWriter;
        try {
            if (p_Map == null) {
                System.out.println("Map object is NULL! ");
            }

            String l_Content = parseMapAndReturnString(p_Map);
            fileWriter = new FileWriter(p_file, false);
            fileWriter.write(l_Content);
            fileWriter.close();
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

    private String parseMapAndReturnString(Map p_Map) {

        StringBuilder l_Content = new StringBuilder();
        l_Content = processMapAttribute(p_Map);
        l_Content.append(processContinent(p_Map));
        l_Content.append(processCountries(p_Map));
        return l_Content.toString();
    }

    /**
     * This method processes map attributes.
     *
     * @param p_Map object of the map is being processed
     * @return a String that contains map properties.
     **/

    private StringBuilder processMapAttribute(Map p_Map) {
        StringBuilder l_MapAttribute = new StringBuilder();
        l_MapAttribute.append("[Map]");
        l_MapAttribute.append("\n");

        for (java.util.Map.Entry<String, String> keymap : p_Map.getD_MapData().entrySet()) {
            l_MapAttribute.append(keymap.getKey() + "=" + keymap.getValue());
            l_MapAttribute.append("\n");
        }
        return l_MapAttribute;
    }

    /**
     * This method processes the continents.
     *
     * @param p_Map object of the map which is being processed
     * @return a string that contains details of the continents that will eventually be written in the map file.
     * great
     */

    private StringBuilder processContinent(Map p_Map) {
        StringBuilder l_ContinentData = new StringBuilder();
        l_ContinentData.append("\n");
        l_ContinentData.append("[continents]");
        l_ContinentData.append("\n");
        for (Continent l_Continent : p_Map.getD_Continents()) {
            l_ContinentData.append(l_Continent.getD_ContinentName() + "=" + l_Continent.getD_ContinentValue());
            l_ContinentData.append("\n");
        }
        return l_ContinentData;
    }

    /**
     * This method is for processing countries.
     *
     * @param p_Map object of the map that is being processed
     * @return a string that contains details of countries that will ultimately be written in the map file.
     */

    private StringBuilder processCountries(Map p_Map) {
        StringBuilder l_CountryData = new StringBuilder();
        l_CountryData.append("\n");
        l_CountryData.append("[countries]");
        l_CountryData.append("\n");

        for (Continent l_Continent : p_Map.getD_Continents()) {
            List<Country> countryList = l_Continent.getD_Countries();
            if (countryList != null) {
                for (Country l_country : countryList) {
                    l_CountryData.append(l_country.getD_CountryID() + "," + l_country.getD_CountryName() + "," + l_country.getD_BelongToContinent().getD_ContinentName());
                    for (Country l_adjacentCountries : l_country.getD_AdjacentCountries()) {
                        l_CountryData.append(",");
                        l_CountryData.append(l_adjacentCountries.getD_CountryName());
                    }
                    l_CountryData.append("\n");
                }
                l_CountryData.append("\n");
            }
            l_CountryData.append("\n");
        }
        return l_CountryData;
    }
}