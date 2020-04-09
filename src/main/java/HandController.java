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
//    @FXML private AnchorPane card6;
//    @FXML private AnchorPane card7;
//    @FXML private AnchorPane card8;



    private GameplayController gameplayController;

    public void init(GameplayController g) {
        System.out.println("HandController Linked!");
        this.gameplayController = g;
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
        gameplayController.setDescCard(ret);
        System.out.println("Source gotten successfully");
    }


}
