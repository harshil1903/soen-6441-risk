package com.risk.phases;

import com.risk.controller.MapCommands;
import com.risk.exception.InvalidMapException;
import com.risk.main.GameEngine;
import com.risk.main.GameEngineNew;
import com.risk.main.MapEngine;

import java.util.ArrayList;
import java.util.List;

public class PreMapLoad extends MapEdit{

    public PreMapLoad(GameEngineNew p_gameEngine) {
        super(p_gameEngine);
    }

    public boolean editMap(List<String> p_argumentTokens){
        //Call EditMap Operation from here.
        boolean l_mapLoaded = false;
        try {
            l_mapLoaded = MapCommands.editMapCommand(p_argumentTokens);
        }
        catch (Exception e)
        {}

        return l_mapLoaded;
    }

    public void validateMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public void showMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public void saveMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public void editCountry(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public void editContinent(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public void editNeighbor(List<String> p_argumentTokens) {
        printInvalidCommandMessage();

    }

    public void next() {
        d_gameEngineNew.setPhase(new PostMapLoad(d_gameEngineNew));
    }
}
