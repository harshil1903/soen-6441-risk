package com.risk.controller;

import com.risk.maputils.EditMap;
import com.risk.maputils.ShowMap;

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
        if(p_ArgumentTokens.stream().count() != 1)
        {
            System.out.println("Wrong Number of Arguments provided. editmap command has only one argument.");
            return false;
        }


        //Call MAP READER Function by passing filename.
        new EditMap();

        //System.out.println("Reached Edit Map Command. Argument List : " + p_ArgumentTokens);
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


        //System.out.println("Reached Save Map Command. Argument List : " + p_ArgumentTokens);
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


        //System.out.println("Reached Validate Map Command. Argument List : " + p_ArgumentTokens);
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
        //ShowMap.displayEditorMap();

        //System.out.println("Reached Show Map Command. Argument List : " + p_ArgumentTokens);
    }

    /**
     * Validates the command arguments and then edits the Continent information accordingly.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void editContinentCommand(List<String> p_ArgumentTokens)
    {

            int l_ContinentID;
            int l_ContinentValue;

            for(int i = 0; i < p_ArgumentTokens.size() ; i++)
            {
                if(p_ArgumentTokens.get(i).equals("-add"))
                {
                    try
                    {
                        l_ContinentID = Integer.parseInt(p_ArgumentTokens.get(++i));
                        l_ContinentValue =Integer.parseInt(p_ArgumentTokens.get(++i));
                        System.out.println("Continent ID: " + l_ContinentID + " Control Value: " + l_ContinentValue);



                        //CALL ADD CONTINENT FUNCTION



                    }
                    catch (Exception e)
                    {
                        System.out.println("Wrong number of Arguments provided. add option has 2 arguments");
                        return;
                    }
                }

                else if(p_ArgumentTokens.get(i).equals("-remove"))
                {
                    try
                    {
                        l_ContinentID = Integer.parseInt(p_ArgumentTokens.get(++i));
                        System.out.println("Continent ID: " + l_ContinentID);



                        //CALL REMOVE CONTINENT FUNCTION



                    }
                    catch (Exception e)
                    {
                        System.out.println("Wrong number of Arguments provided. remove option has 1 argument");
                        return;
                    }
                }

                else
                {
                    System.out.println("Invalid option. editneighbor has -add and -remove options only");
                }

            }

    }

    /**
     * Validates the command arguments and then edits the country information accordingly.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void editCountryCommand(List<String> p_ArgumentTokens)
    {

        int l_CountryID;
        int l_ContinentID;

        for(int i = 0; i < p_ArgumentTokens.size() ; i++)
        {
            if(p_ArgumentTokens.get(i).equals("-add"))
            {
                try
                {
                    l_CountryID =Integer.parseInt(p_ArgumentTokens.get(++i));
                    l_ContinentID = Integer.parseInt(p_ArgumentTokens.get(++i));

                    System.out.println("Country ID: " + l_CountryID + " Continent ID: " + l_ContinentID);



                    //CALL ADD COUNTRY FUNCTION



                }
                catch (Exception e)
                {
                    System.out.println("Wrong number of Arguments provided. add option has 2 arguments");
                    return;
                }
            }

            else if(p_ArgumentTokens.get(i).equals("-remove"))
            {
                try
                {
                    l_CountryID = Integer.parseInt(p_ArgumentTokens.get(++i));
                    System.out.println("Country ID: " + l_CountryID);



                    //CALL REMOVE COUNTRY FUNCTION



                }
                catch (Exception e)
                {
                    System.out.println("Wrong number of Arguments provided. remove option has 1 argument");
                    return;
                }
            }

            else
            {
                System.out.println("Invalid option. editneighbor has -add and -remove options only");
            }

        }
    }

    /**
     * Validates the command arguments and then edits the neighbor of the country
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void editNeighborCommand(List<String> p_ArgumentTokens)
    {

        int l_CountryID;
        int l_NeighborCountryID;

        for(int i = 0; i < p_ArgumentTokens.size() ; i++)
        {
            if(p_ArgumentTokens.get(i).equals("-add"))
            {
                try
                {
                    l_CountryID =Integer.parseInt(p_ArgumentTokens.get(++i));
                    l_NeighborCountryID = Integer.parseInt(p_ArgumentTokens.get(++i));

                    System.out.println("Country ID: " + l_CountryID + " Neighbor Country ID: " + l_NeighborCountryID);



                    //CALL ADD NEIGHBOR COUNTRY FUNCTION



                }
                catch (Exception e)
                {
                    System.out.println("Wrong number of Arguments provided. add option has 2 arguments");
                    return;
                }
            }

            else if(p_ArgumentTokens.get(i).equals("-remove"))
            {
                try
                {
                    l_CountryID = Integer.parseInt(p_ArgumentTokens.get(++i));
                    l_NeighborCountryID = Integer.parseInt(p_ArgumentTokens.get(++i));

                    System.out.println("Country ID: " + l_CountryID + " Neighbor Country ID: " + l_NeighborCountryID);



                    //CALL REMOVE NEIGHBOR COUNTRY FUNCTION



                }
                catch (Exception e)
                {
                    System.out.println("Wrong number of Arguments provided. remove option has 2 argument");
                    return;
                }
            }

            else
            {
                System.out.println("Invalid option. editneighbor has -add and -remove options only");
            }

        }

    }



}
