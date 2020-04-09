package com.avatarduel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GameplayController {

    @FXML private CardController cardController;
    @FXML private HandController handController;

    @FXML public void initialize() {
        System.out.println("App started");
        cardController.init(this);
        handController.init(this);
    }

    public void btnPressed(Event event) {
        System.out.println("Button pressed!");
        cardController.setName("Zuko");
        cardController.setPwr("2");
        cardController.setAtt("7");
        cardController.setDef("4");
    }

    public void setDescCard(String s) {
        System.out.println("Setting description...");
        String[] splitString = s.split(" ");
        cardController.setName(splitString[0]);
        cardController.setPwr(splitString[1]);
        cardController.setAtt(splitString[2]);
        cardController.setDef(splitString[3]);
    }
}