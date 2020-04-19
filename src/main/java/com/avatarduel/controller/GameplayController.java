package com.avatarduel.controller;

import com.avatarduel.Card.*;
import com.avatarduel.Player;
import com.avatarduel.phase.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class GameplayController implements NotifyPhase {

    @FXML private CardDescController cardDescController;
    @FXML private P1FieldController p1FieldController;
    @FXML private P2FieldController p2FieldController;

    @FXML private Label playerLabel;
    @FXML private Label phaseLabel;
    @FXML private Button nextPhaseButton;

    private Phase phase;
    private int phaseNumber;
    private int playerTurn;

    @FXML public void initialize() {
        System.out.println("App started");
//        System.out.println("Linking cardDescController...");
        cardDescController.init(this);
        p1FieldController.init(this);
        p2FieldController.init(this);
        this.phaseNumber = 1;
        this.playerTurn = 1;
        phase = new Phase(p1FieldController, p2FieldController, this);
        phase.nextPhase();
        // handle next phase
        nextPhaseButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
            event -> {
                phase.nextPhase();
            });
    }

    public void setDescCard(Card card) {
//        System.out.println("Setting description...");
        cardDescController.setName(card.getName());
        cardDescController.setDesc(card.getDeskripsi());
        cardDescController.setCardImage(card.getImagePath());
        cardDescController.setElementImage(card.getElement());
        cardDescController.setColor(card.getElement());
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
        cardDescController.setColor(card.getElement());
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

    // Observer pattern
    public void notifyPhase(int phaseNumber, int playerTurn) {
        this.phaseNumber = phaseNumber;
        this.playerTurn = playerTurn;
        playerLabel.setText("Player " + String.valueOf(playerTurn));
        phaseLabel.setText(PhaseEnum.phaseEnum.get(phaseNumber));
    }

    public void startAttack(int idx) {
        // Nanti di chek turn siapa, misalnya skarang turn P1 Yang nyerang
        System.out.println("Gameplay Start Attack");
        int atkValue = p1FieldController.getPlayer().getMonsterOnField()[idx].getAttackValue();
        p2FieldController.receiveAttack(idx,atkValue);
    }

    public void startBattle(int idxAttacker, int idxReceiver) {
        // Misalkan yang nyerang p1
        System.out.println("Gameplay Start Battle");
        Player attackingPlayer = p1FieldController.getPlayer();
        Player receivingPlayer = p2FieldController.getPlayer();
        // Nanti dibikin if klo lagi giliran p2

        System.out.println("Monster before battle: ");
        receivingPlayer.printMonsterCardsOnField();
        attackingPlayer.attack(idxAttacker, idxReceiver, receivingPlayer);
        System.out.println("Monster after battle: ");
        receivingPlayer.printMonsterCardsOnField();
        System.out.println("Receiving Player HP is " + receivingPlayer.getHp());
        p2FieldController.setHP(receivingPlayer.getHp());
    }
}