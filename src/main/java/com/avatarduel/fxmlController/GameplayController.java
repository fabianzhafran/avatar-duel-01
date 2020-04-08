package com.avatarduel.fxmlController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GameplayController {

    @FXML private CardController cardController;

    @FXML public void initialize() {
        System.out.println("App started");
        cardController.init(this);
    }

    public void btnPressed(Event event) {
        System.out.println("Button pressed!");
        cardController.setName("Zuko");
        cardController.setPwr("2");
        cardController.setAtt("7");
        cardController.setDef("4");
    }
}