package com.risk.strategy;

import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.Order;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

import static com.risk.main.Main.d_PlayerList;

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
    public CheaterPlayerStrategy(Player p_player, List<Country> p_country) {
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
    public Order createOrder() throws ConcurrentModificationException{
        List<Country> l_tempCountryList = new ArrayList<Country>();


        for (Country l_cheaterCountry : d_player.getD_AssignedCountries()) {
            for (Country l_cheaterAdjCountry : l_cheaterCountry.getD_AdjacentCountries()) {
                if (l_cheaterAdjCountry.getD_Player() != d_player) {
                    Player l_tempPlayer = l_cheaterAdjCountry.getD_Player();
                    l_cheaterAdjCountry.setD_Player(d_player);
                    l_tempCountryList.add(l_cheaterAdjCountry);
                    l_tempPlayer.removeCountryFromAssignedCountries(l_cheaterAdjCountry.getD_CountryID());
                    System.out.println("Cheater Player Occupied Country: " + l_cheaterAdjCountry.getD_CountryName());
                }
            }
        }

         /*

        System.out.println("\n\nINSIDE CHEATER CREATE ORDER\n\n");

        List<Country> l_cheaterCountries = d_player.getD_AssignedCountries();
        List<Country> l_cheaterAdjacentCountries;

        for(Country l_cheaterCountry : l_cheaterCountries)
        {
            l_cheaterAdjacentCountries = l_cheaterCountry.getD_AdjacentCountries();
            for(Country l_cheaterAdjacentCountry : l_cheaterAdjacentCountries)
            {
                System.out.println("Cheater adjacent Country: " + l_cheaterAdjacentCountry.getD_CountryName() + " Owned by : "+ l_cheaterAdjacentCountry.getD_Player().getD_PlayerName());
                if(!l_cheaterAdjacentCountry.getD_Player().getD_PlayerName().equals(d_player.getD_PlayerName())){

                    l_tempCountryList.add(l_cheaterAdjacentCountry);


                    for(Player l_player : d_PlayerList)
                    {
                        if(l_player.getD_PlayerName().equals(l_cheaterAdjacentCountry.getD_Player().getD_PlayerName())){
                            l_player.removeCountryFromAssignedCountries(l_cheaterAdjacentCountry.getD_CountryID());
                        }
                    }
                    l_cheaterAdjacentCountry.getCountryFromCountryName(l_cheaterAdjacentCountry.getD_CountryName()).setD_Player(d_player);
                    System.out.println("Cheater Player Occupied Country: " + l_cheaterAdjacentCountry.getD_CountryName());
                }
            }
        }


         */

        for (Country l_newCountry : l_tempCountryList) {
            if (!d_player.getD_AssignedCountries().contains(l_newCountry))
                d_player.addCountryToAssignedCountries(l_newCountry);
        }

        for(Player p : d_PlayerList) {
            System.out.println("\n\nEnemy Player : " + p.getD_PlayerName());
            for (Country l : p.getD_AssignedCountries()) {
                System.out.println("Enemy Country : " + l.getD_CountryName());
            }
        }


        for (Country l_cheaterCountry : d_player.getD_AssignedCountries()) {
            for (Country l_cheaterAdjCountry : l_cheaterCountry.getD_AdjacentCountries()) {
                if (l_cheaterAdjCountry.getD_Player() != d_player) {
                    System.out.println("Number Of Army at " + l_cheaterAdjCountry.getD_CountryName() + " Before Cheater Player Occupied: " + l_cheaterAdjCountry.getD_NumberOfArmies());
                    l_cheaterAdjCountry.setD_NumberOfArmies(l_cheaterAdjCountry.getD_NumberOfArmies() * 2);
                    System.out.println("Number Of Army at " + l_cheaterAdjCountry.getD_CountryName() + " After Cheater Player Occupied: " + l_cheaterAdjCountry.getD_NumberOfArmies());
                    break;
                }
            }
        }
        return null;
    }
}