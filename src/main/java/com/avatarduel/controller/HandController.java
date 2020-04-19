package com.avatarduel.controller;

import com.avatarduel.card.*;
import com.avatarduel.Player;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

/**
 * HandController is a controller to the Hand.fxml file.
 * This class controls the GUI of a card that is in a Player's hand.
 * This controller is linked to a FieldController class.
 */
public class HandController {
    /**
     * A HBox Container that contains the visualization
     * of the cards that a player has in his/her hand. Each card
     * is represented by a javafx.Scene.Group
     */
    @FXML private HBox handHBox;
    /**
     * A FieldController that is linked to this class.
     * HandController passes information to FieldController
     * so it can be given to another controller.
     */
    @FXML private FieldController fieldController;

    /**
     * true if the player has used a land card in the turn.
     */
    private boolean landUsed;
    /**
     * Indicates the phase that the player is in.
     * 1 == Draw Phase
     * 2 == Main Phase
     * 3 == Battle Phase
     * 4 == End Phase
     */
    private int phaseNumber;
    /**
     * The value is -1 if player is not in turn
     * The value can be 1 or 2 according to the player
     */
    private int playerTurn;

    /**
     * Link HandController to a FieldController
     * @param f
     */
    public void init(FieldController f) {
        this.fieldController = f;
    }

    /**
     * Get notified by FieldController for the current phase and turn.
     * This method is run through a linked FieldController.
     * @param phaseNumber
     * @param playerTurn
     */
    public void notifyPhaseToHand(int phaseNumber, int playerTurn) {
        this.phaseNumber = phaseNumber;
        this.playerTurn = playerTurn;
        landUsed = false;
        setTurn();
    }

    /**
     * Flips the card each turn
     */
    public void setTurn() {
        int opacity;
        if (this.playerTurn != -1) {
            opacity = 0;
            landUsed = false;
        } else {
            opacity = 1;
        }

        for (Node node: handHBox.getChildren()) {
            Group handCard = (Group) node;
            ImageView backImage = (ImageView) handCard.getChildren().get(handCard.getChildren().size() - 1);
            backImage.setOpacity(opacity);
        }
    }

    public boolean isLandUsed() {
        return landUsed;
    }

    public void setLandUsed(boolean landUsed) {
        this.landUsed = landUsed;
    }

    /**
     * Add a Group that is a representation of a Card to the handHBox.
     * Gives method according to the event that the Card representation get
     * Event MOUSE_ENTERED -> cardHover
     * Event MOUSE_EXITED -> exitHover
     * @param newCard
     */
    public void addCard(Group newCard) {
        newCard.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event ->  cardHover(event));
        newCard.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> exitHover(event));
        handHBox.getChildren().add(newCard);
    }

    /**
     * Gives button to the Card according to the current situation the player is in
     * @param evt hovered Card
     */
    public void cardHover(Event evt) {
        if (playerTurn != -1) {
            int idx;
            Group hoveredCardGroup = (Group) evt.getSource();
            hoveredCardGroup.setTranslateY(-10);
            idx = getIdx(hoveredCardGroup);
            fieldController.setDescCard(fieldController.player.getHand().get(idx));
            Player player = fieldController.getPlayer();

            if (!fieldController.isSkillActivating()) {
                Card hoveredCard = player.getHand().get(idx);
                if (hoveredCard.getType().equals("Monster") && phaseNumber == 2) {
                    // Monster monster = (Monster) hoveredCard;
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
                    if (phaseNumber == 2) {
                        boolean isSkill = (hoveredCard.getType().equals("Skill") && player.getLandPowerByElement(hoveredCard.getElement()) >= ((Skill) hoveredCard).getPowerValue() && player.getNumberOfSkillsOnField() < 6);
                        boolean isLand = hoveredCard.getType().equals("Land") && (!landUsed);
                        boolean isAuraOrPowerUp = false;
                        boolean isDestroy = false;
                        if (isSkill) {
                            Skill skillCard = (Skill) hoveredCard;
//                            System.out.println(skillCard.getSkillType());
                            if (skillCard.getSkillType().equals("Aura") || skillCard.getSkillType().equals("Power Up")) {
                                if (player.getNumberOfMonstersOnField() > 0) {
                                    isAuraOrPowerUp = true;
                                }
                            } else if (skillCard.getSkillType().equals("Destroy")) {
                                // Nanti kasih condition buat aktifin destroy
                                if (fieldController.getEnemy().getNumberOfMonstersOnField() > 0) {
                                    System.out.println("Is Destroy");
                                    isDestroy = true;
                                }
                            }
                        }
                        if (isDestroy || isAuraOrPowerUp || isLand) {
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
    }

    /**
     * Delete button that is added to the group from cardhover method
     * @param evt exited hover card
     */
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

    /**
     * Send card info to fieldcontroller to be used
     * @param idx idx of card in handHbox and in Player.Hand
     * @param isAtt true if a monster wants to be summoned in attack position.
     *              False if not in attack position or not a monster card
     */
    public void useCard(int idx, boolean isAtt) {
        fieldController.useCard(idx, isAtt);
    }

    /**
     * Remove card from HandHBox by Index
     * @param idx index of card in handHBox
     */
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

    /**
     * Get index of card in handHBox
     * @param card card that wants to be searched its index
     * @return the card's index
     */
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
}
