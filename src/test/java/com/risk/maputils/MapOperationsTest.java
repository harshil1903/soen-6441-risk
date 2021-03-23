package com.risk.maputils;

import static org.junit.Assert.*;

import org.junit.*;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;
import com.risk.exception.InvalidMapException;


/**
 * This test class is for map operations.
 *
 * @author Harsh
 */
public class MapOperationsTest {

    static Map d_map;
    static Continent d_continent;
    static Country d_country;
    static Country d_adjCountry;
    int d_controlValue1 = 1;
    int d_controlValue2 = 2;
    String d_continentName = "TempContinent";
    String d_countryName = "TestCountry";
    String d_AdjacentCountryName = "Test2Country";

    /**
     * This method is executed before any test methods of the class.
     *
     * @throws InvalidMapException InvalidMapException
     */
    @Before
    public void beforeTest() throws InvalidMapException {
        try {
            d_map = new EditMap().editMap("europe");
        } catch (Exception e) {
            throw new InvalidMapException(e.getMessage());
        }
        d_country = new Country();
        d_adjCountry = new Country();
        d_continent = new Continent();

    }

    /**
     * This method is executed after every test method.
     *
     * @throws InvalidMapException the invalid map exception
     */
    @After
    public void afterTest() throws InvalidMapException {
        System.out.println();
        d_map.clearMapData();
        d_map.getD_Continents().clear();
    }


    /**
     * This test case is used to check the add continent functionality.
     *
     * @throws InvalidMapException InvalidMapException
     */
    @Test
    public void testAddContinent() throws InvalidMapException {
        MapOperations.addContinent(d_map, d_continentName, d_controlValue1);
        for (Continent l_continent : d_map.getD_Continents()) {
            if (l_continent.getD_ContinentName().equals(d_continentName)) {
                assertEquals(l_continent.getD_ContinentValue(), d_controlValue1);
                assertNotEquals(l_continent.getD_ContinentValue(), d_controlValue2);
                assertEquals(l_continent.getD_ContinentName(), d_continentName);
            }
        }

    }


    /**
     * This test case is used to check the add country functionality.
     *
     * @throws InvalidMapException InvalidMapException
     */
    @Test
    public void testAddCountry() throws InvalidMapException {
        String cont = null;
        MapOperations.addContinent(d_map, d_continentName, d_controlValue1);
        MapOperations.addCountry(d_map, d_countryName, d_continentName);
        for (Continent l_continent : d_map.getD_Continents()) {
            if (l_continent.getD_ContinentName() == d_continentName) {
                assertNotNull(l_continent);
                cont = l_continent.getD_ContinentName();
                System.out.println(cont);
            }
        }

    }


}


