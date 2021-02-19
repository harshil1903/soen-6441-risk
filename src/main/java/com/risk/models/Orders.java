package com.risk.models;

import java.util.List;

public class Orders {
    String d_countryName;
    Country d_countries;
    int d_numberOfArmies;

    /**
     * constructor for the Orders class
     */
    public Orders(){
        d_countries=new Country();
    }

    /**
     * Sets country Id
     * @param d_countryName country Id
     */
    public void setD_countryName(String d_countryName) {
        this.d_countryName = d_countryName;
    }

    /**
     * Sets number of armies
     * @param d_numberOfArmies number of armies
     */
    public void setD_numberOfArmies(int d_numberOfArmies) {
        this.d_numberOfArmies = d_numberOfArmies;
    }

    /**
     * Get Id of country
     * @return  country ID
     */
    public String getD_countryName() {
        return d_countryName;
    }

    /**
     * Get number of armies
     * @return number of armies
     */
    public int getD_numberOfArmies() {
        return d_numberOfArmies;
    }
    public void execute(){
        d_countries.getCountryFromCountryName(d_countryName).setD_NumberOfArmies(d_numberOfArmies);
    }

}
