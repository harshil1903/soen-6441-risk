package com.risk.phases;

import com.risk.main.MapEngine;

public abstract class MapEdit extends Phase {

    MapEdit(MapEngine p_mapEngine){
        super(p_mapEngine);
    }

    //PreMapLoad
    public void editMap(){
        printInvalidCommandMessage();
    }

    //PostMopLoad commands
    public void validateMap(){
        printInvalidCommandMessage();
    }
    public void showMap(){
        printInvalidCommandMessage();
    }
    public void saveMap(){
        printInvalidCommandMessage();
    }
    public void editCountry(){
        printInvalidCommandMessage();
    }
    public void editContinent(){
        printInvalidCommandMessage();
    }
    public void editNeighbor(){
        printInvalidCommandMessage();
    }

}
