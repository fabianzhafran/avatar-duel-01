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

/**
 * CardDescController is a controller of the CardDesc.fxml file.
 * This class controls which card information is displayed in the Avatar Duel Game GUI.
 * This class is linked to a GameplayController.
 */
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

    /**
     * The rectangle's color is going to be affected by
     * the card's element.
     */
    @FXML private Rectangle nameRect;
    /**
     * The rectangle's color is going to be affected by
     * the card's element.
     */
    @FXML private Rectangle descRect;
    /**
     * The rectangle's color is going to be affected by
     * the card's element.
     */
    @FXML private Rectangle bodyRect;

    @FXML private TextArea textDesc;
    /**
     * Linked GameplayController
     */
    private GameplayController gameplayController;

    /**
     * Link this class with a GameplayController
     * @param g
     */
    public void init(GameplayController g) {
        this.gameplayController = g;
    }

    /**
     * Set the name with the name of the card information received
     * @param name
     */
    public void setName(String name) {
        nameLabel.setText(name);
    }

    /**
     * Set the attack with the attack of the card information received
     * @param atk
     */
    public void setAtt(String atk) {
        atkLabel.setText(atk);
        atkDescLabel.setText(atk);
    }

    /**
     * Set the defense with the defense of the card information received
     * @param def
     */
    public void setDef(String def) {
        defLabel.setText(def);
        defDescLabel.setText(def);
    }

    /**
     * Set the power with the power of the card information received
     * @param pow
     */
    public void setPwr(String pow) {
        powLabel.setText(pow);
        powDescLabel.setText(pow);
    }

    /**
     * Set the description with the description of the card information received
     * @param desc
     */
    public void setDesc(String desc) {
        descLabel.setText(desc);
        descLabel2.setText(desc);
    }

    /**
     * Set the image from the imagepath of the card information received
     * @param url
     */
    public void setCardImage(String url) {
        Image img = new Image(url);
        cardImg.setImage(img);
    }

    /**
     * Set the element icon according to the element of the card information received
     * @param element
     */
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

    /**
     * Set the type according to the type of the card information received
     * @param type
     */
    public void setType(String type) {
        typeLabel.setText(type);
        attachedSkillLabel.setText("None");

    }

    /**
     * Set attached skill according to the name of the skill attached to the card information received.
     * If the card information received is not a monster, set to "None"
     * @param auraNames
     */
    public void setAttachedSkill(ArrayList<String> auraNames) {
        if (auraNames.size() == 0) {
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

    /**
     * Set the displaying card background color according to the element
     * of the card information received
     * @param element
     */
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