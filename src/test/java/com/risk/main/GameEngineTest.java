package com.risk.main;

import com.risk.exception.InvalidMapException;
import com.risk.gameutils.AssignCountries;
import com.risk.maputils.EditMap;
import com.risk.models.Player;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;

public class GameEngineTest
{
    Player l_Player;
    /**
     * This method is executed before every test method.
     */
    @Before
    public void beforeTest() throws InvalidMapException {
        try {
            d_Map = new EditMap().EditMap();
        }
        catch (Exception e)
        {
            throw new InvalidMapException(e.getMessage());
        }
        l_Player = new Player("TestPlayer");
        d_PlayerList.add(l_Player);
    }

    @Test
    public void testReinforcementArmies() throws InvalidMapException
    {
        AssignCountries.assignCountries();
        GameEngine.assignReinforcementArmies();

        assertEquals(3,l_Player.getD_Armies());
    }
}
