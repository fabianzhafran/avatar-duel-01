package com.avatarduel.Card;

public class Destroy extends Skill {
    
    public Destroy(String name, Element element, String path, String desc, int pow) {
        this.name = name;
        this.element = element;
        this.imagePath = path;
        this.description = desc;
        this.power = pow;
    }

    public void ActivateCardEff() {
        // Destroy Skill Implementation here. Destroy chosen Character card on the field. 
    }
    
}
