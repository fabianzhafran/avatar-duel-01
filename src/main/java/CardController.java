package com.avatarduel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

public class CardController {
    @FXML private Label nameLabel;
    @FXML private Label pwrLabel;
    @FXML private Label attLabel;
    @FXML private Label defLabel;

    @FXML private TextArea textDesc;
    private GameplayController gameplayController;

    public void init(GameplayController g) {
        System.out.println("CardController Linked!");
        this.gameplayController = g;
    }

    public void setName(String name) {
        nameLabel.setText(name);
        System.out.println("Masuk ke method ganti name!");
    }

    public void setAtt(String att) {
        attLabel.setText("Att: " + att);
        System.out.println("Masuk ke method ganti att!");
    }

    public void setDef(String def) {
        defLabel.setText("Def: " + def);
        System.out.println("Masuk ke method ganti def!");
    }

    public void setPwr(String pwr) {
        pwrLabel.setText("Pwr: " + pwr);
        System.out.println("Masuk ke method ganti pwr!");
    }

    public void btnPressed(Event event) {
        System.out.println("Button pressed!");
    }
}