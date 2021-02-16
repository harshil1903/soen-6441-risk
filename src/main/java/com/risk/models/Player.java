package com.risk.models;

import java.util.ArrayList;
import java.util.List;

import static com.risk.main.Main.d_PlayerList;


/**
 * This class defines Player and its properties such as
 * ID, Name, number of Armies and number of countries owned by player
 *
 * @author Harshil
 */
public class Player {

    private int d_PlayerID;
    private String d_PlayerName;
    private int d_Armies;
    private List<Country> d_AssignedCountries;

    /**
     * Instantiates a new Player.
     *
     * @param p_PlayerName Name of the player
     */
    public Player(String p_PlayerName) {
        super();
        d_PlayerName = p_PlayerName;
        d_Armies = 0;
        d_AssignedCountries = new ArrayList<Country>();
    }

    /**
     * Gets player id.
     *
     * @return player id
     */
    public int getD_PlayerID() {
        return d_PlayerID;
    }

    /**
     * Sets player id.
     *
     * @param p_PlayerID player id
     */
    public void setD_PlayerID(int p_PlayerID) {
        d_PlayerID = p_PlayerID;
    }

    /**
     * Gets player name.
     *
     * @return player name
     */
    public String getD_PlayerName() {
        return d_PlayerName;
    }

    /**
     * Sets player name.
     *
     * @param p_PlayerName player name
     */
    public void setD_PlayerName(String p_PlayerName) {
        d_PlayerName = p_PlayerName;
    }

    /**
     * Gets number of armies owned by the player.
     *
     * @return armies count
     */
    public int getD_Armies() {
        return d_Armies;
    }

    /**
     * Sets number of armies owned by the player.
     *
     * @param p_Armies armies count
     */
    public void setD_Armies(int p_Armies) {
        d_Armies = p_Armies;
    }

    /**
     * Gets a list of countries assigned to the player.
     *
     * @return list of assigned countries
     */
    public List<Country> getD_AssignedCountries() {
        return d_AssignedCountries;
    }

    /**
     * Sets a list of countries assigned to the player.
     *
     * @param p_AssignedCountries list of assigned countries
     */
    public void setD_AssignedCountries(List<Country> p_AssignedCountries) {
        d_AssignedCountries = p_AssignedCountries;
    }


    /**
     * Gets player object from player id.
     *
     * @param p_PlayerID player id
     * @return the player object
     */
    public Player getPlayerFromPlayerID(int p_PlayerID)
    {
        for(Player l_Player : d_PlayerList)
        {
            if(l_Player.getD_PlayerID() == p_PlayerID)
            {
                return l_Player;
            }
        }
        return null;

    }


    /**
     * Add country to assigned countries.
     *
     * @param p_Country Country to be added
     */
    public void addCountryToAssignedCountries(Country p_Country)
    {
        //To be finished later
        d_AssignedCountries.add(p_Country);
    }

    /**
     * Remove country from assigned countries.
     *
     * @param p_CountryID Country to be removed
     */
    public void removeCountryFromAssignedCountries(int p_CountryID)
    {
        Country l_CountryToBeRemoved = new Country().getCountryFromCountryID(p_CountryID);

        d_AssignedCountries.remove(l_CountryToBeRemoved);
        //To be finished later
        //d_AssignedCountries.remove(p_Country);
    }

    /**
     * To check if country present in assigned countries boolean.
     *
     * @param p_CountryID Country to be checked
     * @return whether country is present in the assigned country list or not
     */
    public boolean isCountryPresentInAssignedCountries(int p_CountryID)
    {
        //return d_AssignedCountries.contains(p_CountryID);
        Country l_Country = new Country().getCountryFromCountryID(p_CountryID);

        return d_AssignedCountries.contains(l_Country);
    }


    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object p_Object) {

        if (!(p_Object instanceof Player)) {
            return false;
        }

        if (p_Object == this) {
            return true;
        }

        Player l_Player = (Player) p_Object;
        return l_Player.getD_PlayerName().equalsIgnoreCase(d_PlayerName);
    }

}
