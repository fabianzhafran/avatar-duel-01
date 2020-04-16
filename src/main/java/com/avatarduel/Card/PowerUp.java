package com.avatarduel.Card;

import java.util.ArrayList;

public class PowerUp extends Skill {

    public PowerUp(String name, Element element, String path, String desc, int pow) {
        this.name = name;
        this.element = element;
        this.imagePath = path;
        this.description = desc;
        this.power = pow;
    }

    @Override
    public void ActivateCardEff() {

    }

    //    @Override
//    public void ActivateCardEff(Player user, Player enemy) {
//        // PowerUp Skill Implementation here. Chosen Character on field has Piercing Attack.
//
//        int userPower = user.getPowerByElement(element);
//
//        // If user has not enough power to use skill
//        if (this.power > userPower) {
//            return;
//        }
//
//        ArrayList<SummonedMonster> userMonsterOnField = user.getMonsterOnField();
//
//        // Anggap kita punya nama monster target kita, mungkin ini dapetnya dari GUI
//
//        String cardName; // Harus ada implementasi untuk target character
//
//        for (SummonedMonster c : userMonsterOnField) {
//            if (c.getName().equals(cardName)) {
//                c.setPiercing();
//            }
//        }
//
//        // Next step update arrayList setelah penggunaan skill
//
//
//        // Update the arrayList to player
//        user.setMonsterOnField(userCharacterOnField);
//
//    }

}
