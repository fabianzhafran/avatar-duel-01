package com.avatarduel.controller;

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
        System.out.println("HandController Linked!");
        this.fieldController = f;
    }

    public void cardClicked(Event evt) {
        Group clickedCard = (Group) evt.getSource();
        String ret = "";
        for (Node child:clickedCard.getChildren()) {
            if (child instanceof Label) {
                Label conv = (Label) child;
//                System.out.println(conv.getText());
                ret += conv.getText() + " ";
            }
        }
        System.out.println("Sending to field...");
        fieldController.setDescCard(ret);
        System.out.println("Source delivered successfully");
    }

    public void printTes() {
        System.out.println("Berhasil yang lu tes!");
    }

    public void addCard() {
        // Nanti bakal nerima parameter card
        Group newCard = CardUtils.createCard();
        newCard.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event ->  cardClicked(event));
        newCard.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> System.out.println("Card clicked"));
        handHBox.getChildren().add(newCard);
    }

}
