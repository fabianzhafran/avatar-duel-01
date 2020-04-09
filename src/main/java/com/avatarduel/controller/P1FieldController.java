package com.avatarduel.controller;

import javafx.fxml.FXML;

public class P1FieldController extends FieldController {

    @FXML private HandController handController;

    public void init(GameplayController g) {
        System.out.println("P1FieldController Linked!");
        this.gameplayController = g;
        System.out.println("Linking handController...");
        handController.init(this);
    }

    public void setDescCard(String s) {
        System.out.println("Sending to gameplay...");
        gameplayController.setDescCard(s);
    }
}
