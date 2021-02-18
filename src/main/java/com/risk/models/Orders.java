package com.risk.models;

import java.util.List;

public class Orders {
    int d_countryId;
    Country d_Countries;
    int d_numberOfArmies;

    /**
     * Sets country Id
     * @param d_countryId country Id
     */
    public void setD_countryId(int d_countryId) {
        this.d_countryId = d_countryId;
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
    public int getD_countryId() {
        return d_countryId;
    }

    /**
     * Get number of armies
     * @return number of armies
     */
    public int getD_numberOfArmies() {
        return d_numberOfArmies;
    }
    public void execute(){
    }

}
