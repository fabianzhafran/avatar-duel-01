package com.avatarduel.controller;

import com.avatarduel.Card.Aura;
import com.avatarduel.Card.Card;
import com.avatarduel.Card.Monster;
import javafx.event.Event;
import javafx.fxml.FXML;

public class GameplayController {

    @FXML private CardDescController cardDescController;
    @FXML private P1FieldController p1FieldController;
    @FXML private P2FieldController p2FieldController;

    @FXML public void initialize() {
        System.out.println("App started");
//        System.out.println("Linking cardDescController...");
        cardDescController.init(this);
//        System.out.println("Linking p1FieldController...");
//        System.out.println("On p1FieldController:");
        p1FieldController.init(this);
//        System.out.println("Linking p2FieldController...");
//        System.out.println("On p2FieldController:");
        p2FieldController.init(this);
    }

    public void btnPressed(Event event) {
        System.out.println("Button pressed!");
        cardDescController.setName("Zuko");
        cardDescController.setPwr("2");
        cardDescController.setAtt("7");
        cardDescController.setDef("4");
    }

    public void setDescCard(Card card) {
//        System.out.println("Setting description...");
        cardDescController.setName(card.getName());
        cardDescController.setDesc(card.getDeskripsi());
        cardDescController.setImage(card.getImagePath());
        // Set DEscription
        // SEt ELement
        if (card.getType().equals("Monster")) {
            Monster castedCard = (Monster) card;
            cardDescController.setPwr("Pow: " + String.valueOf(castedCard.getPowerValue()));
            cardDescController.setAtt("Atk: " + String.valueOf(castedCard.getAttackValue()));
            cardDescController.setDef("Def: " + String.valueOf(castedCard.getDefenseValue()));
        } else if (card.getType().equals("Skill")) {
            Aura castedCard = (Aura) card;
            cardDescController.setPwr("Pow: " + String.valueOf(castedCard.getPowerValue()));
            cardDescController.setAtt("Atk: " + String.valueOf(castedCard.getAttackValue()));
            cardDescController.setDef("Def: " + String.valueOf(castedCard.getDefenseValue()));
        } else {
            cardDescController.setPwr(String.valueOf(" "));
            cardDescController.setAtt(String.valueOf(" "));
            cardDescController.setDef(String.valueOf(" "));
        }
    }
}