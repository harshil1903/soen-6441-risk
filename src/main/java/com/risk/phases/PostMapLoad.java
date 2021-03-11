package com.risk.phases;

import com.risk.main.MapEngine;

public class PostMapLoad extends MapEdit{

    public PostMapLoad(MapEngine p_mapEngine) {
        super(p_mapEngine);
    }

    public void editMap() {
        printInvalidCommandMessage();
    }

    public void validateMap(){
        //Call ValidateMap Operation from here.
    }

    public void showMap(){
        //Call showMap Operation from here.
    }

    public void saveMap(){
        //Call saveMap Operation from here.
    }

    public void editCountry(){
        //Call EditCountry Operation from here.
    }

    public void editContinent(){
        //Call EditContinent Operation from here.
    }

    public void editNeighbor(){
        //Call EditNeighbor Operation from here.
    }
}
