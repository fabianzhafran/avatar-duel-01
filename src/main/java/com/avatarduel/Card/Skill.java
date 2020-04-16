package com.avatarduel.Card;

public abstract class Skill extends Card {

    protected int power;

    public String getType() {
        return "Skill";
    }

    public int getPowerValue() {
        return power;
    }

    abstract void ActivateCardEff();

}
