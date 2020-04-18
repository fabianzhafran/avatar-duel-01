package com.avatarduel.controller;

import com.avatarduel.Card.*;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

    private boolean skillActivating;

    private int idxActivatedSkill;

    public void init(FieldController f, boolean isPlayer1) {
//        System.out.println("CharacterArenaController Linked!");
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

    public void cardHover(Event evt) {
        int i;
        Group hoveredCard = (Group) evt.getSource();
        if (monsterArena.getChildren().contains(hoveredCard)) {
            i = (monsterArena.getColumnIndex(hoveredCard));
            fieldController.setDescCard((fieldController.player.getCharacterOnField())[i]);

            if (skillActivating) {
//                Element monsterElement = fieldController.player.getCharacterOnField()[i].getMonster().getElement();
//                Element skillElement = fieldController.player.getSkillOnField()[idxActivatedSkill].getElement();
//                if (monsterElement.equals(skillElement)) {
                    Button equipButton = new Button("Equip");
                    equipButton.setLayoutX(20);
                    equipButton.setLayoutY(20);
                    equipButton.setPrefWidth(70);
                    equipButton.setFont(Font.font(8));
                    equipButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
                            event -> {
                                System.out.println("Clicked Equip");
                                equip(i);

                            });
                    hoveredCard.getChildren().add(equipButton);
//                }
            }

        } else {
            i = (skillArena.getColumnIndex(hoveredCard));
            fieldController.setDescCard((fieldController.player.getSkillOnField())[i]);
        }

//        System.out.println("Hovered card name is" + hovered.getName());

//        System.out.println("Source delivered successfully");
    }

    public void equip(int monsterIdx) {
        System.out.println("Skill index: " + idxActivatedSkill);
        fieldController.player.printSkillCardsOnField();
        System.out.println("Monster index: " + monsterIdx);
        fieldController.player.printMonsterCardsOnField();
        fieldController.player.activateAuraSkill(idxActivatedSkill, monsterIdx);
        skillActivating = false;
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

    public void destroy(Event evt) {
        Group clickedCard = (Group) evt.getSource();
        int idx = monsterArena.getColumnIndex(clickedCard);
        emptyMonster.add(idx);
        monsterArena.getChildren().remove(clickedCard);
        SummonedMonster monsterData = fieldController.player.getCharacterOnField()[idx];
        ArrayList<Integer> linkedSkill = monsterData.getSkillLinked();
        ArrayList<Integer> idxSkillArena = new ArrayList<Integer>();
        System.out.println("Destroying skill Card");
        for (int skillIdx: linkedSkill) {
            System.out.println("Linked Index: " + skillIdx);
            idxSkillArena.add(0, skillIdx + 1);
            emptySkill.add(skillIdx);
        }
        for (int idxRemove: idxSkillArena) {
            skillArena.getChildren().remove(idxRemove);
        }
        fieldController.player.removeMonsterOnField(idx);
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

