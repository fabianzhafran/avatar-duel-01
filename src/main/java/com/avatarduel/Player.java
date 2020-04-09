package com.avatarduel;

import com.avatarduel.Card.*;
import com.avatarduel.model.*;
import java.util.*;

public class Player {
    protected String namePlayer;
    protected int hp;
    protected ArrayList<Card> deck;
    protected ArrayList<Card> hand;
    protected ArrayList<SummonedCharacter> characterOnField;
    protected ArrayList<Skill> skillOnField;
    protected ArrayList<Element> land;

    public Player(String namePlayer) {
        this.hp = 100;
        this.namePlayer = namePlayer;
        deck = new ArrayList<Card>();
        /*  take cards from deck to hand.
            card generation method currently known.
            will be implemented ASAP.
        */
        hand = new ArrayList<Card>();
        for (int counter = 0; counter < 7; counter++) {
            takeCardFromTop(deck, hand);
        } // takes 7 cards from deck to hand
        characterOnField = new ArrayList<SummonedCharacter>(); // game starts with empty arena
        skillOnField = new ArrayList<Skill>(); // see above comment
        land = new ArrayList<Element>(); // no land powers at start
    }

    public void takeCardFromTop(ArrayList<Card> moveFrom, ArrayList<Card> moveTo) {
        int totalCards = moveFrom.size();
        Card getCard = moveFrom.remove(totalCards - 1);
        moveTo.add(getCard);
    }
}
