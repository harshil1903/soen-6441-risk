package com.risk.orders;

import com.risk.exception.InvalidMapException;
import com.risk.gameutils.AssignCountries;
import com.risk.maputils.EditMap;
import com.risk.models.Country;
import com.risk.models.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;
import static org.junit.Assert.assertFalse;

/**
 * To Test the Advance card functionality in the game i.e move some armies from one of the current player’s
 * territories (source) to an adjacent territory (target). If the target territory belongs to the current player,
 * the armies are moved to the target territory. If the target territory belongs to another player, an attack happens
 * between the two territories.
 *
 * @author Harsh
 * @author Parth
 */

public class AdvanceTest {

    String d_countryName;
    Player d_player1, d_player2;
    Country d_country, d_country1, d_country2;

    List<Country> d_countriesOwned = new ArrayList<Country>();

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

        //d_country = new Country();

        //d_country.setD_CountryName("India");
        //d_country.setD_NumberOfArmies(0);
    }

    /**
     * Test method attacker win
     */
    @Test
    public void testAttacker() {

        //attacker
        String l_countryName1 = d_player1.getD_AssignedCountries().get(0).getD_CountryName();
        d_country1 = new Country();
        d_country1 = d_country1.getCountryFromCountryName(l_countryName1);
        d_country1.setD_NumberOfArmies(8);
        int l_battleArmy = 7;

        //defender
        String l_countryName2 = d_player2.getD_AssignedCountries().get(0).getD_CountryName();
        d_country2 = new Country();
        d_country2 = d_country2.getCountryFromCountryName(l_countryName2);
        d_country2.setD_NumberOfArmies(4);

        //Battle
        Advance advance = new Advance(d_player1, l_countryName1, l_countryName2, 7);
        System.out.println("Attacker Battle Army : " + l_battleArmy);
        System.out.println("Defender Army : " + d_country2.getD_NumberOfArmies());
        System.out.println("Attacker win and occupied defender country");
        System.out.println("Remaining army of attacker after attack : " + d_country2.getD_NumberOfArmies());
        advance.execute();
    }

    /**
     * Test method defender win
     */
    @Test
    public void testDefender() {

        //attacker
        String l_countryName1 = d_player1.getD_AssignedCountries().get(0).getD_CountryName();
        d_country1 = new Country();
        d_country1 = d_country1.getCountryFromCountryName(l_countryName1);
        d_country1.setD_NumberOfArmies(8);
        int l_battleArmy = 25;

        //defender
        String l_countryName2 = d_player2.getD_AssignedCountries().get(0).getD_CountryName();
        d_country2 = new Country();
        d_country2 = d_country2.getCountryFromCountryName(l_countryName2);
        d_country2.setD_NumberOfArmies(20);

        //Battle
        Advance advance = new Advance(d_player1, l_countryName1, l_countryName2, 7);
        System.out.println("Attacker Battle Army : " + l_battleArmy);
        System.out.println("Defender Army : " + d_country2.getD_NumberOfArmies());
        System.out.println("Defender win and defend country");
        System.out.println("Remaining army of defender after attack : " + d_country2.getD_NumberOfArmies());
        advance.execute();
    }


}
