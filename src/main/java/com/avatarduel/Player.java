package com.avatarduel;

import com.avatarduel.Card.*;
import com.avatarduel.model.*;
import java.util.*;

public class Player {
    protected String namePlayer;
    protected int hp;
    protected LinkedList<Card> deck;
    protected LinkedList<Card> hand;
    protected LinkedList<SummonedCharacter> characterOnField;
    protected LinkedList<Skill> skillOnField;
    protected LinkedList<Element> land;

    public Player(String namePlayer) {
        this.hp = 100;
        this.namePlayer = namePlayer;
        deck = new LinkedList<Card>();
        /*  take cards from deck to hand.
            card generation method currently unknown.
            will be implemented ASAP.
        */
        hand = new LinkedList<Card>();
        for (int counter = 0; counter < 7; counter++) {
            takeCardFromTop(deck, hand);
        } // takes 7 cards from deck to hand
        characterOnField = new LinkedList<SummonedCharacter>(); // game starts with empty arena
        skillOnField = new LinkedList<Skill>(); // see above comment
        land = new LinkedList<Element>(); // no land powers at start
    }

    public void takeCardFromTop(LinkedList<Card> moveFrom, LinkedList<Card> moveTo) {
        int totalCards = moveFrom.size();
        Card getCard = moveFrom.remove(totalCards - 1);
        moveTo.add(getCard);
    }
}
