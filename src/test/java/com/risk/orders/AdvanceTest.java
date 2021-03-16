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
import static org.junit.Assert.*;

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

    Player d_player1, d_player2;
    Country d_sourceCountry, d_targetCountry;

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
        AssignCountries.assignCountries();
    }

    /**
     * This method is used to test whether the countries while performing advance order are adjacent or not
     */
    @Test
    public void testAdjacency(){
        String l_countryName1 = d_player1.getD_AssignedCountries().get(0).getD_CountryName();
        System.out.println("Country name 1 is:"+l_countryName1);
        d_sourceCountry = new Country();
        d_sourceCountry = d_sourceCountry.getCountryFromCountryName(l_countryName1);
        d_sourceCountry.setD_NumberOfArmies(10);


        String l_countryName2 = d_player2.getD_AssignedCountries().get(0).getD_CountryName();
        System.out.println("Country name 2 is:"+l_countryName2);
        d_targetCountry = new Country();
        d_targetCountry = d_targetCountry.getCountryFromCountryName(l_countryName2);
        d_targetCountry.setD_NumberOfArmies(4);

        Advance advance = new Advance(d_player1,l_countryName1,l_countryName2,9);

        if(d_sourceCountry.getD_AdjacentCountries().contains(d_targetCountry)){
            assertTrue(advance.valid());
        }else {
            assertFalse(advance.valid());
        }


    }

    /**
     * This method is used to test number of army on source country and target country when attacker win in battle
     */
    @Test
    public void testAttacker() {

        //attacker
        String l_countryName1 = d_player1.getD_AssignedCountries().get(0).getD_CountryName();
        d_sourceCountry = new Country();
        d_sourceCountry = d_sourceCountry.getCountryFromCountryName(l_countryName1);
        d_sourceCountry.setD_NumberOfArmies(8);
        int l_battleArmy = 7;

        //defender
        String l_countryName2 = d_player2.getD_AssignedCountries().get(0).getD_CountryName();
        d_targetCountry = new Country();
        d_targetCountry = d_targetCountry.getCountryFromCountryName(l_countryName2);
        d_targetCountry.setD_NumberOfArmies(4);

        //Battle
        Advance advance = new Advance(d_player1, l_countryName1, l_countryName2, l_battleArmy);
        System.out.println("Attacker Battle Army : " + l_battleArmy);
        System.out.println("Defender Army : " + d_targetCountry.getD_NumberOfArmies());
        System.out.println("Attacker win and occupied defender country");
        advance.execute();
        assertEquals(d_sourceCountry.getD_NumberOfArmies(),3);
        assertEquals(d_targetCountry.getD_NumberOfArmies(),4);
        System.out.println("Number of army that occupied defender country : " + d_targetCountry.getD_NumberOfArmies());
    }

    /**
     * This method is used to test number of army on source country and target country when defender win in battle
     */
    @Test
    public void testDefender() {

        //attacker
        String l_countryName1 = d_player1.getD_AssignedCountries().get(0).getD_CountryName();
        d_sourceCountry = new Country();
        d_sourceCountry = d_sourceCountry.getCountryFromCountryName(l_countryName1);
        d_sourceCountry.setD_NumberOfArmies(26);
        int l_battleArmy = 25;

        //defender
        String l_countryName2 = d_player2.getD_AssignedCountries().get(0).getD_CountryName();
        d_targetCountry = new Country();
        d_targetCountry = d_targetCountry.getCountryFromCountryName(l_countryName2);
        d_targetCountry.setD_NumberOfArmies(20);

        //Battle
        Advance advance = new Advance(d_player1, l_countryName1, l_countryName2, l_battleArmy);
        System.out.println("Attacker Battle Army : " + l_battleArmy);
        System.out.println("Defender Army : " + d_targetCountry.getD_NumberOfArmies());
        System.out.println("Defender win and defend country");
        advance.execute();
        assertEquals(d_sourceCountry.getD_NumberOfArmies(),12);
        assertEquals(d_targetCountry.getD_NumberOfArmies(),5);
        System.out.println("Remaining army on attacker after attack : " + d_sourceCountry.getD_NumberOfArmies());
        System.out.println("Remaining army on defender after attack : " + d_targetCountry.getD_NumberOfArmies());
    }


}
