package com.risk.strategy;

import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.*;

import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * AggressivePlayer Strategy Class
 *
 * @author Parth
 * @author Harsh
 */
public class AggressivePlayerStrategy extends PlayerStrategy {
    int d_flag = 0;

    /**
     * Instantiates a new Player strategy.
     *
     * It focuses on centralization of forces and then attack, i.e. it deploys on its strongest country, then always
     * attack with its strongest country, then moves its armies in order to maximize aggregation of forces in one country.
     *
     * @param p_player  Player Object
     * @param p_country List of Country
     */
    public AggressivePlayerStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    /**
     * Decide the country to attack to
     * Aggressive Player always attack with its strongest country.
     *
     * @return l_country target country
     */

    protected Country toAttack() {
        d_flag = 0;
        Country l_myMaxArmies = toAttackFrom();

        for (Country l_country : l_myMaxArmies.getD_AdjacentCountries()) {
            if (!l_country.getD_Player().getD_PlayerName().equals(d_player.getD_PlayerName())) {
                    return l_country;

            }
        }

        d_flag = 1;
        ArrayList<Country> l_toAttack =  new ArrayList<Country>();


        for(Country l_country : d_player.getD_AssignedCountries())
        {
            for(Country l_adjacentCountry : l_country.getD_AdjacentCountries())
            {
                if (l_adjacentCountry.getD_Player() != d_player){
                    l_toAttack.add(l_adjacentCountry);
                }
            }
        }

        List<Country> l_newList = l_toAttack.stream().distinct().collect(Collectors.toList());
        int l_random = new Random().nextInt(l_newList.size());
        return l_newList.get(l_random);

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
     * Decide where armies are moved to.
     *
     * @return null
     */
    protected Country toMoveFrom() {
        return null;
    }


    /**
     * Creates an order for Aggressive Player and call advanced method for attack
     *
     * @return null
     */

    @Override
    public Order createOrder() {
        Random l_rand = new Random();
        int l_rndOrder = l_rand.nextInt(4);
        int l_numOfArmies;

        Country l_toAttackFrom, l_toAttack;

        l_toAttackFrom = toAttackFrom();
        l_toAttack = toAttack();

        l_numOfArmies = l_toAttackFrom.getD_NumberOfArmies() - 1;



        System.out.println("\nAggressive Player max army country : " + l_toAttackFrom.getD_CountryName() + "  " + l_toAttackFrom.getD_NumberOfArmies());

        System.out.println("\nAggressive Player to attack country : " + l_toAttack.getD_CountryName() + "  " + l_toAttack.getD_NumberOfArmies());


        if (new Random().nextBoolean()) {

            return new Deploy(d_player, l_toAttackFrom.getD_CountryName(), d_player.getD_Armies());
        }
        else {

            if(l_numOfArmies <= 0)
            {
                return new Deploy(d_player,l_toAttackFrom.getD_CountryName(), d_player.getD_Armies());
            }

            if(l_toAttackFrom.getD_AdjacentCountries().contains(l_toAttack))
            {
                return new Advance(d_player, l_toAttackFrom.getD_CountryName(), l_toAttack.getD_CountryName(), l_numOfArmies);
            }
            else
            {
                for(Country l_country : d_player.getD_AssignedCountries())
                {
                    if(l_country.getD_AdjacentCountries().contains(l_toAttack)){
                        return new Advance(d_player, l_toAttackFrom.getD_CountryName(), l_country.getD_CountryName(), l_numOfArmies);
                    }
                }
            }
        }

        return null;
    }
}