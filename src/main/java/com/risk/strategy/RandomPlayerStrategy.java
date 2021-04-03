package com.risk.strategy;

import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.*;

import java.util.List;
import java.util.Random;

public class RandomPlayerStrategy extends PlayerStrategy {
    /**
     * Instantiates a new Player strategy.
     *
     * @param p_player  Player Object
     * @param p_country List of Country
     */
    RandomPlayerStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    /**
     * Decide the country to attack to.
     * The Random player determines this randomly.
     *
     * @return random territory owned by the player
     */
    protected Country toAttack() {
        Random l_rand = new Random();
        int l_rndTargetCountry;
        l_rndTargetCountry = l_rand.nextInt(d_country.size() - 1);
        return (d_country.get(l_rndTargetCountry));
    }

    /**
     * Determines the country to defend to.
     * The Random player determines this randomly.
     *
     * @return random territory owned by the player
     */
    protected Country toDefend() {
        Random l_rand = new Random();
        int l_rndTargetCountry;
        l_rndTargetCountry = l_rand.nextInt(d_player.getD_AssignedCountries().size() - 1);
        return (d_player.getD_AssignedCountries().get(l_rndTargetCountry));
    }

    /**
     * Determines the country to attack from.
     *
     * @return random territory owned by the player
     */
    protected Country toAttackFrom() {
        return toDefend();
    }

    /**
     * Determines the country to move from.
     *
     * @return random territory owned by the player
     */
    protected Country toMoveFrom() {
        return toDefend();
    }

    /**
     * Create an order benevolent player can use only deploy order
     *
     * @return created order
     */
    @Override
    public Order createOrder() {
        Random l_rand = new Random();
        int l_rndOrder = l_rand.nextInt(3);
        int l_numOfArmies;
        if (l_rand.nextInt(5) != 0) {
            switch (l_rndOrder) {
                case (0):
                    // Deploy Order
                    l_numOfArmies = l_rand.nextInt(20);
                    return new Deploy(d_player, toDefend().getD_CountryName(), l_numOfArmies);
                case (1):
                    // Advance Order
                    l_numOfArmies = l_rand.nextInt(toMoveFrom().getD_NumberOfArmies());
                    return new Advance(d_player, toMoveFrom().getD_CountryName(), toAttack().getD_CountryName(), l_numOfArmies);
                case (2):
                    // AirLift Card
                    l_numOfArmies = l_rand.nextInt(toMoveFrom().getD_NumberOfArmies());
                    return new Airlift(d_player, toMoveFrom().getD_CountryName(), toDefend().getD_CountryName(), l_numOfArmies);
                case (3):
                    //Blockade Card
                    return new Blockade(d_player, toDefend().getD_CountryName());
                case (4):
                    //Bomb Card
                    return new Bomb(d_player, toAttack().getD_CountryName());
            }
        }
        return null;
    }
}
