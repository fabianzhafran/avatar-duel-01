package com.avatarduel.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardDescController {
    @FXML private Label nameLabel;
    @FXML private Label powLabel;
    @FXML private Label atkLabel;
    @FXML private Label defLabel;
    @FXML private Label descLabel;
    @FXML private Label powDescLabel;
    @FXML private Label atkDescLabel;
    @FXML private Label defDescLabel;
    @FXML private Label descLabel2;
    @FXML private ImageView imgView;

    @FXML private TextArea textDesc;
    private GameplayController gameplayController;

    public void init(GameplayController g) {
//        System.out.println("CardController Linked!");
        this.gameplayController = g;
    }

    public void setName(String name) {
        nameLabel.setText(name);
//        System.out.println("Masuk ke method ganti name!");
    }

    public void setAtt(String atk) {
        atkLabel.setText(atk);
        atkDescLabel.setText(atk);
//        System.out.println("Masuk ke method ganti att!");
    }

    public void setDef(String def) {
        defLabel.setText(def);
        defDescLabel.setText(def);
//        System.out.println("Masuk ke method ganti def!");
    }

    public void setPwr(String pow) {
        powLabel.setText(pow);
        powDescLabel.setText(pow);
//        System.out.println("Masuk ke method ganti pwr!");
    }

    public void setDesc(String desc) {
        descLabel.setText(desc);
        descLabel2.setText(desc);
    }

    public void setImage(String url) {
        Image img = new Image(url);
        imgView.setImage(img);
    }

//    public void btnPressed(Event event) {
//        System.out.println("Button pressed!");
//    }
}