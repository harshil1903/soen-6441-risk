package com.risk.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The MapCommandParser class is used to determine whether command is a Map Command or Game Command
 * and call the necessary actions according to command type.
 * @author Harshil
 */
public class MapCommandParser {

    public static boolean d_MapLoaded = false;

    /**
     * Parses map related commands and calls the appropriate MapCommand method.
     *
     * @param p_Action    command name
     * @param p_Arguments command options/arguments (if any)
     */
    public static void mapCommandParser(String p_Action, String p_Arguments)
    {

        String[] l_ArgumentTokens = p_Arguments.split(" ");
        List<String> l_ArgumentList = new ArrayList<>(Arrays.asList(l_ArgumentTokens.clone()));

        if(!l_ArgumentList.isEmpty())
        {
            l_ArgumentList.remove(0);
        }

        switch (p_Action)
        {
            case "editmap":
                try {
                    d_MapLoaded = MapCommands.editMapCommand(l_ArgumentList);
                }
                catch (Exception e)
                {}

                break;

            case "editcontinent":
                if(d_MapLoaded)
                {
                    MapCommands.editContinentCommand(l_ArgumentList);
                    break;
                }
                break;

            case "editcountry":
                if(d_MapLoaded)
                {
                    MapCommands.editCountryCommand(l_ArgumentList);
                    break;
                }
                break;

            case "editneighbor":
                if(d_MapLoaded)
                {
                    MapCommands.editNeighborCommand(l_ArgumentList);
                    break;
                }
                break;

            case "validatemap":
                if(d_MapLoaded)
                {
                    MapCommands.validateMapCommand(l_ArgumentList);
                    break;
                }
                break;

            case "savemap":
                if(d_MapLoaded)
                {
                    MapCommands.saveMapCommand(l_ArgumentList);
                    break;
                }
                break;

            case "showmap":
                if(d_MapLoaded)
                {
                    MapCommands.showMapCommand(l_ArgumentList);
                    break;
                }
                break;


        }

        if(!d_MapLoaded)
        {
            System.out.println("No Map is loaded yet, use editmap command to load map");
        }

    }


}
