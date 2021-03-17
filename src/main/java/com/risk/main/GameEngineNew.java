package com.risk.main;

import com.risk.controller.GameCommands;
import com.risk.gameutils.AssignCountries;
import com.risk.gameutils.Reinforce;
import com.risk.models.Country;
import com.risk.models.Orders;
import com.risk.models.Player;
import com.risk.phases.GameIssueOrder;
import com.risk.phases.GameStartup;
import com.risk.phases.Phase;
import com.risk.phases.PreMapLoad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.risk.main.Main.d_PlayerList;
import static com.risk.main.Main.log;

/**
 * Game Engine Class controls the entire flow of Gameplay including all the game phases
 * and main game loop
 *
 * @author Harshil
 */
public class GameEngineNew {

    /**
     * Contains true if game is loaded otherwise false.
     */
    public static boolean d_gameLoaded = false;
    private static Phase d_gamePhase;
    int mystart;
    int mycommand;

    /**
     * Contains true if map is loaded otherwise false
     */
    public static boolean d_mapLoaded = false;

    public void setPhase(Phase p_gamePhase) {
        d_gamePhase = p_gamePhase;
        System.out.println("New Phase : " + d_gamePhase.getClass().getSimpleName());
        log.notify("New Phase : " + d_gamePhase.getClass().getSimpleName());
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
            mystart = Integer.parseInt(l_scanner.nextLine());
            switch (mystart) {
                case 1:
                    // Set the state to Preload
                    setPhase(new PreMapLoad(this));
                    break;
                case 2:
                    // Set the state to PlaySetup
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
            log.notify("COMMAND : " + l_command);

            while (!l_command.equals("EXIT")) {

                l_resetPhase = compareCommand(l_command);

                if (l_resetPhase) {
                    break;
                }

                System.out.println("Enter command: ");
                l_command = l_scanner.nextLine();
                log.notify("COMMAND : " + l_command);

            }
        } while (mystart != 3);


        System.out.println("GAME HAS ENDED");

    }

    /**
     * The method compares the command input from user to check what type of command is entered.
     *
     * @param p_command command line input
     *
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
                if (d_gamePhase.editMap(l_argumentList)) {
                    d_gamePhase.next();
                }
                break;

            case "validatemap":
                d_gamePhase.validateMap(l_argumentList);
                break;

            case "showmap":
                d_gamePhase.showMap(l_argumentList);
                break;

            case "savemap":
                d_gamePhase.saveMap(l_argumentList);
                return true;

            case "editcontinent":
                d_gamePhase.editContinent(l_argumentList);
                break;

            case "editcountry":
                d_gamePhase.editCountry(l_argumentList);
                break;

            case "editneighbor":
                d_gamePhase.editNeighbor(l_argumentList);
                break;

            case "loadmap":
                d_gamePhase.loadMap(l_argumentList);
                break;

            case "gameplayer":
                d_gamePhase.addPlayer(l_argumentList);
                break;

            case "assigncountries":
                if (d_gamePhase.assignCountries()) {
                    d_gamePhase.next();
                    d_gamePhase.issueOrder();
                }
                break;

            case "issueorder":
                setPhase(new GameIssueOrder(this));
                break;

            case "end":
                d_gamePhase.endGame();
                return true;

            default:
                System.out.println("Invalid Command");

        }

        return false;

    }


}
