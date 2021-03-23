package com.risk.phases;

import com.risk.controller.MapCommands;
import com.risk.main.GameEngineNew;

import java.util.List;

/**
 * Post map load phase allows methods like showmap, validatemap, savemap and other
 * editmap functionalities
 *
 * @author Harshil
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

    /**
     *  editmap Command to load the map
     *
     *  @param p_argumentTokens command parameters
     */
    public boolean editMap(List<String> p_argumentTokens) {

        printInvalidCommandMessage();
        return false;
    }

    /**
     *  validatemap Command to validate map
     *
     *  @param p_argumentTokens command parameters
     */
    public void validateMap(List<String> p_argumentTokens) {

        MapCommands.validateMapCommand(p_argumentTokens);
    }

    /**
     *  showmap Command to diplay map
     *
     * @param p_argumentTokens command parameters
     */
    public void showMap(List<String> p_argumentTokens){

        MapCommands.showMapCommand(p_argumentTokens);
    }

    /**
     *  savemap Command to save map
     *
     *  @param p_argumentTokens command parameters
     */
    public void saveMap(List<String> p_argumentTokens){

        MapCommands.saveMapCommand(p_argumentTokens);
    }

    /**
     *  editcountry Command to add/remove country
     *
     *  @param p_argumentTokens command parameters
     */
    public void editCountry(List<String> p_argumentTokens){

        MapCommands.editCountryCommand(p_argumentTokens);
    }

    /**
     *  editcontinent Command to add/remove continent
     *
     *  @param p_argumentTokens command parameters
     */
    public void editContinent(List<String> p_argumentTokens){

        MapCommands.editContinentCommand(p_argumentTokens);
    }

    /**
     *  editneighbor Command to add remove neighbor
     *
     *  @param p_argumentTokens command parameters
     */
    public void editNeighbor(List<String> p_argumentTokens){

        MapCommands.editNeighborCommand(p_argumentTokens);
    }

    /**
     * To return current phase name in string
     *
     * @return Current phase
     */
    public String currentPhase(){

        return "PostMapLoad";
    }

    /**
     *  end Command to leave game
     */
    public void next() {

        d_gameEngineNew.setPhase(new PreMapLoad(d_gameEngineNew));
    }
}
