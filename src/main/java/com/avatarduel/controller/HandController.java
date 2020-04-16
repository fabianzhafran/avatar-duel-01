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

    }

    public void printTes() {
        System.out.println("Berhasil yang lu tes!");
    }

    public void summon(Event evt) {
        System.out.println("Card Summoned");
        Group clickedCard = (Group) evt.getSource();
        String ret = "";
        for (Node child:clickedCard.getChildren()) {
            if (child instanceof Label) {
                Label conv = (Label) child;
//                System.out.println(conv.getText());
                ret += conv.getText() + " ";
            }
        }
        handHBox.getChildren().remove(clickedCard);
        fieldController.summon(ret);
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
