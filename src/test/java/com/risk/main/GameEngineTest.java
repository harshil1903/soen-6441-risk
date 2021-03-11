package com.risk.main;

import com.risk.controller.MapCommands;
import com.risk.exception.InvalidMapException;
import com.risk.gameutils.AssignCountries;
import com.risk.maputils.EditMap;
import com.risk.maputils.ShowMap;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;
import com.risk.models.Player;
import org.junit.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;
import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;

/**
 * To test the Game Engine functions
 *
 * @author Harshil
 */
public class GameEngineTest {
    Player d_player1, d_player2, d_player3, d_player4;

    /**
     * This method is executed before every test method.
     *
     * @throws InvalidMapException throws exception if Map Loading Fails
     */
    @Before
    public void beforeTest() throws InvalidMapException {

        try {
            d_Map = new EditMap().editMap("europe");
        } catch (Exception e) {
            throw new InvalidMapException(e.getMessage());
        }

        d_player1 = new Player("TestPlayer1");
        d_PlayerList.add(d_player1);
        d_player2 = new Player("TestPlayer2");
        d_PlayerList.add(d_player2);
        d_player3 = new Player("TestPlayer3");
        d_PlayerList.add(d_player3);
        d_player4 = new Player("TestPlayer4");
        d_PlayerList.add(d_player4);

        AssignCountries.assignCountries();


    }

    /**
     * This method is executed after every test method.
     *
     * @throws InvalidMapException the invalid map exception
     */
    @After
    public void afterTest() throws InvalidMapException {
        System.out.println();
        d_Map.getD_Continents().clear();
        d_PlayerList.clear();
    }

    /**
     * Test reinforcement armies count that is assigned to the player.
     */
    @Test
    public void testReinforcementArmies() {

        System.out.println("\n TEST : Counting reinforcement armies assigned to a player");

        GameEngine.assignReinforcementArmies();

        System.out.println("Player 1 country count : " + d_player1.getD_AssignedCountries().size());
        System.out.println("Player 2 country count : " + d_player2.getD_AssignedCountries().size());

        System.out.println("Player 1 reinforcement army count : " + d_player1.getD_Armies());
        System.out.println("Player 2 reinforcement army count : " + d_player2.getD_Armies());

        assertEquals(3, d_player1.getD_Armies());
        assertEquals(3, d_player2.getD_Armies());

    }

    /**
     * player cannot deploy more armies that there is in their reinforcement pool.
     */
    @Test
    public void testDeployMoreThanReinforcementArmies() {

        System.out.println("\n TEST : Testing that player cannot deploy more armies than he has in his reinforcement pool");

        GameEngine.assignReinforcementArmies();

        System.out.println("Player 1 country count : " + d_player1.getD_AssignedCountries().size());
        System.out.println("Player 2 country count : " + d_player2.getD_AssignedCountries().size());

        System.out.println("Player 1 reinforcement army count : " + d_player1.getD_Armies());
        System.out.println("Player 2 reinforcement army count : " + d_player2.getD_Armies());

        String l_countryName1 = d_player1.getD_AssignedCountries().get(0).getD_CountryName();
        String l_countryName2 = d_player2.getD_AssignedCountries().get(0).getD_CountryName();
        int l_numberOfArmies = 6;

        System.out.println("Player 1 trying to deploy 6 armies");
        assertFalse(d_player1.deployOrder(l_countryName1, l_numberOfArmies));

        l_numberOfArmies = 2;
        System.out.println("Player 2 trying to deploy 2 armies");
        assertTrue(d_player2.deployOrder(l_countryName2, l_numberOfArmies));
    }

}




