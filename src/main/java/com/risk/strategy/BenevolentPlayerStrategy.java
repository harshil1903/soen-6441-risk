package com.risk.strategy;

import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.*;

import java.util.List;
import java.util.Random;

/**
 * BenevolentPlayer Strategy Class
 *
 * @author Parth
 * @author Harsh
 */
public class BenevolentPlayerStrategy extends PlayerStrategy {

    /**
     * Instantiates a new Player strategy.
     *
     * It focuses on protecting its weak countries (deploys on its weakest country, never attacks, then moves its
     * armies in order to reinforce its weaker country).
     *
     * @param p_player  Player object
     * @param p_country List of country
     */
    public BenevolentPlayerStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    /**
     * Decide the country to attack to
     * Benevolent Player doesn't attack so this method return null
     *
     * @return null
     */

    protected Country toAttack() {
        return null;
    }

    /**
     * Decide which country should be defend
     * Benevolent Player decide to defend its country with the most armies
     *
     *
     * @return current player country with most number of armies
     */
    protected Country toDefend() {
        Country l_myMinArmies = d_player.getD_AssignedCountries().get(new Random().nextInt(d_player.getD_AssignedCountries().size()));
        List<Country> l_countryList = d_player.getD_AssignedCountries();

        for (Country l_tempCountry : l_countryList) {
            if (l_myMinArmies.getD_NumberOfArmies() > l_tempCountry.getD_NumberOfArmies()) {
                l_myMinArmies = l_tempCountry;
            }
        }
        return l_myMinArmies;
    }

    /**
     * Decide where to launch an attack from. The Defensive player does not attack, so it returns null
     *
     * @return null
     */
    protected Country toAttackFrom() {
        return null;
    }

    /**
     * Decide where armies are moved to.
     *
     * @return null
     */
    protected Country toMoveFrom() {
        Country l_myMaxArmies = d_player.getD_AssignedCountries().get(new Random().nextInt(d_player.getD_AssignedCountries().size()));
        List<Country> l_countryList = d_player.getD_AssignedCountries();

        for (Country l_tempCountry : l_countryList) {
            if (l_myMaxArmies.getD_NumberOfArmies() < l_tempCountry.getD_NumberOfArmies()) {
                l_myMaxArmies = l_tempCountry;
            }
        }
        return l_myMaxArmies;
    }

    /**
     * Create an order Benevolent Player
     *
     * @return created order
     */
    @Override
    public Order createOrder() {
        Random l_rand = new Random();
        int l_rndOrder = l_rand.nextInt(3);
        int l_numOfArmies;

        Country l_toDefend = toDefend();
        Country l_toMoveFrom = toMoveFrom();

        l_numOfArmies = l_toMoveFrom.getD_NumberOfArmies() - 1;

        if(d_player.d_armiesForAdvance > 0){
            d_player.d_armiesForAdvance = 0;
            return new Deploy(d_player, toDefend().getD_CountryName(), d_player.getD_Armies());
        }
        else {
            if (l_rndOrder >= 0) {
                switch (l_rndOrder) {
                    case (0):
                        // Advance Order
                        return new Advance(d_player, l_toMoveFrom.getD_CountryName(), l_toDefend.getD_CountryName(), l_numOfArmies);
                    case (1):
                        // AirLift Card
                        if(l_numOfArmies <= 0 ||!d_player.d_cardList.contains("airlift"))
                        {
                            return new Advance(d_player, l_toMoveFrom.getD_CountryName(), l_toDefend.getD_CountryName(), l_numOfArmies);
                        }
                        return new Airlift(d_player, l_toMoveFrom.getD_CountryName(), l_toDefend.getD_CountryName(), l_numOfArmies);
                    case (2):
                        //Blockade Card
                        if(l_numOfArmies <= 0 ||!d_player.d_cardList.contains("blockade"))
                        {
                            return new Advance(d_player, l_toMoveFrom.getD_CountryName(), l_toDefend.getD_CountryName(), l_numOfArmies);
                        }
                        return new Blockade(d_player,l_toDefend.getD_CountryName());
                }
            }
            return null;
        }
    }
}
