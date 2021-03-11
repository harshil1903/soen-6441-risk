package com.risk.phases;

import com.risk.main.GameEngine;
import com.risk.main.Main;
import com.risk.main.MapEngine;

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
            editmap()
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
     *  Common method to all States.
     */
    public void printInvalidCommandMessage() {
        System.out.println("Invalid command in state " + this.getClass().getSimpleName() );
    }
}
