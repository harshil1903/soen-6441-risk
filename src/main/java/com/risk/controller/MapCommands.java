package com.risk.controller;

import com.risk.exception.InvalidMapException;
import com.risk.maputils.*;

import java.io.File;
import java.util.List;

import static com.risk.main.Main.d_Map;

/**
 * The Map Commands class performs various map related commands.
 * It verifies whether the arguments provided for the commands are valid or not.
 *
 * @author Harshil
 */
public class MapCommands {

    /**
     * Validates the command arguments and then loads the map.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     * @return true if map is successfully loaded into the Game
     * @throws InvalidMapException Invalid Map Exception
     */
    public static boolean editMapCommand(List<String> p_ArgumentTokens) throws InvalidMapException {
        if (p_ArgumentTokens.stream().count() != 1) {
            System.out.println("Wrong Number of Arguments provided. editmap command has only one argument.");
            return false;
        }

        d_Map.getD_Continents().clear();

        try {
            d_Map = new EditMap().editMap(p_ArgumentTokens.get(0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new InvalidMapException(e.getMessage());

        }

        return true;
    }


    /**
     * Validates the command arguments and then save the map.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void saveMapCommand(List<String> p_ArgumentTokens) {
        if (p_ArgumentTokens.stream().count() != 1) {
            System.out.println("Wrong Number of Arguments provided. savemap command has only one argument.");
            return;
        }

        File l_File = new File("src/main/resources/" + p_ArgumentTokens.get(0) + ".map");

        new MapWriter().writeMapFile(d_Map, l_File);

    }

    /**
     * Validates the map.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void validateMapCommand(List<String> p_ArgumentTokens) {
        if (p_ArgumentTokens.stream().count() != 0) {
            System.out.println("Wrong Number of Arguments provided. validatemap command has no argument.");
            return;
        }

        try {
            MapValidator.validateMap(d_Map);
        } catch (Exception e) {
            System.out.println("Map Validation Failed");
            System.out.println(e.getMessage());
        }

    }

    /**
     * Validates the command arguments and then displays the map.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void showMapCommand(List<String> p_ArgumentTokens) {
        if (p_ArgumentTokens.stream().count() != 0) {
            System.out.println("Wrong Number of Arguments provided. showmap command has no argument.");
        }

        ShowMap.displayEditorMap(d_Map);

    }

    /**
     * Validates the command arguments and then edits the Continent information accordingly.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void editContinentCommand(List<String> p_ArgumentTokens) {

        String l_ContinentName;
        int l_ContinentValue;

        for (int i = 0; i < p_ArgumentTokens.size(); i++) {
            if (p_ArgumentTokens.get(i).equals("-add")) {
                try {
                    l_ContinentName = p_ArgumentTokens.get(++i);
                    l_ContinentValue = Integer.parseInt(p_ArgumentTokens.get(++i));
                    System.out.println("Continent ID: " + l_ContinentName + " Control Value: " + l_ContinentValue);

                    //CALL ADD CONTINENT FUNCTION
                    MapOperations.addContinent(d_Map, l_ContinentName, l_ContinentValue);

                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. add option has 2 arguments");
                    return;
                }
            } else if (p_ArgumentTokens.get(i).equals("-remove")) {
                try {
                    l_ContinentName = p_ArgumentTokens.get(++i);
                    System.out.println("Continent ID: " + l_ContinentName);

                    //CALL REMOVE CONTINENT FUNCTION
                    MapOperations.removeContinent(d_Map, l_ContinentName);

                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. remove option has 1 argument");
                    e.printStackTrace();
                    return;
                }
            } else {
                System.out.println("Invalid option. editneighbor has -add and -remove options only");
            }

        }

    }

    /**
     * Validates the command arguments and then edits the country information accordingly.
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void editCountryCommand(List<String> p_ArgumentTokens) {

        String l_CountryName;
        String l_ContinentName;

        for (int i = 0; i < p_ArgumentTokens.size(); i++) {
            if (p_ArgumentTokens.get(i).equals("-add")) {
                try {
                    l_CountryName = p_ArgumentTokens.get(++i);
                    l_ContinentName = p_ArgumentTokens.get(++i);

                    System.out.println("Country ID: " + l_CountryName + " Continent ID: " + l_ContinentName);

                    //CALL ADD COUNTRY FUNCTION
                    MapOperations.addCountry(d_Map, l_CountryName, l_ContinentName);

                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. add option has 2 arguments");
                    return;
                }
            } else if (p_ArgumentTokens.get(i).equals("-remove")) {
                try {
                    l_CountryName = p_ArgumentTokens.get(++i);
                    System.out.println("Country ID: " + l_CountryName);

                    //CALL REMOVE COUNTRY FUNCTION
                    MapOperations.removeCountry(d_Map, l_CountryName);

                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. remove option has 1 argument");
                    return;
                }
            } else {
                System.out.println("Invalid option. editneighbor has -add and -remove options only");
            }

        }
    }

    /**
     * Validates the command arguments and then edits the neighbor of the country
     *
     * @param p_ArgumentTokens list of arguments provided with the command
     */
    public static void editNeighborCommand(List<String> p_ArgumentTokens) {

        String l_CountryName;
        String l_NeighborCountryName;

        for (int i = 0; i < p_ArgumentTokens.size(); i++) {
            if (p_ArgumentTokens.get(i).equals("-add")) {
                try {
                    l_CountryName = p_ArgumentTokens.get(++i);
                    l_NeighborCountryName = p_ArgumentTokens.get(++i);

                    System.out.println("Country ID: " + l_CountryName + " Neighbor Country ID: " + l_NeighborCountryName);

                    //CALL ADD NEIGHBOR COUNTRY FUNCTION
                    MapOperations.addNeighborCountry(d_Map, l_NeighborCountryName, l_CountryName);

                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. add option has 2 arguments");
                    return;
                }
            } else if (p_ArgumentTokens.get(i).equals("-remove")) {
                try {
                    l_CountryName = p_ArgumentTokens.get(++i);
                    l_NeighborCountryName = p_ArgumentTokens.get(++i);

                    System.out.println("Country ID: " + l_CountryName + " Neighbor Country ID: " + l_NeighborCountryName);

                    //CALL REMOVE NEIGHBOR COUNTRY FUNCTION
                    MapOperations.removeNeighborCountry(d_Map, l_NeighborCountryName, l_CountryName);

                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. remove option has 2 argument");
                    return;
                }
            } else {
                System.out.println("Invalid option. editneighbor has -add and -remove options only");
            }

        }

    }


}
