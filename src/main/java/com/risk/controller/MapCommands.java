package com.risk.controller;

import java.util.Arrays;
import java.util.List;

/**
 * The Map Commands class performs various map related commands.
 * It verifies whether the arguments provided for the commands are valid or not.
 * @author Harshil
 */
public class MapCommands {

    /**
     * Validates the command arguments and then loads the map.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     * @return true if map is successfully loaded into the Game
     */
    public static boolean editMapCommand(List<String> p_ArgumentTokens)
    {
        //Validate the Argument Token list and call the map reader command
        if(p_ArgumentTokens.stream().count() != 1)
        {
            System.out.println("Wrong Number of Arguments provided. editmap command has only one argument.");
            return false;
        }

        //Call MAP READER Function by passing filename.
        System.out.println("Reached Edit Map Command. Argument List : " + p_ArgumentTokens);
        return true;
    }


    /**
     * Validates the command arguments and then save the map.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void saveMapCommand(List<String> p_ArgumentTokens)
    {
        if(p_ArgumentTokens.stream().count() != 1)
        {
            System.out.println("Wrong Number of Arguments provided. savemap command has only one argument.");
            return;
        }

        //Call MAP Writer Function by passing filename.
        System.out.println("Reached Save Map Command. Argument List : " + p_ArgumentTokens);
    }

    /**
     * Validates the map.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void validateMapCommand(List<String> p_ArgumentTokens)
    {
        if(p_ArgumentTokens.stream().count() != 0)
        {
            System.out.println("Wrong Number of Arguments provided. validatemap command has no argument.");
            return;
        }

        //Call MAP Validator Function to validate the map
        System.out.println("Reached Validate Map Command. Argument List : " + p_ArgumentTokens);
    }

    /**
     * Validates the command arguments and then displays the map.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void showMapCommand(List<String> p_ArgumentTokens)
    {
        if(p_ArgumentTokens.stream().count() != 0)
        {
            System.out.println("Wrong Number of Arguments provided. showmap command has no argument.");
        }

        //Call MAP Display method.
        System.out.println("Reached Show Map Command. Argument List : " + p_ArgumentTokens);
    }

    /**
     * Validates the command arguments and then edits the Continent information accordingly.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void editContinentCommand(List<String> p_ArgumentTokens)
    {
            //Check arguments and their counts
            //-add needs two more arguments
            //-remove needs one more argument
    }

    /**
     * Validates the command arguments and then edits the country information accordingly.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void editCountryCommand(List<String> p_ArgumentTokens)
    {
        //Check arguments and their counts
        //-add needs two more arguments
        //-remove needs one more argument
    }

    /**
     * Validates the command arguments and then edits the neighbor of the country
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void editNeighborCommand(List<String> p_ArgumentTokens)
    {
        //Check arguments and their counts
        //-add needs two more arguments
        //-remove needs two more argument
    }



}
