package com.risk.maputils;

import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * The class MapWriter will write the map file when the user creates a map.
 * @author Rishabh
 */

public class MapWriter {
    /**
     * Below method writes the map details to map file,
     * @param map object of the map which is being processed..
     * @param file file path
     */
    public void writeMapFile(Map map, File file){

        FileWriter fileWriter;
        try {
            if (map == null){
                System.out.println("Map object is NULL! ");
            }

            String content = parseMapAndReturnString(map);
            fileWriter = new FileWriter(file, false);
            fileWriter.write(content);
            fileWriter.close();
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * this method processes the map by calling three different methods and makes a string to be written in map file.
     * @param map object of the map which is processed
     * @return String to be written in the map file
     */

    private String parseMapAndReturnString(Map map) {

        StringBuilder content = new StringBuilder();
        content = processMapAttribute(map);
        content.append(processContinent(map));
        content.append(processCountries(map));
        return content.toString();
    }
    /**
     * This method processes map attributes.
     * @param map object of the map is being processed
     * @return a String that contains map properties.
     **/

    private StringBuilder processMapAttribute(Map map){
        StringBuilder mapAttribute = new StringBuilder();
        mapAttribute.append("[Map]");
        mapAttribute.append("\n");

        for(java.util.Map.Entry<String, String> keymap: map.getD_MapData().entrySet()) {
            mapAttribute.append(keymap.getKey() + "=" + keymap.getValue());
            mapAttribute.append("\n");
        }
        return mapAttribute;
    }

    /**
     * This method processes the continents.
     * @param map object of the map which is being processed
     * @return a string that contains details of the continents that will eventually be written in the map file.
     * Changes in this method.
     */

    private StringBuilder processContinent(Map map){
        StringBuilder continentData = new StringBuilder();
        continentData.append("\n");
        continentData.append("[Continents]");
        continentData.append("\n");
        for (Continent continent : map.getD_Continents()){
            continentData.append(continent.getD_ContinentName() + "=" + continent.getD_ContinentValue());
            continentData.append("\n");
        }
        return continentData;
    }

    /**
     * This method is for processing countries.
     * @param map object of the map that is being processed
     * @return a string that contains details of countries that will ultimately be written in the map file.
     */

    private StringBuilder processCountries(Map map){
        StringBuilder countryData = new StringBuilder();
        countryData.append("\n");
        countryData.append("[Countries]");
        countryData.append("\n");

        /*for (Continent continent : map.getD_Continents()){
            List<Country> countryList = continent.getD_Countries();
            if (countryList != null){
                for (Country country : countryList){
                    countryData.append(country.getD_CountryName() + "," + country.getD_BelongToContinent().getD_ContinentName());

                    for (Country adjacentCountries : country.getD_AdjacentCountries()){
                        countryData.append(",");
                        countryData.append(adjacentCountries.getD_CountryName());
                    }
                    countryData.append("\n");
                }
                countryData.append("\n");
            }
            countryData.append("\n");
        }*/
        return countryData;
    }
}