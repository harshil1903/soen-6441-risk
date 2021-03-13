package com.risk.phases;

import com.risk.main.GameEngine;
import com.risk.main.GameEngineNew;
import com.risk.main.Main;
import com.risk.main.MapEngine;

import java.util.List;

public abstract class Phase {
    GameEngineNew d_gameEngineNew;
    GameEngine d_gameEngine;
    MapEngine d_mapEngine;

    Phase(GameEngine p_gameEngine){
        d_gameEngine = p_gameEngine;
    }

    Phase(GameEngineNew p_gameEngine){
        d_gameEngineNew = p_gameEngine;
    }

    Phase(MapEngine p_mapEngine){
        d_mapEngine = p_mapEngine;
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


    //PreMapLoad
    abstract public void editMap(List<String> p_argumentTokens);

    //PostMopLoad commands
    abstract public void validateMap(List<String> p_argumentTokens);
    abstract public void showMap(List<String> p_argumentTokens);
    abstract public void saveMap(List<String> p_argumentTokens);
    abstract public void editCountry(List<String> p_argumentTokens);
    abstract public void editContinent(List<String> p_argumentTokens);
    abstract public void editNeighbor(List<String> p_argumentTokens);


    //GameStartup
    abstract public boolean loadMap(List<String> p_argumentList);
    abstract public void addPlayer(List<String> p_argumentTokens);
    abstract public boolean assignCountries();

    //MainGame
    // reinforcement commands
    abstract public void reinforce();

    //issue Order
    abstract public void issueOrder(String p_action, String p_arguments);

    //Execute Order
    abstract public void executeOrder();

    // end command
    abstract public void endGame();

    // go to next phase
    abstract public void next();

    /**
     *  Common method to all States.
     */
    public void printInvalidCommandMessage() {
        System.out.println("Invalid command in state " + this.getClass().getSimpleName() );
    }
}
