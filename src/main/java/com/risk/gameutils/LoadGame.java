package com.risk.gameutils;

import com.risk.models.Map;
import com.risk.models.Player;
import com.risk.phases.Phase;

import java.io.File;
import java.util.ArrayList;

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


    public static void loadGame(String p_gameFile) {
        String l_path = "src/main/resources/";
        String l_fileName = p_gameFile + ".game";
        File l_game = new File(l_path + l_fileName);
        if (l_game.exists()){
            System.out.println("Game will be loaded");

        }

        else
            System.out.println("Game file not found");
    }

    public static void main(String[] args) {
        LoadGame.loadGame("Game1");
    }
}

