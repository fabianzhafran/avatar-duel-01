package com.avatarduel.controller;

import com.avatarduel.card.Card;
import com.avatarduel.card.Skill;
import com.avatarduel.card.Element;
import com.avatarduel.card.SummonedMonster;
import com.avatarduel.Player;
import com.avatarduel.phase.*;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.HashMap;

import static com.avatarduel.card.Element.*;

/**
 * HandController is a controller to the Hand.fxml file.
 * This class controls the GUI of a card that is in a Player's hand.
 * This controller is linked to a FieldController class.
 * FieldController is a controller to the P1Field.fxml or P2Field.fxml.
 * FieldController acts as an intermediary between ArenaController, HandController, and GamePlayController.
 */
abstract public class FieldController implements NotifyPhase {
    /**
     * A linked GameplayController class.
     * Interacts with enemy FieldController through GameplayController.
     */
    @FXML protected GameplayController gameplayController;
    /**
     * A linked HandController class.
     */
    @FXML protected HandController handController;
    /**
     * A linked ArenaController class.
     */
    @FXML protected ArenaController arenaController;

    /**
     * The pane that contains the whole field GUI
     */
    @FXML protected AnchorPane fieldPane;

    @FXML protected Text deckCount;
    @FXML protected Label fireElement;
    @FXML protected Label waterElement;
    @FXML protected Label earthElement;
    @FXML protected Label airElement;
    @FXML protected Label energyElement;
    @FXML protected Label hpLabel;
    @FXML protected Label playerLabel;

    /**
     * Maps an Element to its corresponding Label
     */
    HashMap<Element, Label> elmtMap = new HashMap<Element, Label>();

    /**
     * The player that is linked to this controller
     */
    protected Player player;
    /**
     * Indicates the phase that the player is in.
     * 1 == Draw Phase
     * 2 == Main Phase
     * 3 == Battle Phase
     * 4 == End Phase
     */
    protected int phaseNumber;
    /**
     * The value is -1 if player is not in turn
     * The value can be 1 or 2 according to the player
     */
    protected int playerTurn;

    /**
     * The value is true if a skill is activating in the game
     */
    protected boolean skillActivating = false;

    /**
     * Link this class to a GameplayController class.
     * Make HandController and ArenaController class link to this class.
     * Prepare GUI for Player
     * @param g
     */
    public void init(GameplayController g) {
        this.gameplayController = g;
        handController.init(this);
        player = new Player("Player");
        for (int i = 0; i < 7; i++) {
            this.draw();
        }

        setHP(player.getHp());
        waterElement.setText(String.valueOf(player.getLandPowerByElement(WATER)) + " / " + player.getMaxLandPowerByElement(WATER));
        fireElement.setText(String.valueOf(player.getLandPowerByElement(FIRE)) + " / " + player.getMaxLandPowerByElement(FIRE));
        airElement.setText(String.valueOf(player.getLandPowerByElement(AIR)) + " / " + player.getMaxLandPowerByElement(AIR));
        earthElement.setText(String.valueOf(player.getLandPowerByElement(EARTH)) + " / " + player.getMaxLandPowerByElement(EARTH));
        energyElement.setText(String.valueOf(player.getLandPowerByElement(ENERGY)) + " / " + player.getMaxLandPowerByElement(ENERGY));
        elmtMap.put(WATER, waterElement);
        elmtMap.put(FIRE, fireElement);
        elmtMap.put(AIR, airElement);
        elmtMap.put(EARTH, earthElement);
        elmtMap.put(ENERGY, energyElement);
    }

    // Phase listener function 

    /**
     * Get notified by GameplayController for the current phase and turn.
     * This method is run through a linked GameplayController.
     * Notify HandController and ArenaController.
     * Update Player's information on GUI
     * @param phaseNumber
     * @param playerTurn
     */
    public void notifyPhase(int phaseNumber, int playerTurn) {
        this.phaseNumber = phaseNumber;
        this.playerTurn = playerTurn;
        this.handController.notifyPhaseToHand(phaseNumber, playerTurn);
        this.arenaController.notifyPhaseToArena(phaseNumber, playerTurn);
        if (phaseNumber == 1) {
            if (playerTurn != -1) {
                draw();
                player.resetMonsterHasAttacked();
                player.resetMonsterIsJustSummoned();
            }
            player.setLandPowerToMax();
            waterElement.setText(String.valueOf(player.getLandPowerByElement(WATER)) + " / " + player.getMaxLandPowerByElement(WATER));
            fireElement.setText(String.valueOf(player.getLandPowerByElement(FIRE)) + " / " + player.getMaxLandPowerByElement(FIRE));
            airElement.setText(String.valueOf(player.getLandPowerByElement(AIR)) + " / " + player.getMaxLandPowerByElement(AIR));
            earthElement.setText(String.valueOf(player.getLandPowerByElement(EARTH)) + " / " + player.getMaxLandPowerByElement(EARTH));
            energyElement.setText(String.valueOf(player.getLandPowerByElement(ENERGY)) + " / " + player.getMaxLandPowerByElement(ENERGY));
        }
    } 

