package com.avatarduel;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import com.avatarduel.Card.*;
import com.avatarduel.util.CsvReader;

import static com.avatarduel.Card.Element.*;

public class Player {

    private static final int maxMonstersOnField = 6;
    private static final int maxSkillsOnField = 6;
    private static final int maxCardsOnHand = 6;

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
            this.deck.push(randomNumber.nextInt(89) + 1);
        }
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
        System.out.println(deck.size());
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

    public ArrayList<Card> getHand() {
        return hand;
    }
    
    public SummonedMonster[] getCharacterOnField() {
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
    
    public Card draw() {
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
                    System.out.println(landCard.getName());
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
                        System.out.println(monsterCard.getName());
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
                        System.out.println(skillAuraCard.getName());
                        return skillAuraCard;

                    }
                }
            } else {
                Land makeshiftCard = new Land(String.valueOf((topCardId)) + "(N) Eastern Air Temple", AIR, "(NOT FOUND) One of the two temples exclusively housing female airbenders.", "@/../com/avatarduel/card/image/land/Eastern Air Temple.png");
                this.hand.add(makeshiftCard);
                return makeshiftCard;
            }
        }
        Land makeshiftCard = new Land("(N) Eastern Air Temple", AIR, "(NOT FOUND) One of the two temples exclusively housing female airbenders.", "@/../com/avatarduel/card/image/land/Eastern Air Temple.png");
        this.hand.add(makeshiftCard);
        return makeshiftCard;
    }

    public void removeMonsterOnField(int monsterIndex) {
        ArrayList<Integer> linkedSkill = monsterOnField[monsterIndex].getSkillLinked();
        for (int i = 0; i < linkedSkill.size(); i++) {
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
                        monsterOnField[i].removeSkill(skillIndex);
                        removed = true;
                    }
                }
            }
        }
        skillOnField[skillIndex] = null;
        numberOfSkillsOnField--;
    }

    public void putToField(int cardOnHandIndex, boolean isAttackPosition) {
        int i = 0;
        if (hand.size() > 0) {
            System.out.println("~~ putToField ~~");
            Card handGet = hand.get(cardOnHandIndex);
            if (handGet.getType().equals("Monster")) {
                if (numberOfMonstersOnField < maxMonstersOnField) {
                    while (i < maxMonstersOnField && monsterOnField[i] != null) {
                        System.out.println(i);
                        i++;
                    }
                    monsterOnField[i] = new SummonedMonster(((Monster)handGet), isAttackPosition);
                    numberOfMonstersOnField++;
                }
            } else if (handGet.getType().equals("Land")) {
                addLandMaxPowerByElement(handGet.getElement());
            } else {
                if (numberOfSkillsOnField < maxSkillsOnField) {
                    while (i < maxSkillsOnField && skillOnField[i] != null) {
                        System.out.println(i);
                        i++;
                    }
                    skillOnField[i] = (Skill)handGet;
                    numberOfSkillsOnField++;
                }
            }
            hand.remove(cardOnHandIndex);
        }
    }

    public void activateAuraSkill(int sourceSkillOnFieldIndex, int monsterOnFieldIndex) {
        monsterOnField[monsterOnFieldIndex].addBuff(
            ((Aura)skillOnField[sourceSkillOnFieldIndex]).getAttackValue(),
            ((Aura)skillOnField[sourceSkillOnFieldIndex]).getDefenseValue()
        );
    }

    public void activateDestroySkill(boolean isTargetMonster, int targetOnFieldIndex, Player targetPlayer) {
        if (isTargetMonster) {
            targetPlayer.removeMonsterOnField(targetOnFieldIndex);
        } else {
            targetPlayer.removeSkillOnField(targetOnFieldIndex);
        }
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
            }
        }
    }

    public void printSkillCardsOnField() {
        System.out.println("~~~ print skill cards on field lets ngentot okey ~~~");
        for (int i = 0; i < maxSkillsOnField; i++) {
            if (skillOnField[i] != null) {
                printShit(skillOnField[i]);
                System.out.println("TYPE " + skillOnField[i].getSkillType() + " POWER " + String.valueOf(skillOnField[i].getPowerValue()));
            }
        }
    }

    public void printShit(Card card) {
        System.out.println("Name: " + card.getName() + ", Type: " + card.getType());
    }

    public void destroyMonsterOnField(int idx) {
        monsterOnField[idx] = null;
    }

}
