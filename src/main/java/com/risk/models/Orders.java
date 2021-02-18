package com.risk.models;

import java.util.List;

public class Orders {
    int d_countryId;
    Country d_Countries;
    int d_numberOfArmies;

    public void setD_countryId(int d_countryId) {
        this.d_countryId = d_countryId;
    }

    public void setD_numberOfArmies(int d_numberOfArmies) {
        this.d_numberOfArmies = d_numberOfArmies;
    }

    public int getD_countryId() {
        return d_countryId;
    }

    public int getD_numberOfArmies() {
        return d_numberOfArmies;
    }
    public void execute(){
    }

}
