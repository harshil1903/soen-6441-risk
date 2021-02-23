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

    static Map S_Map;
    static Continent S_Continent;
    static Country S_Country;
    static Country S_AdjCountry;
    int d_ControlValue1 = 1;
    int d_ControlValue2 = 2;
    String d_ContinentName = "TempContinent";
    String d_CountryName = "TestCountry";

    /**
     * This method is executed before any test methods of the class.
     *
     * @throws InvalidMapException InvalidMapException
     */
    @BeforeClass
    public static void beforeClass() throws InvalidMapException {
        try {
            S_Map = new EditMap().editMap("europe");
        } catch (Exception e) {
            throw new InvalidMapException(e.getMessage());
        }
        S_Map = new Map();
        S_Country = new Country();
        S_AdjCountry = new Country();
        S_Continent = new Continent();

    }

    /**
     * This test case is used to check the add continent functionality.
     *
     * @throws InvalidMapException InvalidMapException
     */
    @Test
    public void testAddContinent() throws InvalidMapException {
        MapOperations.addContinent(S_Map, d_ContinentName, d_ControlValue1);
        for (Continent l_Continent : S_Map.getD_Continents()) {
            assertEquals(l_Continent.getD_ContinentValue(), d_ControlValue1);
            assertNotEquals(l_Continent.getD_ContinentValue(), d_ControlValue2);
            assertEquals(l_Continent.getD_ContinentName(), d_ContinentName);
        }

    }

    /**
     * This test case is used to check that respective continent is removed from the map.
     * @throws InvalidMapException InvalidMapException
     */
    /*
    @Test
    public void testRemoveContinent() throws InvalidMapException{
        MapOperations.removeContinent(S_Map,d_ContinentName);
        for(Continent l_Continent : S_Map.getD_Continents()){

                assertFalse(l_Continent.getD_ContinentName().equals(d_ContinentName));
            }

        }

        //S_Map.removeContinentFromContinentList(d_ContinentName);

    }
     */

    /**
     * This test case is used to check the add country functionality.
     *
     * @throws InvalidMapException InvalidMapException
     */
    @Test
    public void testAddCountry() throws InvalidMapException {
        String cont = null;
        MapOperations.addCountry(S_Map, d_CountryName, d_ContinentName);
        for (Continent l_Continent : S_Map.getD_Continents()) {
            if (l_Continent.getD_ContinentName().equals(d_ContinentName)) {
                assertNotNull(l_Continent);
                cont = l_Continent.getD_ContinentName();
                System.out.println(cont);
                assertFalse(l_Continent.getD_Countries().contains(d_CountryName));
            }
        }

    }


    /**
     * This test case is used to check the remove country functionality
     *
     * @throws InvalidMapException InvalidMapException
     */
    @Test
    public void testRemoveCountry() throws InvalidMapException {

    }

    /**
     * This test case is used to check the add neighbour country functionality to any country in the map.
     *
     * @throws InvalidMapException InvalidMapException
     */
    @Test
    public void testAddNeighbourCountry() throws InvalidMapException {

    }

    /**
     * This test case is used to check the remove neighbour country functionality to any country in the map.
     *
     * @throws InvalidMapException InvaliadMapException
     */
    @Test
    public void testRemoveNeighbourCountry() throws InvalidMapException {

    }


}


