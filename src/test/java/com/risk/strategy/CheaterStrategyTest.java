package com.risk.strategy;

import com.risk.exception.InvalidMapException;
import com.risk.gameutils.AssignCountries;
import com.risk.maputils.EditMap;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;

/**
 * Test class for CheaterStrategy class.
 *
 * @author Parth.
 */
public class CheaterStrategyTest {
    Player d_player1,d_player2,d_player3;
    List<Country> d_country = new ArrayList<Country>();

    /**
     * This method is executed before every test method.
     *
     * @throws InvalidMapException Invalid Map Exception
     */
    @Before
    public void beforeTest() throws InvalidMapException {
        try {
            d_Map = new EditMap().editMap("europe");
        } catch (Exception e) {
            throw new InvalidMapException(e.getMessage());
        }

        d_player1 = new Player("player_1");
        d_player2 = new Player("player_2");
        d_player3 = new Player("player_3");
        d_PlayerList.add(d_player1);
        d_PlayerList.add(d_player2);
        d_PlayerList.add(d_player3);
        AssignCountries.assignCountries();
        for (Continent l_continent : d_Map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                d_country.add(l_country);
            }
        }

    }

    /**
     * This method is executed after every test method.
     *
     * @throws InvalidMapException Invalid Map Exception
     */

    @After
    public void afterTest() throws InvalidMapException {
        d_country.clear();
        d_Map.clearMapData();

        d_Map.getD_Continents().clear();

        for (Player l_player : d_PlayerList)
            l_player.clearPlayerData();

        d_PlayerList.clear();
    }

    /**
     * This method is to test strategy of random player
     */
    @Test
    public void cheaterTest() {
        d_player1.setD_playerStrategy(new CheaterPlayerStrategy(d_player1, d_country));


    }

}
