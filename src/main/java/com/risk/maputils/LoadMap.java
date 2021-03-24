package com.risk.maputils;

import com.risk.exception.InvalidMapException;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.risk.main.Main.d_Log;

/**
 * Loads the data from the map file and stores it in a variable
 *
 * @author Chirag
 */
public class LoadMap {
    /**
     * This Method reads Continents from map file and add it to d_Map variable.
     *
     * @param p_mapReader Scanner object that helps to read map file.
     * @throws InvalidMapException if map is not valid.
     */
    public static void getContinents(Scanner p_mapReader, Map d_Map) throws InvalidMapException {
        int l_continentID = 1;
        while (p_mapReader.hasNextLine()) {
            String l_line = p_mapReader.nextLine();
            if (l_line.equals("")) break;
            String[] l_parts = l_line.split(" ");
            String l_continentName = l_parts[0];
            int l_controlValue = Integer.parseInt(l_parts[1]);
            Continent l_continent = new Continent(l_continentID, l_continentName, l_controlValue);
            d_Map.addContinentToContinentList(l_continent);
            l_continentID++;
        }
    }

    /**
     * This method gets country from d_Map.
     *
     * @param p_countryID id of the country.
     * @return the country object for used in GetCountries.
     */
    public static Country getCountry(int p_countryID, Map d_Map) {
        for (Continent l_continent : d_Map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                if (l_country.getD_CountryID() == p_countryID) {
                    return l_country;
                }
            }
        }
        return null;
    }

    /**
     * This Method reads Countries from map file and add it to list in Continent.
     *
     * @param p_mapReader Scanner objects that helps to read the map.
     * @throws InvalidMapException if map is not valid.
     */
    public static void getCountries(Scanner p_mapReader, Map d_Map) throws InvalidMapException {
        while (p_mapReader.hasNextLine()) {
            String l_line = p_mapReader.nextLine();
            if (l_line.equals("")) break;
            String[] l_parts = l_line.split(" ");
            int l_continentID = Integer.parseInt(l_parts[2]);
            Continent l_continent = d_Map.getContinentFromContinentList(l_continentID);
            int l_countryID = Integer.parseInt(l_parts[0]);
            String l_countryName = l_parts[1];
            Country l_country = new Country(l_countryID, l_countryName, l_continentID);
            l_country.setD_BelongToContinent(l_continent);
            l_country.setD_ContinentName(l_continent.getD_ContinentName());
            l_continent.addCountryToCountryList(l_country);
        }
    }

    /**
     * This Method reads Adjacent Countries to a country and it to Adjacency countries list.
     *
     * @param p_mapReader Scanner object that helps to read map.
     * @throws InvalidMapException if map is not valid.
     */
    public static void getAdjacentCountries(Scanner p_mapReader, Map d_Map) throws InvalidMapException {
        while (p_mapReader.hasNextLine()) {
            String l_line = p_mapReader.nextLine();
            if (l_line.equals("")) break;
            String[] l_parts = l_line.split(" ");
            int l_countryID = Integer.parseInt(l_parts[0]);
            Country l_country = getCountry(l_countryID,d_Map);
            for (int i = 1; i < l_parts.length; i++) {
                int l_neighbourID = Integer.parseInt(l_parts[i]);
                Country l_neighbour = getCountry(l_neighbourID,d_Map);
                l_country.addCountryToAdjacentCountries(l_neighbour);
            }
        }
    }

    /**
     * This Method Loads the Map passed to it.
     *
     * @param p_map Reads the map file.
     */
    public static Map loadMap(File p_map, Map d_Map) {
        Scanner l_mapReader = null;
        try {
            l_mapReader = new Scanner(p_map);
            while (l_mapReader.hasNextLine()) {
                String l_line = l_mapReader.nextLine();
                if (l_line.equals("[continents]")) {
                    getContinents(l_mapReader, d_Map);
                }
                if (l_line.equals("[countries]")) {
                    getCountries(l_mapReader, d_Map);
                }
                if (l_line.equals("[borders]")) {
                    getAdjacentCountries(l_mapReader, d_Map);
                }
            }
            System.out.println("Loaded map successfully form existing domination file");
            d_Log.notify("Loaded map successfully from existing domination file");
        } catch (FileNotFoundException | InvalidMapException e) {

        }
        return d_Map;
    }
}
