package com.risk.strategy;

import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.Order;

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
    CheaterPlayerStrategy(Player p_player, List<Country> p_country) {
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
    public Order createOrder() {
        for (Country l_cheaterCountry : d_player.getD_AssignedCountries()) {
            for (Country l_cheaterAdjCountry : l_cheaterCountry.getD_AdjacentCountries()) {
                l_cheaterAdjCountry.setD_Player(d_player);
                d_player.addCountryToAssignedCountries(l_cheaterAdjCountry);
                l_cheaterAdjCountry.setD_NumberOfArmies(l_cheaterAdjCountry.getD_NumberOfArmies() * 2);
            }
        }
        for (Country l_cheaterCountry : d_player.getD_AssignedCountries()) {
            for (Country l_cheaterAdjCountry : l_cheaterCountry.getD_AdjacentCountries()) {
                if (l_cheaterAdjCountry.getD_Player() != d_player) {
                    l_cheaterAdjCountry.setD_NumberOfArmies(l_cheaterAdjCountry.getD_NumberOfArmies() * 2);
                    break;
                }
            }
        }
        return null;
    }
}