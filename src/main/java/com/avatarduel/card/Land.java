package com.avatarduel.Card;

public class Land extends Card {

    public Land(String name, Element element, String desc, String path) {
        this.name = name;
        this.element = element;
        this.description = desc;
        this.imagePath = path;
    }

//    public void ActivateCardEff(Player user) {
//
//        user.addMaxPower(this.element);
//    }

    public String getType() {
        return "Land";

    }

}