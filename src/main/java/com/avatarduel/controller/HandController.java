package com.avatarduel.controller;

import com.avatarduel.Card.*;
import com.avatarduel.Player;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class HandController {
    @FXML private HBox handHBox;
    @FXML private FieldController fieldController;

//    private boolean highlightCard = false;

    public void init(FieldController f) {
//        System.out.println("HandController Linked!");
        this.fieldController = f;
    }

    public void cardHover(Event evt) {
        int idx;
        Group hoveredCardGroup = (Group) evt.getSource();
        hoveredCardGroup.setTranslateY(-10);
        idx = getIdx(hoveredCardGroup);
//        System.out.println("Index in hand is " + idx);
//        System.out.println("Sending to field...");
        fieldController.setDescCard(fieldController.player.getHand().get(idx));
        Player player = fieldController.getPlayer();

        Card hoveredCard = player.getHand().get(idx);
        if (hoveredCard.getType().equals("Monster")) {
            Monster monster = (Monster) hoveredCard;
            if (player.getNumberOfMonstersOnField() < 6 && player.getLandPowerByElement(hoveredCard.getElement()) >= ((Monster) hoveredCard).getPowerValue()) {
                Button attButton = new Button("Summon Att");
                Button defButton = new Button("Summon Def");
                attButton.setLayoutX(20);
                attButton.setLayoutY(20);
                attButton.setPrefWidth(70);
                defButton.setLayoutX(20);
                defButton.setLayoutY(60);
                defButton.setPrefWidth(70);
                attButton.setFont(Font.font(8));
                defButton.setFont(Font.font(8));

                attButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            System.out.println("Clicked summon att");
                            useCard(idx, true);
                        });
                defButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            System.out.println("Clicked summon def");
                            useCard(idx, false);
                        });
                hoveredCardGroup.getChildren().addAll(attButton, defButton);
            }

        } else {
            if ((hoveredCard.getType().equals("Skill") && player.getLandPowerByElement(hoveredCard.getElement()) >= ((Skill) hoveredCard).getPowerValue() && player.getNumberOfSkillsOnField() < 6) || hoveredCard.getType().equals("Land")) {
                Button actButton = new Button("Activate");
                actButton.setLayoutX(20);
                actButton.setLayoutY(20);
                actButton.setPrefWidth(70);
                actButton.setFont(Font.font(8));
                actButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            System.out.println("Clicked activate");
                            useCard(idx, false);
                        });
                hoveredCardGroup.getChildren().add(actButton);
            }

        }
//        System.out.println("Source delivered successfully");
    }

    public void printTes() {
        System.out.println("Berhasil yang lu tes!");
    }

    public int getIdx(Group card) {
        int i = 0;
        for (Node node : handHBox.getChildren()) {
            if (card.getChildren().get(4).equals(((Group) node).getChildren().get(4))) {
                break;
            }
            i++;
        }

        return i;
    }

    public void useCard(int idx, boolean isAtt) {

//        System.out.println("Card Summoned");
//        Group clickedCard = (Group) evt.getSource();
        fieldController.useCard(idx, isAtt);
    }

    public void removeCard(int idx) {
        Group cardGroup = new Group();
        int i = 0;
        for (Node node : handHBox.getChildren()) {
            if (i == idx) {
                cardGroup = (Group) node;
                break;
            }
            i++;
        }
        handHBox.getChildren().remove(cardGroup);
    }

    public void exitHover(Event evt) {
        Group hoveredCard = (Group) evt.getSource();
        ArrayList<Integer> removeIdx = new ArrayList<Integer>();
        int i = 0;
        for (Node node: hoveredCard.getChildren()) {
            if (node instanceof Button) {
                removeIdx.add(0, i);
//                System.out.println("Index " + i);
            }
            i++;
        }

        for (int idx:removeIdx) {
            hoveredCard.getChildren().remove(idx);
        }

        hoveredCard.setTranslateY(0);
    }

    public void addCard(Group newCard) {
        // Nanti bakal nerima parameter card
//        Group newCard = CardUtils.createCard(card);
        newCard.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event ->  cardHover(event));
//        newCard.addEventHandler(MouseEvent.MOUSE_CLICKED,
//                event -> useCard(event));
        newCard.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> exitHover(event));
        handHBox.getChildren().add(newCard);
    }


}
