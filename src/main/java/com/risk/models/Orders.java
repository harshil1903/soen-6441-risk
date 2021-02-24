package com.risk.models;

/**
 * This class defines Orders and its properties such as
 * number of Armies and country in which armies are to be deployed
 *
 * @author Parth Navsariwala
 */
public class Orders {
    String d_CountryName;
    Country d_Country;
    int d_NumberOfArmies;

    /**
     * constructor for the Orders class
     */
    public Orders() {
        d_Country = new Country();
    }

    /**
     * Sets country Id
     *
     * @param d_CountryName country Id
     */
    public void setD_countryName(String d_CountryName) {
        this.d_CountryName = d_CountryName;
    }

    /**
     * Sets number of armies
     *
     * @param d_NumberOfArmies number of armies
     */
    public void setD_numberOfArmies(int d_NumberOfArmies) {
        this.d_NumberOfArmies = d_NumberOfArmies;
    }

    /**
     * Get Id of country
     *
     * @return country ID
     */
    public String getD_countryName() {
        return d_CountryName;
    }

    /**
     * Get number of armies
     *
     * @return number of armies
     */
    public int getD_numberOfArmies() {
        return d_NumberOfArmies;
    }

    /**
     * Execute phase of the game
     * Set number of army in each player country
     */
    public void execute() {
        int l_PreviousArmies = d_Country.getCountryFromCountryName(d_CountryName).getD_NumberOfArmies();
        d_Country.getCountryFromCountryName(d_CountryName).setD_NumberOfArmies(d_NumberOfArmies + l_PreviousArmies);
    }

}
