package com.risk.controller;

import com.risk.exception.InvalidMapException;
import com.risk.maputils.*;

import java.io.File;
import java.util.List;

import static com.risk.main.Main.d_Log;
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
     * @param p_argumentTokens list of arguments provided with the command
     * @return true if map is successfully loaded into the Game
     * @throws InvalidMapException Invalid Map Exception
     */
    public static boolean editMapCommand(List<String> p_argumentTokens) throws InvalidMapException {
        if (p_argumentTokens.stream().count() != 1) {
            System.out.println("Wrong Number of Arguments provided. editmap command has only one argument.");
            d_Log.notify("Wrong Number of Arguments provided. editmap command has only one argument.");
            return false;
        }

        d_Map.getD_Continents().clear();

        try {
            d_Map = new EditMap().editMap(p_argumentTokens.get(0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            d_Log.notify(e.getMessage());
            throw new InvalidMapException(e.getMessage());

        }
        if (d_Map.d_isEmpty) {
            return true;
        }

        try {
            MapValidator.validateMap(d_Map);
        } catch (Exception e) {
            System.out.println("Map Validation Failed \nMap data has been cleared, use editmap to load a map again");
            d_Log.notify("Map Validation Failed \nMap data has been cleared, use editmap to load a map again");
            System.out.println(e.getMessage());
            d_Log.notify(e.getMessage());
            d_Map.clearMapData();
            return false;
        }

        return true;
    }


    /**
     * Validates the command arguments and then save the map.
     *
     * @param p_argumentTokens list of arguments provided with the command
     */
    public static void saveMapCommand(List<String> p_argumentTokens) {
        if (p_argumentTokens.stream().count() != 1) {
            System.out.println("Wrong Number of Arguments provided. savemap command has only one argument.");
            d_Log.notify("Wrong Number of Arguments provided. savemap command has only one argument.");
            return;
        }

        File l_file = new File("src/main/resources/" + p_argumentTokens.get(0) + ".map");
        try {
            MapValidator.validateMap(d_Map);
        } catch (Exception e) {
            System.out.println("Map Validation Failed");
            d_Log.notify("Map Validation Failed");
            System.out.println(e.getMessage());
            d_Log.notify(e.getMessage());
        }
        if (MapValidator.d_isValid == false) {
            System.out.println("Map cannot be saved");
            d_Log.notify("Map cannot be saved");
        } else {
            new MapWriter().writeMapFile(d_Map, l_file);
        }


    }

    /**
     * Validates the map.
     *
     * @param p_argumentTokens list of arguments provided with the command
     */
    public static void validateMapCommand(List<String> p_argumentTokens) {
        if (p_argumentTokens.stream().count() != 0) {
            System.out.println("Wrong Number of Arguments provided. validatemap command has no argument.");
            d_Log.notify("Wrong Number of Arguments provided. validatemap command has no argument.");
            return;
        }

        try {
            MapValidator.validateMap(d_Map);
        } catch (Exception e) {
            System.out.println("Map Validation Failed");
            d_Log.notify("Map Validation Failed");
            System.out.println(e.getMessage());
            d_Log.notify(e.getMessage());
        }

    }

    /**
     * Validates the command arguments and then displays the map.
     *
     * @param p_argumentTokens list of arguments provided with the command
     */
    public static void showMapCommand(List<String> p_argumentTokens) {
        if (p_argumentTokens.stream().count() != 0) {
            System.out.println("Wrong Number of Arguments provided. showmap command has no argument.");
            d_Log.notify("Wrong Number of Arguments provided. showmap command has no argument.");
        }

        ShowMap.displayEditorMap(d_Map);

    }

    /**
     * Validates the command arguments and then edits the Continent information accordingly.
     *
     * @param p_argumentTokens list of arguments provided with the command
     */
    public static void editContinentCommand(List<String> p_argumentTokens) {

        String l_continentName;
        int l_continentValue;

        for (int i = 0; i < p_argumentTokens.size(); i++) {
            if (p_argumentTokens.get(i).equals("-add")) {
                try {
                    l_continentName = p_argumentTokens.get(++i);
                    l_continentValue = Integer.parseInt(p_argumentTokens.get(++i));
                    System.out.println("Continent ID: " + l_continentName + " Control Value: " + l_continentValue);
                    d_Log.notify("Continent ID: " + l_continentName + " Control Value: " + l_continentValue);
                    MapOperations.addContinent(d_Map, l_continentName, l_continentValue);

                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. add option has 2 arguments");
                    d_Log.notify("Wrong number of Arguments provided. add option has 2 arguments");
                    return;
                }
            } else if (p_argumentTokens.get(i).equals("-remove")) {
                try {
                    l_continentName = p_argumentTokens.get(++i);
                    System.out.println("Continent ID: " + l_continentName);
                    d_Log.notify("Continent ID: " + l_continentName);
                    MapOperations.removeContinent(d_Map, l_continentName);

                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. remove option has 1 argument");
                    d_Log.notify("Wrong number of Arguments provided. remove option has 1 argument");
                    e.printStackTrace();
                    return;
                }
            } else {
                System.out.println("Invalid option. editneighbor has -add and -remove options only");
                d_Log.notify("Invalid option. editneighbor has -add and -remove options only");
            }

        }

    }

    /**
     * Validates the command arguments and then edits the country information accordingly.
     *
     * @param p_argumentTokens list of arguments provided with the command
     */
    public static void editCountryCommand(List<String> p_argumentTokens) {

        String l_countryName;
        String l_continentName;

        for (int i = 0; i < p_argumentTokens.size(); i++) {
            if (p_argumentTokens.get(i).equals("-add")) {
                try {
                    l_countryName = p_argumentTokens.get(++i);
                    l_continentName = p_argumentTokens.get(++i);

                    System.out.println("Country ID: " + l_countryName + " Continent ID: " + l_continentName);
                    d_Log.notify("Country ID: " + l_countryName + " Continent ID: " + l_continentName);

                    MapOperations.addCountry(d_Map, l_countryName, l_continentName);

                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. add option has 2 arguments");
                    //d_Log.notify("Wrong number of Arguments provided. add option has 2 arguments");
                    return;
                }
            } else if (p_argumentTokens.get(i).equals("-remove")) {
                try {
                    l_countryName = p_argumentTokens.get(++i);
                    System.out.println("Country ID: " + l_countryName);
                    //d_Log.notify("Country ID: " + l_countryName);

                    MapOperations.removeCountry(d_Map, l_countryName);

                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. remove option has 1 argument");
                    //d_Log.notify("Wrong number of Arguments provided. remove option has 1 argument");
                    return;
                }
            } else {
                System.out.println("Invalid option. editneighbor has -add and -remove options only");
                //d_Log.notify("Invalid option. editneighbor has -add and -remove options only");
            }

        }
    }

    /**
     * Validates the command arguments and then edits the neighbor of the country
     *
     * @param p_argumentTokens list of arguments provided with the command
     */
    public static void editNeighborCommand(List<String> p_argumentTokens) {

        String l_countryName;
        String l_neighborCountryName;

        for (int i = 0; i < p_argumentTokens.size(); i++) {
            if (p_argumentTokens.get(i).equals("-add")) {
                try {
                    l_countryName = p_argumentTokens.get(++i);
                    l_neighborCountryName = p_argumentTokens.get(++i);

                    System.out.println("Country ID: " + l_countryName + " Neighbor Country ID: " + l_neighborCountryName);
                    //d_Log.notify("Country ID: " + l_countryName + " Neighbor Country ID: " + l_neighborCountryName);
                    MapOperations.addNeighborCountry(d_Map, l_neighborCountryName, l_countryName);

                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. add option has 2 arguments");
                    //d_Log.notify("Wrong number of Arguments provided. add option has 2 arguments");
                    return;
                }
            } else if (p_argumentTokens.get(i).equals("-remove")) {
                try {
                    l_countryName = p_argumentTokens.get(++i);
                    l_neighborCountryName = p_argumentTokens.get(++i);

                    System.out.println("Country ID: " + l_countryName + " Neighbor Country ID: " + l_neighborCountryName);
                    //d_Log.notify("Country ID: " + l_countryName + " Neighbor Country ID: " + l_neighborCountryName);
                    MapOperations.removeNeighborCountry(d_Map, l_neighborCountryName, l_countryName);

                } catch (Exception e) {
                    System.out.println("Wrong number of Arguments provided. remove option has 2 argument");
                    //d_Log.notify("Wrong number of Arguments provided. remove option has 2 argument");
                    return;
                }
            } else {
                System.out.println("Invalid option. editneighbor has -add and -remove options only");
                //d_Log.notify("Invalid option. editneighbor has -add and -remove options only");
            }

        }

    }


}
