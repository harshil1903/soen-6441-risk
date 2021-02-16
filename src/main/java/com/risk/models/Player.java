package com.risk.models;

import com.risk.controller.CommandParser;
import com.risk.maputils.MapOperations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.risk.main.Main.d_Map;
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
    private Player d_currentPlayer;
    private List<Country> d_AssignedCountries;
    private List<Orders> d_OrderList;
    private List<Player> d_PlayerList;

    /**
     * Instantiates a new Player.
     *
     * @param p_PlayerID   Unique ID of the player
     * @param p_PlayerName Name of the player
     */
    public Player(int p_PlayerID, String p_PlayerName) {
        super();
        d_PlayerID = p_PlayerID;
        d_PlayerName = p_PlayerName;
        d_AssignedCountries = new ArrayList<Country>();
        d_OrderList=new ArrayList<Orders>();
        d_PlayerList=new ArrayList<Player>();
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
     * Gets a list of orders given by the player.
     *
     * @return list of orders
     */
    public List<Orders> getD_OrderList(){return d_OrderList;}

    /**
     * Sets a list of orders given by the player.
     * @param p_orderList list of orders
     * @return list of orders
     */
    public void setD_OrderList(List<Orders> p_orderList){d_OrderList=p_orderList;}

    /**
     * Gets a list of  player.
     *
     * @return list of player
     */
    public List<Player> getD_PlayerList(){return d_PlayerList;}

    /**
     * Sets a list of player.
     * @param p_playerList list of players
     * @return list of players
     */
    public void setD_PlayerList(List<Player> p_playerList){d_PlayerList=p_playerList;}

    public Player getD_currentPlayer(){return d_currentPlayer;}

    public void setD_currentPlayer(Player p_currentPlayer){d_currentPlayer=p_currentPlayer;}



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

    public void issue_order()
    {
        Scanner l_Scanner = new Scanner(System.in);
        String l_Command;

        System.out.println("Enter command: ");
        l_Command = l_Scanner.nextLine();

        String l_Action=l_Command.split(" ")[0];
        String l_Arguments =l_Command.substring(l_Action.length());

        List<String> l_OrderCommands = Arrays.asList("deploy");

        String[] l_ArgumentTokens = l_Arguments.split(" ");
        List<String> l_ArgumentList = new ArrayList<>(Arrays.asList(l_ArgumentTokens.clone()));

        if(l_ArgumentList.stream().count() != 1)
        {
            System.out.println("Wrong Number of Arguments provided. editmap command has only one argument.");
        }

        int l_countryId;
        int l_numberOfArmies;
        Orders orders=new Orders();

        for(int i = 0; i <l_ArgumentList.size() ; i++){
            try
            {
                l_countryId = Integer.parseInt(l_ArgumentList.get(++i));
                l_numberOfArmies =Integer.parseInt(l_ArgumentList.get(++i));
                System.out.println("Continent ID: " + l_countryId + " Control Value: " + l_numberOfArmies);
                orders.setD_countryId(l_countryId);
                orders.setD_numberOfArmies(l_numberOfArmies);
            }
            catch (Exception e)
            {
                System.out.println("Wrong number of Arguments provided.deploy option has 2 arguments");
                return;
            }
        }

    }
    public void next_order()
    {

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
