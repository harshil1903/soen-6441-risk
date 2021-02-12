package com.risk.maputils;
import com.risk.exception.InvalidMapException;
import com.risk.models.Country;
import com.risk.models.Map;
import java.util.Scanner;
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
     *
     * @param p_MapReader Scanner object that helps to read map file.
     */
    public void GetContinets(Scanner p_MapReader) throws InvalidMapException {
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
     * This method gets country from d_Map.
     *
     * @param p_Country_Id id of the country.
     * @return the country object for used in GetCountries.
     */
    public static Country getCountry(int p_Country_Id) {
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
     * @param p_MapReader
     */

    public static void GetCountries(Scanner p_MapReader) {
        while (p_MapReader.hasNextLine()) {
            String line = p_MapReader.nextLine();
            if (line.equals("")) break;
            String[] parts = line.split(" ");
            int continent_id = Integer.parseInt(parts[3]);
            int conti_id = Integer.parseInt(parts[2]);
            Continent continent = d_Map.d_Continents.get(conti_id - 1);
            Country country = new Country(Integer.parseInt(parts[0]), parts[1], Integer.parseInt(parts[2]));
            continent.d_Countries.add(country);
        }
    }


    /**
     * This Method reads Adjacent Countries to a country and it to Adjacency countries list.
     *
     * @param p_MapReader
     */

    public static void GetAdj(Scanner p_MapReader) {
    }
}