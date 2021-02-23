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

    static Map d_Map;
    static Continent d_Continent;
    static Country d_Country;
    static Country d_AdjCountry;
    int d_ControlValue1 = 1;
    int d_ControlValue2 = 2;
    String d_ContinentName = "TempContinent";
    String d_CountryName = "TestCountry";
    String d_AdjacentCountryName = "Test2Country";

    /**
     * This method is executed before any test methods of the class.
     *
     * @throws InvalidMapException InvalidMapException
     */
    @BeforeClass
    public static void beforeClass() throws InvalidMapException {
        try {
            d_Map = new EditMap().editMap("europe");
        } catch (Exception e) {
            throw new InvalidMapException(e.getMessage());
        }
        d_Country = new Country();
        d_AdjCountry = new Country();
        d_Continent = new Continent();

    }

    /**
     * This test case is used to check the add continent functionality.
     *
     * @throws InvalidMapException InvalidMapException
     */
    @Test
    public void testAddContinent() throws InvalidMapException {
        MapOperations.addContinent(d_Map, d_ContinentName, d_ControlValue1);
        for (Continent l_Continent : d_Map.getD_Continents()) {
            if (l_Continent.getD_ContinentName().equals(d_ContinentName)) {
                assertEquals(l_Continent.getD_ContinentValue(), d_ControlValue1);
                assertNotEquals(l_Continent.getD_ContinentValue(), d_ControlValue2);
                assertEquals(l_Continent.getD_ContinentName(), d_ContinentName);
            }
        }

    }

//    /**
//     * This test case is used to check that respective continent is removed from the map.
//     * @throws InvalidMapException InvalidMapException
//     */
//
//    @Test
//    public void testRemoveContinent() throws InvalidMapException{
//        MapOperations.addContinent(d_Map, d_ContinentName, d_ControlValue1);
//        MapOperations.removeContinent(d_Map,d_ContinentName);
//        for(Continent l_Continent : d_Map.getD_Continents()){
//                assertFalse(l_Continent.getD_ContinentName().equals(d_ContinentName));
//            }
//        }

    //S_Map.removeContinentFromContinentList(d_ContinentName);


    /**
     * This test case is used to check the add country functionality.
     *
     * @throws InvalidMapException InvalidMapException
     */
    @Test
    public void testAddCountry() throws InvalidMapException {
        String cont = null;
        MapOperations.addContinent(d_Map, d_ContinentName, d_ControlValue1);
        MapOperations.addCountry(d_Map, d_CountryName, d_ContinentName);
        for (Continent l_Continent : d_Map.getD_Continents()) {
            if (l_Continent.getD_ContinentName() == d_ContinentName) {
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
//    @Test
//    public void testRemoveCountry() throws InvalidMapException {
//        MapOperations.addContinent(d_Map, d_ContinentName, d_ControlValue1);
//        MapOperations.addCountry(d_Map, d_CountryName, d_ContinentName);
//        MapOperations.removeCountry(d_Map, d_CountryName);
//        //ShowMap.displayEditorMap(d_Map);
//    }
//

    /**
     * This test case is used to check the add neighbour country functionality to any country in the map.
     *
     * @throws InvalidMapException InvalidMapException
     */
    @Test
    public void testAddNeighbourCountry() throws InvalidMapException {
        MapOperations.addContinent(d_Map, d_ContinentName, d_ControlValue1);
        MapOperations.addCountry(d_Map, d_CountryName, d_ContinentName);
        MapOperations.addCountry(d_Map, d_AdjacentCountryName, d_ContinentName);
    }
//
//    /**
//     * This test case is used to check the remove neighbour country functionality to any country in the map.
//     *
//     * @throws InvalidMapException InvaliadMapException
//     */
//    @Test
//    public void testRemoveNeighbourCountry() throws InvalidMapException {
//
//    }


}


