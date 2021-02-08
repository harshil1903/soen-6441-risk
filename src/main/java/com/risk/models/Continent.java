package com.risk.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * This class defines Continent and its properties such as Name, control value, list of Countries it has
 * @author Harshil
 */
public class Continent {
    private int d_continentID;
    private String d_continentName;
    private int d_continentvalue;
    private List<Country> d_countries;
    private boolean d_isVisited = false;
    //private HashMap<String, Country> d_countryMap;

    /**
     * Default Constructor
     */
    public Continent() {
        this.d_countries = new ArrayList<>();
        //this.d_countryMap = new HashMap<>();
    }

    /**
     * @param p_continentName  Name of the continent
     * @param p_continentvalue control value of the continent
     */
    public Continent(String p_continentName, int p_continentvalue) {
        super();
        d_continentName = p_continentName;
        d_continentvalue = p_continentvalue;
        this.d_countries = new ArrayList<>();
        //this.d_countryMap = new HashMap<>();
    }


}