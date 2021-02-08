package com.risk.models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines Country and its properties such as the continent to which it
 * belongs, its adjacent Countries and whether its been assigned to any player or not.
 *
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
     * Instantiates a new Country.
     *
     * @param p_countryID         List of adjacent territories names
     * @param p_adjacentCountries List of object of adjacent territories
     */
    public Country(int p_countryID, List<Country> p_adjacentCountries) {
        d_countryID = p_countryID;
        d_adjacentCountries = p_adjacentCountries;
    }

    /**
     * Gets Country ID.
     *
     * @return The Country ID
     */
    public int getD_countryID() {
        return d_countryID;
    }


    /**
     * Sets Country ID.
     *
     * @param p_countryID Country ID
     */
    public void setD_countryID(int p_countryID) {
        d_countryID = p_countryID;
    }

    /**
     * Gets Country name.
     *
     * @return The Country Name
     */
    public String getD_countryName() {
        return d_countryName;
    }

    /**
     * Sets Country name.
     *
     * @param p_countryName Country Name
     */
    public void setD_countryName(String p_countryName) {
        d_countryName = p_countryName;
    }

    /**
     * Gets Continent to which country belongs to
     *
     * @return Continent of the country
     */
    public Continent getD_belongToContinent() {
        return d_belongToContinent;
    }

    /**
     * Sets Continent to which country belongs to
     *
     * @param p_belongToContinent Continent of the country
     */
    public void setD_belongToContinent(Continent p_belongToContinent) {
        d_belongToContinent = p_belongToContinent;
    }

    /**
     * Gets Player to which country belongs to
     *
     * @return Player who owns the country
     */
    public Player getD_player() {
        return d_player;
    }

    /**
     * Sets Player to which country belongs to
     *
     * @param p_player Player who owns the country
     */
    public void setD_player(Player p_player) {
        d_player = p_player;
    }

    /**
     * Gets a list adjacent countries.
     *
     * @return adjacent countries list
     */
    public List<Country> getD_adjacentCountries() {
        return d_adjacentCountries;
    }

    /**
     * Sets a list of adjacent countries.
     *
     * @param p_adjacentCountries adjacent countries list
     */
    public void setD_adjacentCountries(List<Country> p_adjacentCountries) {
        d_adjacentCountries = p_adjacentCountries;
    }

    /**
     * To check whether country has been distributed to any player or not.
     *
     * @return whether country is processed or not
     */
    public boolean isD_isProcessed() {
        return d_isProcessed;
    }

    /**
     * To notify that that country had been assigned to a player.
     *
     * @param p_isProcessed whether country is processed or not
     */
    public void setD_isProcessed(boolean p_isProcessed) {
        d_isProcessed = p_isProcessed;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object p_object) {

        if (!(p_object instanceof Country)) {
            return false;
        }

        if (p_object == this) {
            return true;
        }

        Country l_country = (Country) p_object;
        return l_country.getD_countryName().equalsIgnoreCase(d_countryName);
    }
}
