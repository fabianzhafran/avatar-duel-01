package com.avatarduel.controller;

import com.avatarduel.Card.*;
import com.avatarduel.phase.Phase;
import javafx.event.Event;
import javafx.fxml.FXML;

import java.util.ArrayList;

public class GameplayController {

    private Phase phase;

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
        phase = new Phase(p1FieldController.getPlayer(), p2FieldController.getPlayer());
    }

    public void setDescCard(Card card) {
//        System.out.println("Setting description...");
        cardDescController.setName(card.getName());
        cardDescController.setDesc(card.getDeskripsi());
        cardDescController.setCardImage(card.getImagePath());
        cardDescController.setElementImage(card.getElement());
        cardDescController.setType(card.getType());
        // Set DEscription
        // SEt ELement
        if (card.getType().equals("Monster")) {
            Monster castedCard = (Monster) card;
            cardDescController.setPwr("Pow: " + String.valueOf(castedCard.getPowerValue()));
            cardDescController.setAtt("Atk: " + String.valueOf(castedCard.getAttackValue()));
            cardDescController.setDef("Def: " + String.valueOf(castedCard.getDefenseValue()));
        } else if (card.getType().equals("Skill")) {
            Skill skillCard = (Skill) card;
            cardDescController.setType(skillCard.getSkillType());
            if (skillCard.getSkillType().equals("Aura")) {
                Aura castedCard = (Aura) skillCard;
                cardDescController.setPwr("Pow: " + String.valueOf(castedCard.getPowerValue()));
                cardDescController.setAtt("Atk: " + String.valueOf(castedCard.getAttackValue()));
                cardDescController.setDef("Def: " + String.valueOf(castedCard.getDefenseValue()));
            }

        } else {
            cardDescController.setPwr(String.valueOf(" "));
            cardDescController.setAtt(String.valueOf(" "));
            cardDescController.setDef(String.valueOf(" "));
        }
    }

    public void setDescCard(SummonedMonster Summoned, Skill[] skillOnField) {
        Monster card = Summoned.getMonster();
        cardDescController.setName(card.getName());
        cardDescController.setDesc(card.getDeskripsi());
        cardDescController.setCardImage(card.getImagePath());
        cardDescController.setElementImage(card.getElement());
        cardDescController.setType(card.getType());
        cardDescController.setPwr("Pow: " + String.valueOf(card.getPowerValue()));
        cardDescController.setAtt("Atk: " + String.valueOf(Summoned.getAttackValue()));
        cardDescController.setDef("Def: " + String.valueOf(Summoned.getDefenseValue()));

        ArrayList<Integer> idxSkillOnField = Summoned.getSkillLinked();
        ArrayList<String> attachedSkillName = new ArrayList<String>();
        for (int idx:idxSkillOnField) {
            attachedSkillName.add(skillOnField[idx].getName());
        }

        cardDescController.setAttachedSkill(attachedSkillName);
    }
}