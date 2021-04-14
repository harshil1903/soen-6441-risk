package com.risk.main;

import com.risk.exception.InvalidMapException;
import com.risk.models.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;
import static org.junit.Assert.*;

/**
 * The Tournament test class
 *
 * @author Harshil
 */
public class TournamentTest {
    List<String> d_argumentList;
    String l_command;

    /**
     * This method is executed before every test method.
     *
     * @throws InvalidMapException throws exception if Map Loading Fails
     */
    @Before
    public void beforeTest() throws InvalidMapException {

        l_command = "-M europe -P benevolent benevolent cheater -G 2 -D 15";

        String[] l_argumentTokens = l_command.split(" ");
        d_argumentList = new ArrayList<>(Arrays.asList(l_argumentTokens.clone()));

    }

    /**
     * This method is executed after every test method.
     *
     * @throws InvalidMapException the invalid map exception
     */
    @After
    public void afterTest() throws InvalidMapException {
        System.out.println();
        l_command = "";
        d_Map.clearMapData();

        for (Player l_player : d_PlayerList)
            l_player.clearPlayerData();

        d_PlayerList.clear();

    }

    /**
     * Test execution of an instance of Tournament
     */
    @Test
    public void testTournamentExecution() {

        System.out.println("\nTEST : Testing Tournament Execution:");

        Tournament.begin(d_argumentList);

        System.out.println("Success");

    }


    /**
     * Testing the arguments validity to run the tournament
     */
    @Test
    public void testTournamentArgumentsValidity() {
        System.out.println("\nTEST : Testing Tournament Arguments Validation:");

        l_command = "-M europe Asia -P benevolent benevolent cheater -G 2 -D 15";

        String[] l_argumentTokens = l_command.split(" ");
        List<String> l_argumentList = new ArrayList<>(Arrays.asList(l_argumentTokens.clone()));

        assertTrue(Tournament.validateTournamentArguments(l_argumentList));
    }

    /**
     * checks number of maps allowed in a single tournament
     */
    @Test
    public void testTournamentAllowedNumberOfMaps() {
        System.out.println("\nTEST : Testing Allowed number of Maps in a single Tournament:");

        l_command = "-M europe Asia europe Asia europe Asia -P benevolent benevolent aggressive -G 3 -D 15";

        String[] l_argumentTokens = l_command.split(" ");
        d_argumentList = new ArrayList<>(Arrays.asList(l_argumentTokens.clone()));

        assertFalse(Tournament.validateTournamentArguments(d_argumentList));
    }

    /**
     * checks number of players allowed in a single tournament
     */
    @Test
    public void testTournamentAllowedNumberOfPlayers() {
        System.out.println("\nTEST : Testing Allowed number of Players in a single Tournament:");

        l_command = "-M europe Asia -P benevolent benevolent aggressive cheater cheater -G 3 -D 15";

        String[] l_argumentTokens = l_command.split(" ");
        d_argumentList = new ArrayList<>(Arrays.asList(l_argumentTokens.clone()));

        assertFalse(Tournament.validateTournamentArguments(d_argumentList));
    }

    /**
     * checks number of Games allowed in a single tournament
     */
    @Test
    public void testTournamentAllowedNumberOfGames() {
        System.out.println("\nTEST : Testing Allowed number of Games in a single Tournament:");

        l_command = "-M europe Asia -P benevolent benevolent aggressive -G 10 -D 15";

        String[] l_argumentTokens = l_command.split(" ");
        d_argumentList = new ArrayList<>(Arrays.asList(l_argumentTokens.clone()));

        assertFalse(Tournament.validateTournamentArguments(d_argumentList));
    }

    /**
     * checks number of Turns per game allowed in a single tournament
     */
    @Test
    public void testTournamentAllowedNumberOfTurns() {
        System.out.println("\nTEST : Testing Allowed number of Turns per game in a single Tournament:");

        l_command = "-M europe Asia -P benevolent benevolent aggressive -G 3 -D 100";

        String[] l_argumentTokens = l_command.split(" ");
        d_argumentList = new ArrayList<>(Arrays.asList(l_argumentTokens.clone()));

        assertFalse(Tournament.validateTournamentArguments(d_argumentList));
    }

}
