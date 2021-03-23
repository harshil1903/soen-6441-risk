package com.risk.phases;

import com.risk.exception.InvalidMapException;
import com.risk.gameutils.AssignCountries;
import com.risk.main.GameEngineNew;
import com.risk.maputils.EditMap;
import com.risk.models.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PreMapLoadTest {

    GameEngineNew d_gameEngine;


    /**
     * This method is executed before every test method.
     *
     * @throws InvalidMapException throws exception if Map Loading Fails
     */
    @Before
    public void beforeTest() throws InvalidMapException {

        try {
            d_Map = new EditMap().editMap("europe1");
        } catch (Exception e) {
            throw new InvalidMapException(e.getMessage());
        }

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

    @Test
    public void testPreMapLoadPhase()
    {
        System.out.println("\nTEST : Testing PreMapLoad Phase\n");

        d_gameEngine = new GameEngineNew();

        d_gameEngine.setPhase(new PreMapLoad(d_gameEngine));

        ArrayList<String> l_arguments = new ArrayList<>();
        l_arguments.add("europe");

        System.out.println("Testing editmap command : ");
        assertTrue(d_gameEngine.d_gamePhase.editMap(l_arguments));


        System.out.println("\n Testing assigncountries command : ");
        d_gameEngine.d_gamePhase.assignCountries();

    }
}
