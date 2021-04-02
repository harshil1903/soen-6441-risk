package com.risk.models;

import java.util.ArrayList;
import java.util.List;

import static com.risk.main.Main.d_Map;


/**
 * This class defines Continent and its properties such as
 * Name, control value, list of Countries it has
 *
 * @author Harshil
 */
public class Continent {
    private int d_continentID;
    private String d_continentName;
    private int d_continentValue;
    private List<Country> d_countries;
    private boolean d_isVisited = false;

    /**
     * Default Constructor
     */
    public Continent() {
        this.d_countries = new ArrayList<>();

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
    }

    /**
     * Instantiates new Continent.
     *
     * @param p_continentID    ID of the continent
     * @param p_continentName  Name of the continent
     * @param p_continentValue control value of the continent
     */

    public Continent(int p_continentID, String p_continentName, int p_continentValue) {
        super();
        d_continentID = p_continentID;
        d_continentName = p_continentName;
        d_continentValue = p_continentValue;
        this.d_countries = new ArrayList<>();
    }

    /**
     * Gets continent id.
     *
     * @return continent id
     */
    public int getD_ContinentID() {
        return d_continentID;
    }

    /**
     * Sets continent id.
     *
     * @param p_continentID continent id
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
     * @param p_continentName continent name
     */
    public void setD_ContinentName(String p_continentName) {
        d_continentName = p_continentName;
    }

    /**
     * Gets control value of continent
     *
     * @return continent control value
     */
    public int getD_ContinentValue() {
        return d_continentValue;
    }

    /**
     * Sets control value of continent
     *
     * @param p_continentValue continent control value
     */
    public void setD_ContinentValue(int p_continentValue) {
        d_continentValue = p_continentValue;
    }

    /**
     * Gets list of countries in the continent.
     *
     * @return countries list
     */
    public List<Country> getD_Countries() {
        return d_countries;
    }

    /**
     * Sets list of countries to the continent.
     *
     * @param p_countries countries list
     */
    public void setD_Countries(List<Country> p_countries) {
        d_countries = p_countries;
    }

    /**
     * Checks if continent is visited or not
     *
     * @return whether continent is visited or not
     */
    public boolean isD_IsVisited() {
        return d_isVisited;
    }

    /**
     * Sets visited value of continent
     *
     * @param p_isVisited whether continent is visited or not
     */
    public void setD_Visited(boolean p_isVisited) {
        d_isVisited = p_isVisited;
    }


    /**
     * Gets continent object from continent id.
     *
     * @param p_continentID continent id
     * @return the continent object
     */
    public Continent getContinentFromContinentID(int p_continentID) {

        for (Continent l_continent : d_Map.getD_Continents()) {
            if (l_continent.getD_ContinentID() == p_continentID) {
                return l_continent;
            }
        }
        return null;

    }

    /**
     * Gets continent object from continent id.
     *
     * @param p_continentName continent id
     * @return the continent object
     */
    public Continent getContinentFromContinentName(String p_continentName) {

        for (Continent l_continent : d_Map.getD_Continents()) {
            if (l_continent.getD_ContinentName().equals(p_continentName)) {
                return l_continent;
            }
        }
        return null;

    }

    /**
     * Gets continent object from continent id.
     *
     * @param p_countryName Name of the country to be searched
     * @return the country object
     */
    public Country getCountryFromCountryName(String p_countryName) {
        for (Country l_country : d_countries) {
            if (l_country.getD_CountryName().equals(p_countryName)) {
                return l_country;
            }
        }
        return null;
    }


    /**
     * Add country to country list
     *
     * @param p_country country to be added
     */
    public void addCountryToCountryList(Country p_country) {
        d_countries.add(p_country);
    }

    /**
     * Remove country from country list.
     *
     * @param p_countryName country to be removed
     */
    public void removeCountryFromCountryList(String p_countryName) {
        Country l_countryToBeRemoved = new Country().getCountryFromCountryName(p_countryName);

        d_countries.remove(l_countryToBeRemoved);
    }

    /**
     * To check if country present in country list of the continent or not.
     *
     * @param p_countryName country to be checked
     * @return whether country is present in the country list or not
     */
    public boolean isCountryPresentInCountryList(String p_countryName) {
        Country l_country = new Country().getCountryFromCountryName(p_countryName);

        return d_countries.contains(l_country);
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
        return l_continent.getD_ContinentName().equalsIgnoreCase(d_continentName);
    }
}