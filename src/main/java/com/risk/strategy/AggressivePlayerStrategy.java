package com.risk.strategy;

import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.*;

import java.util.List;
import java.util.Random;

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
     * Decide the country to attack to
     * Aggressive Player always attack with its strongest country
     *
     * @return l_country target country
     */

    protected Country toAttack() {
        Country l_myMaxArmies = d_player.getD_AssignedCountries().get(0);
        for (Country l_tempCountry : d_country) {
            if (l_myMaxArmies.getD_NumberOfArmies() < l_tempCountry.getD_NumberOfArmies() && d_player.getD_AssignedCountries().contains(l_tempCountry)) {
                l_myMaxArmies = l_tempCountry;
            }
        }
        for (Country l_country : l_myMaxArmies.getD_AdjacentCountries()) {
            if (l_country.getD_Player() != d_player) {
                return l_country;
            }
        }
        return null;
    }

    /**
     * Decide which country should be defend
     * Benevolent Player decide to defend its country with the most armies
     *
     * @return current player country with most number of armies
     */
    protected Country toDefend() {
        return null;
    }

    /**
     * Decide where to launch an attack from. The Defensive player does not attack, so it returns null
     *
     * @return null
     */
    protected Country toAttackFrom() {
        Country l_myMaxArmies = d_player.getD_AssignedCountries().get(0);
        for (Country l_tempCountry : d_country) {
            if (l_myMaxArmies.getD_NumberOfArmies() < l_tempCountry.getD_NumberOfArmies() && d_player.getD_AssignedCountries().contains(l_tempCountry)) {
                l_myMaxArmies = l_tempCountry;
            }
        }
        return l_myMaxArmies;
    }

    /**
     * Decide where armies are moved to.
     *
     * @return null
     */
    protected Country toMoveFrom() {
        return null;
    }


    /**
     * Creates an order for Aggressive Player
     *
     * @return null
     */

    @Override
    public Order createOrder() {
        Random l_rand = new Random();
        int l_rndOrder = l_rand.nextInt(4);
        int l_numOfArmies;
        if (d_player.getD_Armies() > 0) {
            // Deploy Order
            l_numOfArmies = l_rand.nextInt(d_player.getD_Armies() + 1);
            return new Deploy(d_player, toAttackFrom().getD_CountryName(), l_numOfArmies);
        } else {
            // Advance Order
            l_numOfArmies = l_rand.nextInt(toMoveFrom().getD_NumberOfArmies() + 1);
            return new Advance(d_player, toAttackFrom().getD_CountryName(), toAttack().getD_CountryName(), l_numOfArmies);
        }
    }
}