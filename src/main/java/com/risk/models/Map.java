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

    private HashMap<String, String> d_mapData;
    private List<Continent> d_continents;

    /**
     * Default Constructor
     */
    public Map() {
        d_mapData = new HashMap<String, String>();
        d_continents = new ArrayList<Continent>();
    }


    /**
     * Gets map data in a HashMap format.
     *
     * @return map data
     */
    public HashMap<String, String> getD_MapData() {
        return d_mapData;
    }

    /**
     * Sets map data in a HashMap format
     *
     * @param p_mapData map data
     */
    public void setD_MapData(HashMap<String, String> p_mapData) {
        d_mapData = p_mapData;
    }

    /**
     * Gets a list of continents of the map.
     *
     * @return continent list
     */
    public List<Continent> getD_Continents() {
        return d_continents;
    }

    /**
     * Sets a list of continent to the map.
     *
     * @param p_continents continent list
     */
    public void setD_Continents(List<Continent> p_continents) {
        d_continents = p_continents;
    }


    /**
     * Add continent to continent list.
     *
     * @param p_continent continent
     */
    public void addContinentToContinentList(Continent p_continent) {
        d_continents.add(p_continent);
    }

    /**
     * This method returns continent from continents list.
     *
     * @param p_continentId gives the id of the continent to be fetched.
     * @return matching continent from the list.
     */
    public Continent getContinentFromContinentList(int p_continentId) {
        Continent l_continent = d_continents.get(p_continentId - 1);
        return l_continent;
    }

    /**
     * Remove continent from continent list.
     *
     * @param p_continentName continent
     */
    public void removeContinentFromContinentList(String p_continentName) {
        Continent l_continentToBeRemoved = new Continent().getContinentFromContinentName(p_continentName);

        System.out.println("\n Continent Remove Info  " + l_continentToBeRemoved.getD_ContinentName() + " " + l_continentToBeRemoved.getD_ContinentID());

        d_continents.remove(l_continentToBeRemoved);
    }

    /**
     * TO check if continent present in continent list
     *
     * @param p_continentName continent
     * @return whether Continent is present in Map or not
     */
    public boolean isContinentPresentInContinentList(String p_continentName) {
        Continent l_continent = new Continent().getContinentFromContinentName(p_continentName);

        return d_continents.contains(l_continent);
    }

    /**
     * Clear map data.
     */
    public void clearMapData(){
        for (Continent l_continent : this.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                    l_country = null;
            }
            l_continent.getD_Countries().clear();
            l_continent = null;
        }
        this.getD_Continents().clear();
    }
}