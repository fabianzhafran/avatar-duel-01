package com.avatarduel;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import com.avatarduel.Card.*;
import com.avatarduel.util.CsvReader;

public class Player {

    protected String namePlayer;
    protected int hp;
    protected Stack<Integer> deck;
    protected Card[] hand;
    protected SummonedCharacter[] characterOnField;
    protected Skill[] skillOnField;
    protected ElementPower[] elementPower;

    public Player(String namePlayer) {

        this.namePlayer = namePlayer;
        hp = 80;
        deck = new Stack<Integer>();
        Random randomNumber = new Random();
        for (int i = 0; i < 50; i++) {
            this.deck.push(randomNumber.nextInt(90));
        }
        hand = new Card[7];
        characterOnField = new SummonedCharacter[6];
        skillOnField = new Skill[];
        elementPower = new ElementPower[4];
        System.out.println(deck.size());

    }

    public Card[] getHand() {
        return hand;
    }
    
    public SummonedCharacter[] getCharacterOnField() {
        return characterOnField;
    }
    
    public Skill[] getSkillOnField() {
        return skillOnField;
    }
    
    public ElementPower[] getElementPower() {
        return elementPower;
    }
    
    public void draw() {

        // System.out.println("~~ " + this.namePlayer + " Draw : ");
        ListOfCards listOfCards = new ListOfCards();
        boolean found = false;
        ElementDictionary elementDictionary = new ElementDictionary();
        if (deck.size() > 0) {
            int topCardId = deck.pop();
            // System.out.println("top card id : ");
            // System.out.println(topCardId);
            for (String[] landRow : listOfCards.listOfLandCards) {
                System.out.println(landRow[0]);
                if (topCardId == Integer.parseInt(landRow[0])) {
                        // System.out.println("~~ Found a land card.");
                        Land landCard = new Land(landRow[1], 
                                             elementDictionary.getElement(landRow[2]), 
                                             landRow[3], 
                                             landRow[4]
                                            );
                    this.hand.add(landCard);
                    System.out.println(landCard.getDeskripsi());
                    found = true;
                    break;
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
                        System.out.println(monsterCard.getDeskripsi());
                        found = true;
                        break;
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
                        System.out.println(skillAuraCard.getDeskripsi());
                        break;
                    }
                }
            }
        }
        
    }



}
