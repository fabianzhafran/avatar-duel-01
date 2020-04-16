package com.avatarduel.controller;

import com.avatarduel.Card.Card;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class HandController {
    @FXML private HBox handHBox;
    @FXML private FieldController fieldController;

    public void init(FieldController f) {
//        System.out.println("HandController Linked!");
        this.fieldController = f;
    }

    public void cardHover(Event evt) {
        int i = 0;
        Group hoveredCard = (Group) evt.getSource();
        hoveredCard.setTranslateY(-10);
        for (Node node : handHBox.getChildren()) {
            if (hoveredCard.getChildren().get(4).equals(((Group) node).getChildren().get(4))) {
                break;
            }
            i++;
        }
//        System.out.println("Sending to field...");
        fieldController.setDescCard(fieldController.player.getHand().get(i));
        System.out.println("Source delivered successfully");
    }

    public void printTes() {
        System.out.println("Berhasil yang lu tes!");
    }

    public void summon(Event evt) {
        int i = 0;
        System.out.println("Card Summoned");
        Group clickedCard = (Group) evt.getSource();
        for (Node node : handHBox.getChildren()) {
            if (clickedCard.getChildren().get(4).equals(((Group) node).getChildren().get(4))) {
                break;
            }
            i++;
        }
        handHBox.getChildren().remove(clickedCard);
        fieldController.summon(i);
    }

    public void addCard(Group newCard) {
        // Nanti bakal nerima parameter card
//        Group newCard = CardUtils.createCard(card);
        newCard.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event ->  cardHover(event));
        newCard.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> summon(event));
        newCard.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> newCard.setTranslateY(0));
        handHBox.getChildren().add(newCard);
    }


}
