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



    public static Group createCard(Card cardInput) {
        Group newCard = new Group();
        ImageView cardImg = new ImageView();
        ImageView backCardImg = new ImageView();
        try {
            Image img = new Image(cardInput.getImagePath());
            cardImg.setImage(img);
            cardImg.setFitWidth(70);
            cardImg.setFitHeight(50);
            cardImg.setSmooth(true);
            cardImg.setCache(true);
            cardImg.setLayoutX(20);
            cardImg.setLayoutY(28);

            Image backImg = new Image("@/../assets/card-background.jpg");
            backCardImg.setImage(backImg);
            backCardImg.setFitWidth(80);
            backCardImg.setFitHeight(110);
            backCardImg.setSmooth(true);
            backCardImg.setCache(true);
            backCardImg.setLayoutX(15);
            backCardImg.setLayoutY(5);
            backCardImg.setOpacity(0);
        } catch (Exception e) {
            System.out.println("Image not found!");
        } finally {
            Element element = cardInput.getElement();
            Rectangle outer = createRectCard(80, 110, 15, 5, element);
            Rectangle nameRect = createRectCard(70, 15, 20, 10, element);
            Rectangle imgRect = createRectCard(70,50, 20, 28, element);
            Rectangle descRect = createRectCard(70, 30, 20, 80, element);
            Label nameLabel = createLabel(cardInput.getName(), 22, 12, 8);
            nameLabel.setPrefWidth(70);
            // Monster
            if (cardInput.getType().equals("Land")) {
                Land castedCard = (Land) cardInput;
                Label elmtLabel = createLabel(castedCard.getElement().toString(), 40, 80, 10);
                newCard.getChildren().addAll(outer, nameRect, imgRect, descRect, nameLabel, elmtLabel,cardImg, backCardImg);
            } else if (cardInput.getType().equals("Monster")) {
                Monster castedCard = (Monster) cardInput;
                Label powLabel = createLabel(String.valueOf(castedCard.getPowerValue()), 45, 92, 10);
                Label attLabel = createLabel(String.valueOf(castedCard.getAttackValue()), 42, 80, 10);
                Label defLabel = createLabel(String.valueOf(castedCard.getDefenseValue()), 78, 80, 10);
                Label elmtLabel = createLabel(castedCard.getElement().toString(), 60, 95, 8);
                Text powText = createText("Pow:", 22, 103, 10);
                Text attText = createText("Att:", 22, 91, 10);
                Text defText = createText("Def:", 55, 91, 10);
                newCard.getChildren().addAll(outer, nameRect, imgRect, descRect, nameLabel, powLabel, attLabel, defLabel, elmtLabel, powText, attText, defText, cardImg, backCardImg);
            } else {
                Skill skillCard = (Skill) cardInput;
                if (skillCard.getSkillType().equals("Aura")) {
                    Aura castedCard = (Aura) skillCard;
                    Label attLabel = createLabel(String.valueOf(castedCard.getAttackValue()), 45, 80, 10);
                    Label powLabel = createLabel(String.valueOf(castedCard.getPowerValue()), 42, 92, 10);
                    Label defLabel = createLabel(String.valueOf(castedCard.getDefenseValue()), 78, 80, 10);
                    Label elmtLabel = createLabel(castedCard.getElement().toString(), 60, 95, 8);
                    Text powText = createText("Pow:", 22, 103, 10);
                    Text attText = createText("Att:", 22, 91, 10);
                    Text defText = createText("Def:", 55, 91, 10);
                    newCard.getChildren().addAll(outer, nameRect, imgRect, descRect, nameLabel, powLabel, attLabel, defLabel, elmtLabel, powText, attText, defText, cardImg, backCardImg);
                } else if (skillCard.getSkillType().equals("Power Up")) {
                    PowerUp castedCard = (PowerUp) skillCard;
                    Label attLabel = createLabel("-", 45, 80, 10);
                    Label powLabel = createLabel(String.valueOf(castedCard.getPowerValue()), 42, 92, 10);
                    Label defLabel = createLabel("-", 78, 80, 10);
                    Label elmtLabel = createLabel(castedCard.getElement().toString(), 60, 95, 8);
                    Text powText = createText("Pow:", 22, 103, 10);
                    Text attText = createText("Att:", 22, 91, 10);
                    Text defText = createText("Def:", 55, 91, 10);
                    newCard.getChildren().addAll(outer, nameRect, imgRect, descRect, nameLabel, powLabel, attLabel, defLabel, elmtLabel, powText, attText, defText, cardImg, backCardImg);
                }
            }

            return newCard;
        }
    }
}
