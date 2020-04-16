package com.avatarduel.controller;

import com.avatarduel.Card.Aura;
import com.avatarduel.Card.Card;
import com.avatarduel.Card.Land;
import com.avatarduel.Card.Monster;
import javafx.scene.Group;
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
    public static Rectangle createRectCard(double width, double height, double layoutX, double layoutY) {
        Rectangle rect = new Rectangle(width, height);
        rect.setLayoutX(layoutX);
        rect.setLayoutY(layoutY);
        rect.setFill(Color.WHITE);
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

    public static Group createCard(Card cardInput) {
        Group newCard = new Group();
        ImageView imgView = new ImageView();
        try {
            Image img = new Image(cardInput.getImagePath());
            imgView.setImage(img);
            imgView.setFitWidth(70);
            imgView.setFitHeight(50);
            imgView.setSmooth(true);
            imgView.setCache(true);
            imgView.setLayoutX(20);
            imgView.setLayoutY(28);
        } catch (Exception e) {

        } finally {
            Rectangle outer = createRectCard(80, 110, 15, 5);
            Rectangle nameRect = createRectCard(70, 15, 20, 10);
            Rectangle imgRect = createRectCard(70,50, 20, 28);
            Rectangle descRect = createRectCard(70, 30, 20, 80);
            Label nameLabel = createLabel(cardInput.getName(), 22, 12, 8);
            // Monster
            if (cardInput.getType().equals("Land")) {
                Land castedCard = (Land) cardInput;
                Label elmtLabel = createLabel(castedCard.getElement().toString(), 40, 80, 10);
                newCard.getChildren().addAll(outer, nameRect, imgRect, descRect, nameLabel, elmtLabel,imgView);
            } else if (cardInput.getType().equals("Monster")) {
                Monster castedCard = (Monster) cardInput;
                Label powLabel = createLabel(String.valueOf(castedCard.getAttackValue()), 45, 80, 10);
                Label attLabel = createLabel(String.valueOf(castedCard.getPowerValue()), 45, 92, 10);
                Label defLabel = createLabel(String.valueOf(castedCard.getDefenseValue()), 77, 80, 10);
                Label elmtLabel = createLabel(castedCard.getElement().toString(), 55, 95, 8);
                Text powText = createText("Pow:", 22, 91, 10);
                Text attText = createText("Att:", 22, 103, 10);
                Text defText = createText("Def:", 55, 91, 10);
                newCard.getChildren().addAll(outer, nameRect, imgRect, descRect, nameLabel, powLabel, attLabel, defLabel, elmtLabel, powText, attText, defText, imgView);
            } else {
                Aura castedCard = (Aura) cardInput;
                Label powLabel = createLabel(String.valueOf(castedCard.getAttackValue()), 45, 80, 10);
                Label attLabel = createLabel(String.valueOf(castedCard.getPowerValue()), 45, 92, 10);
                Label defLabel = createLabel(String.valueOf(castedCard.getDefenseValue()), 77, 80, 10);
                Label elmtLabel = createLabel(castedCard.getElement().toString(), 55, 95, 8);
                Text powText = createText("Pow:", 22, 91, 10);
                Text attText = createText("Att:", 22, 103, 10);
                Text defText = createText("Def:", 55, 91, 10);
                newCard.getChildren().addAll(outer, nameRect, imgRect, descRect, nameLabel, powLabel, attLabel, defLabel, elmtLabel, powText, attText, defText, imgView);
            }

            return newCard;
        }
    }
}
