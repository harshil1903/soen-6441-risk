package com.risk.strategy;

import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.Order;

import java.util.List;

/**
 * The abstract claas Player strategy defines various methods for all the strategies to implement
 *
 * @author Harshil
 */
public abstract class PlayerStrategy {


    Player d_player;

    List<Country> d_country;

    /**
     * Instantiates a new Player strategy.
     *
     * @param p_player  the p player
     * @param p_country the p map
     */
    public PlayerStrategy(Player p_player, List<Country> p_country) {
        d_player = p_player;
        d_country = p_country;
    }

    /**
     * Create order.
     *
     * @return the order
     */
    public abstract Order createOrder();

    /**
     * abstract of toAttack.
     *
     * @return Country to be attacked.
     */
    protected abstract Country toAttack();

    /**
     * abstract of toAttackFrom.
     *
     * @return Country to be AttackFrom.
     */
    protected abstract Country toAttackFrom();

    /**
     * abstract of toMoveFrom.
     *
     * @return Country to be MoveFrom.
     */
    protected abstract Country toMoveFrom();

    /**
     * abstract of toDefend.
     *
     * @return Country to be Defend.
     */
    protected abstract Country toDefend();
}
