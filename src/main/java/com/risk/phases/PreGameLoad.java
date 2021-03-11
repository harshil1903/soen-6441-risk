package com.risk.phases;

import com.risk.main.GameEngine;

public class PreGameLoad extends GamePlay{

    public PreGameLoad(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }

    @Override
    public void editMap() {

    }

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

    @Override
    public void loadMap() {

    }

    @Override
    public void showGameMap() {

    }

    @Override
    public void addplayer() {

    }

    @Override
    public void assignCountries() {

    }

    @Override
    public void reinforce() {

    }

    @Override
    public void issueOrder() {

    }

    @Override
    public void executeOrder() {

    }

    @Override
    public void endGame() {

    }

    @Override
    public void next() {

    }
}
