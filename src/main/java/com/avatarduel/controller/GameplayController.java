package com.avatarduel.controller;

import com.avatarduel.card.*;
import com.avatarduel.Player;
import com.avatarduel.phase.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class GameplayController implements NotifyPhase {

    @FXML private CardDescController cardDescController;
    @FXML private P1FieldController p1FieldController;
    @FXML private P2FieldController p2FieldController;

    @FXML private Label playerLabel;
    @FXML private Label phaseLabel;
    @FXML private Button nextPhaseButton;
    @FXML private Text winLabel;
    @FXML private ImageView winImage;
    @FXML private ImageView winImageModal;


    private Phase phase;
    private int phaseNumber;
    private int playerTurn;
    private boolean isAttacking;

    @FXML public void initialize() {
        System.out.println("App started");
//        System.out.println("Linking cardDescController...");
        cardDescController.init(this);
        p1FieldController.init(this);
        p2FieldController.init(this);
        this.phaseNumber = 1;
        this.playerTurn = 1;
        phase = new Phase(p1FieldController, p2FieldController, this);
        phase.nextPhase();
        // handle next phase
        isAttacking = false;
        nextPhaseButton.addEventHandler(MouseEvent.MOUSE_CLICKED,
            event -> {
                if (!p1FieldController.isSkillActivating() && !p2FieldController.isSkillActivating() && !isAttacking) {
                    phase.nextPhase();
                }
            });
        winImage.setVisible(false);
        winImage.setDisable(true);
        winImageModal.setVisible(false);
        winImageModal.setDisable(true);
        winLabel.setVisible(false);
        winLabel.setDisable(true);

    }

    public void setDescCard(Card card) {
//        System.out.println("Setting description...");
        cardDescController.setName(card.getName());
        cardDescController.setDesc(card.getDescription());
        cardDescController.setCardImage(card.getImagePath());
        cardDescController.setElementImage(card.getElement());
        cardDescController.setColor(card.getElement());
        cardDescController.setType(card.getType());
        // Set DEscription
        // SEt ELement
        if (card.getType().equals("Monster")) {
            Monster castedCard = (Monster) card;
            cardDescController.setPwr("Pow: " + String.valueOf(castedCard.getPowerValue()));
            cardDescController.setAtt("Atk: " + String.valueOf(castedCard.getAttackValue()));
            cardDescController.setDef("Def: " + String.valueOf(castedCard.getDefenseValue()));
        } else if (card.getType().equals("Skill")) {
            Skill skillCard = (Skill) card;
            cardDescController.setType(skillCard.getSkillType());
            if (skillCard.getSkillType().equals("Aura")) {
                Aura castedCard = (Aura) skillCard;
                cardDescController.setPwr("Pow: " + String.valueOf(castedCard.getPowerValue()));
                cardDescController.setAtt("Atk: " + String.valueOf(castedCard.getAttackValue()));
                cardDescController.setDef("Def: " + String.valueOf(castedCard.getDefenseValue()));
            } else if (skillCard.getSkillType().equals("Power Up")) {
                PowerUp castedCard = (PowerUp) skillCard;
                cardDescController.setPwr("Pow: " + String.valueOf(castedCard.getPowerValue()));
                cardDescController.setAtt(" ");
                cardDescController.setDef(" "); 
            }

        } else {
            cardDescController.setPwr(String.valueOf(" "));
            cardDescController.setAtt(String.valueOf(" "));
            cardDescController.setDef(String.valueOf(" "));
        }
    }

    public void setDescCard(SummonedMonster Summoned, Skill[] skillOnField) {
        Monster card = Summoned.getMonster();
        cardDescController.setName(card.getName());
        cardDescController.setDesc(card.getDescription());
        cardDescController.setCardImage(card.getImagePath());
        cardDescController.setElementImage(card.getElement());
        cardDescController.setColor(card.getElement());
        cardDescController.setType(card.getType());
        cardDescController.setPwr("Pow: " + String.valueOf(card.getPowerValue()));
        cardDescController.setAtt("Atk: " + String.valueOf(Summoned.getAttackValue()));
        cardDescController.setDef("Def: " + String.valueOf(Summoned.getDefenseValue()));

        ArrayList<Integer> idxSkillOnField = Summoned.getSkillLinked();
        ArrayList<String> attachedSkillName = new ArrayList<String>();
        for (int idx:idxSkillOnField) {
            attachedSkillName.add(skillOnField[idx].getName());
        }

        cardDescController.setAttachedSkill(attachedSkillName);
    }

    // Observer pattern
    public void notifyPhase(int phaseNumber, int playerTurn) {
        this.phaseNumber = phaseNumber;
        this.playerTurn = playerTurn;
        playerLabel.setText("Player " + String.valueOf(playerTurn));
        phaseLabel.setText(PhaseEnum.phaseEnum.get(phaseNumber));
    }

    public void useDestroy(int idxSource) {
        System.out.println("Gameplay use Destroy");
        if (playerTurn == 1) {
            p2FieldController.receiveDestroy(idxSource);
        } else {
            p1FieldController.receiveDestroy(idxSource);
        }
    }

    public void startDestroy(int idxSource, int idxReceiver) {
        System.out.println("Gameplay Start Destroy");
        Player sourcePlayer = getPlayerInTurn();
        Player receivingPlayer = getPlayerNotInTurn();

        System.out.println("Monster before battle: ");
        receivingPlayer.printMonsterCardsOnField();
        sourcePlayer.activateDestroySkill(true, idxSource, idxReceiver, receivingPlayer);
        System.out.println("Monster after battle: ");
        receivingPlayer.printMonsterCardsOnField();

        if (playerTurn == 1) {
            p1FieldController.getArenaController().destroyByIndex(idxSource, false);
            p1FieldController.setSkillActivating(false);
        } else {
            p2FieldController.getArenaController().destroyByIndex(idxSource, false);
            p2FieldController.setSkillActivating(false);
        }
    }

    public Player getPlayerInTurn() {
        return (playerTurn == 1) ? p1FieldController.getPlayer() : p2FieldController.getPlayer();
    }

    public Player getPlayerNotInTurn() {
        return (playerTurn == 1) ? p2FieldController.getPlayer() : p1FieldController.getPlayer();
    }

    public void startAttack(int idx) {
        // Nanti di chek turn siapa, misalnya skarang turn P1 Yang nyerang
        System.out.println("Gameplay Start Attack");
        isAttacking = true;

        if (playerTurn == 1) {
            SummonedMonster p1AttackingMonster = p1FieldController.getPlayer().getMonsterOnField()[idx];
            if (!p1AttackingMonster.getHasAttacked()) {
                int atkValue = p1FieldController.getPlayer().getMonsterOnField()[idx].getAttackValue();
                p2FieldController.receiveAttack(idx,atkValue);
            }
        } else {
            SummonedMonster p2AttackingMonster = p2FieldController.getPlayer().getMonsterOnField()[idx];
            if (!p2AttackingMonster.getHasAttacked()) {
                int atkValue = p2FieldController.getPlayer().getMonsterOnField()[idx].getAttackValue();
                p1FieldController.receiveAttack(idx,atkValue);
            }
        }
    }

    public void startBattle(int idxAttacker, int idxReceiver) {
        // Misalkan yang nyerang p1
        System.out.println("Gameplay Start Battle");
        Player attackingPlayer = getPlayerInTurn();
        Player receivingPlayer = getPlayerNotInTurn();

//        System.out.println("Monster before battle: ");
//        receivingPlayer.printMonsterCardsOnField();
        attackingPlayer.attack(idxAttacker, idxReceiver, receivingPlayer);
//        System.out.println("Monster after battle: ");
//        receivingPlayer.printMonsterCardsOnField();
        System.out.println("Receiving Player HP is " + receivingPlayer.getHp());
        
        boolean win = false;
        if (receivingPlayer.getHp() <= 0) {
            win = true;
            winLabel.setVisible(true);
            winLabel.setDisable(false);
            winImage.setVisible(true);
            winImage.setDisable(false);
            winImageModal.setVisible(true);
            winImageModal.setDisable(false);
        } 

        if (playerTurn == 1) {
            if (win) {
                winLabel.setText("Player 1 Wins!");
            }
            p2FieldController.setHP(receivingPlayer.getHp());
            p1FieldController.arenaController.resetHightlight();
        } else {
            if (win) {
                winLabel.setText("Player 2 Wins!");
            }
            p1FieldController.setHP(receivingPlayer.getHp());
            p2FieldController.arenaController.resetHightlight();
        }

        isAttacking = false;
    }
}