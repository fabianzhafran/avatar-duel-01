package com.avatarduel;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import com.avatarduel.Card.*;
import com.avatarduel.util.CsvReader;

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
    protected ElementPower[] elementPower;

    public Player(String namePlayer) {

        this.namePlayer = namePlayer;
        int id = namePlayer.charAt(namePlayer.length() - 1) - 48;
        this.playerID = id;
        hp = 80;
        deck = new Stack<Integer>();
        Random randomNumber = new Random();
        for (int i = 0; i < 50; i++) {
            this.deck.push(randomNumber.nextInt(90));
        }
        hand = new ArrayList<Card>();
        monsterOnField = new SummonedMonster[maxMonstersOnField];
        numberOfMonstersOnField = 0;
        skillOnField = new Skill[maxSkillsOnField];
        elementPower = new ElementPower[4];
        System.out.println(deck.size());
    }

    public int getPlayerID() {
        return playerID;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    
    public SummonedMonster[] getCharacterOnField() {
        return monsterOnField;
    }
    
    public Skill[] getSkillOnField() {
        return skillOnField;
    }
    
    public ElementPower[] getElementPower() {
        return elementPower;
    }

    public int getDeckCount() { return this.deck.size(); }

    public int getLandPowerByElement(Element e) {
        for (ElementPower elPow : elementPower) {
            if (elPow.getElement() == e) {
                return elPow.getCurrentPow();
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

        // System.out.println("~~ " + this.namePlayer + " Draw : ");
        ListOfCards listOfCards = new ListOfCards();
        boolean found = false;
        ElementDictionary elementDictionary = new ElementDictionary();
        if (deck.size() > 0) {
            int topCardId = deck.pop();
            // System.out.println("top card id : ");
            // System.out.println(topCardId);
            for (String[] landRow : listOfCards.listOfLandCards) {
//                System.out.println(landRow[0]);
                if (topCardId == Integer.parseInt(landRow[0])) {
                        // System.out.println("~~ Found a land card.");
                        Land landCard = new Land(landRow[1], 
                                             elementDictionary.getElement(landRow[2]), 
                                             landRow[3], 
                                             landRow[4]
                                            );
                    this.hand.add(landCard);
                    System.out.println(landCard.getNama());
                    found = true;
                    return landCard;
                }
            }
            if (!found) {
                for (String[] monsterRow : listOfCards.listOfMonsterCards) {
                    if (topCardId == Integer.parseInt(monsterRow[0])) {
                        // System.out.println("~~ Found a monster card.");
                        Monster monsterCard = new Monster(monsterRow[1], 
                                                           elementDictionary.getElement(monsterRow[2]), 
                                                           monsterRow[3], 
                                                           monsterRow[4], 
                                                           Integer.parseInt(monsterRow[5]), 
                                                           Integer.parseInt(monsterRow[6]), 
                                                           Integer.parseInt(monsterRow[7])
                                                          );
                        this.hand.add(monsterCard);
                        System.out.println(monsterCard.getNama());
                        found = true;
                        return monsterCard;
                    }
                }
            }
            if (!found) {
                for (String[] skillAuraRow : listOfCards.listOfSkillAuraCards) {
                    if (topCardId == Integer.parseInt(skillAuraRow[0])) {
                        // System.out.println("~~ Found a skillAura card.");
                        Aura skillAuraCard = new Aura(skillAuraRow[1], 
                                                      elementDictionary.getElement(skillAuraRow[2]), 
                                                      skillAuraRow[3], 
                                                      skillAuraRow[4], 
                                                      Integer.parseInt(skillAuraRow[5]), 
                                                      Integer.parseInt(skillAuraRow[6]), 
                                                      Integer.parseInt(skillAuraRow[7])
                                                     );
                        this.hand.add(skillAuraCard);
                        System.out.println(skillAuraCard.getNama());
                        return skillAuraCard;

                    }
                }
            }
        }
        return new Land("NOT FOUND", Element.AIR, "NOT FOUND", "NOT FOUND");

    }

    public void putToField(int indexHand, boolean isAttackPosition) {

        Card handGet = hand.get(indexHand);
        if (handGet.getType().equals("Monster")) {
            if (numberOfMonstersOnField < maxMonstersOnField) {
                for (int i = 0; i < maxMonstersOnField; i++) {
                    if (monsterOnField[i] == null) {
                        monsterOnField[i] = new SummonedMonster(((Monster)handGet), isAttackPosition);
                        numberOfMonstersOnField++;
                    }
                }
            }
        } else if (handGet.getType().equals("Land")) {
            addLandMaxPowerByElement(handGet.getElement());
        }
        hand.remove(indexHand);

    }

    

}
