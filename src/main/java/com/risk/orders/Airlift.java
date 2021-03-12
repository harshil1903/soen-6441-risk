package com.risk.orders;

import com.risk.models.Country;
import com.risk.models.Player;

import java.util.ArrayList;

/**
 * The type Airlift.
 *
 * @author Harsh
 * @author Parth
 */

public class Airlift implements Order{

    String d_sourceCountryName;
    String d_targetCountryName;

    Country d_sourceCountry;
    Country d_targetCountry;

    int d_numberOfArmies;
    Player d_player;

    /**
     *
     * @param p_player              Player ID
     * @param p_sourceCountryName   Source country ID
     * @param p_targetCountryName   Target country ID
     * @param p_numberOfArmies      Number of armies to be placed from source to target country
     */

    public Airlift(Player p_player,String p_sourceCountryName,String p_targetCountryName,int p_numberOfArmies){
        this.d_sourceCountryName = p_sourceCountryName;
        this.d_targetCountryName = p_targetCountryName;
        this.d_numberOfArmies = p_numberOfArmies;

        d_sourceCountry = new Country();
        d_targetCountry = new Country();

    }
    /**
     * Valid boolean.
     *
     * @return the boolean
     */

    public boolean valid(){
    return false;
    };

    /**
     * Execute.
     */
    public void execute(){

    };
}
