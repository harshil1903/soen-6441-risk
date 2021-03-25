package com.risk.main;

import com.risk.phases.GameStartup;
import com.risk.phases.Phase;
import com.risk.phases.PreMapLoad;

import java.util.*;

import static com.risk.main.Main.d_Log;

/**
 * Game Engine Class controls the entire flow of Gameplay including all the game phases
 * and main game loop
 *
 * @author Harshil
 */
public class GameEngine extends Observable {

    /**
     * holds the current phase value of the game
     */
    public static Phase d_GamePhase;
    int d_choice;

    /**
     * Contains true if map is loaded otherwise false
     */
    public static boolean d_mapLoaded = false;

    /**
     * sets the current phase value
     *
     * @param p_gamePhase holds current phase value.
     */
    public void setPhase(Phase p_gamePhase) {
        d_GamePhase = p_gamePhase;
        System.out.println("New Phase : " + d_GamePhase.getClass().getSimpleName());
        d_Log.notify("New Phase : " + d_GamePhase.getClass().getSimpleName());
    }


    /**
     * The Game execution begins from this method.
     */
    public void runGame() {
        Scanner l_scanner = new Scanner(System.in);
        String l_command;
        boolean l_resetPhase;

        do {
            System.out.println("1. Edit Map");
            System.out.println("2. Play Game");
            System.out.println("3. Quit");
            System.out.println("Where do you want to start?: ");
            d_choice = Integer.parseInt(l_scanner.nextLine());
            switch (d_choice) {
                case 1:
                    // Set the state to PreMapload
                    setPhase(new PreMapLoad(this));
                    break;
                case 2:
                    // Set the state to GameStartup
                    setPhase(new GameStartup(this));
                    break;
                case 3:
                    System.out.println("Bye!");
                    return;

                default:
                    System.out.println("Wrong Input");
                    continue;
            }

            System.out.println("Enter First command: ");
            l_command = l_scanner.nextLine();
            d_Log.notify("COMMAND : " + l_command);

            while (!l_command.equals("EXIT")) {

                l_resetPhase = compareCommand(l_command);

                if (l_resetPhase) {
                    break;
                }

                System.out.println("Enter command: ");
                l_command = l_scanner.nextLine();
                d_Log.notify("COMMAND : " + l_command);

            }
        } while (d_choice != 3);


        System.out.println("GAME HAS ENDED");

    }

    /**
     * The method compares the command input from user to check what type of command is entered.
     *
     * @param p_command command line input
     * @return boolean value to reset the game phase
     */
    public boolean compareCommand(String p_command) {
        String l_action = p_command.split(" ")[0];

        String l_arguments = p_command.substring(l_action.length());

        String[] l_argumentTokens = l_arguments.split(" ");
        List<String> l_argumentList = new ArrayList<>(Arrays.asList(l_argumentTokens.clone()));

        l_argumentList.remove(0);


        switch (l_action) {
            case "editmap":
                if (d_GamePhase.editMap(l_argumentList)) {
                    d_GamePhase.next();
                }
                break;

            case "validatemap":
                d_GamePhase.validateMap(l_argumentList);
                break;

            case "showmap":
                d_GamePhase.showMap(l_argumentList);
                break;

            case "savemap":
                d_GamePhase.saveMap(l_argumentList);
                return true;

            case "editcontinent":
                d_GamePhase.editContinent(l_argumentList);
                break;

            case "editcountry":
                d_GamePhase.editCountry(l_argumentList);
                break;

            case "editneighbor":
                d_GamePhase.editNeighbor(l_argumentList);
                break;

            case "loadmap":
                d_GamePhase.loadMap(l_argumentList);
                break;

            case "gameplayer":
                d_GamePhase.addPlayer(l_argumentList);
                break;

            case "assigncountries":
                if (d_GamePhase.assignCountries()) {
                    d_GamePhase.next();
                }
                break;

            case "end":
                d_GamePhase.endGame();
                return true;

            default:
                System.out.println("Invalid Command");
                d_Log.notify("Invalid Command");

        }

        return false;

    }


}
