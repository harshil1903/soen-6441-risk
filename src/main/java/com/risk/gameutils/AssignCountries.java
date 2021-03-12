package com.risk.gameutils;

import com.risk.exception.InvalidMapException;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;
import com.risk.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static com.risk.gameutils.GenerateRandomNumber.getRandomNumber;
import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;

/**
 * AssignCountries helps in assigning random countries to players.
 *
 * @author Chirag
 */
public class AssignCountries {


    /**
     * helps to fill arraylist with countries present in map.
     *
     * @param p_map contains map data
     * @return filled arraylist of countries
     * @throws InvalidMapException if Map data is not present.
     */
    public static ArrayList<Country> fillCountryList(Map p_map) throws InvalidMapException {
        ArrayList<Country> l_countries = new ArrayList<>();
        for (Continent l_continent : p_map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                l_countries.add(l_country);
            }
        }
        return l_countries;
    }

    /**
     * gets a random country form the list and removes it.
     *
     * @param p_countries holds the list of all countries yet to assigned.
     * @return the randomly selected Country.
     */
    public static Country getRandomCountry(ArrayList<Country> p_countries) {
        Country l_country = null;
        if (p_countries.size() != 0) {
            int l_num = getRandomNumber(p_countries.size());
            l_country = p_countries.get(l_num);
            p_countries.remove(l_num);
        }
        return l_country;
    }

    /**
     * assign random countries to players
     *
     * @param p_list      contains list of players.
     * @param p_countries contains list of countries.
     * @param p_hashMap   contains no of countries left to assign to that player.
     */
    public static void assignRandomCountries(ArrayList<Player> p_list, ArrayList<Country> p_countries,
                                             HashMap<Integer, Integer> p_hashMap) {

        for (int i = 0; i < p_list.size(); i++) {
            int l_frequency = p_hashMap.get(i);
            while (l_frequency != 0) {
                Country l_randomCountry = getRandomCountry(p_countries);
                p_list.get(i).addCountryToAssignedCountries(l_randomCountry);

                for (Continent l_continent : d_Map.getD_Continents()) {
                    for (Country l_country : l_continent.getD_Countries()) {
                        if (l_country.getD_CountryName().equals(l_randomCountry.getD_CountryName())) {
                            l_country.setD_Player(p_list.get(i));
                        }
                    }
                }
                l_frequency--;
            }
        }
        while (p_countries.size() != 0) {
            Country l_randomCountry = getRandomCountry(p_countries);
            int l_playerID = getRandomNumber(p_list.size());
            p_list.get(l_playerID).addCountryToAssignedCountries(l_randomCountry);
        }
    }

    /**
     * assigns countries to the player.
     * Method to be called and used in GameEngine.
     *
     * @throws InvalidMapException if Map is not present.
     */
    public static void assignCountries() throws InvalidMapException {
        ArrayList<Country> l_countries = fillCountryList(d_Map);
        HashMap<Integer, Integer> l_map = new HashMap<>();
        for (int i = 0; i < d_PlayerList.size(); i++) {
            l_map.put(i, l_countries.size() / d_PlayerList.size());
        }
        assignRandomCountries(d_PlayerList, l_countries, l_map);
        System.out.println("Assigned countries randomly to players");
    }
}
