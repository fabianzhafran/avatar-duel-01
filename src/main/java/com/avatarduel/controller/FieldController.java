package com.avatarduel.controller;

import com.avatarduel.Card.Card;
import com.avatarduel.Card.Monster;
import com.avatarduel.Player;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.text.Text;

abstract public class FieldController {
    @FXML protected GameplayController gameplayController;
    @FXML protected HandController handController;
    @FXML protected CharacterArenaController characterArenaController;
    @FXML protected SkillArenaController skillArenaController;

    @FXML private Text deckCount;

    protected Player player;

    public void init(GameplayController g) {
//        System.out.println("FieldController Linked!");
        this.gameplayController = g;
//        System.out.println("Linking handController...");
        handController.init(this);
//        System.out.println("Linking characterArenaController...");
        characterArenaController.init(this);
//        System.out.println("Linking skillArenaController...");
        skillArenaController.init(this);
        player = new Player("Player");
        for (int i = 0; i < 7; i++) {
                this.draw();
        }
//        deckCount.setText(String.valueOf(this.player.getDeckCount()));

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

    public void summon(int idx) {
        Card card = player.getHand().get(idx);
        player.putToField(idx, true);
        if (card.getType().equals("Monster")) {
            characterArenaController.summon(card);
        }
    }
}
