package com.risk.gameutils;

import com.risk.models.Continent;
import com.risk.models.Country;
import com.risk.models.Player;

import static com.risk.main.Main.d_Map;
import static com.risk.main.Main.d_PlayerList;

public class Reinforce {

    /**
     * Check if reinforcement armies are left to be assigned for any player
     *
     * @return whether to continue issue orders or not
     */
    public static boolean checkReinforcementArmiesCount() {
        for (Player l_player : d_PlayerList) {
            if (l_player.getD_Armies() != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * Assign reinforcement armies to each player in the beginning of each turn.
     */
    public static void assignReinforcementArmies() {
        for (Player l_player : d_PlayerList) {
            int l_countriesOwned = l_player.getD_AssignedCountries().size();
            int l_reinforcementCount, l_flag;
            String l_playerName = l_player.getD_PlayerName();

            l_reinforcementCount = Math.max((l_countriesOwned / 3), 3);

            for (Continent l_continent : d_Map.getD_Continents()) {
                l_flag = 0;
                for (Country l_country : l_continent.getD_Countries()) {
                    if (!l_country.getD_Player().getD_PlayerName().equals(l_playerName)) {
                        l_flag = 1;
                        break;
                    }
                }
                if (l_flag == 0) {
                    l_reinforcementCount += l_continent.getD_ContinentValue();
                }
            }

            l_player.setD_Armies(l_player.getD_Armies() + l_reinforcementCount);
        }
    }
}
