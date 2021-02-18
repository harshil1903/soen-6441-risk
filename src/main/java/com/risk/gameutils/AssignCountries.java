package com.risk.gameutils;

import com.risk.exception.InvalidMapException;
import com.risk.maputils.EditMap;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;
import com.risk.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
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
     * gets a random country form the list and removes it.
     *
     * @param p_Countries holds the list of all countries yet to assigned.
     * @return the randomly selected Country.
     */
    public static Country getRandomCountry(ArrayList<Country> p_Countries) {
        Country l_Country = null;
        if (p_Countries.size() != 0) {
            int l_Num = getRandomNumber(p_Countries.size());
            l_Country = p_Countries.get(l_Num);
            int cid = l_Country.getD_CountryID();
            p_Countries.remove(l_Num);
        }
        return l_Country;
    }

    /**
     * assign random countries to players
     *
     * @param p_List      contains list of players.
     * @param p_Countries contains list of countries.
     * @param p_HashMap   contains no of countries left to assign to that player.
     */
    public static void assignRandomCountries(ArrayList<Player> p_List, ArrayList<Country> p_Countries,
                                             HashMap<Integer, Integer> p_HashMap) {

        for (int i = 0; i < p_List.size(); i++) {
            int l_Frequency = p_HashMap.get(i);
            while (l_Frequency != 0) {
                Country l_RandomCountry = getRandomCountry(p_Countries);
                p_List.get(i).addCountryToAssignedCountries(l_RandomCountry);
                l_Frequency--;
            }
        }
        while (p_Countries.size() != 0) {
            Country l_RandomCountry = getRandomCountry(p_Countries);
            int playerId = getRandomNumber(p_List.size());
            p_List.get(playerId).addCountryToAssignedCountries(l_RandomCountry);
        }
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
