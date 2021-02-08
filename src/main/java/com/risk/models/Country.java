package com.risk.models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines Country and its properties such as the continent to which it
 * belongs, its adjacent Countries and whether its been assigned to any player or not.
 * @author Harshil
 */
public class Country {
    private int d_countryID;
    private String d_countryName;
    private Continent d_belongToContinent;
    private Player d_player;
    private List<Country> d_adjacentCountries;
    private boolean d_isProcessed = false;
    //private List<Integer> d_adjCountries;


    /**
     * Default Constructor
     */
    public Country() {
        //d_adjCountries = new ArrayList<>();
        d_adjacentCountries = new ArrayList<>();
    }

    /**
     * @param p_countryID List of adjacent territories names
     * @param p_adjacentCountries List of object of adjacent territories
     */
    public Country(int p_countryID, List<Country> p_adjacentCountries) {
        d_countryID = p_countryID;
        d_adjacentCountries = p_adjacentCountries;
    }


}
