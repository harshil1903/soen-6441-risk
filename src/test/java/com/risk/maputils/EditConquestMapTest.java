package com.risk.maputils;

import com.risk.exception.InvalidMapException;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * This is test class for EditConquestMap
 *
 * @author Chirag
 */
public class EditConquestMapTest {

    /**
     * Tests if file exist and is not empty.
     *
     * @throws InvalidMapException if Map is invalid.
     */
    @Test
    public void editMapFilePresent() throws InvalidMapException {
        assertFalse(EditConquestMap.loadConquestMap("Asia").getD_Continents().isEmpty());
    }

    /**
     * Tests if map file doesn't exist
     *
     * @throws InvalidMapException if Map is invalid.
     */
    @Test
    public void editMapFileNotPresent() throws InvalidMapException {
        assertTrue(EditConquestMap.loadConquestMap("file not present").getD_Continents().isEmpty());
    }

}
