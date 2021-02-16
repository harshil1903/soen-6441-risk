package com.risk.main;

public class GameEngine {

    public static void GamePlay()
    {
        //STARTUP PHASE

            //"loadmap filename"
            //Call editmap function to load map and run validate map function on the map

            //"gameplayer -add playername -remove playername"
            //Add or remove player from player list created in MAIN

            //"assigncountries"
            //Assign countries to players

        //MAIN GAME LOOP

            //REINFORCEMENT PHASE
                //Method to assign reinforcement armies to each player

            //ISSUE ORDERS PHASE
                //Scan for deploy command
                //Add the order by calling Player.issue_Order();
                //Subtract reinforcement armies from that player

            //EXECUTE ORDERS PHASE
                //Call next_Order() for each player which returns Order Object
                //call Order.execute()

    }
}
