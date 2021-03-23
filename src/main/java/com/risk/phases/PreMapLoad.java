package com.risk.phases;

import com.risk.controller.MapCommands;
import com.risk.main.GameEngine;

import java.util.List;

/**
 * Pre map load phase only allows editmap function to load the map
 *
 * @author Harshil
 */
public class PreMapLoad extends MapEdit{

    /**
     * Instantiates a new Pre map load.
     *
     * @param p_gameEngine the p game engine
     */
    public PreMapLoad(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }

    /**
     *  editmap Command
     *
     *  @param p_argumentTokens command parameters
     */
    public boolean editMap(List<String> p_argumentTokens){
        boolean l_mapLoaded = false;
        try {
            l_mapLoaded = MapCommands.editMapCommand(p_argumentTokens);
        }
        catch (Exception e)
        {}

        return l_mapLoaded;
    }

    /**
     *  validatemap Command
     *
     *  @param p_argumentTokens command parameters
     */
    public void validateMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    /**
     *  showmap Command
     *
     * @param p_argumentTokens command parameters
     */
    public void showMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    /**
     *  savemap Command
     *
     *  @param p_argumentTokens command parameters
     */
    public void saveMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    /**
     *  editcountry Command
     *
     *  @param p_argumentTokens command parameters
     */
    public void editCountry(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    /**
     *  editcontinent Command
     *
     *  @param p_argumentTokens command parameters
     */
    public void editContinent(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    /**
     *  editneighbor Command
     *
     *  @param p_argumentTokens command parameters
     */
    public void editNeighbor(List<String> p_argumentTokens) {
        printInvalidCommandMessage();

    }

    /**
     * To return current phase name in string
     *
     * @return Current phase
     */
    public String currentPhase(){
        return "PreMapLoad";
    }

    /**
     *  end Command to leave game
     */
    public void next() {
        d_gameEngine.setPhase(new PostMapLoad(d_gameEngine));
    }
}
