package com.risk.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;


/**
 * This class defines Map and its list of continents.
 *
 * @author Harshil
 * @see Country
 * @see Continent
 */
public class Map extends Observable {

    public HashMap<String, String> d_MapData;
    public List<Continent> d_Continents;

    /**
     * Default Constructor
     */
    public Map() {
        d_MapData = new HashMap<String, String>();
        d_Continents = new ArrayList<Continent>();
    }


    /**
     * Gets map data in a HashMap format.
     *
     * @return map data
     */
    public HashMap<String, String> getD_MapData() {
        return d_MapData;
    }

    /**
     * Sets map data in a HashMap format
     *
     * @param p_MapData map data
     */
    public void setD_MapData(HashMap<String, String> p_MapData) {
        d_MapData = p_MapData;
    }

    /**
     * Gets a list of continents of the map.
     *
     * @return continent list
     */
    public List<Continent> getD_Continents() {
        return d_Continents;
    }

    /**
     * Sets a list of continent to the map.
     *
     * @param p_Continents continent list
     */
    public void setD_Continents(List<Continent> p_Continents) {
        d_Continents = p_Continents;
    }


    /**
     * Add continent to continent list.
     *
     * @param p_Continent continent
     */
    public void addContinentToContinentList(Continent p_Continent) {
        //To be finished later
        d_Continents.add(p_Continent);
    }

    /**
     * Remove continent from continent list.
     *
     * @param p_ContinentID continent
     */
    public void removeContinentFromContinentList(int p_ContinentID) {
        //To be finished later
        Continent l_ContinentToBeRemoved = new Continent().getContinentFromContinentID(p_ContinentID);

        System.out.println("\n Continent Remove Info  " + l_ContinentToBeRemoved.getD_ContinentName() + " " + l_ContinentToBeRemoved.getD_ContinentID() );

        d_Continents.remove(l_ContinentToBeRemoved);
        //d_Continents.remove(p_ContinentID);
    }

    /**
     * TO check if continent present in continent list
     *
     * @param p_ContinentID continent
     * @return whether Continent is present in Map or not
     */
    public boolean isContinentPresentInContinentList(int p_ContinentID) {
        //To be finished later
        Continent l_Continent = new Continent().getContinentFromContinentID(p_ContinentID);

        return d_Continents.contains(l_Continent);
    }
}