package com.risk.gameutils;

import com.risk.exception.InvalidMapException;
import com.risk.maputils.EditMap;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

import java.util.ArrayList;
import java.util.Random;

/**
 * AssignCountires helps in assigning random countries to players.
 *
 * @author Chirag
 */
public class AssignCountries {
    /**
     * generates random number between zero and p_High-1.
     *
     * @param p_High upper bound for random number generation.
     * @return random number generated.
     */
    public static int getRandomNumber(int p_High) {
        Random l_Random = new Random();
        int l_Low = 0;
        int l_Number = l_Random.nextInt(p_High - l_Low) + l_Low;
        return l_Number;
    }

    /**
     * helps to fill arraylist with countries present in map.
     *
     * @param p_Map contains map data
     * @return filled arraylist of countries
     * @throws InvalidMapException if Map data is not present.
     */
    public static ArrayList<Country> fillCountryList(Map p_Map) throws InvalidMapException {
        ArrayList<Country> l_Countries = new ArrayList<>();
        for (Continent l_Continent : p_Map.getD_Continents()) {
            for (Country l_Country : l_Continent.getD_Countries()) {
                l_Countries.add(l_Country);
            }
        }
        return l_Countries;
    }

    /**
     * assigns random countries to the players.
     *
     * @throws InvalidMapException if Map is not present.
     */
    public static void assignCountries() throws InvalidMapException {
        Map d_Map = new EditMap().EditMap();
        ArrayList<Country> countries = fillCountryList(d_Map);
    }
}
