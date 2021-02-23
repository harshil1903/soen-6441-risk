package com.risk.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
     * @param p_PlayerName Name of the player
     */
    public Player(String p_PlayerName) {
        super();
        d_PlayerName = p_PlayerName;
        d_Armies = 0;
        d_AssignedCountries = new ArrayList<Country>();
        d_OrderList = new ArrayList<Orders>();
        d_PlayerList = new ArrayList<Player>();
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
    public List<Orders> getD_OrderList() {
        return d_OrderList;
    }

    /**
     * Sets a list of orders given by the player.
     *
     * @param p_orderList list of orders
     */
    public void setD_OrderList(List<Orders> p_orderList) {
        d_OrderList = p_orderList;
    }

    /**
     * Gets a list of  player.
     *
     * @return list of player
     */
    public List<Player> getD_PlayerList() {
        return d_PlayerList;
    }

    /**
     * Sets a list of player.
     *
     * @param p_playerList list of players
     */
    public void setD_PlayerList(List<Player> p_playerList) {
        d_PlayerList = p_playerList;
    }

    /**
     * Gets Current Player.
     *
     * @return Current Player
     */
    public Player getD_currentPlayer() {
        return d_currentPlayer;
    }

    /**
     * Sets current player.
     *
     * @param p_currentPlayer Current Player to be set
     */
    public void setD_currentPlayer(Player p_currentPlayer) {
        d_currentPlayer = p_currentPlayer;
    }


    /**
     * Gets player object from player id.
     *
     * @param p_PlayerID player id
     * @return the player object
     */
    public Player getPlayerFromPlayerID(int p_PlayerID) {
        for (Player l_Player : d_PlayerList) {
            if (l_Player.getD_PlayerID() == p_PlayerID) {
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
    public void addCountryToAssignedCountries(Country p_Country) {
        //To be finished later
        d_AssignedCountries.add(p_Country);
    }

    /**
     * Remove country from assigned countries.
     *
     * @param p_CountryID Country to be removed
     */
    public void removeCountryFromAssignedCountries(int p_CountryID) {
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
    public boolean isCountryPresentInAssignedCountries(int p_CountryID) {
        //return d_AssignedCountries.contains(p_CountryID);
        Country l_Country = new Country().getCountryFromCountryID(p_CountryID);

        return d_AssignedCountries.contains(l_Country);
    }

    /**
     * To add an order to the list of orders held by the player
     * Issue order phase of game
     */
    public void issue_order() {
        Scanner l_Scanner = new Scanner(System.in);
        String l_Command;

        System.out.println("\nEnter command: ");
        l_Command = l_Scanner.nextLine();

        String l_Action = l_Command.split(" ")[0];
        String l_Arguments = l_Command.substring(l_Action.length());

        List<String> l_OrderCommands = Arrays.asList("deploy");

        String[] l_ArgumentTokens = l_Arguments.split(" ");
        List<String> l_ArgumentList = new ArrayList<>(Arrays.asList(l_ArgumentTokens.clone()));

        if (!l_ArgumentList.isEmpty()) {
            l_ArgumentList.remove(0);
        }

        if (l_ArgumentList.stream().count() != 2) {
            System.out.println("Wrong Number of Arguments provided.");
            return;
        }

        String l_CountryName;
        int l_NumberOfArmies;
        boolean l_DeploySuccess;


        l_CountryName = l_ArgumentList.get(0);
        l_NumberOfArmies = Integer.parseInt(l_ArgumentList.get(1));
        l_DeploySuccess = deployOrder(l_CountryName, l_NumberOfArmies);

        if (!l_DeploySuccess) {
            issue_order();
        }

    }

    /**
     * First order in the player’s list of orders, then removes it from the list.
     *
     * @return l_tempOrder object of the order class
     */
    public Orders next_order() {
        Orders l_tempOrder = new Orders();
        Orders orders = new Orders();

        if (d_OrderList.isEmpty()) {
            return null;
        } else {
            l_tempOrder = d_OrderList.get(0);
            d_OrderList.remove(d_OrderList.get(0));
        }

        return l_tempOrder;
    }

    public boolean deployOrder(String l_CountryName, int l_NumberOfArmies) {

        Orders l_Orders = new Orders();
        if (d_Armies >= l_NumberOfArmies) {
            l_Orders.setD_countryName(l_CountryName);
            l_Orders.setD_numberOfArmies(l_NumberOfArmies);

            ArrayList<String> l_CountriesOwnedList = new ArrayList<>();
            for (Country l_Country : d_AssignedCountries) {
                l_CountriesOwnedList.add(l_Country.getD_CountryName());
            }
            if (!l_CountriesOwnedList.contains(l_CountryName)) {
                System.out.println("You do not own this country. Try Again with a country name that has been assigned to you.");
                return false;
            }


            d_Armies = d_Armies - l_NumberOfArmies;
            d_OrderList.add(l_Orders);
            System.out.println("Country: " + l_CountryName + " Number of Armies: " + l_NumberOfArmies + " successfully deployed");
            return true;
        } else {
            System.out.println("You are trying to deploy more armies than you have. Try Again in your next turn.");
            System.out.println("You currently have " + d_Armies + " number of reinforcement armies left.");
            return false;
        }
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
