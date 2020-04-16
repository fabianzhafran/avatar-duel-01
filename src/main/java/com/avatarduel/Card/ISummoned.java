//package com.avatarduel.Card;

public interface ISummoned {

    // To change battle position, from attack to def and vice versa.
    public void rotate();

    // To get the battle position of a SummonedCharacter, return Attack if is in AttackPos, otherwise, return Defense
    public int getPositionValue();

}
