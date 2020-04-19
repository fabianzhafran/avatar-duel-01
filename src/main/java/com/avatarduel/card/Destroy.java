package com.avatarduel.card;

public class Destroy extends Skill {
    
    public Destroy(String name, Element element, String desc, String path, int pow) {
        super(name, element, desc, path, pow);
    }

    public String getSkillType() {
        return "Destroy";
    }
    
}
