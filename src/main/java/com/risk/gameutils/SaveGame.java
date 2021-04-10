package com.risk.gameutils;

import com.risk.models.Continent;
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
     * entry point for the SaveGame class helps in saving the current game
//     *
//     * @param p_Map        Contains the Map data
//     * @param p_gamePhase  contains the current phase of the game
//     * @param d_PlayerList contains the list of all the players
     */
    //public void saveGame(Map p_Map, Phase p_gamePhase, ArrayList<Player> d_PlayerList,String d_fileName){
    public void saveGame(String d_fileName) {
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
            l_fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * Process the map used in the game and add it to Game file
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


//    public static void main(String[] args) {
//        SaveGame sg = new SaveGame();
//        sg.saveGame("Game1");
//    }
}
