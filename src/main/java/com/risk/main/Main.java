package com.risk.main;

import com.risk.models.Map;
import com.risk.models.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    /**
     * The entry point of application
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome to WARZONE");
        System.out.println("GAME BEGINS\n\n");

        Scanner l_scanner = new Scanner(System.in);
        String l_command;

        GameEngineNew l_gameEngineNew = new GameEngineNew();

        System.out.println("Enter command: ");
        l_command = l_scanner.nextLine();

        if(l_command.equals("NEW")){
            l_gameEngineNew.runGame();
        }
        //runGame();
    }

    /**
     * The Game execution begins from this method.
     */
    public static void runGame() {
        Scanner l_scanner = new Scanner(System.in);
        String l_command;

        System.out.println("Enter command: ");
        l_command = l_scanner.nextLine();


        while (!l_command.equals("EXIT")) {
            compareCommand(l_command);
            System.out.println("Enter command: ");
            l_command = l_scanner.nextLine();
        }

        System.out.println("GAME HAS ENDED");

    }

    /**
     * The method compares the command input from user to check what type of command is entered.
     *
     * @param p_command command line input
     */
    public static void compareCommand(String p_command) {
        String l_action = p_command.split(" ")[0];

        String l_arguments = p_command.substring(l_action.length());

        List<String> l_gameCommands = Arrays.asList("loadmap");

        List<String> l_mapCommands = Arrays.asList("editmap", "validatemap", "savemap", "editcontinent", "editcountry", "editneighbor", "showmap");

        if (l_mapCommands.contains(l_action)) {
            MapEngine.mapCommandParser(l_action, l_arguments);
        } else if (l_gameCommands.contains(l_action)) {
            GameEngine.checkNextGameCommands(l_action, l_arguments);

        } else {
            System.out.println("Invalid Command, Try again");
        }

    }


}
