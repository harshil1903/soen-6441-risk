package com.risk.gameutils;

import static com.risk.gameutils.GenerateRandomNumber.getRandomNumber;

/**
 * Assigning random card to players.
 */
public class AssignCard {

    /**
     * Generates random card to be assigned to a player.
     *
     * @return Random card generated from cards.
     */
    public static String getCard() {
        String[] l_cards = {"bomb", "blockade", "airlift", "negotiate"};
        int l_index = getRandomNumber(l_cards.length);
        return l_cards[l_index];

    }
}
