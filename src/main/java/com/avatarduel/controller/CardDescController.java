package com.avatarduel.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.avatarduel.Card.Element;
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
    @FXML private Label typeLabel;
    @FXML private Label powLabel;
    @FXML private Label atkLabel;
    @FXML private Label defLabel;
    @FXML private Label descLabel;
    @FXML private Label powDescLabel;
    @FXML private Label atkDescLabel;
    @FXML private Label defDescLabel;
    @FXML private Label descLabel2;
    @FXML private ImageView cardImg;
    @FXML private ImageView elementImg;

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

    public void setCardImage(String url) {
        Image img = new Image(url);
        cardImg.setImage(img);
    }

    public void setElementImage(Element element) {
        String url;
        if (element.equals(Element.WATER)) {
            url = "@/../assets/drop.png";
        } else if (element.equals(Element.EARTH)) {
            url = "@/../assets/rocks.png";
        } else if (element.equals(Element.FIRE)) {
            url = "@/../assets/fire.png";
        } else {
            url = "@/../assets/wind.png";
        }
        Image img = new Image(url);
        elementImg.setImage(img);
    }

    public void setType(String type) {
        typeLabel.setText(type);
    }

    //    public void btnPressed(Event event) {
//        System.out.println("Button pressed!");
//    }
}