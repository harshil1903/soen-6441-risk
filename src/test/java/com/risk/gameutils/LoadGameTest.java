package com.risk.gameutils;

import com.risk.exception.InvalidMapException;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * This is test class for LoadGame
 *
 * @author Chirag
 */
public class LoadGameTest {


    /**
     * Tests if game file exist and is not empty.
     *
     * @throws InvalidMapException if Map is invalid.
     */
    @Test
    public void gameFilePresent() throws InvalidMapException {
        LoadGame.loadGame("conquest");
        assertTrue(LoadGame.d_gamePhaseName.length() != 0);
    }
}
