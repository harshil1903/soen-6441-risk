package com.risk.maputils;

import com.risk.exception.InvalidMapException;
import org.junit.*;

import static org.junit.Assert.*;

/**
 * This is test class for EditMap
 */
public class EditMapTest {

    /**
     * Tests if file exist and is not empty.
     *
     * @throws InvalidMapException if Map is invalid.
     */
    @Test
    public void editMapFilePresent() throws InvalidMapException {
        assertFalse(EditMap.editMap("europe").d_Continents.isEmpty());
    }

    /**
     * Tests if map file doesn't exist
     *
     * @throws InvalidMapException if Map is invalid.
     */
    @Test
    public void editMapFileNotPresent() throws InvalidMapException {
        assertTrue(EditMap.editMap("file not present").d_Continents.isEmpty());
    }

}
