package com.avatarduel.Card;

public class Skill extends Card {

    protected int power;

    public Skill(String name, Element element, String path, String desc, int pow) {
        this.name = name;
        this.element = element;
        this.imagePath = path;
        this.description = desc;
        this.power = pow;
    }

    public String getType() {
        return "Skill";
    }

    public int getPowerValue() {
        return power;
    }

    public String getSkillType() {
        return "Nothing";
    }

}
