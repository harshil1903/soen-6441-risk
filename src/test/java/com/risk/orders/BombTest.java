package com.risk.orders;

import com.risk.orders.*;
import com.risk.models.Country;
import com.risk.models.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * To Test the Bomb functionality in the game i.e  on using the bomb card, the target country loses
 * half of their army units.
 *
 * @author Harsh
 * @author Parth
 */
public class BombTest {

    String d_countryName;
    Player d_player1;
    Country d_country;

    @Before
    /**
     * This method is executed before every test method.
     */
    public void beforeTest(){
        d_player1 = new Player("player_1");
        d_country = new Country();
        d_country.setD_CountryName("India");
        d_country.setD_NumberOfArmies(5);
    }

    @Test
    public void testNumberOfArmies(){
        
    }


}
