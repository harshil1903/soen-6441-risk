package com.risk.phases;

import com.risk.exception.InvalidMapException;
import com.risk.main.GameEngineNew;
import com.risk.maputils.EditMap;
import com.risk.models.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;
import static org.junit.Assert.assertTrue;

public class GameStartupTest {
    Player d_player1, d_player2, d_player3, d_player4;
    GameEngineNew d_gameEngine;


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

    @Test
    public void testGameStartupPhase()
    {
        System.out.println("\nTEST : Testing Game Startup Phase\n");

        d_gameEngine = new GameEngineNew();

        d_gameEngine.setPhase(new GameStartup(d_gameEngine));

        ArrayList<String> l_arguments = new ArrayList<>();
        l_arguments.add("europe");

        System.out.println("Testing loadmap command : ");
        assertTrue(d_gameEngine.d_gamePhase.loadMap(l_arguments));

        System.out.println("\n Testing assigncountries command : ");
        assertTrue(d_gameEngine.d_gamePhase.assignCountries());

        System.out.println("\n Testing editmap command : ");
        d_gameEngine.d_gamePhase.editMap(l_arguments);

        System.out.println("\n Testing editcountry command : ");
        d_gameEngine.d_gamePhase.editCountry(l_arguments);

        System.out.println("\n Testing editcontinent command : ");
        d_gameEngine.d_gamePhase.editContinent(l_arguments);

        System.out.println("\n Testing validatemap command : ");
        d_gameEngine.d_gamePhase.validateMap(l_arguments);

        System.out.println("\n Testing savemap command : ");
        d_gameEngine.d_gamePhase.saveMap(l_arguments);

        System.out.println("\n Testing editneighbor command : ");
        d_gameEngine.d_gamePhase.editNeighbor(l_arguments);

    }
}
