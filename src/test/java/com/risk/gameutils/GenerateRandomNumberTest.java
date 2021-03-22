package com.risk.gameutils;

import org.junit.Assert;
import org.junit.Test;

import static com.risk.gameutils.GenerateRandomNumber.getRandomNumber;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class of GenerateRandomNumber in gameutils
 *
 * @author Chirag
 */
public class GenerateRandomNumberTest {
    /**
     * Checks if number is in Range with Upper Bound while Lower Bound is zero.
     */
    @Test
    public void checkNumberInRange() {
        int l_getNum = getRandomNumber(1000);
        System.out.println("The Random Number Generated is:" + l_getNum);
        assertTrue(l_getNum >= 0 && l_getNum < 1000 ? true : false);
        assertFalse(l_getNum >= 1000 ? true : false);
    }

    /**
     * Checks if number is in Range with Upper Bound and Lower Bound.
     */
    @Test
    public void checkNumberInLowerAndUpperBound() {
        int l_getNum = getRandomNumber(1000, 5);
        System.out.println("The Random Number Generated is:" + l_getNum);
        assertTrue(l_getNum >= 5 && l_getNum < 1000 ? true : false);
        assertFalse(l_getNum >= 1000 ? true : false);
    }
}
