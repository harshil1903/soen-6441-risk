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
     * @param p_MapReader Scanner object that helps to read map file.
     * @throws InvalidMapException if map is not valid.
     */
    public static void getContinents(Scanner p_MapReader) throws InvalidMapException {
        int l_Continent_Id = 1;
        while (p_MapReader.hasNextLine()) {
            String l_Line = p_MapReader.nextLine();
            if (l_Line.equals("")) break;
            String[] l_Parts = l_Line.split(" ");
            String l_ContinentName = l_Parts[0];
            int l_ControlValue = Integer.parseInt(l_Parts[1]);
            Continent l_Continent = new Continent(l_Continent_Id, l_ContinentName, l_ControlValue);
            d_Map.addContinentToContinentList(l_Continent);
            l_Continent_Id++;
        }
    }

    /**
     * This method gets country from d_Map.
     *
     * @param p_Country_Id id of the country.
     * @return the country object for used in GetCountries.
     */
    public static Country getCountry(int p_Country_Id) {
        for (Continent l_Continent : d_Map.getD_Continents()) {
            for (Country l_Country : l_Continent.getD_Countries()) {
                if (l_Country.getD_CountryID() == p_Country_Id) {
                    return l_Country;
                }
            }
        }
        return null;
    }

    /**
     * This Method reads Countries from map file and add it to list in Continent.
     *
     * @param p_MapReader Scanner objects that helps to read the map.
     * @throws InvalidMapException if map is not valid.
     */
    public static void getCountries(Scanner p_MapReader) throws InvalidMapException {
        while (p_MapReader.hasNextLine()) {
            String l_Line = p_MapReader.nextLine();
            if (l_Line.equals("")) break;
            String[] l_Parts = l_Line.split(" ");
            int l_Continent_Id = Integer.parseInt(l_Parts[2]);
            Continent l_Continent = d_Map.getContinentFromContinentList(l_Continent_Id);
            int l_Country_Id = Integer.parseInt(l_Parts[0]);
            String l_CountryName = l_Parts[1];
            int l_ContinentId = Integer.parseInt(l_Parts[2]);
            Country l_Country = new Country(l_Country_Id, l_CountryName, l_ContinentId);
            l_Continent.addCountryToCountryList(l_Country);
        }
    }

    /**
     * This Method reads Adjacent Countries to a country and it to Adjacency countries list.
     *
     * @param p_MapReader Scanner object that helps to read map.
     * @throws InvalidMapException if map is not valid.
     */
    public static void getAdjacentCountries(Scanner p_MapReader) throws InvalidMapException {
        while (p_MapReader.hasNextLine()) {
            String l_Line = p_MapReader.nextLine();
            if (l_Line.equals("")) break;
            String[] l_Parts = l_Line.split(" ");
            int l_Country_Id = Integer.parseInt(l_Parts[0]);
            Country l_Country = getCountry(l_Country_Id);
            for (int i = 1; i < l_Parts.length; i++) {
                int l_Neighbour_Id = Integer.parseInt(l_Parts[i]);
                Country l_Neighbour = getCountry(l_Neighbour_Id);
                l_Country.addCountryToAdjacentCountries(l_Neighbour);
            }
        }
    }

    /**
     * This Method Loads the Map passed to it.
     *
     * @param p_Map Reads the map file.
     */
    public static void loadMap(File p_Map) {
        Scanner MapReader = null;
        try {
            MapReader = new Scanner(p_Map);
            while (MapReader.hasNextLine()) {
                String l_Line = MapReader.nextLine();
                if (l_Line.equals("[continents]")) {
                    getContinents(MapReader);
                }
                if (l_Line.equals("[countries]")) {
                    getCountries(MapReader);
                }
                if (l_Line.equals("[borders]")) {
                    getAdjacentCountries(MapReader);
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
    }

    /**
     * This Method loads map and process it accordingly.
     *
     * @param p_FileName Filename to be loaded if already present or to be created if not.
     * @return the Map object with loaded values.
     * @throws InvalidMapException if map is not valid.
     */
    public static Map editMap(String p_FileName) throws InvalidMapException {
        String l_Path = "src/main/resources/";
        String l_FileName = p_FileName + ".map";
        File l_Map = new File(l_Path + l_FileName);
        loadMap(l_Map);
        return d_Map;
    }
}