package com.avatarduel.controller;

/** ArenaController is the GUI controller for the monsters and 
 * skills field
 * 
 * @author K01_01_IF2210
 */

import com.avatarduel.card.*;
import com.avatarduel.Player;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ArenaController {
    @FXML private FieldController fieldController;
    @FXML private GridPane monsterArena;
    @FXML private GridPane skillArena;

    private PriorityQueue<Integer> emptyMonster;
    private PriorityQueue<Integer> emptySkill;

    private boolean receivingAttack = false;
    private int idxAttacker = -1;
    private int atkValue = 0;

    private boolean receivingDestroy = false;
    
    private int phaseNumber;
    private int playerTurn;

    private boolean equippingSkill;
    private boolean skillPowerUpActivating;

    private int idxActivatedSkill;

    /** Initial setup for the arena controller
     * 
     * @param f arena controller's field assignee
     * @param isPlayer1 indicates whether this arena is owned by player 1 or not. 
     */
    public void init(FieldController f, boolean isPlayer1) {
        this.fieldController = f;
        emptyMonster = new PriorityQueue<Integer>();
        emptySkill = new PriorityQueue<Integer>();
        equippingSkill = false;
        skillPowerUpActivating = false;
        for (int i = 0; i < 6; i++) {
            emptyMonster.add(i);
            emptySkill.add(i);
        }

        if (!isPlayer1) {
            skillArena.setLayoutY(-110);
        }
    }

    public void notifyPhaseToArena(int phaseNumber, int playerTurn) {
        this.phaseNumber = phaseNumber;
        this.playerTurn = playerTurn;
    }

    public void setIdxActivatedSkill(int idxActivatedSkill) {
        this.idxActivatedSkill = idxActivatedSkill;
    }

    public void receiveAttack(int idxAttacker, int atkValue) {
        receivingAttack = true;
        this.idxAttacker = idxAttacker;
        this.atkValue = atkValue;
    }

    public void receiveDestroy(int idxSource) {
        receivingDestroy = true;
        this.idxAttacker = idxSource;
    }

    /** handle on hover event for cards on field
     * 
     * @param evt the event source, contains the hovered card to apply action to
     */
    public void cardHover(Event evt) {
        int i;
        Group hoveredCard = (Group) evt.getSource();
        Player player = fieldController.getPlayer();
        if (monsterArena.getChildren().contains(hoveredCard) && phaseNumber != 1 && phaseNumber != 4) {
            i = (monsterArena.getColumnIndex(hoveredCard));
            fieldController.setDescCard((fieldController.player.getMonsterOnField())[i]);
            if (playerTurn != -1) {
                if (fieldController.isSkillActivating()) {
                    Button equipButton = CardUtils.createButton("Equip", 20, 20, 70, 8);
                    equipButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            event -> {
                                if (skillPowerUpActivating) {
                                    equipSkill(i, false);
                                } else  {
                                    equipSkill(i, true);
                                }
                                fieldController.setSkillActivating(false);
                                Label attLabel = (Label) hoveredCard.getChildren().get(6);
                                attLabel.setText(String.valueOf(player.getMonsterOnField()[i].getAttackValue()));
                                Label defLabel = (Label) hoveredCard.getChildren().get(7);
                                defLabel.setText(String.valueOf(player.getMonsterOnField()[i].getDefenseValue()));
                                exitHover(evt);
                                resetHightlight();
                            });
                    hoveredCard.getChildren().add(equipButton);
                } else if (phaseNumber == 2) {
                    Button changeButton = CardUtils.createButton("Change Position", 20, 60, 70, 8);
                    changeButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            event -> {
                                if (player.getMonsterOnField()[i].getIsAttackPosition()) {
                                    hoveredCard.setRotate(90);
                                } else {
                                    hoveredCard.setRotate(0);
                                }
                                exitHover(evt);
                                changePosition(i);
                            });
                    Button destroyButton = CardUtils.createButton("Destroy", 20, 20, 70, 8);
                    destroyButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            event -> {
                                destroy(evt);
                            });

                    hoveredCard.getChildren().addAll(changeButton, destroyButton);
                } else if (phaseNumber == 3) {
                    // ditaro kondisi klo lagi battle
                    SummonedMonster hoveredPlayerMonster = player.getMonsterOnField()[i];
                    Player enemy = fieldController.getEnemy();
                    boolean existLess = false;
                    if (enemy.getNumberOfMonstersOnField() > 0) {
                        for (SummonedMonster enemyMonster: enemy.getMonsterOnField()) {
                            if (enemyMonster != null) {
                                if (player.getMonsterOnField()[i].getPositionValue() > enemyMonster.getPositionValue())
                                {
                                    existLess = true;
                                    break;
                                }
                            }

                        }
                    }
                    if (hoveredPlayerMonster.getIsAttackPosition()
                            && !hoveredPlayerMonster.getIsJustSummoned()
                            && !hoveredPlayerMonster.getHasAttacked()
                            && (existLess || enemy.getNumberOfMonstersOnField() == 0)) {
                        Button attackButton = CardUtils.createButton("Attack", 20, 20, 70, 8);
                        attackButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                                event -> {
                                    Rectangle outerRect = (Rectangle) hoveredCard.getChildren().get(0);
                                    highlightCard(hoveredCard);
                                    fieldController.startAttack(i);
                                    exitHover(evt);
                                });
                        hoveredCard.getChildren().add(attackButton);
                        existLess = false;
                    }
                }
            } else {
                if (receivingAttack && player.getMonsterOnField()[i].getPositionValue() < atkValue) { // lagi bukan turn player ini
                    // ditaro kondisi klo lagi battle
                    Button attackButton = CardUtils.createButton("Attack this", 20, 20, 70, 8);
                    attackButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            event -> {
                                startBattle(i, evt);
                            });
                    hoveredCard.getChildren().add(attackButton);
                } else if (receivingDestroy) {
                    Button attackButton = CardUtils.createButton("Destroy this", 20, 20, 70, 8);
                    attackButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            event -> {
                                startDestroy(i, evt);
                            });
                    hoveredCard.getChildren().add(attackButton);
                }
            }

        } else {
            i = (skillArena.getColumnIndex(hoveredCard));
            fieldController.setDescCard((player.getSkillOnField())[i]);

            // Kalo lagi Main Phase
            if (playerTurn == 1 && !equippingSkill) {
                Button destroyButton = CardUtils.createButton("Destroy", 20, 20, 70, 8);
                destroyButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            destroy(evt);
                        });
                hoveredCard.getChildren().add(destroyButton);
            }
        }
    }

    /** highlights a chosen card
     * 
     * @param cardGroup the chosen card on the field to be highlighted
     */
    public void highlightCard (Group cardGroup) {
        Rectangle outerRect = (Rectangle) cardGroup.getChildren().get(0);
        outerRect.setStroke(Color.RED);
        outerRect.setStrokeWidth(5);
    }

    /** Resets highlight effect for the previously chosen card 
     * 
     */
    public void resetHightlight () {
        for (int i = 0; i < 6; i++) {
            if (getGridPaneNode(skillArena, i) != null) {
                Group card = (Group) getGridPaneNode(skillArena, i);
                Rectangle outerRect = (Rectangle) card.getChildren().get(0);
                outerRect.setStroke(Color.BLACK);
                outerRect.setStrokeWidth(1);
            }

            if (getGridPaneNode(monsterArena, i) != null) {
                Group card = (Group) getGridPaneNode(monsterArena, i);
                Rectangle outerRect = (Rectangle) card.getChildren().get(0);
                outerRect.setStroke(Color.BLACK);
                outerRect.setStrokeWidth(1);
            }
        }
    }

    /** Invokes battle when a Group's (small cards on field) attack button on battle phase is clicked 
     * 
     * @param idxReceiver Player card's attacking monster's index on his/her field
     * @param evt Event envoker's source (Group on GUI)
     */
    public void startBattle(int idxReceiver, Event evt) {
        if (fieldController.player.getMonsterOnField()[idxReceiver].getPositionValue() < atkValue) {
            destroy(evt);
            fieldController.startBattle(idxAttacker, idxReceiver);
        }

        receivingAttack = false;
    }

    /** Destroys a card on the field
     * 
     * @param idxReceiver Player card's target card to be destroyed
     * @param evt Event envoker's source (Group on GUI)
     */
    public void startDestroy(int idxReceiver, Event evt) {
        destroy(evt);
        fieldController.startDestroy(idxAttacker, idxReceiver);
        receivingDestroy = false;
    }

    /** Equip skill to a monster on the GUI level
     * 
     * @param monsterIdx Monster card on player's field (not on GUI) to be eqiupped with skill
     * @param isAura Indicates whether the skill to be equipped is aura. If true, then it is an Aura skill. Otherwise it is a power up skill.
     */
    public void equipSkill(int monsterIdx, boolean isAura) {
        if (isAura) {
            fieldController.player.activateAuraSkill(idxActivatedSkill, monsterIdx);
        } else {
            fieldController.player.activatePowerUpSkill(idxActivatedSkill, monsterIdx);
            skillPowerUpActivating = false;
        }

        equippingSkill = false;
    }

    public void changePosition(int idxMonster) {
        fieldController.player.getMonsterOnField()[idxMonster].rotate();
    }

    public void summon(Card card, boolean isAtt) {
        int emptyCol = emptyMonster.poll();
        Group newCard = CardUtils.createCard(card);
        if (!isAtt) {
            newCard.setRotate(90);
        }
        try {
            newCard.addEventHandler(MouseEvent.MOUSE_ENTERED,
                    event -> cardHover(event));
            newCard.addEventHandler(MouseEvent.MOUSE_EXITED,
                    event -> exitHover(event));
        } catch (Exception e){
            //unhandled exception
        }
        monsterArena.add(newCard, emptyCol, 0,  1, 1);
    }

    /** Activates card effect after the card is put on field
     * 
     * @param card card whose effect is activated
     */
    public void activateCardEff(Skill card) {
        int emptyCol = emptySkill.poll();
        Group newCard = CardUtils.createCard(card);
        newCard.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> cardHover(event));
        newCard.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> exitHover(event));
        skillArena.add(newCard, emptyCol, 0,  1, 1);
        highlightCard(newCard);
        if (card.getSkillType().equals("Aura")) {
            equippingSkill = true;
        } else if (card.getSkillType().equals("Destroy")) {
            fieldController.useDestroy(emptyCol);
        } else {
            skillPowerUpActivating = true;
        }
        idxActivatedSkill = emptyCol;
    }

    public Node getGridPaneNode(GridPane arena, int col) {
        for (Node node: arena.getChildren()) {
            try {
                if (arena.getColumnIndex(node) == col) {
                    return node;
                }
            } catch (Exception e) {

            }

        }

        return null;
    }

    public void destroyByIndex(int intDestroy, boolean isMonster) {
        Group destroyCard;
        if (isMonster) {
            destroyCard = (Group) getGridPaneNode(monsterArena, intDestroy);
            monsterArena.getChildren().remove(destroyCard);
            emptyMonster.add(intDestroy);
        } else {
            destroyCard = (Group) getGridPaneNode(skillArena, intDestroy);
            skillArena.getChildren().remove(destroyCard);
            emptySkill.add(intDestroy);
        }

    }

    /** Destroy a card on the field
     * 
     * @param evt Event envoker's target to be destroyed (Group on GUI)
     */
    public void destroy(Event evt) {
        Group clickedCard = (Group) evt.getSource();
        int idx;
        Player player = fieldController.getPlayer();
        if (monsterArena.getChildren().contains(clickedCard)) {
            idx = monsterArena.getColumnIndex(clickedCard);
            emptyMonster.add(idx);
            monsterArena.getChildren().remove(clickedCard);
            SummonedMonster monsterData = player.getMonsterOnField()[idx];
            ArrayList<Integer> linkedSkill = monsterData.getSkillLinked();
            // Percobaan 3
            player.printLinkedSkill(idx);
            for (int i = linkedSkill.size() - 1; i >= 0; i--) {
                Group destroySkill = (Group) getGridPaneNode(skillArena, linkedSkill.get(i));
                skillArena.getChildren().remove(destroySkill);
                emptySkill.add(linkedSkill.get(i));
            }
            if (!receivingAttack && !receivingDestroy) {
                player.removeMonsterOnField(idx);
            }
        } else {
            idx = skillArena.getColumnIndex(clickedCard);
            emptySkill.add(idx);
            skillArena.getChildren().remove(clickedCard);
            boolean removed = false;
            int idxMonster = -1;
            for (int i = 0; i < 6 && !removed; i++) {
                if (player.getMonsterOnField()[i] != null) {
                    ArrayList<Integer> linkedSkill = player.getMonsterOnField()[i].getSkillLinked();
                    int j = 0;
                    while (j < linkedSkill.size() && idx != linkedSkill.get(j)) {
                        j++;
                    }
                    if (j < linkedSkill.size()) {
                        idxMonster = i;
                        break;
                    }
                }
            }
            player.removeSkillOnField(idx);

            Group affectedMonsterGroup = (Group) getGridPaneNode(monsterArena, idxMonster);
            Label attLabel = (Label) affectedMonsterGroup.getChildren().get(6);
            attLabel.setText(String.valueOf(player.getMonsterOnField()[idxMonster].getAttackValue()));
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
}

