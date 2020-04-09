package com.avatarduel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class HandController {
    @FXML private AnchorPane card1;
    @FXML private AnchorPane card2;
    @FXML private AnchorPane card3;
    @FXML private AnchorPane card4;
    @FXML private AnchorPane card5;
    @FXML private AnchorPane card6;
    @FXML private AnchorPane card7;
    @FXML private AnchorPane card8;

    @FXML private FieldController fieldController;

    public void init(FieldController f) {
        System.out.println("FieldController Linked!");
        this.fieldController = f;
    }

    public void cardClicked(Event evt) {
        AnchorPane clickedCard = (AnchorPane) evt.getSource();
        String ret = "";
        for (Node child:clickedCard.getChildren()) {
            if (child instanceof Label) {
                Label conv = (Label) child;
                System.out.println(conv.getText());
                ret += conv.getText() + " ";
            }
        }
        System.out.println("Sending to field...");
        fieldController.setDescCard(ret);
        System.out.println("Source delivered successfully");
    }


}
