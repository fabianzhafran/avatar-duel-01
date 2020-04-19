package com.avatarduel.controller;

import com.avatarduel.Card.*;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CardUtils {
    public static Rectangle createRectCard(double width, double height, double layoutX, double layoutY, Element element) {
        Rectangle rect = new Rectangle(width, height);
        String color;
        rect.setLayoutX(layoutX);
        rect.setLayoutY(layoutY);
        if (element.equals(Element.WATER)) {
            color = "#4049f4";
        } else if (element.equals(Element.FIRE)) {
            color = "#f73131";
        } else if (element.equals(Element.EARTH)) {
            color = "#efd040";
        } else {
            color = "#41f282";
        }
        rect.setFill(Color.web(color));
        rect.setStroke(Color.BLACK);
        rect.setStrokeWidth(1);
        rect.setSmooth(true);
        rect.setStrokeType(StrokeType.INSIDE);

        return rect;
    }

    public static Label createLabel(String str, double layoutX, double layoutY, double fontSize) {
        Label l = new Label(str);
        l.setLayoutX(layoutX);
        l.setLayoutY(layoutY);
        l.setFont(Font.font(fontSize));

        return l;
    }

    public static Text createText(String str, double layoutX, double layoutY, double fontSize){
        Text t = new Text(str);
        t.setLayoutX(layoutX);
        t.setLayoutY(layoutY);
        t.setFont(Font.font(fontSize));


        return t;
    }

    public static Button createButton(String str, double layoutX, double layoutY, double width, double fontSize) {
        Button newButton = new Button(str);
        newButton.setLayoutX(layoutX);
        newButton.setLayoutY(layoutY);
        newButton.setPrefWidth(width);
        newButton.setFont(Font.font(fontSize));

        return newButton;
    }

    public static ImageView createImageView(String path, double width, double height, double layoutX, double layoutY) {
        ImageView imgView = new ImageView();
        Image img = new Image(path);
        imgView.setImage(img);
        imgView.setFitWidth(width);
        imgView.setFitHeight(height);
        imgView.setSmooth(true);
        imgView.setCache(true);
        imgView.setLayoutX(layoutX);
        imgView.setLayoutY(layoutY);

        return imgView;
    }



    public static Group createCard(Card cardInput) {
        Group newCard;
        boolean isLand = cardInput.getType().equals("Land");
        boolean isMonster = cardInput.getType().equals("Monster");
        boolean isSkill = cardInput.getType().equals("Skill");
        boolean isAura = false;
        boolean isDestroyOrPowerUp = false;
        if (isSkill) {
            Skill skillCard = (Skill) cardInput;
            if (skillCard.getSkillType().equals("Aura")) {
                isAura = true;
            } else {
                isDestroyOrPowerUp = true;
            }
        }
        if (isLand || isDestroyOrPowerUp) {
            newCard = new CardGuiBuilder()
                    .type(cardInput.getType())
                    .name(cardInput.getName())
                    .element(cardInput.getElement())
                    .imagePath(cardInput.getImagePath()).build();
        } else {
            if (isMonster) {
                Monster castedCard = (Monster) cardInput;
                newCard = new CardGuiBuilder()
                        .type(castedCard.getType())
                        .name(castedCard.getName())
                        .element(castedCard.getElement())
                        .imagePath(castedCard.getImagePath())
                        .att(castedCard.getAttackValue())
                        .def(castedCard.getDefenseValue())
                        .pow(castedCard.getPowerValue())
                        .build();
            } else {
                Aura castedCard = (Aura) cardInput;
                newCard = new CardGuiBuilder()
                        .type(castedCard.getType())
                        .name(castedCard.getName())
                        .element(castedCard.getElement())
                        .imagePath(castedCard.getImagePath())
                        .att(castedCard.getAttackValue())
                        .def(castedCard.getDefenseValue())
                        .pow(castedCard.getPowerValue())
                        .build();
            }
        }

        return newCard;
    }
}
