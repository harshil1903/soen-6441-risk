package com.risk.strategy;

import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.Deploy;
import com.risk.orders.Order;

import java.util.List;
import java.util.Random;

public class BenevolentPlayerStrategy extends PlayerStrategy {

    /**
     * Instantiates a new Player strategy.
     *
     * @param p_player  the p player
     * @param p_country the p map
     */
    BenevolentPlayerStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    protected Country toAttack() {
        return null;
    }

    protected Country toDefend() {
        Country l_myMaxArmies = d_player.getD_AssignedCountries().get(0);
        for (Country l_tempCountry : d_country) {
            if (l_myMaxArmies.getD_NumberOfArmies() < l_tempCountry.getD_NumberOfArmies() & d_player.getD_AssignedCountries().contains(l_tempCountry)) {
                l_myMaxArmies = l_tempCountry;
            }
        }
        return l_myMaxArmies;
    }

    protected Country toAttackFrom() {
        return null;
    }

    protected Country toMoveFrom() {
        return null;
    }

    @Override
    public Order createOrder() {
        Random l_rand = new Random();
        if (l_rand.nextInt(5) != 0) {
            return new Deploy(d_player, toDefend().getD_CountryName(), l_rand.nextInt(20));
        }
        return null;
    }
}
