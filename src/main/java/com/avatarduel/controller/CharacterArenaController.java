package com.avatarduel.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

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

    public void summon(String str) {
//        int emptyCol = emptyGrid.poll();
//        Group newCard = CardUtils.createCard();
////        newCard.addEventHandler(MouseEvent.MOUSE_ENTERED,
////                event -> cardHover(event));
//        newCard.addEventHandler(MouseEvent.MOUSE_CLICKED,
//                event -> destroy(event));
//        arena.add(newCard, emptyCol, 0,  1, 1);
    }

    public void destroy(Event evt) {
        Group clickedCard = (Group) evt.getSource();
        emptyGrid.add(arena.getColumnIndex(clickedCard));
        arena.getChildren().remove(clickedCard);
    }
}

