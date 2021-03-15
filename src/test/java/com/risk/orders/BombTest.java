package com.risk.orders;

import com.risk.exception.InvalidMapException;
import com.risk.gameutils.AssignCountries;
import com.risk.maputils.EditMap;
import com.risk.orders.*;
import com.risk.models.Country;
import com.risk.models.Player;
import org.junit.Before;
import org.junit.Test;
import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;
import static org.junit.Assert.*;

import com.risk.exception.InvalidMapException;



import java.util.ArrayList;
import java.util.List;

/**
 * To Test the Bomb functionality in the game i.e  on using the bomb card, the target country loses
 * half of their army units.
 *
 * @author Harsh
 * @author Parth
 */
public class BombTest {

    String d_countryName;
    Player d_player1,d_player2;
    Country d_country,d_country1,d_country2;

    List<Country> d_countriesOwned =new ArrayList<Country>();

    @Before
    /**
     * This method is executed before every test method.
     */
    public void beforeTest() throws InvalidMapException{

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
     * Test method for test that player can not use bomb card on enemy having zero army
     */

    @Test
    public void testZeroNumberOfArmies(){

        String l_countryName1 = d_player1.getD_AssignedCountries().get(0).getD_CountryName();
        d_country = new Country();
        d_country=d_country.getCountryFromCountryName(l_countryName1);
        d_country.setD_NumberOfArmies(0);
        Bomb bomb = new Bomb(d_player2,l_countryName1);
        assertFalse(bomb.valid());
    }

    /**
     * Test method for test that player can not use bomb card on own country
     */
    @Test
    public void testBombOwnArmies(){
        String l_countryName1 = d_player1.getD_AssignedCountries().get(0).getD_CountryName();
        d_country = new Country();
        d_country=d_country.getCountryFromCountryName(l_countryName1);
        d_country.setD_NumberOfArmies(10);
        Bomb bomb = new Bomb(d_player1,l_countryName1);
        assertFalse(bomb.valid());
    }

    /**
     * Test method for check that execute method of bomb class run successfully
     */
    @Test
    public void testSuccessfulBomb(){
        String l_countryName1 = d_player1.getD_AssignedCountries().get(0).getD_CountryName();
        String l_countryName2 = d_player2.getD_AssignedCountries().get(0).getD_CountryName();
        d_country1 = new Country();
        d_country2 = new Country();
        d_country1 = d_country1.getCountryFromCountryName(l_countryName1);
        d_country2 = d_country2.getCountryFromCountryName(l_countryName2);

        d_country1.setD_NumberOfArmies(10);
        d_country2.setD_NumberOfArmies(6);
        Bomb bomb = new Bomb(d_player1,l_countryName2);
        System.out.println("Armies before bomb in " +l_countryName2 + " are "+ d_country2.getD_NumberOfArmies());
        bomb.execute();
        System.out.println("Armies after bomb are: "+ d_country2.getCountryFromCountryName(l_countryName2).getD_NumberOfArmies());
    }
}