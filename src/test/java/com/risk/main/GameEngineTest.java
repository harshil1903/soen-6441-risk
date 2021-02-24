package com.risk.main;

import com.risk.exception.InvalidMapException;
import com.risk.gameutils.AssignCountries;
import com.risk.maputils.EditMap;
import com.risk.models.Player;
import org.junit.*;

import static org.junit.Assert.*;
import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;

/**
 * To test the Game Engine functions
 *
 * @author Harshil
 */
public class GameEngineTest {
    Player d_Player1, d_Player2;

    /**
     * This method is executed before every test method.
     *
     * @throws InvalidMapException throws exception if Map Loading Fails
     */
    @Before
    public void beforeTest() throws InvalidMapException {
        try {
            d_Map = new EditMap().editMap("europe");
        } catch (Exception e) {
            throw new InvalidMapException(e.getMessage());
        }
        d_Player1 = new Player("TestPlayer1");
        d_PlayerList.add(d_Player1);
        d_Player2 = new Player("TestPlayer2");
        d_PlayerList.add(d_Player2);
        AssignCountries.assignCountries();
    }

    /**
     * Test reinforcement armies count that is assigned to the player.
     */
    @Test
    public void testReinforcementArmies() {
        GameEngine.assignReinforcementArmies();

        assertEquals(4, d_Player1.getD_Armies());
    }

    /**
     * player cannot deploy more armies that there is in their reinforcement pool.
     */
    @Test
    public void testDeployMoreThanReinforcementArmies() {
        GameEngine.assignReinforcementArmies();

        String l_CountryName = d_Player1.getD_AssignedCountries().get(0).getD_CountryName();
        int l_NumberOfArmies = 6;

        assertFalse(d_Player1.deployOrder(l_CountryName, l_NumberOfArmies));

        l_NumberOfArmies = 2;
        assertTrue(d_Player1.deployOrder(l_CountryName, l_NumberOfArmies));
    }


<<<<<<< HEAD
}
=======
}
>>>>>>> origin/master
