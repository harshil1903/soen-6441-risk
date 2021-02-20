package com.risk.maputils;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

import com.risk.exception.InvalidMapException;


/**
 * This test class is for map operations.
 * @author Harsh
 */

public class MapOperationsTest {

    static Map S_Map;
    static Continent S_Continent;
    static Country S_Country;
    static Country S_AdjCountry;
    int d_ControlValue1 = 1;
    int d_ControlValue2 = 2;
    String d_ContinentName = "North Europe";
    String d_CountryName = "England";

    /**
     * This method is executed before any test methods of the class.
     */
    @BeforeClass
    public static void beforeClass(){
        S_Map = new Map();
        S_Country = new Country();
        S_AdjCountry = new Country();
        S_Continent = new Continent();

    }

    /**
     * This test case is used to check the add continent functionality
     * @throws InvalidMapException InvalidMapException
     */
    @Test
    public void testAddContinent() throws InvalidMapException{
        

    }

}

