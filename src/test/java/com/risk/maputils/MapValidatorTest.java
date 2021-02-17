package com.risk.maputils;

import static org.junit.Assert.*;
import java.io.File;
import java.util.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;

import com.risk.exception.InvalidMapException;

/**
 * This is the test class for the MapValidator.
 * @author Harsh
 */

public class MapValidatorTest {

    static Continent S_Continent;
    static Country S_Country;
    static Map S_Map;


    String d_continentName = "North_Europe";
    int d_controlValue = 5;

    List<Continent> d_continentList;

    /**
     * This method runs before any test methods in the class, as these are static methods,they can work only upon static members.
     */
    @BeforeClass
    public static void beforeClass(){
        S_Continent = new Continent();
        S_Country = new Country();
        S_Map = new Map();

    }

    /**
     * This method is executed before every test method.
     */
    @Before
    public void beforeTest(){
        S_Continent.setD_ContinentName(d_continentName);
        S_Continent.setD_ContinentValue(d_controlValue);
        S_Country.setD_CountryName("England");

        d_continentList = new ArrayList<>();
        d_continentList.add(S_Continent);
    }

    /**
     * This method is used for checking whether the map is null or not.
     * @throws InvalidMapException invalid map exception.
     */

    @Test (expected = InvalidMapException.class)
    public void validatedNullMap() throws InvalidMapException{
        MapValidator.validateMap(null);
    }


    /** This method is used to validate whether the continent is null or not.
     * @throws InvalidMapException invalid map exception.
     */
    @Test (expected = InvalidMapException.class)
    public void validateContinentForNullCountry() throws InvalidMapException {
        S_Map.setD_Continents(d_continentList);
        MapValidator.validateContinents(S_Map);
    }
}
