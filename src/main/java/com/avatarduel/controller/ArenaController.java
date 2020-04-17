package com.avatarduel.controller;

import com.avatarduel.Card.Card;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.PriorityQueue;

public class ArenaController {
    @FXML private FieldController fieldController;
    @FXML private GridPane monsterArena;
    @FXML private GridPane skillArena;

    PriorityQueue<Integer> emptyMonster = new PriorityQueue<Integer>();
    PriorityQueue<Integer> emptySkill = new PriorityQueue<Integer>();

    public void init(FieldController f, boolean isPlayer1) {
//        System.out.println("CharacterArenaController Linked!");
        this.fieldController = f;
        for (int i = 0; i < 6; i++) {
            emptyMonster.add(i);
            emptySkill.add(i);
        }

        if (!isPlayer1) {
            skillArena.setLayoutY(-110);
        }
    }

    public void cardHover(Event evt) {
        Group hoveredCard = (Group) evt.getSource();
        int i = (monsterArena.getColumnIndex(hoveredCard));
        Card hovered = (fieldController.player.getCharacterOnField())[i].getMonster();

//        System.out.println("Hovered card name is" + hovered.getName());
        fieldController.setDescCard((fieldController.player.getCharacterOnField())[i].getMonster());
//        System.out.println("Source delivered successfully");
    }

    public void summon(Card card, boolean isAtt) {
        int emptyCol = emptyMonster.poll();
        Group newCard = CardUtils.createCard(card);
        if (!isAtt) {
            newCard.setRotate(90);
        }
        newCard.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> cardHover(event));
        newCard.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> destroy(event));
        monsterArena.add(newCard, emptyCol, 0,  1, 1);
    }

    public void activateCardEff(Card card) {
        int emptyCol = emptySkill.poll();
        Group newCard = CardUtils.createCard(card);
        newCard.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> cardHover(event));
//        newCard.addEventHandler(MouseEvent.MOUSE_CLICKED,
//                event -> destroy(event));
        skillArena.add(newCard, emptyCol, 0,  1, 1);
    }

    public void destroy(Event evt) {
        Group clickedCard = (Group) evt.getSource();
        int idx = monsterArena.getColumnIndex(clickedCard);
        emptyMonster.add(idx);
        monsterArena.getChildren().remove(clickedCard);
        fieldController.player.destroyMonsterOnField(idx);
    }
}

