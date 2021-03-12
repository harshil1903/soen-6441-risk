package com.risk.phases;

import com.risk.main.GameEngine;
import com.risk.main.Main;
import com.risk.main.MapEngine;

import java.util.List;

public abstract class Phase {

    GameEngine d_gameEngine;
    MapEngine d_mapEngine;
    Main d_main;

    Phase(Main p_main){
        d_main = p_main;
    }

    Phase(GameEngine p_gameEngine){
        d_gameEngine = p_gameEngine;
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
    abstract public void editMap();

    //PostMopLoad commands
    abstract public void validateMap();
    abstract public void showMap();
    abstract public void saveMap();
    abstract public void editCountry();
    abstract public void editContinent();
    abstract public void editNeighbor();


    //GameSetup
    abstract public void loadMap(List<String> p_argumentList);
    abstract public void showGameMap();
    abstract public void addPlayer(List<String> p_argumentTokens);
    abstract public void assignCountries();

    //MainGame
    // reinforcement commands
    abstract public void reinforce();

    //issue Order
    abstract public void issueOrder();

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
