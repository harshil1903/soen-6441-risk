package com.risk.main;

import com.risk.controller.GameCommands;
import com.risk.controller.MapCommands;
import com.risk.exception.InvalidMapException;
import com.risk.gameutils.AssignCountries;
import com.risk.gameutils.Reinforce;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Player;
import com.risk.orders.Order;
import com.risk.strategy.CheaterPlayerStrategy;

import java.util.ArrayList;
import java.util.List;

import static com.risk.main.Main.d_Log;
import static com.risk.main.Main.d_PlayerList;
import static com.risk.main.Main.d_Map;



//  tournament -M europe -P benevolent benevolent cheater -G 3 -D 15
public class Tournament {
    static ArrayList<String> d_mapNames = new ArrayList<>();
    static ArrayList<String> d_listOfPlayerStrategies = new ArrayList<>();
    static int d_numGames;
    static int d_maxTurns;

    public static void begin(List<String> p_argumentTokens){

        d_listOfPlayerStrategies.clear();
        d_mapNames.clear();

        if(!validateTournamentArguments(p_argumentTokens)){
            System.out.println("Exiting Tournament Mode");
            return;
        }

        for(int i = 0 ; i < d_listOfPlayerStrategies.size(); i++)
        {
            Player l_player = new Player(d_listOfPlayerStrategies.get(i) + i,d_listOfPlayerStrategies.get(i));
            System.out.println("Player Name and Strategy : " + l_player.getD_PlayerName() + "   " + l_player.d_playerStrategyType);
            d_PlayerList.add(l_player);
        }

        for(int i = 0 ; i < d_mapNames.size(); i++)
        {

            for(int j = 0; j < d_numGames ; j++) {
                List<String> l_mapName = new ArrayList<>();
                l_mapName.add(d_mapNames.get(i));
                boolean l_mapLoaded = false;

                try {
                    l_mapLoaded = MapCommands.editMapCommand(d_mapNames);

                    if(!l_mapLoaded){
                        System.out.println("Map "+ d_mapNames.get(i) + " could not be loaded, moving to next map");
                        continue;
                    }


                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nPLAYING GAME " + (j+1) + " FOR MAP " + (i+1) + ":\n\n\n");

                    playGame();

                    d_Map.clearMapData();
                }
                catch (Exception e){}
                for (Player l_player : d_PlayerList)
                    l_player.clearPlayerData();

            }
        }

    }

    public static void playGame() throws InvalidMapException {
        int l_turnCount = 0;
        String l_playerWon = "";

        AssignCountries.assignCountries();
        System.out.println("\n\nCountries have been successfully assigned to all the players\n");

        for(int i = 0 ; i < d_maxTurns ; i++)
        {
            System.out.println("\n\nNEW TURN ");

            System.out.println("\nReinforcing Armies");
            Reinforce.assignReinforcementArmies();
            System.out.println("\nArmies have been successfully reinforced among players\n\n\n\n");



            issueOrder();


            executeOrder();

            l_playerWon = playerWon();

            if(!l_playerWon.equals(""))
            {
                System.out.println("\n\n******************************************\n");
                System.out.println("Player " + l_playerWon + " has Won the Game!!!");
                System.out.println("\n******************************************\n\n\n");
                d_Log.notify("Player " + l_playerWon + " has Won the Game!!!");
                GameCommands.showMapCommand(new ArrayList<>());
                break;
            }
//
//            for(Player p : d_PlayerList)
//            {
//                if(p.getD_AssignedCountries().size() == d_Map.getCountryListOfMap().size())
//                {
//                    System.out.println("Player " + l_playerWon+ " has Won the Game!!!");
//                    MapCommands.showMapCommand(new ArrayList<>());
//                    break;
//                }
//            }
//
//            for(Country c : d_Map.getCountryListOfMap()){
//                System.out.println("Country Name : " + c.getD_CountryName() + "   Owned by : " + c.getD_Player().getD_PlayerName());
//            }
        }

        if(l_playerWon.equals("")){
            System.out.println("\n\n\n\nGame is draw because nobody won in the given number of turns\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }


    }

    public static void issueOrder()
    {
        for(Player l_player : d_PlayerList)
        {
            l_player.issueOrder();
//            if (l_player.getD_playerStrategy() instanceof CheaterPlayerStrategy) {
//                System.out.println("INSIDE INSTANCE OF CHEATER CHECK");
//                String l_playerWon = playerWon();
//
//
//                System.out.println("Player Won value : " + l_playerWon);
//                System.out.println(l_player.getD_AssignedCountries().size() + "\n\n\n");
//                if (!l_playerWon.equals("")) {
//                    System.out.println("\n\n******************************************\n");
//                    System.out.println("Player " + l_playerWon + " has Won the Game!!!");
//                    System.out.println("\n******************************************\n\n\n");
//                    d_Log.notify("Player " + l_playerWon + " has Won the Game!!!");
//                    GameCommands.showMapCommand(new ArrayList<>());
//                    break;
//                }
//            }
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
        System.out.println("Executing Orders finished\n \n");

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
//        int l_flag;
//
//        for (Player l_player : d_PlayerList) {
//            l_flag = 0;
//            String l_playerName = l_player.getD_PlayerName();
//            System.out.println("\n\nPlayer Name : " + l_playerName +"\n\n");
//            for (Continent l_continent : d_Map.getD_Continents()) {
//
//                for (Country l_country : l_continent.getD_Countries()) {
//
//                    if (!l_country.getD_Player().getD_PlayerName().equals(l_playerName)) {
//                        //return "";
//                        l_flag = 1;
//                    }
//                }
//            }
//
//            return l_playerName;
//        }
//        return "";
        String l_winner = d_Map.getCountryListOfMap().get(0).getD_Player().getD_PlayerName();

        for(Country l_country : d_Map.getCountryListOfMap())
        {
            if(!l_country.getD_Player().getD_PlayerName().equals(l_winner))
            {
                return "";
            }
        }
        return l_winner;
    }




    public static boolean validateTournamentArguments(List<String> p_argumentTokens)
    {
        for (int i = 0; i < p_argumentTokens.size(); i++) {
            System.out.println("TOKEN : " + p_argumentTokens.get(i));
            if (p_argumentTokens.get(i).equals("-M")) {
                i++;
                while(!p_argumentTokens.get(i).equals("-P")){
                    System.out.println("MAP : " + p_argumentTokens.get(i));
                    d_mapNames.add(p_argumentTokens.get(i++));
                }
                i--;
            }
            else if (p_argumentTokens.get(i).equals("-P")) {
                i++;
                while(!p_argumentTokens.get(i).equals("-G")){
                    System.out.println("Strategy : " + p_argumentTokens.get(i));
                    d_listOfPlayerStrategies.add(p_argumentTokens.get(i++));
                }
                i--;
            }
            else if (p_argumentTokens.get(i).equals("-G")) {
                i++;
                System.out.println("Num of Games : " + p_argumentTokens.get(i));
                d_numGames = Integer.parseInt(p_argumentTokens.get(i));
            }
            else if (p_argumentTokens.get(i).equals("-D")) {
                i++;
                System.out.println("Num of Turns : " + p_argumentTokens.get(i));
                d_maxTurns = Integer.parseInt(p_argumentTokens.get(i));
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
