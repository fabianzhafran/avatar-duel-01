package com.avatarduel.card;

public class Skill extends Card {

    protected int power;
    protected boolean isUsed;

    public Skill(String name, Element element, String desc, String path, int pow) {
        super(name, element, desc, path);
        this.power = pow;
        this.isUsed = false;
    }

    public boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsedToTrue() {
        isUsed = true;
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
