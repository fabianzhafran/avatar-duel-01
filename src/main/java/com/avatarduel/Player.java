package com.avatarduel;
/** Represents the state of the player playing the game.
 * @author Fabian Zhafransyah
 * @author Ananda Yulizar
 * @author Rafi Adyatma 
*/

import java.util.ArrayList;
import java.util.Random;

import com.avatarduel.card.*;

import static com.avatarduel.card.Element.*;

public class Player {

    private static final int maxMonstersOnField = 6;
    private static final int maxSkillsOnField = 6;
    private static final int maxCardsOnHand = 9;

    protected int playerID;
    protected String namePlayer;
    protected int hp;
    protected ArrayList<Integer> deck;
    protected ArrayList<Card> hand;
    protected SummonedMonster[] monsterOnField;
    protected int numberOfMonstersOnField;
    protected Skill[] skillOnField;
    protected int numberOfSkillsOnField;
    protected ElementPower[] elementPower;
    
    public Player(String namePlayer) {
        this.namePlayer = namePlayer;
        int id = namePlayer.charAt(namePlayer.length() - 1) - 48;
        this.playerID = id;
        hp = 80;
        deck = new ArrayList<Integer>();
        Random randomNumber = new Random();
        for (int i = 0; i < 50; i++) {
            deck.add(randomNumber.nextInt(48) + 21);
            if (i < 18) {
                deck.add(randomNumber.nextInt(19) + 1);
            }
            if (i < 12) {
                deck.add(randomNumber.nextInt(38) + 70);
            }
        }
        hand = new ArrayList<Card>();
        monsterOnField = new SummonedMonster[maxMonstersOnField];
        numberOfMonstersOnField = 0;
        numberOfSkillsOnField = 0;
        skillOnField = new Skill[maxSkillsOnField];
        elementPower = new ElementPower[5];
        elementPower[0] = new ElementPower(EARTH);
        elementPower[1] = new ElementPower(WATER);
        elementPower[2] = new ElementPower(FIRE);
        elementPower[3] = new ElementPower(AIR);
        elementPower[4] = new ElementPower(ENERGY);

    }

