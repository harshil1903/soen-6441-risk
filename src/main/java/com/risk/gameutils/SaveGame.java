package com.risk.gameutils;

import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;
import com.risk.models.Player;
import com.risk.phases.Phase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.risk.main.Main.d_Log;


/**
 * Helps in saving the game so it can be loaded next time
 *
 * @author Chirag
 */
public class SaveGame {

    /**
     * Process the map used in the game and add it to Game file
     *
     * @param p_map Map to be used
     * @return the Map data to be added to game file.
     */
    public StringBuilder processMap(Map p_map) {
        String l_mapName = p_map.d_mapName;
        StringBuilder l_mapData = new StringBuilder();
        l_mapData.append("[map]");
        l_mapData.append("\n");
        l_mapData.append(l_mapName + " " + p_map.d_mapType);
        l_mapData.append("\n");
        return l_mapData;
    }


    /**
     * Process the countries used in the game and add it to Game file
     *
     * @param p_map Map to be used
     * @return the country data to be added to game file.
     */
    public StringBuilder processCountries(Map p_map) {
        StringBuilder l_countryData = new StringBuilder();
        l_countryData.append("\n[countries]");
        for (Continent l_continent : p_map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                l_countryData.append("\n" + l_country.getD_CountryName() + "," + l_country.getD_ContinentID()
                        + "," + l_country.getD_NumberOfArmies());
            }
        }
        l_countryData.append("\n");
        return l_countryData;
    }

    /**
     * Process the current phase of the game and add it to Game file
     * @param p_gamePhase Game phase to be saved.
     * @return the phase data to be added to game file.
     */
    public StringBuilder processPhase(Phase p_gamePhase) {
        String l_phaseName = p_gamePhase.getClass().getSimpleName();
        StringBuilder l_phaseData = new StringBuilder();
        l_phaseData.append("\n[phase]");
        l_phaseData.append("\n");
        l_phaseData.append(l_phaseName);
        l_phaseData.append("\n");
        return l_phaseData;
    }


    /**
     * Process the players of the game and add it to Game file
     * @param d_playerList list of players to be saved.
     * @return the phase data to be added to game file.
     */
    public StringBuilder processPlayers(ArrayList<Player> d_playerList) {
        int l_playerId = 1;
        StringBuilder l_playerData = new StringBuilder();
        l_playerData.append("\n");
        l_playerData.append("[players]");
        l_playerData.append("\n");
        for (Player l_player : d_playerList) {
            l_playerData.append(l_playerId + " " + l_player.getD_PlayerName() + " " + l_player.d_playerStrategyType);
            //l_mapData.append("\n \n");
            l_playerData.append("\nCountries Owned: ");
            for (Country l_country : l_player.getD_AssignedCountries()) {
                l_playerData.append(l_country.getD_CountryName() + ",");
            }
            l_playerData.append("\nCards: ");

            for (String card : l_player.getD_cardList()) {
                l_playerData.append(card + " ");
            }
            l_playerData.append("\nArmies: ");
            l_playerData.append(l_player.getD_Armies());
            l_playerData.append("\n\n");
            l_playerId++;
        }
        l_playerData.append("\n");
        return l_playerData;
    }

    /**
     * entry point for the SaveGame class helps in saving the current game
     *
     * @param p_map            Contains the Map data
     * @param p_gamePhase      contains the current phase of the game
     * @param p_playerListGame contains the list of all the players
     * @param p_gameFileName   file name of the game to be saved
     */
    public void saveGame(Map p_map, Phase p_gamePhase, ArrayList<Player> p_playerListGame, String p_gameFileName) {
        String l_path = "src/main/resources/";
        String l_fileName = p_gameFileName + ".game";
        File l_map = new File(l_path + l_fileName);
        FileWriter l_fileWriter;
        try {
            l_fileWriter = new FileWriter(l_map, false);
            StringBuilder l_mapData = processMap(p_map);
            l_fileWriter.write(l_mapData.toString());

            StringBuilder l_phaseData = processPhase(p_gamePhase);
            l_fileWriter.write(l_phaseData.toString());

            StringBuilder l_countryData = processCountries(p_map);
            l_fileWriter.write(l_countryData.toString());

            StringBuilder l_playerData = processPlayers(p_playerListGame);
            l_fileWriter.write(l_playerData.toString());

            l_fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            d_Log.notify(e.getMessage());

        }
    }
}
