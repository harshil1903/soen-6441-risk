package com.risk.strategy;

import com.risk.models.Map;
import com.risk.models.Player;
import com.risk.orders.Order;

/**
 * The abstract claas Player strategy defines various methods for all the strategies to implement
 *
 * @author Harshil
 */
public abstract class PlayerStrategy {


    Player d_player;

    Map d_map;

    /**
     * Instantiates a new Player strategy.
     *
     * @param p_player the p player
     * @param p_map    the p map
     */
    PlayerStrategy(Player p_player, Map p_map){
        d_player = p_player;
        d_map = p_map;
    }

    /**
     * Create order.
     *
     * @return the order
     */
    public abstract Order createOrder();
}
