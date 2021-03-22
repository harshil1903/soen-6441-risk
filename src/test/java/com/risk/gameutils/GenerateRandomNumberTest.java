package com.risk.gameutils;

import org.junit.Assert;
import org.junit.Test;

import static com.risk.gameutils.GenerateRandomNumber.getRandomNumber;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GenerateRandomNumberTest {
    @Test
    public void checkNumberInRange(){
        int getNum=getRandomNumber(1000);
        System.out.println("The Random Number Generated is:"+getNum);
        assertTrue(getNum>=0 && getNum<1000?true:false);
        assertFalse(getNum>=1000?true:false);
}

    @Test
    public void checkNumberInLowerAndUpperBound(){
        int getNum=getRandomNumber(1000,5);
        System.out.println("The Random Number Generated is:"+getNum);
        assertTrue(getNum>=5 && getNum<1000?true:false);
        assertFalse(getNum>=1000?true:false);
    }
}
