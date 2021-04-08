package com.risk.phases;

import com.risk.controller.GameCommands;
import com.risk.main.GameEngine;
import com.risk.main.Main;

import java.util.ArrayList;
import java.util.List;

import static com.risk.main.Main.d_Log;
import static com.risk.main.Main.d_PlayerList;

/**
 * Abstract Class for Game Phase
 *
 * @author Harshil
 */
public abstract class Game extends Phase {

    /**
     * Instantiates a new Game.
     *
     * @param p_gameEngine the p game engine
     */
    Game(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }


    /**
     * showmap Command
     *
     * @param p_argumentTokens command parameters
     */
    public void showMap(List<String> p_argumentTokens) {
        GameCommands.showMapCommand(new ArrayList<>());
    }

    /**
     * editmap Command
     *
     * @param p_argumentTokens command parameters
     */
    public boolean editMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
        return false;
    }

    /**
     * validatemap Command
     *
     * @param p_argumentTokens command parameters
     */
    public void validateMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    /**
     * savemap Command
     *
     * @param p_argumentTokens command parameters
     */
    public void saveMap(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    /**
     * editcountry Command
     *
     * @param p_argumentTokens command parameters
     */
    public void editCountry(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    /**
     * editcontinent Command
     *
     * @param p_argumentTokens command parameters
     */
    public void editContinent(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    /**
     * editneighbor Command
     *
     * @param p_argumentTokens command parameters
     */
    public void editNeighbor(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    /**
     * end Command to leave game
     */
    @Override
    public void endGame() {
        System.out.println("Ending Game");
        d_Log.notify("Ending Game");
        d_gameEngine.runGame();
    }
}
