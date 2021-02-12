package com.risk.maputils;
import com.risk.models.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import com.risk.models.Continent;

/**
* This class loads a map from an existing domination map file,
* or if file doesn't exits creates a new map from scratch.
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
     * @param p_MapReader Scanner object that helps to read map file.
     */
    public void GetContinets(Scanner p_MapReader) {
        //Countinent Id is auto incremented.
        int l_Continet_Id = 1;
        while (p_MapReader.hasNextLine()) {
            String l_Line = p_MapReader.nextLine();
            //System.out.println(line);
            if (l_Line.equals("")) break;
            String[] parts = l_Line.split(" ");
            Continent continent = new Continent(l_Continet_Id, parts[0], Integer.parseInt(parts[1]));
            d_Map.d_Continents.add(continent);
            l_Continet_Id++;
        }

    }

    /**
     * This Method reads Countries from map file and add it to list in Continent.
     * @param p_MapReader
     */

    public static void GetCountries(Scanner p_MapReader) {
    }

    /**
     * This Method reads Adjacent Countries to a country and it to Adjacency countries list.
     * @param p_MapReader
     */
    public static void GetAdj(Scanner p_MapReader) {
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
                    GetAdj(MapReader);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Map not Found" + e);
        }

    }
}