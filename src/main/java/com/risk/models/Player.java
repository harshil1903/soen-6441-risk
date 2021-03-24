package com.risk.models;

import com.risk.orders.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.risk.main.Main.d_Log;
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
    private boolean d_noOrdersLeft;
    private List<Country> d_assignedCountries;
    private List<Orders> d_OrderLists;
    private List<Order> d_orderList;
    public Order d_currentOrder;
    public List<String> d_cardList;
    public List<String> testOrderList;

    public List<Player> d_diplomacyPlayerList;

    private String d_orderType;
    private String d_orderArgs;

    /**
     * Default constructor for player class
     */
    public Player() {
        d_armies = 0;
        d_assignedCountries = new ArrayList<Country>();
        d_OrderLists = new ArrayList<Orders>();
        d_orderList = new ArrayList<>();
        testOrderList = new ArrayList<>();
        d_diplomacyPlayerList = new ArrayList<>();
        d_cardList = new ArrayList<>();
    }

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
        d_OrderLists = new ArrayList<Orders>();
        d_orderList = new ArrayList<>();
        testOrderList = new ArrayList<>();
        d_diplomacyPlayerList = new ArrayList<>();
        d_cardList = new ArrayList<>();
    }

    /**
     * Set order values for issue order execution
     *
     * @param p_orderType the order type
     * @param p_orderArgs the order args
     */
    public void setOrderValues(String p_orderType, String p_orderArgs){
        d_orderType = p_orderType;
        d_orderArgs = p_orderArgs;
    }



    /**
     * adds card to cardList.
     *
     * @param p_card card to be added.
     */
    public void addCard(String p_card) {
        d_cardList.add(p_card);
    }

    /**
     * Finds the card removes it from the card list.
     *
     * @param p_card    Card name
     */
    public void removeCard(String p_card) {
        for (int i = 0; i < d_cardList.size(); i++) {
            String l_card = d_cardList.get(i);
            if (l_card.equals(p_card)) {
                d_cardList.remove(i);
                break;
            }
        }
    }

    /**
     * retrieves card list and displays it.
     */
    public void showCardList() {
        System.out.print(d_playerName + " Owns card: ");
        d_Log.notify(d_playerName + " Owns card: ");
        for (String l_card : d_cardList)
            System.out.print(l_card + " ");

        System.out.println();
    }

    /**
     * returns list of card owned by player
     *
     * @return card list owned by player
     */
    public List<String> getD_cardList() {
        return d_cardList;
    }

    /**
     * To get list of player which are in diplomacyList
     *
     * @return d_diplomacyPlayerList
     */
    public List<Player> getDiplomacyPlayer() {
        return d_diplomacyPlayerList;
    }

    /**
     * To set player in diplomacyList
     *
     * @param p_diplomacyPlayerList list of player
     */
    public void setDiplomacyPlayer(List<Player> p_diplomacyPlayerList) {
        d_diplomacyPlayerList = p_diplomacyPlayerList;
    }

    /**
     * To add player in DiplomacyList
     *
     * @param p_player player object
     */
    public void addPlayerToDiplomacyList(Player p_player) {
        d_diplomacyPlayerList.add(p_player);
    }

    /**
     * To remove player from DiplomacyList
     *
     * @param p_player player name
     */
    public void removePlayerFromDiplomacyList(String p_player) {
        Player l_player = new Player().getPlayerFromPlayerName(p_player);
        d_diplomacyPlayerList.remove(l_player);
    }

    /**
     * To get player name
     *
     * @param p_playerName player name
     * @return l_player
     */
    public Player getPlayerFromPlayerName(String p_playerName) {
        for (Player l_player : d_PlayerList) {
            if (l_player.getD_PlayerName().equals(p_playerName)) {
                return l_player;
            }
        }
        return null;
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
        return d_OrderLists;
    }

    /**
     * Sets a list of orders given by the player.
     *
     * @param p_orderList list of orders
     */
    public void setD_OrderList(List<Orders> p_orderList) {
        d_OrderLists = p_orderList;
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
     * Is d no orders left boolean.
     *
     * @return the boolean
     */
    public boolean isD_noOrdersLeft() {
        return d_noOrdersLeft;
    }

    /**
     * Sets d no orders left.
     *
     * @param p_noOrdersLeft the d no orders left
     */
    public void setD_noOrdersLeft(boolean p_noOrdersLeft) {
        d_noOrdersLeft = p_noOrdersLeft;
    }

    /**
     * Gets d order list.
     *
     * @return the d order list
     */
    public List<Order> getD_orderList() {
        return d_orderList;
    }

    /**
     * Sets d order list.
     *
     * @param p_orderList the d order list
     */
    public void setD_orderList(List<Order> p_orderList) {
        d_orderList = p_orderList;
    }

    /**
     * Gets d current order.
     *
     * @return the d current order
     */
    public Order getD_currentOrder() {
        return d_currentOrder;
    }

    /**
     * Sets d current order.
     *
     * @param p_currentOrder the d current order
     */
    public void setD_currentOrder(Order p_currentOrder) {
        d_currentOrder = p_currentOrder;
    }


    /**
     * To add an order to the list of orders held by the player
     * Issue order phase of game
     *
     */
    public void issueOrder() {

        String[] l_argumentTokens = d_orderArgs.split(" ");
        List<String> l_argumentList = new ArrayList<>(Arrays.asList(l_argumentTokens.clone()));

        if (!l_argumentList.isEmpty()) {
            l_argumentList.remove(0);
        }


        switch (d_orderType) {
            case "deploy":
                System.out.println("Deploy Order done with " + d_orderArgs);
                d_Log.notify("Deploy Order done with " + d_orderArgs);
                d_currentOrder = new Deploy(this, l_argumentList.get(0), Integer.parseInt(l_argumentList.get(1)));
                d_orderList.add(d_currentOrder);
                break;

            case "advance":
                System.out.println("Advance Order");
                d_Log.notify("Advance Order");
                d_currentOrder = new Advance(this, l_argumentList.get(0), l_argumentList.get(1), Integer.parseInt(l_argumentList.get(2)));
                d_orderList.add(d_currentOrder);
                break;

            case "bomb":
                System.out.println("Bomb Order");
                d_Log.notify("Bomb Order");
                d_currentOrder = new Bomb(this, l_argumentList.get(0));
                d_orderList.add(d_currentOrder);
                break;

            case "blockade":
                System.out.println("Blockade Order");
                d_Log.notify("Blockade Order");
                d_currentOrder = new Blockade(this, l_argumentList.get(0));
                d_orderList.add(d_currentOrder);
                break;

            case "airlift":
                System.out.println("Airlift Order");
                d_Log.notify("Airlift Order");
                d_currentOrder = new Airlift(this, l_argumentList.get(0), l_argumentList.get(1), Integer.parseInt(l_argumentList.get(2)));
                d_orderList.add(d_currentOrder);
                break;

            case "negotiate":
                System.out.println("Negotiate Order");
                d_Log.notify("Negotiate Order");
                d_currentOrder = new Diplomacy(this, l_argumentList.get(0));
                d_orderList.add(d_currentOrder);
                break;

            case "end":
                return;

            default:
                System.out.println("Invalid Command \nAllowed Commands are : deploy, advance, bomb, blockade, airlift, negotiate");
                d_Log.notify("Invalid Command \nAllowed Commands are : deploy, advance, bomb, blockade, airlift, negotiate");
                break;
        }


    }

    /**
     * First order in the player’s list of orders, then removes it from the list.
     *
     * @return l_tempOrder object of the order class
     */
    public Order nextOrder() {
        Order l_tempOrder;

        if (d_orderList.isEmpty()) {
            return null;
        } else {
            l_tempOrder = d_orderList.get(0);
            d_orderList.remove(d_orderList.get(0));

        }
        return l_tempOrder;

    }


    /**
     * Clear player data.
     */
    public void clearPlayerData(){
        this.setD_Armies(0);
        this.getD_AssignedCountries().clear();
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
