package com.risk.gameutils;

import com.risk.exception.InvalidMapException;
import com.risk.maputils.EditMap;
import com.risk.maputils.ShowMap;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;
import com.risk.models.Player;
import com.risk.phases.Phase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Helps in loading previously load game form the game file.
 * @author Chirag
 */
public class LoadGame {

    private static Map d_Map=new Map();
    private static ArrayList<Player> d_PlayerList = new ArrayList<Player>();
    private  static Phase d_GamePhase;

    /**
     * returns map data member.
     * @return Map to be returned.
     */
    public static Map getD_Map() {return d_Map;}

    /**
     * returns list of player
     * @return arraylist of player
     */
    public static ArrayList<Player> getD_PlayerList(){ return d_PlayerList;}

    /**
     * returns phase of the saved game
     * @return current phase of the game
     */
    public static Phase getD_GamePhase(){return d_GamePhase;}



    public static void fillMap(Scanner p_gameReader) throws InvalidMapException {
        while (p_gameReader.hasNextLine()) {
            String l_line = p_gameReader.nextLine();
            if (l_line.equals("")) break;
            String[] l_parts = l_line.split(" ");
            String p_mapName=l_parts[0];
            d_Map= EditMap.editMap(p_mapName);
        }
    }


    public static void loadGame(String p_gameFile) {
        String l_path = "src/main/resources/";
        String l_fileName = p_gameFile + ".game";
        File l_game = new File(l_path + l_fileName);
        if (l_game.exists()) {
            Scanner l_gameReader = null;
            try {
                l_gameReader = new Scanner(l_game);
                while (l_gameReader.hasNextLine()) {
                    String l_line = l_gameReader.nextLine();
                    if (l_line.equals("[map]")) {
                        fillMap(l_gameReader);
                    }
//                    if (l_line.equals("[countries]")) {
//                        getCountries(l_gameReader, p_Map);
//                    }
//                    if (l_line.equals("[borders]")) {
//                        getAdjacentCountries(l_gameReader, p_Map);
//                    }
                }
                System.out.println("Loaded map successfully form existing domination file");


                System.out.println("Game will be loaded");

            } catch (FileNotFoundException | InvalidMapException e) {
                System.out.println("Game file not found");
            }


        }
    }
    public static void main(String[] args) {
        LoadGame.loadGame("Game1");
        ShowMap.displayEditorMap(d_Map);

    }
}

