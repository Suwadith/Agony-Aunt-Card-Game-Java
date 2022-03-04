package controller;

import model.Counter;
import model.Player;

import java.util.Arrays;

import static model.CounterColor.*;
import static model.CounterColor.YELLOW;

public class GameController {

    //Creating player array
    Player[] players = new Player[4];
    //Creating counter array with different colours
    Counter[] counter = new Counter[]{new Counter(RED), new Counter(GREEN), new Counter(BLUE), new Counter(YELLOW)};

    public void createPlayers(String[] playerNames) {

        //Creating individual player objects
        for (int i = 0; i < 4; i++) {
            players[i] = new Player((i + 1), playerNames[i], counter[i]);
        }

        for (int i = 0; i < 4; i++) {
            System.out.println(players[i].getPlayerName());
        }

    }

}
