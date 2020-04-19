package com.avatarduel.phase;

/** Handles the phase and turn for the game.
 * Uses the "Observer" design pattern, with 
 * phase as its "observer" and its attributes 
 * as its "listener"
 * 
 * @author K01_01_IF2210
 */

import com.avatarduel.Player;
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

    /** Creates a new phase
     * 
     * @param p1FieldController Player 1's field controller, acts as the listener to this class
     * @param p2FieldController Player 2's field controller, acts as the listener to this class
     * @param gameplayController The core GUI controller, acts as the listener to this class
     */
    public Phase(P1FieldController p1FieldController, P2FieldController p2FieldController, GameplayController gameplayController) {
        phaseNumber = 0;
        playerTurn = 1;
        this.p1FieldController = p1FieldController;
        this.p2FieldController = p2FieldController; 
        this.gameplayController = gameplayController;
    }

    /** Changes the phase (and player turn) of the game to the next one.
     * Notifies the changes of phase to all listeners.
     */
    public void nextPhase() {
        if (phaseNumber == PhaseEnum.END_PHASE) {
            phaseNumber = 1;
            playerTurn = playerTurn % 2 + 1;
        } else {
            phaseNumber++;
        }
        gameplayController.notifyPhase(phaseNumber, playerTurn);
        if (playerTurn == 1) {
            p1FieldController.notifyPhase(phaseNumber, playerTurn);
            p2FieldController.notifyPhase(phaseNumber, -1);
        } else {
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