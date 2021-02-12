package com.risk.maputils;

import com.risk.exception.InvalidMapException;
import com.risk.models.Country;
import com.risk.models.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.risk.models.Continent;

/**
 * This class loads a map from an existing domination map file,
 * or if file doesn't exits creates a new map from scratch.
 *
 * @author Chirag
 */

public class EditMap {
    // Object map that will be returned after processing.
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
        this.d_Map = p_map;
    }

    /**
     * return map object after loading or creating if doesn't exist.
     *
     * @return the map
     */
    private Map GetMap() {
        return d_Map;
    }

    /**
     * This Method reads Continents from map file and add it to d_Map variable.
     *
     * @param p_MapReader Scanner object that helps to read map file.
     */
    public void GetContinets(Scanner p_MapReader) {
        int l_Continet_Id = 1;
        while (p_MapReader.hasNextLine()) {
            String l_Line = p_MapReader.nextLine();
            if (l_Line.equals("")) break;
            String[] l_Parts = l_Line.split(" ");
            Continent continent = new Continent(l_Continet_Id, l_Parts[0], Integer.parseInt(l_Parts[1]));
            d_Map.d_Continents.add(continent);
            l_Continet_Id++;
        }

    }

    /**
     * This method gets country from d_Map.
     *
     * @param p_Country_Id id of the country.
     * @return the country object for used in GetCountries.
     */
    public static Country GetCountry(int p_Country_Id) {
        for (Continent l_Continent : d_Map.d_Continents) {
            for (Country l_Country : l_Continent.d_Countries) {
                if (l_Country.d_CountryID == p_Country_Id) {
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
     */

    public static void GetCountries(Scanner p_MapReader) {
        while (p_MapReader.hasNextLine()) {
            String l_Line = p_MapReader.nextLine();
            if (l_Line.equals("")) break;
            String[] l_Parts = l_Line.split(" ");
            int continent_id = Integer.parseInt(l_Parts[3]);
            int l_Continent_Id = Integer.parseInt(l_Parts[2]);
            Continent l_Continent = d_Map.d_Continents.get(l_Continent_Id - 1);
            Country l_Country = new Country(Integer.parseInt(l_Parts[0]), l_Parts[1], Integer.parseInt(l_Parts[2]));
            l_Continent.d_Countries.add(l_Country);
        }
    }


    /**
     * This Method reads Adjacent Countries to a country and it to Adjacency countries list.
     *
     * @param p_MapReader Scanner object that helps to read map.
     */

    public static void GetAdjacentCountries(Scanner p_MapReader) {
        while (p_MapReader.hasNextLine()) {
            String l_Line = p_MapReader.nextLine();
            if (l_Line.equals("")) break;
            String[] l_Parts = l_Line.split(" ");
            int l_Base = Integer.parseInt(l_Parts[0]);
            Country l_Country = GetCountry(l_Base);
            for (int i = 1; i < l_Parts.length; i++) {
                int l_Adjacent_Id = Integer.parseInt(l_Parts[i]);
                Country l_Neighbour = GetCountry(l_Adjacent_Id);
                l_Country.d_AdjacentCountries.add(l_Neighbour);
            }

        }
    }

    /**
     * This Method loads map and process it accordingly.
     */

    public void EditMap() {
        try {
            File Map = new File("../soen-6441-risk/src/main/resources/europe.map");
            Scanner MapReader = new Scanner(Map);
            while (MapReader.hasNextLine()) {
                String l_Line = MapReader.nextLine();
                if (l_Line.equals("[continents]")) {
                    GetContinets(MapReader);
                }
                if (l_Line.equals("[countries]")) {
                    GetCountries(MapReader);
                }
                if (l_Line.equals("[borders]")) {
                    GetAdjacentCountries(MapReader);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Map not Found" + e);
        }

    }
} 