package com.risk.models;

/**
 * This class defines Orders and its properties such as
 * number of Armies and country in which armies are to be deployed
 *
 * @author Parth Navsariwala
 */
public class Orders {
    String d_countryName;
    Country d_country;
    int d_numberOfArmies;

    /**
     * constructor for the Orders class
     */
    public Orders() {
        d_country = new Country();
    }

    /**
     * Sets country Id
     *
     * @param d_countryName country Id
     */
    public void setD_CountryName(String d_countryName) {
        this.d_countryName = d_countryName;
    }

    /**
     * Sets number of armies
     *
     * @param d_numberOfArmies number of armies
     */
    public void setD_NumberOfArmies(int d_numberOfArmies) {
        this.d_numberOfArmies = d_numberOfArmies;
    }

    /**
     * Get Id of country
     *
     * @return country ID
     */
    public String getD_CountryName() {
        return d_countryName;
    }

    /**
     * Get number of armies
     *
     * @return number of armies
     */
    public int getD_NumberOfArmies() {
        return d_numberOfArmies;
    }

    /**
     * Execute phase of the game
     * Set number of army in each player country
     */
    public void execute() {
        int l_previousArmies = d_country.getCountryFromCountryName(d_countryName).getD_NumberOfArmies();
        d_country.getCountryFromCountryName(d_countryName).setD_NumberOfArmies(d_numberOfArmies + l_previousArmies);
    }

}
