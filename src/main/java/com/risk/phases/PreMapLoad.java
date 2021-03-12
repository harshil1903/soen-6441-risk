package com.risk.phases;

import com.risk.main.GameEngine;
import com.risk.main.MapEngine;

public class PreMapLoad extends MapEdit {

    public PreMapLoad(MapEngine p_mapEngine) {
        super(p_mapEngine);
    }

    public PreMapLoad(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }

    public void editMap() {
        //Call EditMap Operation from here.
    }

    public void validateMap() {
        printInvalidCommandMessage();
    }

    public void showMap() {
        printInvalidCommandMessage();
    }

    public void saveMap() {
        printInvalidCommandMessage();
    }

    public void editCountry() {
        printInvalidCommandMessage();
    }

    public void editContinent() {
        printInvalidCommandMessage();
    }

    public void editNeighbor() {
        printInvalidCommandMessage();

    }

    public void next() {

    }
}
