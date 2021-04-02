package com.risk.maputils;

import com.risk.exception.InvalidMapException;
import com.risk.models.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.risk.main.Main.d_Log;

/**
 * Helps in Loading the Conquest Format Map file
 *
 * @author Chirag
 */
public class LoadConquestMap {


    /**
     * This Method reads Continents from map file and add it to p_Map variable.
     *
     * @param p_mapReader Scanner object that helps to read map file.
     * @param p_map       Stores data read from p_mapReader
     * @throws InvalidMapException if map is not valid.
     */
    private static void getConquestContinents(Scanner p_mapReader, Map p_map) {
    }

    /**
     * Helps in Loading Adjacent Countries to the Map
     *
     * @param p_mapReader Scanner objects that helps to read the map.
     * @param p_map       Stores data read from p_mapReader
     */
    private static void getConquestAdjacentCountries(Scanner p_mapReader, Map p_map) {
    }

    /**
     * This Method reads Countries from map file and add it to list in Continent.
     *
     * @param p_mapReader Scanner objects that helps to read the map.
     * @param p_map       Stores data read from p_mapReader
     * @throws InvalidMapException if map is not valid.
     */
    private static void getConquestCountries(Scanner p_mapReader, Map p_map) {
    }


    /**
     * This Method Loads the Conquest Map passed to it.
     *
     * @param p_map Reads the map file.
     * @param p_Map Stores data read from p_mapReader
     * @return the Map object with loaded values.
     */
    public static Map loadConquestMap(File p_map, Map p_Map) {
        Scanner l_mapReader = null;
        try {
            l_mapReader = new Scanner(p_map);
            while (l_mapReader.hasNextLine()) {
                String l_line = l_mapReader.nextLine();
                if (l_line.equals("[continents]")) {
                    getConquestContinents(l_mapReader, p_Map);
                }
                if (l_line.equals("[countries]")) {
                    getConquestCountries(l_mapReader, p_Map);
                }
                if (l_line.equals("[borders]")) {
                    getConquestAdjacentCountries(l_mapReader, p_Map);
                }
            }
            System.out.println("Loaded map successfully form existing conquest file");

        } catch (FileNotFoundException e) {

        }
        return p_Map;
    }

}