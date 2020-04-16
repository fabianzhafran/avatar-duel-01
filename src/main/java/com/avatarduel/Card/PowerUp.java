package com.avatarduel.Card;

public class PowerUp extends Skill {

    public PowerUp(String name, Element element, String path, String desc, int pow) {
        super(name, element, path, desc, pow);
    }
    
    @Override
    public String getSkillType() {
        return "Power Up";
    }

}
