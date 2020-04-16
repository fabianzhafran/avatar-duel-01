package com.avatarduel.Card;

public class Destroy extends Skill {
    
    public Destroy(String name, Element element, String path, String desc, int pow) {
        super(name, element, path, desc, pow);
    }

    public String getSkillType() {
        return "Destroy";
    }

    public void ActivateCardEff() {
        // Destroy Skill Implementation here. Destroy chosen Character card on the field. 
    }
    
}
