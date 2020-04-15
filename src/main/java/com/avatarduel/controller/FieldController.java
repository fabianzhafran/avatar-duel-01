package com.avatarduel.controller;

import javafx.fxml.FXML;

abstract public class FieldController {
    @FXML protected GameplayController gameplayController;
    @FXML protected HandController handController;
    @FXML protected CharacterArenaController characterArenaController;
    @FXML protected SkillArenaController skillArenaController;

    public void init(GameplayController g) {
        System.out.println("FieldController Linked!");
        this.gameplayController = g;
        System.out.println("Linking handController...");
        handController.init(this);
        System.out.println("Linking characterArenaController...");
        characterArenaController.init(this);
        System.out.println("Linking skillArenaController...");
        skillArenaController.init(this);
    }

    public void setDescCard(String s) {
        System.out.println("Sending to gameplay...");
        gameplayController.setDescCard(s);
    }

    public void draw() {
        handController.addCard();
    }
}
