package com.risk.phases;

import com.risk.exception.InvalidMapException;
import com.risk.main.GameEngine;
import com.risk.models.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;
import static org.junit.Assert.assertTrue;

/**
 * The type Game startup test is to test that only valid commands in Game Startup
 * phase are allowed.
 *
 * @author Harshil
 */
public class GameStartupTest {
    Player d_player1, d_player2, d_player3, d_player4;
    GameEngine d_gameEngine;


    /**
     * This method is executed before every test method.
     *
     * @throws InvalidMapException throws exception if Map Loading Fails
     */
    @Before
    public void beforeTest() throws InvalidMapException {

        d_player1 = new Player("TestPlayer1");
        d_PlayerList.add(d_player1);
        d_player2 = new Player("TestPlayer2");
        d_PlayerList.add(d_player2);
        d_player3 = new Player("TestPlayer3");
        d_PlayerList.add(d_player3);
        d_player4 = new Player("TestPlayer4");
        d_PlayerList.add(d_player4);
    }

    /**
     * This method is executed after every test method.
     *
     * @throws InvalidMapException the invalid map exception
     */
    @After
    public void afterTest() throws InvalidMapException {
        System.out.println();
        d_Map.clearMapData();
        d_Map.getD_Continents().clear();

        for(Player l_player : d_PlayerList)
            l_player.clearPlayerData();

        d_PlayerList.clear();

    }

    /**
     * Test game startup phase by checking that only valid commands are executed
     * and invalid commands are invalidated.
     */
    @Test
    public void testGameStartupPhase()
    {
        System.out.println("\nTEST : Testing Game Startup Phase\n");

        d_gameEngine = new GameEngine();

        d_gameEngine.setPhase(new GameStartup(d_gameEngine));

        ArrayList<String> l_arguments = new ArrayList<>();
        l_arguments.add("europe");

        System.out.println("Testing loadmap command : ");
        assertTrue(d_gameEngine.d_GamePhase.loadMap(l_arguments));

        System.out.println("\n Testing assigncountries command : ");
        assertTrue(d_gameEngine.d_GamePhase.assignCountries());

        System.out.println("\n Testing editmap command : ");
        d_gameEngine.d_GamePhase.editMap(l_arguments);

        System.out.println("\n Testing editcountry command : ");
        d_gameEngine.d_GamePhase.editCountry(l_arguments);

        System.out.println("\n Testing editcontinent command : ");
        d_gameEngine.d_GamePhase.editContinent(l_arguments);

        System.out.println("\n Testing validatemap command : ");
        d_gameEngine.d_GamePhase.validateMap(l_arguments);

        System.out.println("\n Testing savemap command : ");
        d_gameEngine.d_GamePhase.saveMap(l_arguments);

        System.out.println("\n Testing editneighbor command : ");
        d_gameEngine.d_GamePhase.editNeighbor(l_arguments);

    }
}
