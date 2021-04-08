package com.risk.strategy;

import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.*;

import java.util.List;
import java.util.Random;

/**
 * RandomPlayer Strategy Class
 *
 * @author Parth
 * @author Harsh
 */
public class RandomPlayerStrategy extends PlayerStrategy {
    /**
     * Instantiates a new Player strategy.
     *
     * @param p_player  Player Object
     * @param p_country List of Country
     */
    public RandomPlayerStrategy(Player p_player, List<Country> p_country) {
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
     * Create an order for Random player
     *
     * @return created order
     */
    @Override
    public Order createOrder() {
        Random l_rand = new Random();
        int l_rndOrder = l_rand.nextInt(5);
        int l_numOfArmies;
        if (l_rndOrder >= 0) {
            System.out.println(l_rndOrder);
            switch (l_rndOrder) {
                case (0):
                    // Deploy Order
                    System.out.println("case 0 called");
                    l_numOfArmies = l_rand.nextInt(d_player.getD_Armies());
                    return new Deploy(d_player, toDefend().getD_CountryName(), l_numOfArmies);
                case (1):
                    // Advance Order
                    System.out.println("case 1 called");
                    l_numOfArmies = l_rand.nextInt(d_player.getD_Armies());
                    return new Advance(d_player, toMoveFrom().getD_CountryName(), toAttack().getD_CountryName(), l_numOfArmies);
                case (2):
                    // AirLift
                    System.out.println("case 2 called");
                    l_numOfArmies = l_rand.nextInt(d_player.getD_Armies());
                    return new Airlift(d_player, toMoveFrom().getD_CountryName(), toDefend().getD_CountryName(), l_numOfArmies);
                case (3):
                    //Blockade Card
                    System.out.println("case 3 called");
                    return new Blockade(d_player, toDefend().getD_CountryName());
                case (4):
                    //Bomb Card
                    System.out.println("case 4 called");
                    return new Bomb(d_player, toAttack().getD_CountryName());
            }
        }
        return null;
    }
}
