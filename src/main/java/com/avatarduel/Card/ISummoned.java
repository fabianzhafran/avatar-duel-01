package com.avatarduel.Card;

public interface ISummoned {

    // To Flip Summoned Character,can only be activated when the card is summoned face down
    public boolean flip();

    // To change battle position, from attack to def and vice versa.
    public void rotate();

    // To get the battle position of a SummonedCharacter, return Attack if is in AttackPos, otherwise, return Defense
    public int getPositionValue();

}
