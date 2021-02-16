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
    private int d_CountryID;
    private int d_ContinentID;
    private String d_CountryName;
    private Continent d_BelongToContinent;
    private Player d_Player;
    private List<Country> d_AdjacentCountries;
    private boolean d_IsProcessed = false;


    private int d_NumberOfArmies;
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
     * Parameterized constructor which instantiates a new country
     *
     * @param p_CountryID
     * @param p_CountryName
     * @param p_ContinentID
     */
    public Country(int p_CountryID, String p_CountryName, int p_ContinentID) {

        d_CountryID = p_CountryID;
        d_CountryName = p_CountryName;
        p_ContinentID = p_ContinentID;
        d_AdjacentCountries = new ArrayList<>();

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
     * Gets continent id.
     *
     * @return Continent ID
     */
    public int getD_ContinentID() {
        return d_ContinentID;
    }

    /**
     * Sets continent id.
     *
     * @param p_ContinentID Continent ID
     */
    public void setD_ContinentID(int p_ContinentID) {
        d_ContinentID = p_ContinentID;
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
     * To check whether country is processed or not.
     *
     * @return d_IsProcessed returns boolean value whether country is processed or not
     */
    public boolean isD_IsProcessed() {
        return d_IsProcessed;
    }

    /**
     * To set the country as processed.
     *
     * @param p_IsProcessed is used to set that the country is processed.
     */
    public void setD_Processed(boolean p_IsProcessed) {
        d_IsProcessed = p_IsProcessed;
    }

    /**
     * Gets number of armies deployed by the player
     *
     * @return number of armies
     */
    public int getD_NumberOfArmies() {
        return d_NumberOfArmies;
    }

    /**
     * Sets number of armies to be deployed in the country.
     *
     * @param p_NumberOfArmies number of armies
     */
    public void setD_NumberOfArmies(int p_NumberOfArmies) {
        d_NumberOfArmies = p_NumberOfArmies;
    }


    /**
     * Gets country object from country id.
     *
     * @param p_CountryID country id
     * @return the country object
     */
    public Country getCountryFromCountryID(int p_CountryID)
    {
        for(Continent l_Continent : d_Map.getD_Continents())
        {
            for(Country l_Country : l_Continent.getD_Countries())
            {
                if (l_Country.getD_CountryID() == p_CountryID)
                {
                    return l_Country;
                }
            }
        }
        return null;

    }




    /**
     * Add country to adjacent countries list.
     *
     * @param p_Country country to be added
     */
    public void addCountryToAdjacentCountries(Country p_Country) {
        //To be finished later
        d_AdjacentCountries.add(p_Country);
    }

    /**
     * Remove country from adjacent countries list.
     *
     * @param p_CountryID country to be removed
     */
    public void removeCountryFromAdjacentCountries(int p_CountryID) {
        //To be finished later
        Country l_CountryToBeRemoved = new Country().getCountryFromCountryID(p_CountryID);

        d_AdjacentCountries.remove(l_CountryToBeRemoved);
        //d_AdjacentCountries.remove(p_CountryID);
    }

    /**
     * To check if country present in adjacent countries list.
     *
     * @param p_CountryID country to checked for presence
     * @return whether country is present in the adjacent country list or not
     */
    public boolean isCountryPresentInAdjacentCountries(int p_CountryID) {
        //return d_AdjacentCountries.contains(p_CountryID);
        Country l_Country = new Country().getCountryFromCountryID(p_CountryID);

        return d_AdjacentCountries.contains(l_Country);
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
