package com.risk.phases;

import com.risk.exception.InvalidMapException;
import com.risk.main.GameEngine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.risk.main.Main.d_Map;
import static org.junit.Assert.assertTrue;

/**
 * The Pre map load test is used to verify that only allowed commands in Pre Map Load
 * phase are allowed while others are invalid
 *
 * @author Harshil
 */
public class PreMapLoadTest {

    GameEngine d_gameEngine;


    /**
     * This method is executed before every test method.
     *
     * @throws InvalidMapException throws exception if Map Loading Fails
     */
    @Before
    public void beforeTest() throws InvalidMapException {


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

    }

    /**
     * Test pre map load phase by checking that only valid commands are executed
     * and invalid commands are invalidated.
     */
    @Test
    public void testPreMapLoadPhase()
    {
        System.out.println("\nTEST : Testing PreMapLoad Phase\n");

        d_gameEngine = new GameEngine();

        d_gameEngine.setPhase(new PreMapLoad(d_gameEngine));

        ArrayList<String> l_arguments = new ArrayList<>();
        l_arguments.add("europe");

        System.out.println("Testing editmap command : ");
        assertTrue(d_gameEngine.d_gamePhase.editMap(l_arguments));

        System.out.println("\n Testing loadmap command : ");
        d_gameEngine.d_gamePhase.loadMap(l_arguments);

        System.out.println("\n Testing gameplayer command : ");
        d_gameEngine.d_gamePhase.addPlayer(l_arguments);

        System.out.println("\n Testing assigncountries command : ");
        d_gameEngine.d_gamePhase.assignCountries();

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
