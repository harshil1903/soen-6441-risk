package com.risk.orders;

import com.risk.exception.InvalidMapException;
import com.risk.gameutils.AssignCountries;
import com.risk.maputils.EditMap;
import com.risk.models.Country;
import com.risk.models.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import java.util.ArrayList;

import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;
import static org.junit.Assert.*;

/**
 * To Test the Airlift card functionality in the game i.e advance some armies from one of the current player’s
 * territories to any another territory that is owned by the player.
 *
 * @author Harsh
 * @author Parth
 */

public class AirliftTest {

    String d_sourceCountryName, d_targetCountryName;
    Country d_sourceCountry, d_targetCountry;
    Player d_player1, d_player2;
    int d_numArmies;

    @Before
    /**
     * This method is executed before every test method.
     * @throws InvalidMapException
     */
    public void beforeTest() throws InvalidMapException {

        try {
            d_Map = new EditMap().editMap("europe");
        } catch (Exception e) {
            throw new InvalidMapException(e.getMessage());
        }
        d_player1 = new Player("player_1");
        d_PlayerList.add(d_player1);
        d_player2 = new Player("player_2");
        d_PlayerList.add(d_player2);

        d_player1.addCard("airlift");
        d_player2.addCard("airlift");
        AssignCountries.assignCountries();

    }

    /**
     * This test case is used to verify that source country name and target country names are different and only then the
     * Airlift can be performed.
     */
    @Test
    public void testDifferentCountryNames() {
        String l_sourceCountryName = d_player1.getD_AssignedCountries().get(0).getD_CountryName();
        String l_targetCountryName = d_player1.getD_AssignedCountries().get(1).getD_CountryName();

        d_sourceCountry = new Country();
        d_sourceCountry = d_sourceCountry.getCountryFromCountryName(l_sourceCountryName);
        d_sourceCountry.setD_NumberOfArmies(10);

        d_targetCountry = new Country();
        d_targetCountry = d_targetCountry.getCountryFromCountryName(l_targetCountryName);
        d_targetCountry.setD_NumberOfArmies(4);

        Airlift airlift = new Airlift(d_player1, l_sourceCountryName, l_targetCountryName, 9);

        assertTrue(airlift.valid());

    }

    /**
     * This test case is used to verify that the player can not perform Airlift on the country that is not owned by
     * that player.
     */
    @Test
    public void testAirliftNotOwnedCountry() {
        String l_sourceCountryName = d_player1.getD_AssignedCountries().get(1).getD_CountryName();
        String l_targetCountryName = d_player2.getD_AssignedCountries().get(0).getD_CountryName();

        d_sourceCountry = new Country();
        d_sourceCountry = d_sourceCountry.getCountryFromCountryName(l_sourceCountryName);
        d_sourceCountry.setD_NumberOfArmies(10);

        d_targetCountry = new Country();
        d_targetCountry = d_targetCountry.getCountryFromCountryName(l_targetCountryName);
        d_targetCountry.setD_NumberOfArmies(4);

        Airlift airlift = new Airlift(d_player1, l_sourceCountryName, l_targetCountryName, 9);

        assertFalse(airlift.valid());
        System.out.println("Airlift can not be used as Player 1 does not own " + l_targetCountryName);

    }

    /**
     * This test case verifies that the source country has sufficient armies while ordering Airlift to any target
     * country.
     */
    @Test
    public void testSufficientArmiesToAirlift() {
        String l_sourceCountryName = d_player1.getD_AssignedCountries().get(3).getD_CountryName();
        String l_targetCountryName = d_player1.getD_AssignedCountries().get(2).getD_CountryName();

        d_sourceCountry = new Country();
        d_sourceCountry = d_sourceCountry.getCountryFromCountryName(l_sourceCountryName);
        d_sourceCountry.setD_NumberOfArmies(10);

        d_targetCountry = new Country();
        d_targetCountry = d_targetCountry.getCountryFromCountryName(l_targetCountryName);
        d_targetCountry.setD_NumberOfArmies(4);

        Airlift airlift = new Airlift(d_player1, l_sourceCountryName, l_targetCountryName, 15);

        assertFalse(airlift.valid());

    }

    /**
     * This test case verifies successful behaviour of the Airlift operation with desired results
     */
    @Test
    public void testSuccessfulAirliftOperation() {
        String l_sourceCountryName = d_player1.getD_AssignedCountries().get(3).getD_CountryName();
        String l_targetCountryName = d_player1.getD_AssignedCountries().get(2).getD_CountryName();

        d_sourceCountry = new Country();
        d_sourceCountry = d_sourceCountry.getCountryFromCountryName(l_sourceCountryName);
        d_sourceCountry.setD_NumberOfArmies(10);

        d_targetCountry = new Country();
        d_targetCountry = d_targetCountry.getCountryFromCountryName(l_targetCountryName);
        d_targetCountry.setD_NumberOfArmies(4);

        Airlift airlift = new Airlift(d_player1, l_sourceCountryName, l_targetCountryName, 5);
        airlift.execute();

        assertEquals(d_sourceCountry.getD_NumberOfArmies(), 5);
        assertEquals(d_targetCountry.getD_NumberOfArmies(), 9);
    }
}
