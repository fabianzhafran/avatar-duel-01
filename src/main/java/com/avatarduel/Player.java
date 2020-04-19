package com.avatarduel;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import com.avatarduel.Card.*;
import com.avatarduel.phase.PhaseEnum;

import static com.avatarduel.Card.Element.*;

public class Player {

    private static final int maxMonstersOnField = 6;
    private static final int maxSkillsOnField = 6;
    private static final int maxCardsOnHand = 8;

    protected int playerID;
    protected String namePlayer;
    protected int hp;
    protected Stack<Integer> deck;
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
        deck = new Stack<Integer>();
        Random randomNumber = new Random();
        for (int i = 0; i < 50; i++) {
            deck.push(randomNumber.nextInt(99) + 1);
        }
        // Debugging
        deck.pop();
        deck.pop();
        deck.push(new Integer(1));
        deck.push(new Integer(2));
        //
        hand = new ArrayList<Card>();
        monsterOnField = new SummonedMonster[maxMonstersOnField];
        numberOfMonstersOnField = 0;
        numberOfSkillsOnField = 0;
        skillOnField = new Skill[maxSkillsOnField];
        elementPower = new ElementPower[4];
        elementPower[0] = new ElementPower(EARTH);
        elementPower[1] = new ElementPower(WATER);
        elementPower[2] = new ElementPower(FIRE);
        elementPower[3] = new ElementPower(AIR);

        // DEBUG
         addLandMaxPowerByElement(EARTH);
         addLandMaxPowerByElement(WATER);
         addLandMaxPowerByElement(FIRE);
         addLandMaxPowerByElement(AIR);
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
                System.out.println(sm.getMonster().getName());
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
                elPow.setCurrentPow(elPow.getCurrentPow() + 100);
                elPow.setMaxPow(elPow.getMaxPow() + 100);
            }
        }
    }
    
    public Card draw() {
        if (hand.size() < maxCardsOnHand) {
            ListOfCards listOfCards = new ListOfCards();
            boolean found = false;
            ElementDictionary elementDictionary = new ElementDictionary();
            if (getDeckCount() > 0) {
                int topCardId = deck.pop();
                for (String[] landRow : listOfCards.listOfLandCards) {
                    if (topCardId == Integer.parseInt(landRow[0])) {
                            Land landCard = new Land(landRow[1],
                                                 elementDictionary.getElement(landRow[2]),
                                                 landRow[3],
                                                 landRow[4]
                                                );
                        this.hand.add(landCard);
    //                    System.out.println(landCard.getName());
                        found = true;
                        return landCard;
                    }
                }
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
    //                        System.out.println(monsterCard.getName());
                            found = true;
                            return monsterCard;
                        }
                    }
                }
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
    //                        System.out.println(skillAuraCard.getName());
                            return skillAuraCard;
                        }
                    }
                }
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
     //                        System.out.println(skillAuraCard.getName());
                             return skillDestroyCard;
                         }
                     }
                 }
            }
        }
        return null;
    }

    public void removeMonsterOnField(int monsterIndex) {
        ArrayList<Integer> linkedSkill = monsterOnField[monsterIndex].getSkillLinked();
//        int totalSize = linkedSkill.size();
//        for (int i = 0; i < totalSize; i++) {
//            removeSkillOnField(linkedSkill.get(i));
//        }

        for (int i = linkedSkill.size() - 1; i >= 0; i--) {
            removeSkillOnField(linkedSkill.get(i));
        }
        monsterOnField[monsterIndex] = null;
        numberOfMonstersOnField--;
    }

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

    // return true kalo sukses
    public void putToField(int cardOnHandIndex, boolean isAttackPosition) {
        int i = 0;
        System.out.println("~~~Put to field~~~");
        if (hand.size() > 0) {
        // if (hand.size() > 0 && (isCorrectPhase(PhaseEnum.MAIN_PHASE_1)) {
            Card handGet = hand.get(cardOnHandIndex);
            if (handGet.getType().equals("Monster")) {
               System.out.println("Card is monster");
                int currentElementPower = getLandPowerByElement(handGet.getElement());
                int monsterPower = ((Monster)handGet).getPowerValue();
                Element monsterElement = handGet.getElement();
                System.out.println("monster power : " + String.valueOf(monsterPower));
                System.out.println("current element power : " + String.valueOf(currentElementPower));
                if (monsterPower <= currentElementPower && numberOfMonstersOnField < maxMonstersOnField) {
                    while (i < maxMonstersOnField && monsterOnField[i] != null) {
                        i++;
                    }
//                    System.out.println("Got on index " + i);
                    setLandPowerByElement(monsterElement, currentElementPower - monsterPower);
                    System.out.println(getLandPowerByElement(EARTH));
                    System.out.println(getLandPowerByElement(WATER));
                    System.out.println(getLandPowerByElement(FIRE));
                    System.out.println(getLandPowerByElement(AIR));
                    monsterOnField[i] = new SummonedMonster(((Monster)handGet), isAttackPosition);
                    numberOfMonstersOnField++;
//                    putCardIsSuccessful = true;
                }
            } else if (handGet.getType().equals("Land")) {
                addLandMaxPowerByElement(handGet.getElement());
//                putCardIsSuccessful = true;
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
//                    putCardIsSuccessful = true;
                }
            }
            hand.remove(cardOnHandIndex);
         }
