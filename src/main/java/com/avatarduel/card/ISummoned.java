package com.avatarduel.card;

/**
 * ISummoned is an interface that is implemented
 * to a Monster that has been summoned to the field.
 */
public interface ISummoned {

    /**
     *  To change battle position, from attack to def and vice versa.
     */
    public void rotate();

    /**
     * To get the battle position of a SummonedCharacter, return Attack if is in AttackPos, otherwise, return Defense
     * @return att value if in attack position, def value if in defense position
     */
    public int getPositionValue();

}
