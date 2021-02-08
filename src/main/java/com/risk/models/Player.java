package com.risk.models;

import java.util.ArrayList;
import java.util.List;


/**
 * This class defines Player and its properties such as id, name, armies and number of territories owned by player
 *
 * @author Harshil
 */
public class Player {

    private int d_playerID;
    private String d_playerName;
    private int d_armies;
    private List<Country> d_assignedCountries;

    /**
     * Instantiates a new Player.
     *
     * @param p_playerID   Unique ID of the player
     * @param p_playerName Name of the player
     */
    public Player(int p_playerID, String p_playerName) {
        super();
        d_playerID = p_playerID;
        d_playerName = p_playerName;
        d_assignedCountries = new ArrayList<Country>();
    }

    /**
     * Gets player id.
     *
     * @return player id
     */
    public int getD_playerID() {
        return d_playerID;
    }

    /**
     * Sets player id.
     *
     * @param p_playerID player id
     */
    public void setD_playerID(int p_playerID) {
        d_playerID = p_playerID;
    }

    /**
     * Gets player name.
     *
     * @return player name
     */
    public String getD_playerName() {
        return d_playerName;
    }

    /**
     * Sets player name.
     *
     * @param p_playerName player name
     */
    public void setD_playerName(String p_playerName) {
        d_playerName = p_playerName;
    }

    /**
     * Gets number of armies owned by the player.
     *
     * @return armies count
     */
    public int getD_armies() {
        return d_armies;
    }

    /**
     * Sets number of armies owned by the player.
     *
     * @param p_armies armies count
     */
    public void setD_armies(int p_armies) {
        d_armies = p_armies;
    }

    /**
     * Gets a list of countries assigned to the player.
     *
     * @return list of assigned countries
     */
    public List<Country> getD_assignedCountries() {
        return d_assignedCountries;
    }

    /**
     * Sets a list of countries assigned to the player.
     *
     * @param p_assignedCountries list of assigned countries
     */
    public void setD_assignedCountries(List<Country> p_assignedCountries) {
        d_assignedCountries = p_assignedCountries;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object p_object) {

        if (!(p_object instanceof Player)) {
            return false;
        }

        if (p_object == this) {
            return true;
        }

        Player l_player = (Player) p_object;
        return l_player.getD_playerName().equalsIgnoreCase(d_playerName);
    }

}
