package com.risk.maputils;

import static org.junit.Assert.*;

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
 *
 * @author Harsh
 */

public class MapValidatorTest {

    static Continent S_Continent;
    static Country S_Country;
    static Map S_Map;


    String d_continentName = "Temp_Cont";
    int d_controlValue = 6;

    List<Continent> d_continentList;

    /**
     * This method runs before any test methods in the class, as these are static methods,they can work only upon static members.
     */
    @BeforeClass
    public static void beforeClass() {
        S_Continent = new Continent();
        S_Country = new Country();
        S_Map = new Map();
    }


    /**
     * This method is executed before every test method.
     *
     * @throws InvalidMapException throws exception if Map Loading Fails
     */
    @Before
    public void beforeTest() throws InvalidMapException {
        try {
            S_Map = new EditMap().editMap("europe");
        } catch (Exception e) {
            throw new InvalidMapException(e.getMessage());
        }
        S_Continent.setD_ContinentName(d_continentName);
        S_Continent.setD_ContinentValue(d_controlValue);
        S_Country.setD_CountryName("Country No.1");

        d_continentList = new ArrayList<>();
        d_continentList.add(S_Continent);
    }


    /**
     * This method is used for checking whether the map is null or not.
     *
     * @throws InvalidMapException invalid map exception.
     */

    @Test(expected = InvalidMapException.class)
    public void validatedNullMap() throws InvalidMapException {
        MapValidator.validateMap(null);
    }


    /**
     * This method is used to validate whether the continent is null or not.
     *
     * @throws InvalidMapException invalid map exception.
     */
    @Test(expected = InvalidMapException.class)
    public void validateContinentForNullCountry() throws InvalidMapException {
        S_Map.addContinentToContinentList(S_Continent);
        MapValidator.validateContinents(S_Map);
    }


    /**
     * This method is used to test whether the continents in the map form subgraph or not
     *
     * @throws InvalidMapException invalid map exception.
     */
    @Test
    public void validateMapForSubgraph() throws InvalidMapException {

        assertEquals(true, !MapValidator.isMapConnectedGraph(S_Map));
    }


    /**
     * This method is used to test whether continent from any graph forms subgraph or not by its countries.
     *
     * @throws InvalidMapException invalid map exception.
     */
    @Test
    public void validateContinentForSubgraph() throws InvalidMapException {
        List<Country> l_CountryList = new ArrayList<>();
        l_CountryList.add(S_Country);
        Country l_NewCountry = new Country();
        l_NewCountry.setD_CountryName("Country No.2");
        l_CountryList.add(l_NewCountry);
        S_Continent.setD_Countries(l_CountryList);
        assertEquals(MapValidator.isContinentConnectedGraph(S_Continent, S_Map), false);

        List<Country> l_AdjCountryList = new ArrayList<>();
        l_AdjCountryList.add(S_Country);
        l_NewCountry.setD_AdjacentCountries(l_AdjCountryList);

        l_AdjCountryList = new ArrayList<>();
        l_AdjCountryList.add(l_NewCountry);
        S_Country.setD_AdjacentCountries(l_AdjCountryList);


        assertTrue(MapValidator.isContinentConnectedGraph(S_Continent, S_Map));
        assertEquals(MapValidator.isContinentConnectedGraph(S_Continent, S_Map), true);
    }


}
