package com.avatarduel.phase;

import com.avatarduel.Player;
import com.avatarduel.controller.P1FieldController;
import com.avatarduel.controller.P2FieldController;
import com.avatarduel.controller.GameplayController;

import javafx.fxml.FXML;

public class Phase {

    private P1FieldController p1FieldController;
    private P2FieldController p2FieldController;
    private int phaseNumber;
    private int playerTurn;

    public Phase(P1FieldController p1FieldController, P2FieldController p2FieldController) {
        phaseNumber = 1;
        playerTurn = 1;
        this.p1FieldController = p1FieldController;
        this.p2FieldController = p2FieldController; 
    }

    public void nextPhase() {
        if (phaseNumber == PhaseEnum.END_PHASE) {
            phaseNumber = 1;
            playerTurn = (playerTurn + 1) % 2;
        } else {
            phaseNumber++;
        }
    }

}