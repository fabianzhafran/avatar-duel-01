package com.avatarduel.phase;

// import com.avatarduel.Player;
import com.avatarduel.controller.P1FieldController;
import com.avatarduel.controller.P2FieldController;
import com.avatarduel.controller.GameplayController;

// import javafx.fxml.FXML;

public class Phase {

    private P1FieldController p1FieldController;
    private P2FieldController p2FieldController;
    private GameplayController gameplayController; 
    private int phaseNumber;
    private int playerTurn;

    public Phase(P1FieldController p1FieldController, P2FieldController p2FieldController, GameplayController gameplayController) {
        phaseNumber = 0;
        playerTurn = 1;
        this.p1FieldController = p1FieldController;
        this.p2FieldController = p2FieldController; 
        this.gameplayController = gameplayController;
    }

    public void nextPhase() {
        if (phaseNumber == PhaseEnum.END_PHASE) {
            phaseNumber = 1;
            playerTurn = playerTurn % 2 + 1;
        } else {
            phaseNumber++;
        }
        gameplayController.notifyPhase(phaseNumber, playerTurn);
        if (playerTurn == 1) {
            System.out.println("Giliran 1");
            p1FieldController.notifyPhase(phaseNumber, playerTurn);
            p2FieldController.notifyPhase(phaseNumber, -1);
        } else {
            System.out.println("Giliran 2");
            p1FieldController.notifyPhase(phaseNumber, -1);
            p2FieldController.notifyPhase(phaseNumber, playerTurn);
        }
    }

    public int getPhaseNumber() {
        return phaseNumber;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

}