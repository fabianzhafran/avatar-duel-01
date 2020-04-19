package com.avatarduel.Card;

public class Skill extends Card {

    protected int power;
    protected boolean isUsed;

    public Skill(Skill other) {
        name = other.getName();
        element = other.getElement();
        imagePath = other.getImagePath();
        description = other.getDescription();
        power = other.getPowerValue();
        isUsed = other.getIsUsed();
        System.out.println(name);
    }

    public Skill(String name, Element element, String desc, String path, int pow) {
        this.name = name;
        this.element = element;
        this.imagePath = path;
        this.description = desc;
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
