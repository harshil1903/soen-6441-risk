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
import java.util.List;
import java.util.Random;

import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;

/**
 * Test class for AggressiveStrategy Class.
 *
 * @author Parth
 */
public class AggressiveStrategyTest {
    Player d_player1;
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
        d_PlayerList.add(d_player1);
        d_PlayerList.add(new Player("player 2", "benevolent"));
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
     * This method is to test strategy of aggressive player
     */
    @Test
    public void aggressiveTest() {
        d_player1.setD_Armies(10);
        d_player1.d_armiesForAdvance = 10;
        d_player1.setD_playerStrategy(new AggressivePlayerStrategy(d_player1, d_country));
        Order order = d_player1.getD_playerStrategy().createOrder();
        order.execute();
        order.printOrder();
    }

}
