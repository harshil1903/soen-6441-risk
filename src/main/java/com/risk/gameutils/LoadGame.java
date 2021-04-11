package com.risk.gameutils;

import com.risk.exception.InvalidMapException;
import com.risk.maputils.EditMap;
import com.risk.maputils.ShowMap;
import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Map;
import com.risk.models.Player;
import com.risk.phases.Phase;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static com.risk.maputils.EditConquestMap.getCountry;

/**
 * Helps in loading previously load game form the game file.
 *
 * @author Chirag
 */
public class LoadGame {

    private static Map d_Map = new Map();
    private static ArrayList<Player> d_PlayerList = new ArrayList<Player>();
    private static Phase d_GamePhase;

    /**
     * returns map data member.
     *
     * @return Map to be returned.
     */
    public static Map getD_Map() {
        return d_Map;
    }

    /**
     * returns list of player
     *
     * @return arraylist of player
     */
    public static ArrayList<Player> getD_PlayerList() {
        return d_PlayerList;
    }

    /**
     * returns phase of the saved game
     *
     * @return current phase of the game
     */
    public static Phase getD_GamePhase() {
        return d_GamePhase;
    }


    /**
     * Retrieves the map name asd creates new map.
     *
     * @param p_gameReader reads the game file
     * @throws InvalidMapException if game file not present
     */
    public static void getMap(Scanner p_gameReader) throws InvalidMapException {
        while (p_gameReader.hasNextLine()) {
            String l_line = p_gameReader.nextLine();
            if (l_line.equals("")) break;
            String[] l_parts = l_line.split(" ");
            String p_mapName = l_parts[0];
            d_Map = EditMap.editMap(p_mapName);
        }
    }

    public static void getPhase(Scanner p_gameReader) {
        while (p_gameReader.hasNextLine()) {
            String l_line = p_gameReader.nextLine();
            if (l_line.equals("")) break;
            String[] l_parts = l_line.split(" ");
            String p_phaseName = l_parts[0];
            //phase to be set
        }
    }


    public static void getCountries(Scanner p_gameReader) {
        while (p_gameReader.hasNextLine()) {
            String l_line = p_gameReader.nextLine();
            if (l_line.equals("")) break;
            String[] l_parts = l_line.split(" ");
            String p_countryName = l_parts[0];
            int p_continentId = Integer.parseInt(l_parts[1]);
            int p_armies = Integer.parseInt(l_parts[2]);
            Continent l_continent = d_Map.getContinentFromContinentList(p_continentId);
            Country l_country = l_continent.getCountryFromCountryName(p_countryName);
            l_country.setD_NumberOfArmies(p_armies);
        }
    }


    public static void getPlayers(Scanner p_gameReader) {
        while (p_gameReader.hasNextLine()) {
            String l_line = p_gameReader.nextLine();
            if (l_line.equals("")) continue;
            String[] l_parts = l_line.split(" ");
            int p_playerId = Integer.parseInt(l_parts[0]);
            String p_playerName = l_parts[1];
            String p_playerStrategy=l_parts[2];
            Player l_player = new Player();
            l_player.d_playerStrategyType=p_playerStrategy;
            d_PlayerList.add(l_player);

            l_line = p_gameReader.nextLine();
            l_parts = l_line.split(" ");

            for (int i = 1; i < l_parts.length; i++) {
                Country l_country = getCountry(l_parts[i], d_Map);
                l_player.addCountryToAssignedCountries(l_country);
            }

            l_line = p_gameReader.nextLine();
            l_parts = l_line.split(" ");
            for (int i = 1; i < l_parts.length; i++) {
                String l_card = l_parts[i];
                l_player.addCard(l_card);
            }

            l_line = p_gameReader.nextLine();
            l_parts = l_line.split(" ");
            int l_armies = Integer.parseInt(l_parts[1]);
            l_player.setD_Armies(l_armies);


//            int p_continentId=Integer.parseInt(l_parts[1]);
//            int p_armies=Integer.parseInt(l_parts[2]);
//            System.out.println(p_countryName +" "+p_continentId+" "+p_armies);
//            Continent l_continent=d_Map.getContinentFromContinentList(p_continentId);
//            Country l_country=l_continent.getCountryFromCountryName(p_countryName);
//            l_country.setD_NumberOfArmies(p_armies);
        }
    }

    public static void loadGame(String p_gameFile) {
        String l_path = "src/main/resources/";
        String l_fileName = p_gameFile + ".game";
        File l_game = new File(l_path + l_fileName);
        if (l_game.exists()) {
            Scanner l_gameReader = null;
            try {
                l_gameReader = new Scanner(l_game);
                while (l_gameReader.hasNextLine()) {
                    String l_line = l_gameReader.nextLine();
                    if (l_line.equals("[map]")) {
                        getMap(l_gameReader);
                    }
                    if (l_line.equals("[phase]")) {
                        getPhase(l_gameReader);
                    }
                    if (l_line.equals("[countries]")) {
                        getCountries(l_gameReader);
                    }
                    if (l_line.equals("[players]")) {
                        getPlayers(l_gameReader);
                    }
                }
                System.out.println("Loaded Game successfully form existing game file");


                System.out.println("Game is loaded");

            } catch (FileNotFoundException | InvalidMapException e) {
                System.out.println("Game file not found");
            }


        }
    }

//    public static void main(String[] args) {
//        LoadGame.loadGame("Game1");
//        //ShowMap.displayEditorMap(d_Map);
//
//    }
}

