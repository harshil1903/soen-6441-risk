package com.risk.gameutils;

import java.util.Random;

/**
 * Generates a random number between upper bound and lower bound.
 *
 * @author Chirag
 */
public class GenerateRandomNumber {

    /**
     * generates random number between zero and p_high-1.
     *
     * @param p_high upper bound for random number generation.
     * @return random number generated.
     */
    public static int getRandomNumber(int p_high) {
        Random l_random = new Random();
        int l_low = 0;
        int l_number = l_random.nextInt(p_high - l_low) + l_low;
        return l_number;
    }

    /**
     * generates random number between p_low and p_high-1.
     *
     * @param p_high upper bound for random number generation.
     * @param p_low  lower bound for random number generation.
     * @return random number generated
     */
    public static int getRandomNumber(int p_high, int p_low) {
        Random l_random = new Random();
        int l_low = 0;
        int l_number = l_random.nextInt(p_high - l_low) + l_low;
        return l_number;
    }
}
