package com.risk.orders;

import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.gameutils.AssignCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static com.risk.main.Main.d_Log;

/**
 * The type advance
 *
 * @author Parth
 * @author Harsh
 */

public class Advance implements Order {

    String d_sourceCountryName;
    String d_targetCountryName;
    String d_assignedCard;

    Country d_sourceCountry;
    Country d_targetCountry;
    Player d_player;
    int d_numberOfArmies;

    /**
     * constructor for advance class
     *
     * @param p_sourceCountryName Source Country Name
     * @param p_targetCountryName Target Country Name
     * @param p_player            Current player object
     * @param p_numberOfArmies    Number of armies
     */

    public Advance(Player p_player, String p_sourceCountryName, String p_targetCountryName, int p_numberOfArmies) {
        this.d_sourceCountryName = p_sourceCountryName;
        this.d_targetCountryName = p_targetCountryName;
        this.d_numberOfArmies = p_numberOfArmies;
        this.d_player = p_player;

        d_sourceCountry = new Country();
        d_sourceCountry = d_sourceCountry.getCountryFromCountryName(d_sourceCountryName);
        d_targetCountry = new Country();
        d_targetCountry = d_targetCountry.getCountryFromCountryName(d_targetCountryName);
    }


    /**
     * Valid boolean.
     *
     * @return the boolean
     */
    public boolean valid() {
        //first check player has a advance card or not in card list

        if (!d_player.getDiplomacyPlayer().contains(d_targetCountry.getD_Player())) {
            if (d_sourceCountry.getD_NumberOfArmies() > d_numberOfArmies) {
                if (d_sourceCountryName != d_targetCountryName) {
                    if (d_sourceCountry.getD_AdjacentCountries().contains(d_targetCountry)) {
                        return true;
                    } else {
                        System.out.println(d_sourceCountryName + " and " + d_targetCountryName + " are not adjacent");
                        d_Log.notify(d_sourceCountryName + " and " + d_targetCountryName + " are not adjacent");
                        return false;
                    }
                } else {
                    System.out.println("Source country name " + d_targetCountryName + " and Target country name " + d_targetCountryName + " are same so advance order does not changes anything");
                    d_Log.notify("Source country name " + d_targetCountryName + " and Target country name " + d_targetCountryName + " are same so advance order does not changes anything");
                    return true;
                }

            } else {
                System.out.println(d_player.getD_PlayerName() + " does not have sufficient armies to be attack to the target country");
                d_Log.notify(d_player.getD_PlayerName() + " does not have sufficient armies to be attack to the target country");
                return false;
            }
        } else {
            System.out.println(d_player.getD_PlayerName() + " can not attack on " + d_targetCountry.getD_Player().getD_PlayerName() + " because of diplomacy card");
            d_Log.notify(d_player.getD_PlayerName() + " can not attack on " + d_targetCountry.getD_Player().getD_PlayerName() + " because of diplomacy card");
            return false;
        }
    }

    /**
     * Prints the order.
     */
    public void printOrder() {
        System.out.println("Order Type : Advance \nPlayer : " + d_player.getD_PlayerName() + " Source Country : " + d_sourceCountryName
                + " Target Country : " + d_targetCountryName + " Number Of Armies : " + d_numberOfArmies);
        d_Log.notify("Order Type : Advance \nPlayer : " + d_player.getD_PlayerName() + " Source Country : " + d_sourceCountryName
                + " Target Country : " + d_targetCountryName + " Number Of Armies : " + d_numberOfArmies);
    }


    /**
     * Perform one time attack, compare the result of dice, do deduction of result. check if the country is conquered
     */
    public void execute() {
        printOrder();
        if (valid()) {

            int l_sourceArmies = d_numberOfArmies;
            int l_targetArmies = d_targetCountry.getD_NumberOfArmies();

            ArrayList<Integer> attackDice = rollNDice(l_sourceArmies);
            ArrayList<Integer> defensiveDice = rollNDice(l_targetArmies);


            for (int i = 0; i < Math.min(defensiveDice.size(), attackDice.size()); i++) {

                if (attackDice.get(i) * 0.6 > defensiveDice.get(i) * 0.7) {
                    l_targetArmies--;
                } else {
                    l_sourceArmies--;
                }
            }

            if (l_targetArmies <= 0) {
                //Attacker Won
                if (l_sourceArmies > 0) {
                    d_sourceCountry.setD_NumberOfArmies(d_sourceCountry.getD_NumberOfArmies() - d_numberOfArmies);
                    d_targetCountry.setD_NumberOfArmies(l_sourceArmies);
                }
                d_player.addCountryToAssignedCountries(d_targetCountry);

                if (!d_sourceCountry.getD_Player().getD_PlayerName().equals(d_targetCountry.getD_Player().getD_PlayerName())) {
                    d_assignedCard = AssignCard.getCard();
                    d_player.addCard(d_assignedCard);
                    System.out.println(d_player.getD_PlayerName() + " won and got " + d_assignedCard);
                    d_Log.notify(d_player.getD_PlayerName() + " won and got " + d_assignedCard);
                }
                Player l_tempPlayer = d_targetCountry.getD_Player();
                l_tempPlayer.removeCountryFromAssignedCountries(d_targetCountry.getD_CountryID());
                d_targetCountry.setD_Player(d_player);
            } else {
                //Defender won

                d_sourceCountry.setD_NumberOfArmies(d_sourceCountry.getD_NumberOfArmies() - l_sourceArmies);
                d_targetCountry.setD_NumberOfArmies(l_targetArmies);
                System.out.println(d_targetCountry.getD_Player().getD_PlayerName() + " Successfully Defended Country " + d_targetCountryName);
                d_Log.notify(d_targetCountry.getD_Player().getD_PlayerName() + " Successfully Defended Country " + d_targetCountryName);


            }
            System.out.println("Order Executed Successfully\n");
        } else {
            System.out.println("Invalid Order, not executed\n");
            d_Log.notify("Invalid Order, not executed\n");
        }
    }


    /**
     * Assign random strength to each army of the country.
     *
     * @param p_numberOfDice no of armies.
     * @return arraylist of strength of armies
     */
    public ArrayList<Integer> rollNDice(int p_numberOfDice) {

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < p_numberOfDice; i++) {
            result.add(new Random().nextInt(p_numberOfDice) + 1);
        }

        return result;
    }

}
