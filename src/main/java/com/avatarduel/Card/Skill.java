package com.avatarduel.card;

abstract class Skill extends Card {

    protected int power;

    public int getPowerValue() {
        return power;
    }

    abstract void ActivateCardEff();

}
