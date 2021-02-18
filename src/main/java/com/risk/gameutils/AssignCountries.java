package com.risk.gameutils;

import java.util.Random;

/**
 * AssignCountires helps in assigning random countries to players.
 *
 * @author Chirag
 */
public class AssignCountries {
    /**
     * generates random number between zero and p_High-1.
     * @param p_High upper bound for random number generation.
     * @return random number generated.
     */
    public static int getRandomNumber(int p_High) {
        Random l_Random = new Random();
        int l_Low = 0;
        int l_Number = l_Random.nextInt(p_High - l_Low) + l_Low;
        return l_Number;
    }
    
}
