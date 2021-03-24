package com.risk.main;

import com.risk.gamelogs.LogWriter;
import com.risk.models.Map;
import com.risk.models.Player;
import com.risk.gamelogs.LogEntryBuffer;

import java.util.ArrayList;

/**
 * This is main entrance point of the program. The execution of the game begins from here.
 *
 * @author Harshil
 */
public class Main {
    /**
     * Contains map data to be used.
     */
    public static Map d_Map = new Map();
    /**
     * Contains list of players.
     */
    public static ArrayList<Player> d_PlayerList = new ArrayList<Player>();

    public static Player d_NeutralPlayer = new Player();

    public static LogEntryBuffer d_Log = new LogEntryBuffer();

    public static LogWriter d_LogWriter = new LogWriter(d_Log);

    /**
     * The entry point of application
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to WARZONE");
        d_Log.notify("Welcome to warzone");

        System.out.println("GAME BEGINS\n\n");
        //d_Log.notify("Game Begins");

        GameEngine l_gameEngine = new GameEngine();

        l_gameEngine.runGame();

    }

}
