package com.risk.models;

import java.util.ArrayList;
import java.util.List;


/**
 * This class defines Player and its properties such as id, name, armies and number of territories owned by player
 * @author Harshil
 */
public class Player {

    private int d_playerID;
    private String d_playername;
    private int d_armies;
    private List<Country> d_assignedCountries;

    /**
     * @param p_playerID   Unique ID of the player
     * @param p_playername Name of the player
     */
    public Player(int p_playerID, String p_playername) {
        super();
        d_playerID = p_playerID;
        d_playername = p_playername;
        d_assignedCountries = new ArrayList<Country>();
    }
}
