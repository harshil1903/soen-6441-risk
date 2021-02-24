package com.risk.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.risk.main.Main.d_PlayerList;

/**
 * This class defines Player and its properties such as
 * ID, Name, number of Armies and number of countries owned by player
 *
 * @author Harshil
 */
public class Player {

    private int d_playerID;
    private String d_playerName;
    private int d_armies;
    private List<Country> d_assignedCountries;
    private List<Orders> d_orderList;

    /**
     * Instantiates a new Player.
     *
     * @param p_PlayerName Name of the player
     */
    public Player(String p_PlayerName) {
        super();
        d_playerName = p_PlayerName;
        d_armies = 0;
        d_assignedCountries = new ArrayList<Country>();
        d_orderList = new ArrayList<Orders>();
    }

    /**
     * Gets player id.
     *
     * @return player id
     */
    public int getD_PlayerID() {
        return d_playerID;
    }

    /**
     * Sets player id.
     *
     * @param p_playerID player id
     */
    public void setD_PlayerID(int p_playerID) {
        d_playerID = p_playerID;
    }

    /**
     * Gets player name.
     *
     * @return player name
     */
    public String getD_PlayerName() {
        return d_playerName;
    }

    /**
     * Sets player name.
     *
     * @param p_playerName player name
     */
    public void setD_PlayerName(String p_playerName) {
        d_playerName = p_playerName;
    }

    /**
     * Gets number of armies owned by the player.
     *
     * @return armies count
     */
    public int getD_Armies() {
        return d_armies;
    }

    /**
     * Sets number of armies owned by the player.
     *
     * @param p_armies armies count
     */
    public void setD_Armies(int p_armies) {
        d_armies = p_armies;
    }

    /**
     * Gets a list of countries assigned to the player.
     *
     * @return list of assigned countries
     */
    public List<Country> getD_AssignedCountries() {
        return d_assignedCountries;
    }

    /**
     * Sets a list of countries assigned to the player.
     *
     * @param p_assignedCountries list of assigned countries
     */
    public void setD_AssignedCountries(List<Country> p_assignedCountries) {
        d_assignedCountries = p_assignedCountries;
    }

    /**
     * Gets a list of orders given by the player.
     *
     * @return list of orders
     */
    public List<Orders> getD_OrderList() {
        return d_orderList;
    }

    /**
     * Sets a list of orders given by the player.
     *
     * @param p_orderList list of orders
     */
    public void setD_OrderList(List<Orders> p_orderList) {
        d_orderList = p_orderList;
    }


    /**
     * Gets player object from player id.
     *
     * @param p_playerID player id
     * @return the player object
     */
    public Player getPlayerFromPlayerID(int p_playerID) {
        for (Player l_player : d_PlayerList) {
            if (l_player.getD_PlayerID() == p_playerID) {
                return l_player;
            }
        }
        return null;

    }


    /**
     * Add country to assigned countries.
     *
     * @param p_country Country to be added
     */
    public void addCountryToAssignedCountries(Country p_country) {
        d_assignedCountries.add(p_country);
    }

    /**
     * Remove country from assigned countries.
     *
     * @param p_countryID Country to be removed
     */
    public void removeCountryFromAssignedCountries(int p_countryID) {
        Country l_countryToBeRemoved = new Country().getCountryFromCountryID(p_countryID);

        d_assignedCountries.remove(l_countryToBeRemoved);
    }

    /**
     * To check if country present in assigned countries boolean.
     *
     * @param p_countryID Country to be checked
     * @return whether country is present in the assigned country list or not
     */
    public boolean isCountryPresentInAssignedCountries(int p_countryID) {
        Country l_country = new Country().getCountryFromCountryID(p_countryID);

        return d_assignedCountries.contains(l_country);
    }

    /**
     * To add an order to the list of orders held by the player
     * Issue order phase of game
     */
    public void issueOrder() {
        Scanner l_scanner = new Scanner(System.in);
        String l_command;

        System.out.println("\nEnter command: ");
        l_command = l_scanner.nextLine();

        String l_action = l_command.split(" ")[0];
        String l_arguments = l_command.substring(l_action.length());

        List<String> l_orderCommands = Arrays.asList("deploy");

        String[] l_argumentTokens = l_arguments.split(" ");
        List<String> l_argumentList = new ArrayList<>(Arrays.asList(l_argumentTokens.clone()));

        if (!l_argumentList.isEmpty()) {
            l_argumentList.remove(0);
        }

        if (l_argumentList.stream().count() != 2) {
            System.out.println("Wrong Number of Arguments provided.");
            return;
        }

        String l_countryName;
        int l_numberOfArmies;
        boolean l_deploySuccess;


        l_countryName = l_argumentList.get(0);
        l_numberOfArmies = Integer.parseInt(l_argumentList.get(1));
        l_deploySuccess = deployOrder(l_countryName, l_numberOfArmies);

        if (!l_deploySuccess) {
            issueOrder();
        }

    }

    /**
     * First order in the player’s list of orders, then removes it from the list.
     *
     * @return l_tempOrder object of the order class
     */
    public Orders nextOrder() {
        Orders l_tempOrder = new Orders();

        if (d_orderList.isEmpty()) {
            return null;
        } else {
            l_tempOrder = d_orderList.get(0);
            d_orderList.remove(d_orderList.get(0));
        }

        return l_tempOrder;
    }

    /**
     * Deploy order if requirements met
     *
     * @param l_countryName    country name
     * @param l_numberOfArmies number of armies
     * @return true if order successfully deployed
     */
    public boolean deployOrder(String l_countryName, int l_numberOfArmies) {

        Orders l_orders = new Orders();
        if (d_armies >= l_numberOfArmies) {
            l_orders.setD_CountryName(l_countryName);
            l_orders.setD_NumberOfArmies(l_numberOfArmies);

            ArrayList<String> l_countriesOwnedList = new ArrayList<>();
            for (Country l_country : d_assignedCountries) {
                l_countriesOwnedList.add(l_country.getD_CountryName());
            }
            if (!l_countriesOwnedList.contains(l_countryName)) {
                System.out.println("You do not own this country. Try Again with a country name that has been assigned to you.");
                return false;
            }


            d_armies = d_armies - l_numberOfArmies;
            d_orderList.add(l_orders);
            System.out.println("Country: " + l_countryName + " Number of Armies: " + l_numberOfArmies + " successfully deployed");
            return true;
        } else {
            System.out.println("You are trying to deploy more armies than you have. Try Again in your next turn.");
            System.out.println("You currently have " + d_armies + " number of reinforcement armies left.");
            return false;
        }
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
        return l_player.getD_PlayerName().equalsIgnoreCase(d_playerName);
    }
}
