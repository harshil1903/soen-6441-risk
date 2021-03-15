package com.risk.phases;

import com.risk.controller.MapCommands;
import com.risk.main.GameEngine;
import com.risk.main.GameEngineNew;
import com.risk.main.MapEngine;

import java.util.List;

/**
 * The type Post map load.
 */
public class PostMapLoad extends MapEdit{

    /**
     * Instantiates a new Post map load.
     *
     * @param p_gameEngine the p game engine
     */
    public PostMapLoad(GameEngineNew p_gameEngine) {
        super(p_gameEngine);
    }

    public boolean editMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
        return false;
    }

    public void validateMap(List<String> p_argumentTokens) {
        //Call ValidateMap Operation from here.
        MapCommands.validateMapCommand(p_argumentTokens);
    }

    public void showMap(List<String> p_argumentTokens){
        //Call showMap Operation from here.
        MapCommands.showMapCommand(p_argumentTokens);
    }

    public void saveMap(List<String> p_argumentTokens){
        //Call saveMap Operation from here.
        MapCommands.saveMapCommand(p_argumentTokens);
    }

    public void editCountry(List<String> p_argumentTokens){
        //Call EditCountry Operation from here.
        MapCommands.editCountryCommand(p_argumentTokens);
    }

    public void editContinent(List<String> p_argumentTokens){

        //Call EditContinent Operation from here.
        MapCommands.editContinentCommand(p_argumentTokens);
    }

    public void editNeighbor(List<String> p_argumentTokens){

        //Call EditNeighbor Operation from here.
        MapCommands.editNeighborCommand(p_argumentTokens);
    }

    public void next() {
        d_gameEngineNew.setPhase(new PreMapLoad(d_gameEngineNew));
    }
}
