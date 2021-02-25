package com.risk.maputils;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.After;
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

    static Continent d_continent;
    static Country d_country;
    static Map d_map;
    String d_continentName = "Temp_Cont";
    int d_controlValue = 6;
    List<Continent> d_continentList;

    /**
     * This method runs before any test methods in the class, as these are static methods,they can work only upon static members.
     */
    @BeforeClass
    public static void beforeClass() {
        d_continent = new Continent();
        d_country = new Country();
        d_map = new Map();
    }


    /**
     * This method is executed before every test method.
     *
     * @throws InvalidMapException throws exception if Map Loading Fails
     */
    @Before
    public void beforeTest() throws InvalidMapException {
        try {
            d_map = new EditMap().editMap("europe");
        } catch (Exception e) {
            throw new InvalidMapException(e.getMessage());
        }
        d_continent.setD_ContinentName(d_continentName);
        d_continent.setD_ContinentValue(d_controlValue);
        d_country.setD_CountryName("Country No.1");

        d_continentList = new ArrayList<>();
        d_continentList.add(d_continent);
    }
    /**
     * This method is executed after every test method.
     *
     * @throws InvalidMapException the invalid map exception
     */
    @After
    public void afterTest() throws InvalidMapException
    {
        System.out.println();
        d_map.getD_Continents().clear();
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
        d_map.addContinentToContinentList(d_continent);
        MapValidator.validateContinents(d_map);
    }


    /**
     * This method is used to test whether the continents in the map form subgraph or not
     *
     * @throws InvalidMapException invalid map exception.
     */
    @Test
    public void validateMapForSubgraph() throws InvalidMapException {

        assertEquals(false, !MapValidator.isMapConnectedGraph(d_map));
    }


    /**
     * This method is used to test whether continent from any graph forms subgraph or not by its countries.
     *
     * @throws InvalidMapException invalid map exception.
     */
    @Test
    public void validateContinentForSubgraph() throws InvalidMapException {
        List<Country> l_countryList = new ArrayList<>();
        l_countryList.add(d_country);
        Country l_newCountry = new Country();
        l_newCountry.setD_CountryName("Country No.2");
        l_countryList.add(l_newCountry);
        d_continent.setD_Countries(l_countryList);
        assertEquals(MapValidator.isContinentConnectedGraph(d_continent, d_map), false);

        List<Country> l_adjCountryList = new ArrayList<>();
        l_adjCountryList.add(d_country);
        l_newCountry.setD_AdjacentCountries(l_adjCountryList);

        l_adjCountryList = new ArrayList<>();
        l_adjCountryList.add(l_newCountry);
        d_country.setD_AdjacentCountries(l_adjCountryList);


        assertTrue(MapValidator.isContinentConnectedGraph(d_continent, d_map));
        assertEquals(MapValidator.isContinentConnectedGraph(d_continent, d_map), true);
    }


}
