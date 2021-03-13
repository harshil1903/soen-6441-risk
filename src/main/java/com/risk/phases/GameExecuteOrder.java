package com.risk.phases;

import com.risk.main.GameEngineNew;
import com.risk.models.Orders;
import com.risk.models.Player;
import com.risk.orders.Order;

import java.util.List;
import java.util.Scanner;

import static com.risk.main.Main.d_PlayerList;

public class GameExecuteOrder extends Game{
    GameExecuteOrder(GameEngineNew p_gameEngine) {
        super(p_gameEngine);
        executeOrder();
    }

    public boolean loadMap(List<String> p_argumentList) {
        printInvalidCommandMessage();
        return false;
    }

    public void addPlayer(List<String> p_argumentTokens) {
        printInvalidCommandMessage();
    }

    public boolean assignCountries() {
        printInvalidCommandMessage();
        return false;
    }

    public void reinforce() {
        printInvalidCommandMessage();
    }

    public void issueOrder() {
        printInvalidCommandMessage();
    }

    public void executeOrder() {
        int l_noOrdersPlayerCount = 0;
        Scanner l_scanner = new Scanner(System.in);
        String l_choice;
        while (l_noOrdersPlayerCount <= d_PlayerList.size()) {

            for (Player l_player : d_PlayerList) {
                //Order l_order = l_player.next_Order();
                if (l_player.next_Order() != null) {
                    System.out.println("Executing Order");
                    //l_order.execute();
                } else {
                    ++l_noOrdersPlayerCount;
                    System.out.println("Number of Players with No Orders : " + l_noOrdersPlayerCount);
                }
            }

        }
        System.out.println("Executing Orders finished");
        next();
    }

    public void endGame() {

    }

    public void next() {
        d_gameEngineNew.setPhase(new GameIssueOrder(d_gameEngineNew));
    }
}
