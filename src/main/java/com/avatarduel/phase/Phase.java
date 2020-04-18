package com.avatarduel.phase;

import com.avatarduel.Player;

public class Phase {

    private Player[] playerListener;
    private int phaseNumber;
    private int playerTurn;

    public Phase(Player player1, Player player2) {
        playerListener = new Player[2];
        playerListener[0] = player1;
        playerListener[1] = player2;
        phaseNumber = 1;
        playerTurn = 1;
    }

    public void nextPhase() {
        if (phaseNumber == 5) {
            phaseNumber = 1;
            playerTurn = (playerTurn + 1) % 2;
        } else {
            phaseNumber++;
        }
        playerListener[playerTurn].notifyPhase(phaseNumber);
        playerListener[(playerTurn + 1) % 2].notifyPhase(-1);
    }
    
}