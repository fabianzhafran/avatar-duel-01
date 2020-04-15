package com.avatarduel.controller;

import javafx.fxml.FXML;

public class CharacterArenaController {
    @FXML private FieldController fieldController;

    public void init(FieldController f) {
        System.out.println("CharacterArenaController Linked!");
        this.fieldController = f;
    }
}

