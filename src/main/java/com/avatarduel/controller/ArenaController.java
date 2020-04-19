package com.avatarduel.controller;

import com.avatarduel.Card.*;
import com.avatarduel.phase.NotifyPhase;
import com.avatarduel.Player;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

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

    private boolean skillActivating;

    private int idxActivatedSkill;

    public void init(FieldController f, boolean isPlayer1) {
        this.fieldController = f;
        emptyMonster = new PriorityQueue<Integer>();
        emptySkill = new PriorityQueue<Integer>();
        skillActivating = false;
        for (int i = 0; i < 6; i++) {
            emptyMonster.add(i);
            emptySkill.add(i);
        }

        if (!isPlayer1) {
            skillArena.setLayoutY(-110);
        }
    }

    public void setIdxActivatedSkill(int idxActivatedSkill) {
        this.idxActivatedSkill = idxActivatedSkill;
    }

    public void receiveAttack(int idxAttacker, int atkValue) {
        receivingAttack = true;
        this.idxAttacker = idxAttacker;
        this.atkValue = atkValue;
    }

    public void cardHover(Event evt) {
        int i;
        Group hoveredCard = (Group) evt.getSource();
        Player player = fieldController.getPlayer();
        if (monsterArena.getChildren().contains(hoveredCard)) {
            i = (monsterArena.getColumnIndex(hoveredCard));
            fieldController.setDescCard((fieldController.player.getMonsterOnField())[i]);

            if (skillActivating) {
                    Button equipButton = CardUtils.createButton("Equip", 20, 20, 70, 8);
                    equipButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            event -> {
                                System.out.println("Clicked Equip");
                                equip(i);
                                fieldController.getHandController().setIsEquipping(false);
                                Label attLabel = (Label) hoveredCard.getChildren().get(6);
                                attLabel.setText(String.valueOf(player.getMonsterOnField()[i].getAttackValue()));
                                exitHover(evt);
                            });
                    hoveredCard.getChildren().add(equipButton);
            } else if (!receivingAttack){
                // ditaro kondisi klo lagi battle
                if (player.getMonsterOnField()[i].getIsAttackPosition()) {
                    Button attackButton = CardUtils.createButton("Attack", 20, 20, 70, 8);
                    attackButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            event -> {
                                System.out.println("Clicked Attack");
                                fieldController.startAttack(i);
                            });
                    hoveredCard.getChildren().add(attackButton);
                }

                Button changeButton = CardUtils.createButton("Change Position", 20, 60, 70, 8);
                changeButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            System.out.println("Clicked Change Position");
                            if (player.getMonsterOnField()[i].getIsAttackPosition()) {
                                hoveredCard.setRotate(90);
                            } else {
                                hoveredCard.setRotate(0);
                            }
                            exitHover(evt);
                            changePosition(i);
                        });
                hoveredCard.getChildren().add(changeButton);
            } else if (receivingAttack) {
//                 && player.getMonsterOnField()[i].getAttackValue() < atkValue
                // ditaro kondisi klo lagi battle
                Button attackButton = CardUtils.createButton("Attack this", 20, 20, 70, 8);
                attackButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            System.out.println("Arena start Attack");

                            startBattle(i, evt);
                        });
                hoveredCard.getChildren().add(attackButton);
            }

        } else {
//            player.printSkillCardsOnField();
            i = (skillArena.getColumnIndex(hoveredCard));
            fieldController.setDescCard((player.getSkillOnField())[i]);

            // Kalo lagi Main Phase
            if (!skillActivating) {
                Button destroyButton = CardUtils.createButton("Destroy", 20, 20, 70, 8);
                destroyButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                        event -> {
                            destroy(evt);
                        });
                hoveredCard.getChildren().add(destroyButton);
            }
        }
    }

    public void startBattle(int idxReceiver, Event evt) {
        System.out.println("Arena Start Battle");
        receivingAttack = false;
        if (fieldController.player.getMonsterOnField()[idxReceiver].getPositionValue() < atkValue) {
            fieldController.startBattle(idxAttacker, idxReceiver);
            destroy(evt);
        } else {
            System.out.println("The targeted monster atk/def is higher");
        }
    }

    public void equip(int monsterIdx) {
//        System.out.println("Skill index: " + idxActivatedSkill);
//        fieldController.player.printSkillCardsOnField();
//        System.out.println("Monster index: " + monsterIdx);
//        fieldController.player.printMonsterCardsOnField();
        fieldController.player.activateAuraSkill(idxActivatedSkill, monsterIdx);
        skillActivating = false;
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
        newCard.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> cardHover(event));
        newCard.addEventHandler(MouseEvent.MOUSE_CLICKED,
                event -> destroy(event));
        newCard.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> exitHover(event));
        monsterArena.add(newCard, emptyCol, 0,  1, 1);
    }

    public void activateCardEff(Card card) {
        int emptyCol = emptySkill.poll();
        Group newCard = CardUtils.createCard(card);
        newCard.addEventHandler(MouseEvent.MOUSE_ENTERED,
                event -> cardHover(event));
//        newCard.addEventHandler(MouseEvent.MOUSE_CLICKED,
//                event -> destroy(event));
        newCard.addEventHandler(MouseEvent.MOUSE_EXITED,
                event -> exitHover(event));
        skillArena.add(newCard, emptyCol, 0,  1, 1);
        skillActivating = true;
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
            ArrayList<Integer> idxSkillArena = new ArrayList<Integer>();
            System.out.println("Destroying skill Card");

            // Percobaan 3
            System.out.println("Size linked skill: " + linkedSkill.size());
            player.printLinkedSkill(idx);
            for (int i = linkedSkill.size() - 1; i >= 0; i--) {
                System.out.println("Index iterasi: " + i);
                System.out.println("Index skill: " + linkedSkill.get(i));
                Group destroySkill = (Group) getGridPaneNode(skillArena, linkedSkill.get(i));
                skillArena.getChildren().remove(destroySkill);
                emptySkill.add(linkedSkill.get(i));
            }
            if (!receivingAttack) {
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
//                System.out.println("Index " + i);
            }
            i++;
        }

        for (int idx:removeIdx) {
            hoveredCard.getChildren().remove(idx);
        }

        hoveredCard.setTranslateY(0);
    }
}

