package com.risk.strategy;

import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.Order;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * CheaterPlayer Strategy Class
 *
 * @author Parth
 * @author Harsh
 */
public class CheaterPlayerStrategy extends PlayerStrategy {
    /**
     * Instantiates a new Player strategy.
     *
     * @param p_player  Player object
     * @param p_country List of country
     */
    public CheaterPlayerStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    /**
     * Determines the country to attack.
     * The Cheater player does not attack, so this returns null
     *
     * @return null
     */
    protected Country toAttack() {
        return null;
    }

    /**
     * Determines the Country to defend.
     * The Cheater player does not defend, so this returns null
     *
     * @return null
     */
    protected Country toDefend() {
        return null;
    }

    /**
     * Determines the Country to attack from.
     * The Cheater player does not attack, so this returns null
     *
     * @return null
     */
    protected Country toAttackFrom() {
        return null;
    }

    /**
     * Determines the Country to move from
     * The Cheater player does not move, so this returns null
     *
     * @return null
     */
    protected Country toMoveFrom() {
        return null;
    }

    /**
     * Creates an order for Cheater Player
     *
     * @return null
     */

    @Override
    public Order createOrder() throws ConcurrentModificationException {
        List<Country> l_tempCountryList = new ArrayList<Country>();
        for (Country l_cheaterCountry : d_player.getD_AssignedCountries()) {
            for (Country l_cheaterAdjCountry : l_cheaterCountry.getD_AdjacentCountries()) {
                if (l_cheaterAdjCountry.getD_Player() != d_player) {
                    Player l_tempPlayer = l_cheaterAdjCountry.getD_Player();
                    l_cheaterAdjCountry.setD_Player(d_player);
                    l_tempCountryList.add(l_cheaterAdjCountry);
                    l_tempPlayer.removeCountryFromAssignedCountries(l_cheaterAdjCountry.getD_CountryID());
                    System.out.println("Cheater Player Occupied Country: " + l_cheaterAdjCountry.getD_CountryName());
                }
            }
        }


        for (Country l_newCountry : l_tempCountryList) {
            if (!d_player.getD_AssignedCountries().contains(l_newCountry))
                d_player.addCountryToAssignedCountries(l_newCountry);
        }

        for (Country l_cheaterCountry : d_player.getD_AssignedCountries()) {
            for (Country l_cheaterAdjCountry : l_cheaterCountry.getD_AdjacentCountries()) {
                if (l_cheaterAdjCountry.getD_Player() != d_player) {
                    System.out.println("Number Of Army at " + l_cheaterAdjCountry.getD_CountryName() + " Before Cheater Player Occupied: " + l_cheaterAdjCountry.getD_NumberOfArmies());
                    l_cheaterAdjCountry.setD_NumberOfArmies(l_cheaterAdjCountry.getD_NumberOfArmies() * 2);
                    System.out.println("Number Of Army at " + l_cheaterAdjCountry.getD_CountryName() + " After Cheater Player Occupied: " + l_cheaterAdjCountry.getD_NumberOfArmies());
                    break;
                }
            }
        }
        return null;
    }
}