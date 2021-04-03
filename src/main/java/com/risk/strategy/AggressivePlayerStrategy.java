package com.risk.strategy;

import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.Order;

import java.util.List;

/**
 * AggressivePlayer Strategy Class
 *
 * @author Parth
 * @author Harsh
 */
public class AggressivePlayerStrategy extends PlayerStrategy {
    /**
     * Instantiates a new Player strategy.
     *
     * @param p_player  Player Object
     * @param p_country List of Country
     */
    AggressivePlayerStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    /**
     * Determines the country to attack.
     *
     * @return
     */
    protected Country toAttack() {
        return null;
    }

    /**
     * Creates an order for Aggressive Player
     *
     * @return null
     */

    @Override
    public Order createOrder() {
        return null;
    }
}
