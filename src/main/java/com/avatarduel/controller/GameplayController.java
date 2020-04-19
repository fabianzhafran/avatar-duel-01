package com.avatarduel.controller;

/** The core gameplay GUI. Holds the instantiation data of phase,
 * which means it also handles player. 
 * 
 * @author K01_01_IF2210
 */

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

    /** Initialize the GUI, is called automatically
     * by JavaFx
     */
    @FXML public void initialize() {
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

    /** Sets the giant card description located on the left
     * side of the whole application window
     * 
     * @param card card to be displayed on the window
     */
    public void setDescCard(Card card) {
        cardDescController.setName(card.getName());
        cardDescController.setDesc(card.getDescription());
        cardDescController.setCardImage(card.getImagePath());
        cardDescController.setElementImage(card.getElement());
        cardDescController.setColor(card.getElement());
        cardDescController.setType(card.getType());
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

    /** Sets the giant card description on the left side
     * of the whole application window, but for the monster cards
     * on the field 
     * 
     * @param Summoned
     * @param skillOnField
     */
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
        if (playerTurn == 1) {
            p2FieldController.receiveDestroy(idxSource);
        } else {
            p1FieldController.receiveDestroy(idxSource);
        }
    }

    public void startDestroy(int idxSource, int idxReceiver) {
        Player sourcePlayer = getPlayerInTurn();
        Player receivingPlayer = getPlayerNotInTurn();

        receivingPlayer.printMonsterCardsOnField();
        sourcePlayer.activateDestroySkill(true, idxSource, idxReceiver, receivingPlayer);
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

    /** Initiates attack event, invoked originally from arena.
     * 
     * @param idx Attacking monster's index on player's field
     */
    public void startAttack(int idx) {
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

    /** Initiates battle between two monsters from two different players
     * 
     * @param idxAttacker attacking monster's index on attacking player's monster field
     * @param idxReceiver target monster's index on target player's monster field
     */
    public void startBattle(int idxAttacker, int idxReceiver) {
        Player attackingPlayer = getPlayerInTurn();
        Player receivingPlayer = getPlayerNotInTurn();
        attackingPlayer.attack(idxAttacker, idxReceiver, receivingPlayer);
        
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