package com.risk.main;


import com.risk.controller.CommandParser;
import com.risk.exception.InvalidCommand;

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

    private static void compareCommand(String p_Command)
    {
            String l_Action = p_Command.split(" ")[0];

            String l_Arguments = p_Command.substring(l_Action.length());

            String l_CommandType = getCommandType(l_Action);


            if(l_CommandType.equals("Map Command"))
            {
                CommandParser.mapCommandParser(l_Action,l_Arguments);
            }
            else if(l_CommandType.equals("Game Command"))
            {
                //Call GAME ENGINE to Take over and initiate Game. Dont return here.
                CommandParser.gameCommandParser(l_Action,l_Arguments);

            }
            else
            {
                //new InvalidCommand("Invalid Command");
                System.out.println("Invalid Command, Try again");
            }

    }

    private static String getCommandType(String l_action)
    {
        List<String> l_GameCommands = Arrays.asList("showmap", "loadmap", "gameplayer", "assigncountries", "deploy");

        List<String> l_MapCommands = Arrays.asList("editmap", "validatemap", "savemap", "editcontinent", "editcountry", "editneighbour", "showmap");

        if(l_MapCommands.contains(l_action))
        {
            return("Map Command");
        }

        if(l_GameCommands.contains(l_action))
        {
            return("Game Command");
        }

        return ("Invalid Command");
    }

}
