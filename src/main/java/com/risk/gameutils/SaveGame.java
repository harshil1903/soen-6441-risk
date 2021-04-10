package com.risk.gameutils;

import com.risk.exception.InvalidMapException;
import com.risk.maputils.EditMap;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;
import com.risk.models.Player;
import com.risk.phases.Phase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static com.risk.gameutils.AssignCountriesGame.assignCountries;
import static com.risk.main.Main.d_Log;
import static com.risk.main.Main.d_PlayerList;
import static com.risk.maputils.ShowMap.displayGameMap;

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
    public StringBuilder processMap() {
        String p_mapName = "europe";
        StringBuilder l_mapData = new StringBuilder();
        l_mapData.append("[map]");
        l_mapData.append("\n");
        l_mapData.append(p_mapName);
        l_mapData.append("\n");
        return l_mapData;
    }


    public StringBuilder processPlayers(ArrayList<Player> d_PlayerList){
        StringBuilder l_mapData = new StringBuilder();
        l_mapData.append("\n");
        l_mapData.append("[players]");
        l_mapData.append("\n");
        for(Player player:d_PlayerList){
            l_mapData.append(player.getD_PlayerName());
            //l_mapData.append("\n \n");
            l_mapData.append("\nCountries Owned: ");
            for(Country l_country:player.getD_AssignedCountries()){
                l_mapData.append(l_country.getD_CountryName()+" ");
            }
            l_mapData.append("\nCards: ");

            for(String card:player.getD_cardList()){
                l_mapData.append(card+" ");
            }
            l_mapData.append("\nNo of Armies:");
            l_mapData.append(player.getD_Armies());
            l_mapData.append("\n \n");
        }
        l_mapData.append("\n");
        return l_mapData;
    }

    /**
     * entry point for the SaveGame class helps in saving the current game
     * //     *
     * //     * @param p_Map        Contains the Map data
     * //     * @param p_gamePhase  contains the current phase of the game
     * //     * @param d_PlayerList contains the list of all the players
     */
    //public void saveGame(Map p_Map, Phase p_gamePhase, ArrayList<Player> d_PlayerList,String d_fileName){
    public void saveGame(String d_fileName,ArrayList<Player> d_PlayerListGame) {
        String p_mapName = "europe";


        String l_path = "src/main/resources/";
        String l_fileName = d_fileName + ".game";
        File l_map = new File(l_path + l_fileName);
        FileWriter l_fileWriter;
        try {
            String l_content = "Write this to file please please write this to file";
            l_fileWriter = new FileWriter(l_map, false);
            StringBuilder l_mapData = processMap();
            l_fileWriter.write(l_mapData.toString());
            StringBuilder l_playerData = processPlayers(d_PlayerListGame);
            l_fileWriter.write(l_playerData.toString());
            l_fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


//    public static void main(String[] args) throws InvalidMapException {
//        SaveGame sg = new SaveGame();
//
//        /**
//         * Contains list of players.
//         */
//        ArrayList<Player> d_PlayerListGame = new ArrayList<Player>();
//        Player P1 = new Player("P1");
//        Player P2 = new Player("P2");
//        Player P3 = new Player("P3");
//        P1.addCard("first");
//        P2.addCard("first");
//        P2.addCard("first");
//        P3.addCard("first");
//        P3.addCard("first");
//        P3.addCard("last");
//        d_PlayerListGame.add(P1);
//        d_PlayerListGame.add(P2);
//        d_PlayerListGame.add(P3);
//
//        Map d_Map= EditMap.editMap("europe");
//        assignCountries(d_PlayerListGame,d_Map);
//        sg.saveGame("Game1",d_PlayerListGame);
//        //displayGameMap(d_Map);
//
//    }
}
