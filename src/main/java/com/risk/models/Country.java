package com.risk.models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class defines Country and its properties such as
 * the continent to which it belongs, its adjacent Countries and whether its been assigned to any player or not.
 *
 * @author Harshil
 */
public class Country {
    private int d_CountryID;
    private String d_CountryName;
    private Continent d_BelongToContinent;
    private Player d_Player;
    private List<Country> d_AdjacentCountries;
    private boolean d_IsProcessed = false;
    //private List<Integer> d_adjCountries;


    /**
     * Default Constructor
     */
    public Country() {
        d_AdjacentCountries = new ArrayList<>();
    }

    /**
     * Instantiates a new Country.
     *
     * @param p_CountryID         List of adjacent territories names
     * @param p_AdjacentCountries List of object of adjacent territories
     */
    public Country(int p_CountryID, List<Country> p_AdjacentCountries) {
        d_CountryID = p_CountryID;
        d_AdjacentCountries = p_AdjacentCountries;
    }

    /**
     * Gets Country ID.
     *
     * @return The Country ID
     */
    public int getD_CountryID() {
        return d_CountryID;
    }


    /**
     * Sets Country ID.
     *
     * @param p_CountryID Country ID
     */
    public void setD_CountryID(int p_CountryID) {
        d_CountryID = p_CountryID;
    }

    /**
     * Gets Country name.
     *
     * @return The Country Name
     */
    public String getD_CountryName() {
        return d_CountryName;
    }

    /**
     * Sets Country name.
     *
     * @param p_CountryName Country Name
     */
    public void setD_CountryName(String p_CountryName) {
        d_CountryName = p_CountryName;
    }

    /**
     * Gets Continent to which country belongs to
     *
     * @return Continent of the country
     */
    public Continent getD_BelongToContinent() {
        return d_BelongToContinent;
    }

    /**
     * Sets Continent to which country belongs to
     *
     * @param p_BelongToContinent Continent of the country
     */
    public void setD_BelongToContinent(Continent p_BelongToContinent) {
        d_BelongToContinent = p_BelongToContinent;
    }

    /**
     * Gets Player to which country belongs to
     *
     * @return Player who owns the country
     */
    public Player getD_Player() {
        return d_Player;
    }

    /**
     * Sets Player to which country belongs to
     *
     * @param p_Player Player who owns the country
     */
    public void setD_Player(Player p_Player) {
        d_Player = p_Player;
    }

    /**
     * Gets a list adjacent countries.
     *
     * @return adjacent countries list
     */
    public List<Country> getD_AdjacentCountries() {
        return d_AdjacentCountries;
    }

    /**
     * Sets a list of adjacent countries.
     *
     * @param p_AdjacentCountries adjacent countries list
     */
    public void setD_AdjacentCountries(List<Country> p_AdjacentCountries) {
        d_AdjacentCountries = p_AdjacentCountries;
    }

    /**
     * To check whether country has been distributed to any player or not.
     *
     * @return whether country is processed or not
     */
    public boolean isD_IsProcessed() {
        return d_IsProcessed;
    }

    /**
     * To notify that that country had been assigned to a player.
     *
     * @param p_IsProcessed whether country is processed or not
     */
    public void setD_IsProcessed(boolean p_IsProcessed) {
        d_IsProcessed = p_IsProcessed;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object p_Object) {

        if (!(p_Object instanceof Country)) {
            return false;
        }

        if (p_Object == this) {
            return true;
        }

        Country l_Country = (Country) p_Object;
        return l_Country.getD_CountryName().equalsIgnoreCase(d_CountryName);
    }
}
