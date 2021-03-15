package com.risk.orders;

import com.risk.exception.InvalidMapException;
import com.risk.gameutils.AssignCountries;
import com.risk.maputils.EditMap;
import com.risk.models.Country;
import com.risk.models.Player;
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

    @Before
    /**
     * This method is executed before every test method.
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
        AssignCountries.assignCountries();
        /*d_player1.addPlayerToDeplomacyList(d_player2);
        System.out.println(d_player1.getDeplomacyPlayer().get(0).getD_PlayerName());
        d_player1.removePlayerToDeplomacyList(d_player2.getD_PlayerName());
        System.out.println(d_player1.getDeplomacyPlayer().size());*/
    }

    /**
     * Test method for test that player can not use bomb card on enemy having zero army
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

        Advance advance=new Advance(d_player1,l_countryName1,l_countryName2,8);
        advance.test1();

        d_player1.removePlayerFromDiplomacyList(d_player2.getD_PlayerName());
        d_player2.removePlayerFromDiplomacyList(d_player1.getD_PlayerName());
        advance.test1();
    }
}

