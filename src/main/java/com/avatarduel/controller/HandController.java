package com.avatarduel.controller;

import com.avatarduel.Card.*;
import com.avatarduel.Player;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class HandController {
    @FXML private HBox handHBox;
    @FXML private FieldController fieldController;

    private boolean isEquipping = false;
    private boolean isTurn; // Tar diganti sama PHASE

    public void init(FieldController f) {
        this.fieldController = f;
    }

    public void setTurn(boolean turn) {
        this.isTurn = turn;
        int opacity;
        if (!this.isTurn) {
            opacity = 1;
        } else {
            opacity = 0;
        }

        for (Node node: handHBox.getChildren()) {
            Group handCard = (Group) node;
            ImageView backImage = (ImageView) handCard.getChildren().get(handCard.getChildren().size() - 1);
            backImage.setOpacity(opacity);
        }
    }

    public boolean getTurn() {
        return isTurn;
    }

    public void addCard(Group newCard) {
        newCard.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event ->  cardHover(event));
        newCard.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> exitHover(event));
        handHBox.getChildren().add(newCard);
    }

    public void cardHover(Event evt) {
        if (isTurn) {
            int idx;
            Group hoveredCardGroup = (Group) evt.getSource();
            hoveredCardGroup.setTranslateY(-10);
            idx = getIdx(hoveredCardGroup);
            fieldController.setDescCard(fieldController.player.getHand().get(idx));
            Player player = fieldController.getPlayer();

            if (!isEquipping) {
                Card hoveredCard = player.getHand().get(idx);
                if (hoveredCard.getType().equals("Monster")) {
                    Monster monster = (Monster) hoveredCard;
                    if (player.getNumberOfMonstersOnField() < 6 && player.getLandPowerByElement(hoveredCard.getElement()) >= ((Monster) hoveredCard).getPowerValue()) {
                        Button attButton = CardUtils.createButton("Summon Att", 20, 20, 70, 8);
                        Button defButton = CardUtils.createButton("Summon Def", 20, 60, 70, 8);

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
                    if ((hoveredCard.getType().equals("Skill") && player.getLandPowerByElement(hoveredCard.getElement()) >= ((Skill) hoveredCard).getPowerValue() && player.getNumberOfSkillsOnField() < 6) && player.getNumberOfMonstersOnField() > 0 || hoveredCard.getType().equals("Land")) {
                        Button actButton = CardUtils.createButton("Activate", 20, 20, 70, 8);
                        actButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                                event -> {
                                    System.out.println("Clicked activate");
                                    fieldController.useCard(idx, false);
                                });
                        hoveredCardGroup.getChildren().add(actButton);
                    }

                }
            }
        }
    }

    public void exitHover(Event evt) {
        Group hoveredCard = (Group) evt.getSource();
        ArrayList<Integer> removeIdx = new ArrayList<Integer>();
        int i = 0;
        for (Node node: hoveredCard.getChildren()) {
            if (node instanceof Button) {
                removeIdx.add(0, i);
            }
            i++;
        }

        for (int idx:removeIdx) {
            hoveredCard.getChildren().remove(idx);
        }

        hoveredCard.setTranslateY(0);
    }

    public void useCard(int idx, boolean isAtt) {
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

    public void setIsEquipping(boolean equipping) {
        isEquipping = equipping;
    }
}
