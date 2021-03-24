package com.risk.orders;

import com.risk.exception.InvalidMapException;
import com.risk.gameutils.AssignCountries;
import com.risk.maputils.EditMap;
import com.risk.models.Country;
import com.risk.models.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * To Test the Diplomacy functionality in the game i.e  on using the diplomacy card, the target player
 * and current player can not attack on each other.
 *
 * @author Harsh
 * @author Parth
 */
public class DiplomacyTest {

    Player d_player1, d_player2;
    Country d_country1,d_country2;


    /**
     * This method is executed before every test method.
     * @throws InvalidMapException
     */
    @Before
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
        d_player1.addCard("negotiate");
        d_player2.addCard("negotiate");
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
        d_Map.clearMapData();
        d_Map.getD_Continents().clear();

        for(Player l_player : d_PlayerList)
            l_player.clearPlayerData();

        d_PlayerList.clear();


    }


    /**
     * Test method for test that player can not attack other player which is in diplomacy list
     */

    @Test
    public void testDiplomacy() {
        String l_countryName1 = d_player1.getD_AssignedCountries().get(0).getD_CountryName();
        d_country1 = new Country();
        d_country1 = d_country1.getCountryFromCountryName(l_countryName1);
        d_country1.setD_NumberOfArmies(8);

        String l_countryName2 = d_player2.getD_AssignedCountries().get(0).getD_CountryName();
        d_country2 = new Country();
        d_country2 = d_country2.getCountryFromCountryName(l_countryName2);
        d_country2.setD_NumberOfArmies(8);

        d_player1.addPlayerToDiplomacyList(d_player2);
        d_player2.addPlayerToDiplomacyList(d_player1);

        Advance advance=new Advance(d_player1,l_countryName1,l_countryName2,7);
        assertFalse(advance.valid());

    }
}

