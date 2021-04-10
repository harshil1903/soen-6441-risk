package com.risk.gameutils;

import java.io.File;

/**
 * Helps in loading previously load game form the game file.
 * @author Chirag
 */
public class LoadGame {


    public static void loadGame(String p_gameFile) {
        String l_path = "src/main/resources/";
        String l_fileName = p_gameFile + ".game";
        File l_game = new File(l_path + l_fileName);
        if (l_game.exists())
            System.out.println("Game will be loaded");
        else
            System.out.println("Game file not found");
    }
}

