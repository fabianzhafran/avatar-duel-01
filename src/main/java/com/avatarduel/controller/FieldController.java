package com.avatarduel.controller;

import com.avatarduel.Card.Card;
import com.avatarduel.Card.Skill;
import com.avatarduel.Card.Element;
import com.avatarduel.Card.SummonedMonster;
import com.avatarduel.Player;
import com.avatarduel.phase.*;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.HashMap;

import static com.avatarduel.Card.Element.*;

abstract public class FieldController implements NotifyPhase {
    @FXML protected GameplayController gameplayController;
    @FXML protected HandController handController;
    @FXML protected ArenaController arenaController;

    @FXML private AnchorPane fieldPane;

    @FXML private Text deckCount;
    @FXML private Label fireElement;
    @FXML private Label waterElement;
    @FXML private Label earthElement;
    @FXML private Label airElement;
    @FXML private Label hpLabel;
    @FXML private Label playerLabel;

    HashMap<Element, Label> elmtMap = new HashMap<Element, Label>();

    protected Player player;
    protected int phaseNumber;
    protected int playerTurn;

    public void init(GameplayController g) {
        this.gameplayController = g;
        handController.init(this);
        player = new Player("Player");
        for (int i = 0; i < 7; i++) {
            this.draw();
        }
        playerLabel.setText(player.getNamePlayer());
        setHP(player.getHp());
        waterElement.setText(String.valueOf(player.getLandPowerByElement(WATER)) + " / " + player.getMaxLandPowerByElement(WATER));
        fireElement.setText(String.valueOf(player.getLandPowerByElement(FIRE)) + " / " + player.getMaxLandPowerByElement(FIRE));
        airElement.setText(String.valueOf(player.getLandPowerByElement(AIR)) + " / " + player.getMaxLandPowerByElement(AIR));
        earthElement.setText(String.valueOf(player.getLandPowerByElement(EARTH)) + " / " + player.getMaxLandPowerByElement(EARTH));
        elmtMap.put(WATER, waterElement);
        elmtMap.put(FIRE, fireElement);
        elmtMap.put(AIR, airElement);
        elmtMap.put(EARTH, earthElement);
    }

    // Phase listener function 

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
        }
    } 

    // Label setter

    public void setHP(int hp) {
        hpLabel.setText("HP " + String.valueOf(hp));
    }

    public void setDescCard(Card c) {
        gameplayController.setDescCard(c);
    }

    public void setDescCard(SummonedMonster sm) {
        gameplayController.setDescCard(sm, player.getSkillOnField());
    }

    // Getter

    public Player getPlayer() {
        return player;
    }

    public HandController getHandController() {
        return handController;
    }

    // Basic methods

    public void draw() {
        Card cardToPutOnHand = player.draw();
        System.out.println("WOWOWOW");
        if (cardToPutOnHand != null) {
            Group newCard = CardUtils.createCard(cardToPutOnHand);
            handController.addCard(newCard);
        }
        deckCount.setText(String.valueOf(this.player.getDeckCount()));
    }

    public void useCard(int idx, boolean isAtt) {
        Card card = player.getHand().get(idx);
        System.out.println("~~~FieldController~~~");
        System.out.println("Card: " + card.getName());
        player.putToField(idx, isAtt);


            if (card.getType().equals("Land")) {
                // Insert method here
            } else if (card.getType().equals("Monster")) {
                arenaController.summon(card, isAtt);
            } else {
                arenaController.activateCardEff(card);
                handController.setIsEquipping(true);
            }
            handController.removeCard(idx);

        elmtMap.get(card.getElement()).setText(String.valueOf(player.getLandPowerByElement(card.getElement())) + " / " + player.getMaxLandPowerByElement(card.getElement()));
    }

    // Battle Methods

    public void startAttack(int idx) {
        System.out.println("Field start Attack");
        gameplayController.startAttack(idx);
    }

    public void receiveAttack(int idxAttacker, int atkValue) {
        System.out.println("Field Receive Attack");
        if (player.getNumberOfMonstersOnField() > 0) {
            arenaController.receiveAttack(idxAttacker, atkValue);
        } else {
            // Direct Attack
            gameplayController.startBattle(idxAttacker, -1);
        }
    }

    public void startBattle(int idxAttacker, int idxReceiver) {
        System.out.println("Field Start Battle");
        gameplayController.startBattle(idxAttacker, idxReceiver);
    }
}
