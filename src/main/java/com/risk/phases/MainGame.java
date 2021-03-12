package com.risk.phases;

import com.risk.main.GameEngine;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Player;

import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;

public abstract class MainGame extends Game {

    MainGame(GameEngine p_gameEngine) {
        super(p_gameEngine);
    }

    public void loadmap(){
        printInvalidCommandMessage();
    }
    public void showGameMap(){
        //Call showGameMap Operation here
    }

    public void addPlayer(){
        printInvalidCommandMessage();
    }

    public void assignCountries(){
        printInvalidCommandMessage();
    }

    public void reinforce() {
        for (Player l_player : d_PlayerList) {
            int l_countriesOwned = l_player.getD_AssignedCountries().size();
            int l_reinforcementCount, l_flag;
            String l_playerName = l_player.getD_PlayerName();

            l_reinforcementCount = Math.max((l_countriesOwned / 3), 3);

            for (Continent l_continent : d_Map.getD_Continents()) {
                l_flag = 0;
                for (Country l_country : l_continent.getD_Countries()) {
                    if (!l_country.getD_Player().getD_PlayerName().equals(l_playerName)) {
                        l_flag = 1;
                        break;
                    }
                }
                if (l_flag == 0) {
                    l_reinforcementCount += l_continent.getD_ContinentValue();
                }
            }

            l_player.setD_Armies(l_reinforcementCount);
        }
    }

    public void issueOrder() {
        printInvalidCommandMessage();
    }

    public void executeOrder() {
        printInvalidCommandMessage();
    }

    public void endGame() {
        printInvalidCommandMessage();
    }

}