package com.risk.main;


import com.risk.controller.MapCommandParser;
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
    public static Map d_Map = new Map();
    public static ArrayList<Player> d_PlayerList = new ArrayList<Player>();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)
    {
        System.out.println("Welcome to WARZONE");
        System.out.println("GAME BEGINS");
        runGame();
    }

    /**
     * The Game execution begins from this method.
     */
    public static void runGame()
    {
        Scanner l_Scanner = new Scanner(System.in);
        String l_Command;

        System.out.println("Enter command: ");
        l_Command = l_Scanner.nextLine();

        while (!l_Command.equals("EXIT"))
        {
            compareCommand(l_Command);
            System.out.println("Enter command: ");
            l_Command = l_Scanner.nextLine();
        }

        System.out.println("GAME HAS ENDED");

    }

    /**
     * The method compares the command input from user to check what type of command is entered.
     *
     * @param p_Command command line input
     */
    public static void compareCommand(String p_Command)
    {
            String l_Action = p_Command.split(" ")[0];

            String l_Arguments = p_Command.substring(l_Action.length());

            List<String> l_GameCommands = Arrays.asList("showmap", "loadmap", "gameplayer", "assigncountries", "deploy");

            List<String> l_MapCommands = Arrays.asList("editmap", "validatemap", "savemap", "editcontinent", "editcountry", "editneighbor", "showmap");

            if(l_MapCommands.contains(l_Action))
            {
                MapCommandParser.mapCommandParser(l_Action,l_Arguments);
            }
            else if(l_GameCommands.contains(l_Action))
            {
                //Call GAME ENGINE to Take over and initiate Game. Dont return here.
                GameEngine.gameCommandParser(l_Action,l_Arguments);

            }
            else
            {
                //new InvalidCommand("Invalid Command");
                System.out.println("Invalid Command, Try again");
            }

    }



}