//        return putCardIsSuccessful;
//        System.out.println("End of putToField");
    }

    // Reset all attacking condition before battle phase
    public void resetMonsterHasAttacked() {
        for (SummonedMonster monster : monsterOnField) {
            if (monster != null) {
                monster.setHasAttacked(false);
            }
        }
    }

    // Reset monsters that are summoned to be able to attack
    public void resetMonsterIsJustSummoned() {
        for (SummonedMonster monster : monsterOnField) {
            if (monster != null) {
                monster.setIsJustSummonedToFalse();
            }
        }
    }

    public void attack(int sourceMonsterOnFieldIndex, int targetMonsterOnFieldIndex, Player targetPlayer) {
        System.out.println("Masuk attack");
        SummonedMonster attackingMonster = this.monsterOnField[sourceMonsterOnFieldIndex];
        if (!attackingMonster.getHasAttacked() && !attackingMonster.getIsJustSummoned()) {
            if (targetMonsterOnFieldIndex != -1) { // -1 kalau nggak ada monster di field lawan
                System.out.println("Attack Monster");
                SummonedMonster[] targetMonsterField = targetPlayer.getMonsterOnField();
                SummonedMonster targetMonster = targetMonsterField[targetMonsterOnFieldIndex];
                int attackingMonsterAtk = attackingMonster.getAttackValue();
                int targetMonsterAtk = targetMonster.getAttackValue();
                int targetMonsterDef = targetMonster.getDefenseValue();
                if (targetMonster.getIsAttackPosition()) {
                    if (attackingMonsterAtk > targetMonsterAtk) {
                        targetPlayer.removeMonsterOnField(targetMonsterOnFieldIndex);
                        targetPlayer.subtractHp(attackingMonsterAtk - targetMonsterAtk);
                        System.out.println("Target Player HP: " + targetPlayer.getHp());
                        attackingMonster.setHasAttacked(true);
                    }
                } else {
                    if (attackingMonsterAtk > targetMonsterDef) {
                        targetPlayer.removeMonsterOnField(targetMonsterOnFieldIndex);
                        if (attackingMonster.getPierce()) {
                            targetPlayer.subtractHp(attackingMonsterAtk - targetMonsterAtk);
                        }
                    }
                }
            } else {
                targetPlayer.subtractHp(attackingMonster.getAttackValue());
                attackingMonster.setHasAttacked(true);
            }
        } else {
            System.out.println("Monster has attacked");
        }
    }

    public void activateAuraSkill(int sourceSkillOnFieldIndex, int monsterOnFieldIndex) {
        System.out.println("Masuk activate Aura");
//        System.out.println(sm.getMonster().getName());
        Aura aura = (Aura) skillOnField[sourceSkillOnFieldIndex];
        // if ((isCorrectPhase(PhaseEnum.MAIN_PHASE_1) || isCorrectPhase(PhaseEnum.MAIN_PHASE_2)) && aura.getIsUsed()) { 
            System.out.println(aura.getName());
            skillOnField[sourceSkillOnFieldIndex].setIsUsedToTrue();
            monsterOnField[monsterOnFieldIndex].addBuff(
                ((Aura)skillOnField[sourceSkillOnFieldIndex]).getAttackValue(),
                ((Aura)skillOnField[sourceSkillOnFieldIndex]).getDefenseValue()
            );
            monsterOnField[monsterOnFieldIndex].registerSkill(sourceSkillOnFieldIndex);
        // }
    }

    public void activatePowerUpSkill(int sourceSkillOnFieldIndex, int monsterOnFieldIndex) {
        monsterOnField[monsterOnFieldIndex].setPierce();
        removeSkillOnField(sourceSkillOnFieldIndex);
    }

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
        System.out.println("~~~ print cards on hand  ~~~");
        for (int i = 0; i < hand.size(); i++) {
            printShit(hand.get(i));
        }
    }

    public void printMonsterCardsOnField() {
        System.out.println("~~~ print monster cards on field ~~~");
        for (int i = 0; i < maxMonstersOnField; i++) {
            if (monsterOnField[i] != null) {
                printShit(monsterOnField[i].getMonster());
                System.out.println("ATK " 
                                   + String.valueOf(monsterOnField[i].getMonster().getAttackValue())
                                   + " DEF "
                                   + String.valueOf(monsterOnField[i].getMonster().getDefenseValue())
                                   );
            } else {
                System.out.println("Index " + i + " is null");
            }
        }
    }

    public void printSkillCardsOnField() {
        for (int i = 0; i < maxSkillsOnField; i++) {
            if (skillOnField[i] != null) {
                System.out.println("Card name is: " + skillOnField[i].getName());
            }
        }
    }

    public void printShit(Card card) {
        System.out.println("Name: " + card.getName() + ", Type: " + card.getType());
    }

    public void printLinkedSkill(int idxMonster) {
        ArrayList<Integer> linkedSkill = getMonsterOnField()[idxMonster].getSkillLinked();
        System.out.println("Skill linked to" + getMonsterOnField()[idxMonster].getMonster().getName());
        for (int idxSkill:linkedSkill) {
            System.out.println("Skill: " + getSkillOnField()[idxSkill].getName());
        }
    }

}
