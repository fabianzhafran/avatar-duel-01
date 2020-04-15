package com.avatarduel.controller;

import javafx.event.Event;
import javafx.fxml.FXML;

public class GameplayController {

    @FXML private CardDescController cardDescController;
    @FXML private P1FieldController p1FieldController;
    @FXML private P2FieldController p2FieldController;

    @FXML public void initialize() {
        System.out.println("App started");
        System.out.println("Linking cardDescController...");
        cardDescController.init(this);
        System.out.println("Linking p1FieldController...");
        System.out.println("On p1FieldController:");
        p1FieldController.init(this);
        System.out.println("Linking p2FieldController...");
        System.out.println("On p2FieldController:");
        p2FieldController.init(this);
    }

    public void btnPressed(Event event) {
        System.out.println("Button pressed!");
        cardDescController.setName("Zuko");
        cardDescController.setPwr("2");
        cardDescController.setAtt("7");
        cardDescController.setDef("4");
    }

    public void setDescCard(String s) {
        System.out.println("Setting description...");
        String[] splitString = s.split(" ");
        cardDescController.setName(splitString[0]);
        cardDescController.setPwr(splitString[1]);
        cardDescController.setAtt(splitString[2]);
        cardDescController.setDef(splitString[3]);
    }
}