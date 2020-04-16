package com.avatarduel.Card;

import java.util.ArrayList;
import com.avatarduel.Player;

public class Aura extends Skill {

    private int attack;
    private int defense;

    public Aura(String name, Element elemen, String path, String desc, int pow, int atk, int def) {
        super(name, elemen, path, desc, pow);
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
