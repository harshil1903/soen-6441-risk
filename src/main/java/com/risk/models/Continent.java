package com.risk.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * This class defines Continent and its properties such as Name, control value, list of Countries it has
 *
 * @author Harshil
 */
public class Continent {
    private int d_continentID;
    private String d_continentName;
    private int d_continentValue;
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
     * Instantiates a new Continent.
     *
     * @param p_continentName  Name of the continent
     * @param p_continentValue control value of the continent
     */
    public Continent(String p_continentName, int p_continentValue) {
        super();
        d_continentName = p_continentName;
        d_continentValue = p_continentValue;
        this.d_countries = new ArrayList<>();
        //this.d_countryMap = new HashMap<>();
    }

    /**
     * Gets continent id.
     *
     * @return continent id
     */
    public int getD_continentID() {
        return d_continentID;
    }

    /**
     * Sets continent id.
     *
     * @param p_continentID continent id
     */
    public void setD_continentID(int p_continentID) {
        d_continentID = p_continentID;
    }

    /**
     * Gets continent name.
     *
     * @return continent name
     */
    public String getD_continentName() {
        return d_continentName;
    }

    /**
     * Sets continent name.
     *
     * @param p_continentName continent name
     */
    public void setD_continentName(String p_continentName) {
        d_continentName = p_continentName;
    }

    /**
     * Gets control value of continent
     *
     * @return continent control value
     */
    public int getD_continentValue() {
        return d_continentValue;
    }

    /**
     * Sets control value of continent
     *
     * @param p_continentValue continent control value
     */
    public void setD_continentValue(int p_continentValue) {
        d_continentValue = p_continentValue;
    }

    /**
     * Gets list of countries in the continent.
     *
     * @return countries list
     */
    public List<Country> getD_countries() {
        return d_countries;
    }

    /**
     * Sets list of countries to the continent.
     *
     * @param p_countries countries list
     */
    public void setD_countries(List<Country> p_countries) {
        d_countries = p_countries;
    }

    /**
     * Checks if continent is visited or not
     *
     * @return whether continent is visited or not
     */
    public boolean isD_isVisited() {
        return d_isVisited;
    }

    /**
     * Sets visited value of continent
     *
     * @param p_isVisited whether continent is visited or not
     */
    public void setD_isVisited(boolean p_isVisited) {
        d_isVisited = p_isVisited;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object p_object) {

        if (!(p_object instanceof Continent)) {
            return false;
        }

        if (p_object == this) {
            return true;
        }

        Continent l_continent = (Continent) p_object;
        return l_continent.getD_continentName().equalsIgnoreCase(d_continentName);
    }
}