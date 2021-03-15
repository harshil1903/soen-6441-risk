package com.risk.models;

import java.util.ArrayList;
import java.util.List;

import static com.risk.main.Main.d_Map;

/**
 * This class defines Country and its properties such as
 * the continent to which it belongs, its adjacent Countries and whether its been assigned to any player or not.
 *
 * @author Harshil
 */
public class Country {
    private int d_countryID;
    private int d_continentID;
    private String d_continentName;
    private String d_countryName;
    private Continent d_belongToContinent;
    private Player d_player;
    private List<Country> d_adjacentCountries;
    private boolean d_isProcessed = false;
    private int d_numberOfArmies;

    /**
     * Default Constructor
     */
    public Country() {
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
     * Parameterized constructor which instantiates a new country
     *
     * @param p_countryID   Country ID
     * @param p_countryName Country Name
     * @param p_continentID Continent ID
     */
    public Country(int p_countryID, String p_countryName, int p_continentID) {

        d_countryID = p_countryID;
        d_countryName = p_countryName;
        d_continentID = p_continentID;
        d_adjacentCountries = new ArrayList<>();

    }

    /**
     * Gets Country ID.
     *
     * @return The Country ID
     */
    public int getD_CountryID() {
        return d_countryID;
    }


    /**
     * Sets Country ID.
     *
     * @param p_countryID Country ID
     */
    public void setD_CountryID(int p_countryID) {
        d_countryID = p_countryID;
    }


    /**
     * Gets continent id.
     *
     * @return Continent ID
     */
    public int getD_ContinentID() {
        return d_continentID;
    }

    /**
     * Sets continent id.
     *
     * @param p_continentID Continent ID
     */
    public void setD_ContinentID(int p_continentID) {
        d_continentID = p_continentID;
    }

    /**
     * Gets continent name.
     *
     * @return continent name
     */
    public String getD_ContinentName() {
        return d_continentName;
    }

    /**
     * Sets continent name.
     *
     * @param d_continentName continent name
     */
    public void setD_ContinentName(String d_continentName) {
        this.d_continentName = d_continentName;
    }

    /**
     * Gets Country name.
     *
     * @return The Country Name
     */
    public String getD_CountryName() {
        return d_countryName;
    }

    /**
     * Sets Country name.
     *
     * @param p_countryName Country Name
     */
    public void setD_CountryName(String p_countryName) {
        d_countryName = p_countryName;
    }

    /**
     * Gets Continent to which country belongs to
     *
     * @return Continent of the country
     */
    public Continent getD_BelongToContinent() {
        return d_belongToContinent;
    }

    /**
     * Sets Continent to which country belongs to
     *
     * @param p_belongToContinent Continent of the country
     */
    public void setD_BelongToContinent(Continent p_belongToContinent) {
        d_belongToContinent = p_belongToContinent;
    }

    /**
     * Gets Player to which country belongs to
     *
     * @return Player who owns the country
     */
    public Player getD_Player() {
        return d_player;
    }

    /**
     * Sets Player to which country belongs to
     *
     * @param p_player Player who owns the country
     */
    public void setD_Player(Player p_player) {
        d_player = p_player;
    }

    /**
     * Gets a list adjacent countries.
     *
     * @return adjacent countries list
     */
    public List<Country> getD_AdjacentCountries() {
        return d_adjacentCountries;
    }

    /**
     * Sets a list of adjacent countries.
     *
     * @param p_adjacentCountries adjacent countries list
     */
    public void setD_AdjacentCountries(List<Country> p_adjacentCountries) {
        d_adjacentCountries = p_adjacentCountries;
    }

    /**
     * To check whether country is processed or not.
     *
     * @return d_IsProcessed returns boolean value whether country is processed or not
     */
    public boolean isD_IsProcessed() {
        return d_isProcessed;
    }

    /**
     * To set the country as processed.
     *
     * @param p_isProcessed is used to set that the country is processed.
     */
    public void setD_Processed(boolean p_isProcessed) {
        d_isProcessed = p_isProcessed;
    }

    /**
     * Gets number of armies deployed by the player
     *
     * @return number of armies
     */
    public int getD_NumberOfArmies() {
        return d_numberOfArmies;
    }

    /**
     * Sets number of armies to be deployed in the country.
     *
     * @param p_numberOfArmies number of armies
     */
    public void setD_NumberOfArmies(int p_numberOfArmies) {
        d_numberOfArmies = p_numberOfArmies;
    }


    /**
     * Gets country object from country id.
     *
     * @param p_countryID country id
     * @return the country object
     */
    public Country getCountryFromCountryID(int p_countryID) {
        for (Continent l_continent : d_Map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                if (l_country.getD_CountryID() == p_countryID) {
                    return l_country;
                }
            }
        }
        return null;

    }


    /**
     * Gets country object from country Name.
     *
     * @param p_countryName country id
     * @return the country object
     */
    public Country getCountryFromCountryName(String p_countryName) {
        for (Continent l_continent : d_Map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                if (l_country.getD_CountryName().equals(p_countryName)) {
                    return l_country;
                }
            }
        }
        return null;

    }


    /**
     * Add country to adjacent countries list.
     *
     * @param p_country country to be added
     */
    public void addCountryToAdjacentCountries(Country p_country) {
        d_adjacentCountries.add(p_country);
    }

    /**
     * Remove country from adjacent countries list.
     *
     * @param p_countryName country to be removed
     */
    public void removeCountryFromAdjacentCountries(String p_countryName) {
        Country l_countryToBeRemoved = new Country().getCountryFromCountryName(p_countryName);
        d_adjacentCountries.remove(l_countryToBeRemoved);
    }

    /**
     * To check if country present in adjacent countries list.
     *
     * @param p_countryName country to checked for presence
     * @return whether country is present in the adjacent country list or not
     */
    public boolean isCountryPresentInAdjacentCountries(String p_countryName) {
        Country l_country = new Country().getCountryFromCountryName(p_countryName);

        return d_adjacentCountries.contains(l_country);
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
        return l_country.getD_CountryName().equalsIgnoreCase(d_countryName);
    }
}
