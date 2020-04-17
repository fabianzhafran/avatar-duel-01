package com.avatarduel.controller;

import com.avatarduel.Card.Card;
import com.avatarduel.Card.Element;
import com.avatarduel.Card.Monster;
import com.avatarduel.Player;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.HashMap;

import static com.avatarduel.Card.Element.*;

abstract public class FieldController {
    @FXML protected GameplayController gameplayController;
    @FXML protected HandController handController;
    @FXML protected CharacterArenaController characterArenaController;
    @FXML protected SkillArenaController skillArenaController;

    @FXML private AnchorPane fieldPane;

    @FXML private Text deckCount;
    @FXML private Label fireElement;
    @FXML private Label waterElement;
    @FXML private Label earthElement;
    @FXML private Label airElement;
    @FXML private Label hpLabel;
    @FXML private Label playerLabel;

//    @FXML private Button summonAttButton;
//    @FXML private Button summonDefButton;

    HashMap<Element, Label> elmtMap = new HashMap<Element, Label>();

    protected Player player;

    public void init(GameplayController g) {
        this.gameplayController = g;
        handController.init(this);
        characterArenaController.init(this);
        skillArenaController.init(this);
        player = new Player("Player");
        for (int i = 0; i < 7; i++) {
                this.draw();
        }
        playerLabel.setText(player.getNamePlayer());
        hpLabel.setText("HP " + String.valueOf(player.getHp()));
        waterElement.setText(String.valueOf(player.getLandPowerByElement(WATER)) + " / " + player.getMaxLandPowerByElement(WATER));
        fireElement.setText(String.valueOf(player.getLandPowerByElement(FIRE)) + " / " + player.getMaxLandPowerByElement(FIRE));
        airElement.setText(String.valueOf(player.getLandPowerByElement(AIR)) + " / " + player.getMaxLandPowerByElement(AIR));
        earthElement.setText(String.valueOf(player.getLandPowerByElement(EARTH)) + " / " + player.getMaxLandPowerByElement(EARTH));
        elmtMap.put(WATER, waterElement);
        elmtMap.put(FIRE, fireElement);
        elmtMap.put(AIR, airElement);
        elmtMap.put(EARTH, earthElement);
    }

    public void setDescCard(Card c) {
//        System.out.println("Sending to gameplay...");
        gameplayController.setDescCard(c);
    }

    public void draw() {

//        handController.addCard();
        System.out.println("Performing draw...");
//        System.out.println(drawnCard.getDeskripsi());
        Group newCard = CardUtils.createCard(player.draw());
        handController.addCard(newCard);

        System.out.println(this.player.getDeckCount());
        deckCount.setText(String.valueOf(this.player.getDeckCount()));
    }

    public void useCard(int idx, boolean isAtt) {
        Card card = player.getHand().get(idx);
        player.putToField(idx, isAtt);

        if (card.getType().equals("Land")) {
            elmtMap.get(card.getElement()).setText(String.valueOf(player.getLandPowerByElement(card.getElement())) + " / " + player.getMaxLandPowerByElement(card.getElement()));
        } else if (card.getType().equals("Monster")) {
            characterArenaController.summon(card, isAtt);
        }

        handController.removeCard(idx);



    }

}
