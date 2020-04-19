package com.avatarduel.card;

public class Aura extends Skill {

    private int attack;
    private int defense;

    public Aura(String name, Element elemen, String desc, String path, int pow, int atk, int def) {
        super(name, elemen, desc, path, pow);
        this.attack = atk;
        this.defense = def;
    }

    @Override
    public String getSkillType() {
        return "Aura";
    }

    public int getAttackValue() {
        return attack;
    }

    public int getDefenseValue() {
        return defense;
    }

}
