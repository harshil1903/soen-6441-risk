package com.risk.main;

import com.risk.controller.MapCommands;
import com.risk.exception.InvalidMapException;
import com.risk.gameutils.AssignCountries;
import com.risk.gameutils.Reinforce;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.Order;

import java.util.ArrayList;
import java.util.List;

import static com.risk.main.Main.d_Log;
import static com.risk.main.Main.d_PlayerList;
import static com.risk.main.Main.d_Map;

public class Tournament {
    static ArrayList<String> d_mapNames = new ArrayList<>();
    static ArrayList<String> d_listOfPlayerStrategies = new ArrayList<>();
    static int d_numGames;
    static int d_maxTurns;

    public static void begin(List<String> p_argumentTokens){

        if(!validateTournamentArguments(p_argumentTokens)){
            System.out.println("Exiting Tournament Mode");
            return;
        }

        for(int i = 0 ; i < d_listOfPlayerStrategies.size(); i++)
        {
            Player l_player = new Player(d_listOfPlayerStrategies.get(i));
            d_PlayerList.add(l_player);
        }

        for(int i = 0 ; i < d_mapNames.size(); i++)
        {
            List<String> l_mapName = new ArrayList<>();
            l_mapName.add(d_mapNames.get(i));
            boolean l_mapLoaded = false;
            try {
                l_mapLoaded = MapCommands.editMapCommand(l_mapName);
            }
            catch (Exception e)
            {}

            if(!l_mapLoaded){
                System.out.println("Map "+ d_mapNames.get(i) + " could not be loaded, moving to next map");
                continue;
            }

            for(int j = 0; j < d_numGames ; j++) {
                try {
                    System.out.println("PLAYING GAME " + j + " FOR MAP " + i + ":\n");
                    playGame();
                }
                catch (Exception e){}
            }
        }

    }

    public static void playGame() throws InvalidMapException {
        int l_turnCount = 0;
        String l_playerWon = "";

        for(int i = 0 ; i < d_maxTurns ; i++)
        {
            AssignCountries.assignCountries();
            System.out.println("Countries have been successfully assigned to all the players");

            System.out.println("\nReinforcing Armies");
            Reinforce.assignReinforcementArmies();
            System.out.println("\nArmies have been successfully reinforced among players");

            issueOrder();

            executeOrder();

            l_playerWon = playerWon();

            if(!l_playerWon.equals(""))
            {
                System.out.println("Player " + l_playerWon+ " has Won the Game!!!");
                MapCommands.showMapCommand(new ArrayList<>());
            }
        }

        if(l_playerWon.equals("")){
            System.out.println("Game is draw because nobody won in the given number of turns");
        }


    }

    public static void issueOrder()
    {
        for(Player l_Player : d_PlayerList)
        {
            l_Player.issueOrder();
        }
    }

    public static void executeOrder()
    {
        int l_noOrdersPlayerCount = 0;
        while (l_noOrdersPlayerCount <= d_PlayerList.size())
        {

            for (Player l_player : d_PlayerList)
            {

                Order l_order = l_player.nextOrder();

                if (l_order != null) {

                    System.out.println("Executing Order");
                    if(l_order.valid()){

                        l_order.execute();
                    }
                    else {
                        System.out.println("Invalid Order, not executed");
                    }
                } else {
                    ++l_noOrdersPlayerCount;
                }

            }

        }
        System.out.println("Executing Orders finished\n \n\nNEW TURN \n");

        for (Player l_player : d_PlayerList) {
            l_player.getDiplomacyPlayer().clear();
        }

    }

    /**
     *  To check if a player has won the game
     *
     * @return Name of player won or blank string if nobody has won
     */
    public static String playerWon(){
        for (Player l_player : d_PlayerList) {

            String l_playerName = l_player.getD_PlayerName();

            for (Continent l_continent : d_Map.getD_Continents()) {

                for (Country l_country : l_continent.getD_Countries()) {

                    if (!l_country.getD_Player().getD_PlayerName().equals(l_playerName)) {
                        return "";
                    }
                }
            }

            return l_playerName;
        }
        return "";
    }




    public static boolean validateTournamentArguments(List<String> p_argumentTokens)
    {
        for (int i = 0; i < p_argumentTokens.size(); i++) {

            if (p_argumentTokens.get(i).equals("-M")) {
                i++;
                while(!p_argumentTokens.get(i).equals("-P")){
                    d_mapNames.add(p_argumentTokens.get(i++));
                }
            }
            else if (p_argumentTokens.get(i).equals("-P")) {
                i++;
                while(!p_argumentTokens.get(i).equals("-G")){
                    d_listOfPlayerStrategies.add(p_argumentTokens.get(i++));
                }
            }
            else if (p_argumentTokens.get(i).equals("-G")) {
                d_numGames = Integer.parseInt(p_argumentTokens.get(++i));
            }
            else if (p_argumentTokens.get(i).equals("-D")) {
                d_maxTurns = Integer.parseInt(p_argumentTokens.get(++i));
            }
            else {
                System.out.println("Invalid option. Tournament Mode -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns options");
                d_Log.notify("Invalid option. Tournament Mode -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns options");
                return false;
            }
        }

        if(d_mapNames.size() > 5 || d_mapNames.size() < 1){
            System.out.println("Only 1 to 5 Maps allowed");
            d_Log.notify("Only 1 to 5 Maps allowed");
            return false;
        }

        if(d_listOfPlayerStrategies.size() > 4 || d_listOfPlayerStrategies.size() < 2){
            System.out.println("Only 2 to 4 Player Strategies allowed");
            d_Log.notify("Only 2 to 4 Player Strategies allowed");
            return false;
        }

        if(d_numGames > 5 || d_numGames < 1)
        {
            System.out.println("Only 1 to 5 Games allowed");
            d_Log.notify("Only 1 to 5 Games allowed");
            return false;
        }

        if(d_maxTurns > 50 || d_maxTurns < 10)
        {
            System.out.println("Only 10 to 50 Turns allowed");
            d_Log.notify("Only 10 to 50 Turns allowed");
            return false;
        }


        return true;
    }
}
