package com.risk.maputils;

import com.risk.exception.InvalidMapException;
import org.junit.*;

import static org.junit.Assert.*;

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
    
}
