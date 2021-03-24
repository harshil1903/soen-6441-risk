package com.risk.maputils;

import com.risk.exception.InvalidMapException;
import com.risk.models.Country;
import com.risk.models.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.risk.models.Continent;

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
     * This Method reads Continents from map file and add it to d_Map variable.
     *
     * @param p_mapReader Scanner object that helps to read map file.
     * @throws InvalidMapException if map is not valid.
     */
    public static void getContinents(Scanner p_mapReader) throws InvalidMapException {
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
    public static Country getCountry(int p_countryID) {
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
    public static void getCountries(Scanner p_mapReader) throws InvalidMapException {
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
    public static void getAdjacentCountries(Scanner p_mapReader) throws InvalidMapException {
        while (p_mapReader.hasNextLine()) {
            String l_line = p_mapReader.nextLine();
            if (l_line.equals("")) break;
            String[] l_parts = l_line.split(" ");
            int l_countryID = Integer.parseInt(l_parts[0]);
            Country l_country = getCountry(l_countryID);
            for (int i = 1; i < l_parts.length; i++) {
                int l_neighbourID = Integer.parseInt(l_parts[i]);
                Country l_neighbour = getCountry(l_neighbourID);
                l_country.addCountryToAdjacentCountries(l_neighbour);
            }
        }
    }

    /**
     * This Method Loads the Map passed to it.
     *
     * @param p_map Reads the map file.
     */
    public static void loadMap(File p_map) {
        Scanner l_mapReader = null;
        try {
            l_mapReader = new Scanner(p_map);
            while (l_mapReader.hasNextLine()) {
                String l_line = l_mapReader.nextLine();
                if (l_line.equals("[continents]")) {
                    getContinents(l_mapReader);
                }
                if (l_line.equals("[countries]")) {
                    getCountries(l_mapReader);
                }
                if (l_line.equals("[borders]")) {
                    getAdjacentCountries(l_mapReader);
                }
            }
            System.out.println("Loaded map successfully form existing domination file");
        } catch (FileNotFoundException | InvalidMapException e) {
            createMap();
        }

    }

    /**
     * This Method Creates a new map if map is not present.
     */
    public static void createMap() {
        d_Map = new Map();
        System.out.println("Map file not presented will be created from scratch");
        d_Map.d_isEmpty = true;
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
        File l_map = new File(l_path + l_fileName);
        loadMap(l_map);
        return d_Map;
    }
}