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

public class RandomStrategy {
    Player d_player1, d_player2;
    Country d_sourceCountry, d_targetCountry;
    List<Country> d_country = new ArrayList<Country>();

    @Before
    public void beforeTest() throws InvalidMapException {
        try {
            d_Map = new EditMap().editMap("europe");
        } catch (Exception e) {
            throw new InvalidMapException(e.getMessage());
        }

        d_player1 = new Player("player_1");
        d_PlayerList.add(d_player1);
        AssignCountries.assignCountries();
        for (Continent l_continent : d_Map.getD_Continents()) {
            for (Country l_country : l_continent.getD_Countries()) {
                d_country.add(l_country);
            }
        }
    }

    @After
    public void afterTest() throws InvalidMapException {
        d_country.clear();
    }

}
