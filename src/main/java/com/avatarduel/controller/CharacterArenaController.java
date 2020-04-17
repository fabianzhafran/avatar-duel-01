package com.avatarduel.controller;

import com.avatarduel.Card.Card;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Rotate;

import java.util.PriorityQueue;

public class CharacterArenaController {
    @FXML private FieldController fieldController;
    @FXML private GridPane arena;

    PriorityQueue<Integer> emptyGrid = new PriorityQueue<Integer>();

    public void init(FieldController f) {
//        System.out.println("CharacterArenaController Linked!");
        this.fieldController = f;
        for (int i = 0; i < 6; i++) {
            emptyGrid.add(i);
        }
    }

    public void cardHover(Event evt) {
        Group hoveredCard = (Group) evt.getSource();
        int i = (arena.getColumnIndex(hoveredCard));
        System.out.println("Index: " + i);
//        System.out.println("Sending to field...");
        fieldController.setDescCard((fieldController.player.getCharacterOnField())[i].getMonster());
//        fieldController.player.printMonsterOnField();
//        System.out.println("Source delivered successfully");
    }

    public void summon(Card card, boolean isAtt) {
        int emptyCol = emptyGrid.poll();
        Group newCard = CardUtils.createCard(card);
        if (!isAtt) {
            newCard.setRotate(90);
        }
        newCard.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> cardHover(event));
        newCard.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> destroy(event));
        arena.add(newCard, emptyCol, 0,  1, 1);
    }

    public void destroy(Event evt) {
        Group clickedCard = (Group) evt.getSource();
        int idx = arena.getColumnIndex(clickedCard);
        emptyGrid.add(idx);
        arena.getChildren().remove(clickedCard);
        fieldController.player.destroyMonsterOnField(idx);
    }
}

