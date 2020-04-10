package com.avatarduel.controller;

import javafx.fxml.FXML;

public class SkillArenaController {
    @FXML private FieldController fieldController;

    public void init(FieldController f) {
        System.out.println("SkillArenaController Linked!");
        this.fieldController = f;
    }
}
