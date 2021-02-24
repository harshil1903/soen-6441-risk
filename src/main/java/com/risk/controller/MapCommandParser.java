package com.risk.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The MapCommandParser class is used to determine whether command is a Map Command or Game Command
 * and call the necessary actions according to command type.
 *
 * @author Harshil
 */
public class MapCommandParser {

    /**
     * Contains true if map is loaded otherwise false
     */
    public static boolean d_mapLoaded = false;

    /**
     * Parses map related commands and calls the appropriate MapCommand method.
     *
     * @param p_action    command name
     * @param p_arguments command options/arguments (if any)
     */
    public static void mapCommandParser(String p_action, String p_arguments) {

        String[] l_argumentTokens = p_arguments.split(" ");
        List<String> l_argumentList = new ArrayList<>(Arrays.asList(l_argumentTokens.clone()));

        if (!l_argumentList.isEmpty()) {
            l_argumentList.remove(0);
        }

        switch (p_action) {
            case "editmap":
                try {
                    d_mapLoaded = MapCommands.editMapCommand(l_argumentList);
                } catch (Exception e) {
                }

                break;

            case "editcontinent":
                if (d_mapLoaded) {
                    MapCommands.editContinentCommand(l_argumentList);
                    break;
                }
                break;

            case "editcountry":
                if (d_mapLoaded) {
                    MapCommands.editCountryCommand(l_argumentList);
                    break;
                }
                break;

            case "editneighbor":
                if (d_mapLoaded) {
                    MapCommands.editNeighborCommand(l_argumentList);
                    break;
                }
                break;

            case "validatemap":
                if (d_mapLoaded) {
                    MapCommands.validateMapCommand(l_argumentList);
                    break;
                }
                break;

            case "savemap":
                if (d_mapLoaded) {
                    MapCommands.saveMapCommand(l_argumentList);
                    break;
                }
                break;

            case "showmap":
                if (d_mapLoaded) {
                    MapCommands.showMapCommand(l_argumentList);
                    break;
                }
                break;

            default:
                System.out.println("Invalid Command");
                break;


        }

        if (!d_mapLoaded) {
            System.out.println("No Map is loaded yet, use editmap command to load map");
        }

    }


}
