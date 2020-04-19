package com.avatarduel.controller;

import java.util.ArrayList;

import com.avatarduel.card.Element;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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
    @FXML private Text attachedSkillLabel;
    @FXML private ImageView cardImg;
    @FXML private ImageView elementImg;

    @FXML private Rectangle nameRect;
    @FXML private Rectangle descRect;
    @FXML private Rectangle bodyRect;

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
        } else if (element.equals(Element.AIR)) {
            url = "@/../assets/wind.png";
        } else {
            url = "@/../assets/energy.png";
        }
        Image img = new Image(url);
        elementImg.setImage(img);
    }

    public void setType(String type) {
        typeLabel.setText(type);
        attachedSkillLabel.setText("None");

    }

    public void setAttachedSkill(ArrayList<String> auraNames) {
        if (auraNames.size() == 0) {
//            System.out.println("gapunya aura");
            attachedSkillLabel.setText("None");
        } else {
            int i = 0;
            String ret = "";
            while (i < auraNames.size() - 1) {
                ret += auraNames.get(i) + "\n";
                i++;
            }

            ret += auraNames.get(i);
            attachedSkillLabel.setText(ret);
        }
    }

    public void setColor(Element element) {
        String color;
        if (element.equals(Element.WATER)) {
            color = "#4049f4";
        } else if (element.equals(Element.FIRE)) {
            color = "#f73131";
        } else if (element.equals(Element.EARTH)) {
            color = "#efd040";
        } else if(element.equals(Element.AIR)) {
            color = "#41f282";
        } else {
            color = "#82968a";
        }

        nameRect.setFill(Color.web(color));
        bodyRect.setFill(Color.web(color));
        descRect.setFill(Color.web(color));

    }
}