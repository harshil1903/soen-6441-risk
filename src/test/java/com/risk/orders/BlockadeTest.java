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
 * To Test the blockade card functionality in the game i.e on using blockade card the target territory's army
 * units are tripled and it is made neutral.
 *
 * @author Harsh
 * @author Rishabh
 */
public class BlockadeTest {
    Player d_player1, d_player2;
    Country d_country;


    /**
     * This method is executed before every test method.
     * @throws InvalidMapException InvalidMapException
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
        AssignCountries.assignCountries();

        d_country = new Country();

        d_country.setD_CountryName("India");
        d_country.setD_NumberOfArmies(0);
    }

    /**
     * This method is to test blockade card when number of armies in country is zero
     */
    @Test
    public void testZeroNumberOfArmies() {

        String l_countryName1 = d_player1.getD_AssignedCountries().get(0).getD_CountryName();
        d_country = new Country();
        d_country = d_country.getCountryFromCountryName(l_countryName1);
        d_country.setD_NumberOfArmies(0);
        Blockade blockade = new Blockade(d_player1, l_countryName1);
        assertFalse(blockade.valid());
    }

    /**
     * This method is to test blockade card can not apply on opponent's country
     */
    @Test
    public void testBlockadeOwnArmies() {
        String l_countryName1 = d_player1.getD_AssignedCountries().get(0).getD_CountryName();
        d_country = new Country();
        d_country = d_country.getCountryFromCountryName(l_countryName1);
        d_country.setD_NumberOfArmies(10);
        Blockade blockade = new Blockade(d_player2, l_countryName1);
        assertFalse(blockade.valid());
    }

    /**
     * This method is to test number of army after applying blockade card on country
     */
    @Test
    public void testSuccessfulBlockade() {
        String l_countryName1 = d_player1.getD_AssignedCountries().get(0).getD_CountryName();
        d_country = new Country();
        d_country = d_country.getCountryFromCountryName(l_countryName1);

        d_country.setD_NumberOfArmies(10);
        Blockade blockade = new Blockade(d_player1, l_countryName1);
        blockade.execute();
        assertEquals(d_country.getD_NumberOfArmies(),30);
    }
}