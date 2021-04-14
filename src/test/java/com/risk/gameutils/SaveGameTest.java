package com.risk.gameutils;

import com.risk.exception.InvalidMapException;
import com.risk.maputils.EditMap;
import com.risk.models.Country;
import com.risk.models.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;
import static org.junit.Assert.*;

/**
 * This is test class for SaveGame
 *
 * @author Chirag
 */
public class SaveGameTest {

    String d_sourceCountryName, d_targetCountryName;
    Country d_sourceCountry, d_targetCountry;
    Player d_player1, d_player2;
    int d_numArmies;

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
        d_player2 = new Player("player_2");
        d_PlayerList.add(d_player2);

        d_player1.addCard("airlift");
        d_player2.addCard("airlift");

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

        for (Player l_player : d_PlayerList)
            l_player.clearPlayerData();

        d_PlayerList.clear();


    }

    /**
     * Process Map data of the SaveGame
     */
    @Test
    public void testProcessMap() {
        SaveGame l_saveGame = new SaveGame();
        StringBuilder l_processMap = l_saveGame.processMap(d_Map);
        assertFalse(l_processMap.toString().isEmpty());
    }

    /**
     * Process Countries data of the SaveGame
     */
    @Test
    public void testProcessCountries() {
        SaveGame l_saveGame = new SaveGame();
        StringBuilder l_processCountries = l_saveGame.processCountries(d_Map);
        assertFalse(l_processCountries.toString().isEmpty());
    }

    /**
     * Process Players data of the SaveGame
     */
    @Test
    public void testProcessPlayers() {
        SaveGame l_saveGame = new SaveGame();
        StringBuilder l_processPlayers = l_saveGame.processPlayers(d_PlayerList);
        assertFalse(l_processPlayers.toString().isEmpty());
    }


    /**
     * Checks if Map is not empty of the SaveGame
     */
    @Test
    public void testProcessMapNotEmpty() {
        SaveGame l_saveGame = new SaveGame();
        StringBuilder l_processMap = l_saveGame.processMap(d_Map);
        assertTrue(!l_processMap.toString().isEmpty());
    }

    /**
     * Checks if Countries are not empty of the SaveGame
     */
    @Test
    public void testProcessCountriesNotEmpty() {
        SaveGame l_saveGame = new SaveGame();
        StringBuilder l_processCountries = l_saveGame.processCountries(d_Map);
        assertTrue(!l_processCountries.toString().isEmpty());
    }

    /**
     * Checks if Players are not empty of the SaveGame
     */
    @Test
    public void testProcessPlayersNotEmpty() {
        SaveGame l_saveGame = new SaveGame();
        StringBuilder l_processPlayers = l_saveGame.processPlayers(d_PlayerList);
        assertTrue(!l_processPlayers.toString().isEmpty());
    }

}
