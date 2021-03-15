package com.risk.phases;

import com.risk.main.GameEngine;
import com.risk.main.GameEngineNew;
import com.risk.main.Main;
import com.risk.main.MapEngine;

import java.util.List;

/**
 * Phase Class is the parent class for the State Pattern implementation
 */
public abstract class Phase {
    /**
     * The D game engine new.
     */
    GameEngineNew d_gameEngineNew;

    /**
     * Instantiates a new Phase.
     *
     * @param p_gameEngine the p game engine
     */
    Phase(GameEngineNew p_gameEngine){
        d_gameEngineNew = p_gameEngine;
    }


    /*
    Mapedit
        PreLoad
            editmap()
        PostLoad
            showmap()
            validatmap()
            editcountry()
            editcontinent()
            editneighbor()
            showmap()

     Game
        PreLoad
            loadmap()
        Postload
            showmap()
            gameplayer()
            assignocuntries()
        Main Game
            showmap()
            reinforce()
            issueorder()
            executeorder()
     */


    /**
     * Edit map boolean.
     *
     * @param p_argumentTokens the p argument tokens
     * @return the boolean
     */
//PreMapLoad
    abstract public boolean editMap(List<String> p_argumentTokens);

    /**
     * Validate map.
     *
     * @param p_argumentTokens the p argument tokens
     */
//PostMopLoad commands
    abstract public void validateMap(List<String> p_argumentTokens);

    /**
     * Show map.
     *
     * @param p_argumentTokens the p argument tokens
     */
    abstract public void showMap(List<String> p_argumentTokens);

    /**
     * Save map.
     *
     * @param p_argumentTokens the p argument tokens
     */
    abstract public void saveMap(List<String> p_argumentTokens);

    /**
     * Edit country.
     *
     * @param p_argumentTokens the p argument tokens
     */
    abstract public void editCountry(List<String> p_argumentTokens);

    /**
     * Edit continent.
     *
     * @param p_argumentTokens the p argument tokens
     */
    abstract public void editContinent(List<String> p_argumentTokens);

    /**
     * Edit neighbor.
     *
     * @param p_argumentTokens the p argument tokens
     */
    abstract public void editNeighbor(List<String> p_argumentTokens);


    /**
     * Load map boolean.
     *
     * @param p_argumentList the p argument list
     * @return the boolean
     */
//GameStartup
    abstract public boolean loadMap(List<String> p_argumentList);

    /**
     * Add player.
     *
     * @param p_argumentTokens the p argument tokens
     */
    abstract public void addPlayer(List<String> p_argumentTokens);

    /**
     * Assign countries boolean.
     *
     * @return the boolean
     */
    abstract public boolean assignCountries();

    /**
     * Reinforce.
     */
//MainGame
    // reinforcement commands
    abstract public void reinforce();

    /**
     * Issue order.
     */
//issue Order
    abstract public void issueOrder();

    /**
     * Execute order.
     */
//Execute Order
    abstract public void executeOrder();

    /**
     * End game.
     */
// end command
    abstract public void endGame();

    /**
     * Next.
     */
// go to next phase
    abstract public void next();

    /**
     * Common method to all States.
     */
    public void printInvalidCommandMessage() {
        System.out.println("Invalid command in state " + this.getClass().getSimpleName() );
    }
}
