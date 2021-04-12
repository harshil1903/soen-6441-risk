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


/**
 * Helps in saving the game so it can be loaded next time
 *
 * @author Chirag
 */
public class SaveGame {

    /**
     * Process the map used in the game and add it to Game file
     *
     * @return the Map data to be added to game file.
     */
    public StringBuilder processMap(Map p_Map) {
        String l_mapName = p_Map.d_mapName;
        StringBuilder l_mapData = new StringBuilder();
        l_mapData.append("[map]");
        l_mapData.append("\n");
        l_mapData.append(l_mapName);
        l_mapData.append("\n");
        return l_mapData;
    }


    /**
     * Process the map used in the game and add it to Game file
     *
     * @return the Map data to be added to game file.
     */
    public StringBuilder processCountries(Map p_Map) {
        StringBuilder l_countryData = new StringBuilder();
        l_countryData.append("\n[countries]");
        for (Continent l_continent : p_Map.getD_Continents()) {
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
     *
     * @return the Map data to be added to game file.
     */
    public StringBuilder processPhase( Phase p_gamePhase) {
        String l_phaseName = p_gamePhase.getClass().getSimpleName();
        StringBuilder l_phaseData = new StringBuilder();
        l_phaseData.append("\n[phase]");
        l_phaseData.append("\n");
        l_phaseData.append(l_phaseName);
        l_phaseData.append("\n");
        return l_phaseData;
    }

    public StringBuilder processPlayers(ArrayList<Player> d_PlayerList) {
        int l_playerId = 1;
        StringBuilder l_playerData = new StringBuilder();
        l_playerData.append("\n");
        l_playerData.append("[players]");
        l_playerData.append("\n");
        for (Player l_player : d_PlayerList) {
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
     * //     *
     * //     * @param p_Map        Contains the Map data
     * //     * @param p_gamePhase  contains the current phase of the game
     * //     * @param d_PlayerList contains the list of all the players
     */
    public void saveGame(Map p_Map, Phase p_gamePhase, ArrayList<Player> d_PlayerListGame,String d_gameFileName){
        String l_path = "src/main/resources/";
        String l_fileName = d_gameFileName + "test2.game";
        File l_map = new File(l_path + l_fileName);
        FileWriter l_fileWriter;
        try {
            String l_content = "Write this to file please please write this to file";
            l_fileWriter = new FileWriter(l_map, false);
            StringBuilder l_mapData = processMap(p_Map);
            l_fileWriter.write(l_mapData.toString());

            StringBuilder l_phaseData = processPhase(p_gamePhase);
            l_fileWriter.write(l_phaseData.toString());

            StringBuilder l_countryData = processCountries(p_Map);
            l_fileWriter.write(l_countryData.toString());

            StringBuilder l_playerData = processPlayers(d_PlayerListGame);
            l_fileWriter.write(l_playerData.toString());

            l_fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
