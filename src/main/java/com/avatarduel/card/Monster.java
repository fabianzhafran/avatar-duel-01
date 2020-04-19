package com.avatarduel.Card;

public class Monster extends Card {

    protected int attack;
    protected int defense;
    protected int power;

    public Monster(String name, Element Element, String desc, String path, int atk, int def, int pow) {
        this.name = name;
        this.element = Element;
        this.description = desc;
        this.imagePath = path;
        this.attack = atk;
        this.defense = def;
        this.power = pow;
    }

    public String getType() {
        return "Monster";
    }

    public int getAttackValue() {
        return attack;
    }

    public int getDefenseValue() {
        return defense;
    }

    public int getPowerValue() {
        return power;
    }

}