    // Label setter

    /**
     * Set the player's HP on GUI
     * @param hp
     */
    public void setHP(int hp) {
        hpLabel.setText("HP " + String.valueOf(hp));
    }

    /**
     * Send a Card information to GameplayController to be displayed
     * @param c the sent card (Card in hand or skill on field)
     */
    public void setDescCard(Card c) {
        gameplayController.setDescCard(c);
    }

    /**
     * Send a SummonedMonster information to GameplayController to be displayed
     * @param sm Monster on field
     */
    public void setDescCard(SummonedMonster sm) {
        gameplayController.setDescCard(sm, player.getSkillOnField());
    }

    /**
     * Set skillActivating to true when a skill is activating
     * @param skillActivating
     */
    public void setSkillActivating(boolean skillActivating) {
        this.skillActivating = skillActivating;
    }

    // Getter

    public Player getPlayer() {
        return player;
    }

    public boolean isSkillActivating() {
        return skillActivating;
    }

    public HandController getHandController() {
        return handController;
    }

    public ArenaController getArenaController() {
        return arenaController;
    }

    public Player getEnemy() {
        return (playerTurn != -1) ? gameplayController.getPlayerNotInTurn() : null;
    }

    // Basic methods

    /**
     * Visualize draw on GUI
     */
    public void draw() {
        if (player.getHand().size() >= 9) {
            handController.removeCard(0);
        }
        Card cardToPutOnHand = player.draw();
        if (cardToPutOnHand != null) {
            Group newCard = CardUtils.createCard(cardToPutOnHand);
            handController.addCard(newCard);
        }
        deckCount.setText(String.valueOf(this.player.getDeckCount()));
    }

    /**
     * Use Card that is sent from HandController to be
     * used in ArenaController corresponding
     * to each of its type
     * @param idx the index of the card in the player's hand
     * @param isAtt equals to true is monster want to be summoned in attack position
     */
    public void useCard(int idx, boolean isAtt) {
        Card card = player.getHand().get(idx);
        player.putToField(idx, isAtt);
            if (card.getType().equals("Land")) {
                handController.setLandUsed(true);
            } else if (card.getType().equals("Monster")) {
                arenaController.summon(card, isAtt);
            } else {
                Skill skillCard = (Skill) card;
                setSkillActivating(true);
                arenaController.activateCardEff(skillCard);
            }
            handController.removeCard(idx);
        elmtMap.get(card.getElement()).setText(String.valueOf(player.getLandPowerByElement(card.getElement())) + " / " + player.getMaxLandPowerByElement(card.getElement()));
    }



    // Battle Methods

    /**
     * Receive attacking player's monster's index and give to GameplayController
     * @param idx attacking monster's index in Player's array of SummonedMonster
     */
    public void startAttack(int idx) {
        gameplayController.startAttack(idx);
    }

    /**
     * Received from GameplayController when enemy is attacking.
     * Send signal to ArenaController if there is a defending monster,
     * send signal to GameplayController for a direct attack if there is none
     * @param idxAttacker index of monster in attacking player
     * @param atkValue the attacking monster's attack value
     */
    public void receiveAttack(int idxAttacker, int atkValue) {
        System.out.println("Field Receive Attack");
        if (player.getNumberOfMonstersOnField() > 0) {
            arenaController.receiveAttack(idxAttacker, atkValue);
        } else {
            // Making a direct attack
            gameplayController.startBattle(idxAttacker, -1);
        }
    }

    /**
     * Send attacker and receiver index to GameplayController.
     * Activated from ArenaController
     * @param idxAttacker Index of monster in attacking player
     * @param idxReceiver Index of monster in receiving player
     */
    public void startBattle(int idxAttacker, int idxReceiver) {
        gameplayController.startBattle(idxAttacker, idxReceiver);
    }

    /**
     * Receive the destroy index in the field that a player uses from ArenaController.
     * Send signal to GameplayController
     * @param idxSource Destroy skill index that is owned by a player that activates it
     */
    public void useDestroy(int idxSource) {
        gameplayController.useDestroy(idxSource);
    }

    /**
     * Receive signal that enemy is using destroy skill from GameplayController.
     * Send signal to ArenaController
     * @param idxSource Destroy skill index that is owned by a player that activates it
     */
    public void receiveDestroy(int idxSource) {
        arenaController.receiveDestroy(idxSource);
    }

    /**
     * Send activator and receiver index to GameplayController.
     * Activated from ArenaController
     * @param idxSource Index of Destroy in activating player
     * @param idxReceiver Index of Monster targeted to destroy in receiving player
     */
    public void startDestroy(int idxSource, int idxReceiver) {
        gameplayController.startDestroy(idxSource, idxReceiver);
    }
}