    public int getPlayerID() {
        return playerID;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public int getHp() {
        return hp;
    }

    public void subtractHp(int damage) {
        if (hp < damage) {
            hp = 0;
        }
        hp = hp - damage;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    
    public SummonedMonster[] getMonsterOnField() {
        return monsterOnField;
    }

    public void printMonsterOnField() {
        // For debugging
        for (SummonedMonster sm: monsterOnField) {
            if (sm != null) {
            }
        }
    }
    
    public Skill[] getSkillOnField() {
        return skillOnField;
    }
    
    public ElementPower[] getElementPower() {
        return elementPower;
    }

    public int getDeckCount() {
        return this.deck.size(); 
    }

    public int getNumberOfMonstersOnField() {
        return numberOfMonstersOnField;
    }

    public int getNumberOfSkillsOnField() {
        return numberOfSkillsOnField;
    }

    public int getLandPowerByElement(Element e) {
        for (ElementPower elPow : elementPower) {
            if (elPow.getElement() == e) {
                return elPow.getCurrentPow();
            }
        }
        return 0;
    }

    public int getMaxLandPowerByElement(Element e) {
        for (ElementPower elPow : elementPower) {
            if (elPow.getElement() == e) {
                return elPow.getMaxPow();
            }
        }
        return 0;
    }

    public void setLandPowerByElement(Element e, int pow) {
        for (ElementPower elPow : elementPower) {
            if (elPow.getElement() == e) {
                elPow.setCurrentPow(pow);
            }
        }
    }

    public void addLandMaxPowerByElement(Element e) {
        for (ElementPower elPow : elementPower) {
            if (elPow.getElement() == e) {
                elPow.setCurrentPow(elPow.getCurrentPow() + 1);
                elPow.setMaxPow(elPow.getMaxPow() + 1);
            }
        }
    }

    public void setLandPowerToMax() {
        for (ElementPower elPow : elementPower) {
            elPow.setCurrentPow(elPow.getMaxPow());
        }
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public Card draw() {
        Random randomNumber = new Random();
        if (hand.size() >= maxCardsOnHand) {
            hand.remove(0);
        }
            ListOfCards listOfCards = new ListOfCards();
            boolean found = false;
            ElementDictionary elementDictionary = new ElementDictionary();
            if (getDeckCount() > 0) {

                int idx = randomNumber.nextInt(deck.size() - 1);
                int topCardId = deck.get(idx);
                deck.remove(idx);
                // Draw land
                for (String[] landRow : listOfCards.listOfLandCards) {
                    if (topCardId == Integer.parseInt(landRow[0])) {
                            Land landCard = new Land(landRow[1],
                                                 elementDictionary.getElement(landRow[2]),
                                                 landRow[3],
                                                 landRow[4]
                                                );
                        this.hand.add(landCard);
                        found = true;
                        return landCard;
                    }
                }
                // Draw monster
                if (!found) {
                    for (String[] monsterRow : listOfCards.listOfMonsterCards) {
                        if (topCardId == Integer.parseInt(monsterRow[0])) {
                            Monster monsterCard = new Monster(monsterRow[1],
                                                               elementDictionary.getElement(monsterRow[2]),
                                                               monsterRow[3],
                                                               monsterRow[4],
                                                               Integer.parseInt(monsterRow[5]),
                                                               Integer.parseInt(monsterRow[6]),
                                                               Integer.parseInt(monsterRow[7])
                                                              );
                            this.hand.add(monsterCard);
                            found = true;
                            return monsterCard;
                        }
                    }
                }
                // Draw skill aura
                if (!found) {
                    for (String[] skillAuraRow : listOfCards.listOfSkillAuraCards) {
                        if (topCardId == Integer.parseInt(skillAuraRow[0])) {
                            Aura skillAuraCard = new Aura(skillAuraRow[1],
                                                          elementDictionary.getElement(skillAuraRow[2]),
                                                          skillAuraRow[3],
                                                          skillAuraRow[4],
                                                          Integer.parseInt(skillAuraRow[5]),
                                                          Integer.parseInt(skillAuraRow[6]),
                                                          Integer.parseInt(skillAuraRow[7])
                                                         );
                            this.hand.add(skillAuraCard);
                            return skillAuraCard;
                        }
                    }
                }
                // Draw skill destroy
                 if (!found) {
                     for (String[] skillDestroyRow : listOfCards.listOfSkillDestroyCards) {
                         if (topCardId == Integer.parseInt(skillDestroyRow[0])) {
                             Destroy skillDestroyCard = new Destroy(skillDestroyRow[1],
                                                           elementDictionary.getElement(skillDestroyRow[2]),
                                     skillDestroyRow[3],
                                     skillDestroyRow[4],
                                                           Integer.parseInt(skillDestroyRow[5])
                                                          );
                             this.hand.add(skillDestroyCard);
                             return skillDestroyCard;
                         }
                     }
                 }
                // Draw skill power up
                if (!found) {
                    for (String[] skillPowerUpRow : listOfCards.listOfSkillPowerUpCards) {
                        if (topCardId == Integer.parseInt(skillPowerUpRow[0])) {
                            PowerUp skillPowerUpCard = new PowerUp(skillPowerUpRow[1],
                                                          elementDictionary.getElement(skillPowerUpRow[2]),
                                                          skillPowerUpRow[3],
                                                          skillPowerUpRow[4],
                                                          Integer.parseInt(skillPowerUpRow[5])
                                                         );
                            this.hand.add(skillPowerUpCard);
                            return skillPowerUpCard;
                        }
                    }
                }
            }

        return null;
    }

    /** Removes a monster from the monster field
     * @param monsterIndex index of the monster on field to be removed 
     */
    public void removeMonsterOnField(int monsterIndex) {
        ArrayList<Integer> linkedSkill = monsterOnField[monsterIndex].getSkillLinked();
        for (int i = linkedSkill.size() - 1; i >= 0; i--) {
            removeSkillOnField(linkedSkill.get(i));
        }
        monsterOnField[monsterIndex] = null;
        numberOfMonstersOnField--;
    }

    /** Removes a skill from the skill field.
     * @param skillIndex index of the skill on field to be removed
     */
    public void removeSkillOnField(int skillIndex) {
        boolean removed = false;
        if (skillOnField[skillIndex].getSkillType().equals("Aura")) {
            for (int i = 0; i < maxMonstersOnField && !removed; i++) {
                if (monsterOnField[i] != null) {
                    ArrayList<Integer> linkedSkill = monsterOnField[i].getSkillLinked();
                    int j = 0;
                    while (j < linkedSkill.size() && skillIndex != linkedSkill.get(j)) {
                        j++;
                    }
                    if (j < linkedSkill.size()) {
                        monsterOnField[i].subtractBuff(((Aura)skillOnField[skillIndex]).getAttackValue(), 
                                                       ((Aura)skillOnField[skillIndex]).getDefenseValue()
                                                      );
                        monsterOnField[i].removeSkill(skillIndex);
                        removed = true;
                    }
                }
            }
        }
        skillOnField[skillIndex] = null;
        numberOfSkillsOnField--;
    }

    /** Put a card to the field
     * @param cardOnHandIndex index of card on hand to be put on player's field
     * @param isAttackPosition denotes whether the card is put on attack position or not. If the card is not a monster card, it is not used.
     */
    public void putToField(int cardOnHandIndex, boolean isAttackPosition) {
        int i = 0;
        if (hand.size() > 0) {
            Card handGet = hand.get(cardOnHandIndex);
            if (handGet.getType().equals("Monster")) {
                int currentElementPower = getLandPowerByElement(handGet.getElement());
                int monsterPower = ((Monster)handGet).getPowerValue();
                Element monsterElement = handGet.getElement();
                if (monsterPower <= currentElementPower && numberOfMonstersOnField < maxMonstersOnField) {
                    while (i < maxMonstersOnField && monsterOnField[i] != null) {
                        i++;
                    }
                    setLandPowerByElement(monsterElement, currentElementPower - monsterPower);
                    monsterOnField[i] = new SummonedMonster(((Monster)handGet), isAttackPosition);
                    numberOfMonstersOnField++;
                }
            } else if (handGet.getType().equals("Land")) {
                addLandMaxPowerByElement(handGet.getElement());
            } else {
                int skillPower = ((Skill)handGet).getPowerValue();
                Element skillElement = handGet.getElement();
                int currentElementPower = getLandPowerByElement(skillElement);
                if (skillPower <= currentElementPower && numberOfSkillsOnField < maxSkillsOnField) {
                    while (i < maxSkillsOnField && skillOnField[i] != null) {
                        i++;
                    }
                    setLandPowerByElement(skillElement, currentElementPower - skillPower);
                    skillOnField[i] = (Skill)handGet;
                    numberOfSkillsOnField++;
                }
            }
            hand.remove(cardOnHandIndex);
         }
    }

    /** Resets every monster's attacking status to false. It is called when players start their round.
     * 
     */
    public void resetMonsterHasAttacked() {
        for (SummonedMonster monster : monsterOnField) {
            if (monster != null) {
                monster.setHasAttacked(false);
            }
        }
    }

    /** Resets every monster's "just summoned" status to false. It is called when players start their round.
     * 
     */
    public void resetMonsterIsJustSummoned() {
        for (SummonedMonster monster : monsterOnField) {
            if (monster != null) {
                monster.setIsJustSummonedToFalse();
            }
        }
    }

    /** Conduct an attack towards other player. 
     * @param sourceMonsterOnFieldIndex attacking monster's index on player's monster field
     * @param targetMonsterOnFieldIndex attacked monster's index on attacked player's monster field
     * @param targetPlayer player who is targeted for the attack
     */
    public void attack(int sourceMonsterOnFieldIndex, int targetMonsterOnFieldIndex, Player targetPlayer) {
        SummonedMonster attackingMonster = this.monsterOnField[sourceMonsterOnFieldIndex];
        if (!attackingMonster.getHasAttacked() && !attackingMonster.getIsJustSummoned()) {
            if (targetMonsterOnFieldIndex != -1) { // -1 kalau nggak ada monster di field lawan
                SummonedMonster[] targetMonsterField = targetPlayer.getMonsterOnField();
                SummonedMonster targetMonster = targetMonsterField[targetMonsterOnFieldIndex];
                int attackingMonsterAtk = attackingMonster.getAttackValue();
                int targetMonsterAtk = targetMonster.getAttackValue();
                int targetMonsterDef = targetMonster.getDefenseValue();
                if (targetMonster.getIsAttackPosition()) {
                    if (attackingMonsterAtk > targetMonsterAtk) {
                        targetPlayer.removeMonsterOnField(targetMonsterOnFieldIndex);
                        targetPlayer.subtractHp(attackingMonsterAtk - targetMonsterAtk);
                        attackingMonster.setHasAttacked(true);
                    }
                } else {
                    if (attackingMonsterAtk > targetMonsterDef) {
                        targetPlayer.removeMonsterOnField(targetMonsterOnFieldIndex);
                        if (attackingMonster.getPierce()) {
                            targetPlayer.subtractHp(attackingMonsterAtk - targetMonsterDef);
                        }
                    }
                }
            } else {
                targetPlayer.subtractHp(attackingMonster.getAttackValue());
                attackingMonster.setHasAttacked(true);
            }
        } else {
        }
    }

    /** Activates an aura skill to be applied to a monster
     * @param sourceSkillOnFieldIndex source aura's index from skill field
     * @param monsterOnFieldIndex target monster's index from monster field
     */
    public void activateAuraSkill(int sourceSkillOnFieldIndex, int monsterOnFieldIndex) {
        Aura aura = (Aura) skillOnField[sourceSkillOnFieldIndex];
        skillOnField[sourceSkillOnFieldIndex].setIsUsedToTrue();
        monsterOnField[monsterOnFieldIndex].addBuff(
            ((Aura)skillOnField[sourceSkillOnFieldIndex]).getAttackValue(),
            ((Aura)skillOnField[sourceSkillOnFieldIndex]).getDefenseValue()
        );
        monsterOnField[monsterOnFieldIndex].registerSkill(sourceSkillOnFieldIndex);
        // }
    }

    /** Activates power up skill to be applied to a monster. Power up applies a piercing attack for a monster.
     * @param sourceSkillOnFieldIndex source powerup's index from skill field
     * @param monsterOnFieldIndex target monster's index from monster field
     */
    public void activatePowerUpSkill(int sourceSkillOnFieldIndex, int monsterOnFieldIndex) {
        monsterOnField[monsterOnFieldIndex].setPierce();
        skillOnField[sourceSkillOnFieldIndex].setIsUsedToTrue();
        monsterOnField[monsterOnFieldIndex].registerSkill(sourceSkillOnFieldIndex);
    }

    /** Activates destroy skill to be applied to a monster. Destroy destroys a card on the field.
     * @param isTargetMonster indicates whether the target is a monster or not. If it is, then it is true, otherwise it is false.
     * @param sourceSkillOnFieldIndex source destroy's index from skill field
     * @param targetOnFieldIndex target card's index from monster and skill field
     * @param targetPlayer player whose card is targeted by the destroy skill.
     */
    public void activateDestroySkill(boolean isTargetMonster, int sourceSkillOnFieldIndex, int targetOnFieldIndex, Player targetPlayer) {
        if (isTargetMonster) {
            targetPlayer.removeMonsterOnField(targetOnFieldIndex);
        } else {
            targetPlayer.removeSkillOnField(targetOnFieldIndex);
        }
        removeSkillOnField(sourceSkillOnFieldIndex);
    }

    // =============================
    // ========
    // DEBUG METHODS ===============
    // ========
    // =============================

    public void printCardsOnHand() {
        for (int i = 0; i < hand.size(); i++) {
            // printCard(hand.get(i));
        }
    }

    public void printMonsterCardsOnField() {
        //
    }

    public void printSkillCardsOnField() {
        //
    }

    public void printCard(Card card) {
        //
    }

    public void printLinkedSkill(int idxMonster) {
        //
    }

}
