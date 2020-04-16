package com.avatarduel.Card;

import java.util.ArrayList;
import com.avatarduel.Player;

public class Aura extends Skill {

    private int attack;
    private int defense;


    public Aura(String name, Element elemen, String desc, String path, int pow, int atk, int def) {
        this.name = name;
        this.element = elemen;
        this.description = desc;
        this.imagePath = path;
        this.attack = atk;
        this.defense = def;
        this.power = pow;
    }

    public int getAttackValue() {
        return attack;
    }

    public int getDefenseValue() {
        return defense;
    }

    public int getPowerValue() {
        return power;
    }

    @Override
    public void ActivateCardEff() {

    }

    //    @Override
//    public void ActivateCardEff(Player user, Player enemy) {
        // Aura Skill Implementation here. Add Attack and Defense points to certain Character

//        int userPower = user.getPowerByElement(element);
//
//        // If user has not enough power to use skill
//        if (this.power > userPower) {
//            return;
//        }
//
//
//        ArrayList<SummonedMonster> userMonsterOnField = user.getMonsterOnField();
//        ArrayList<SummonedMonster> enemyMonsterOnField = enemy.getMonsterOnField();
//        ArrayList<Skill> userSkillOnField = user.getSkillOnField();
//
//        // Mungkin dapetin kartu target dari gui, gua ambil misalnya pake nama kartu
//        String cardName;
//        int castOn;
//        // Lets say we have the card name
//        // CastOn = 1 kalo dipake ke kartu user, caston = 2 kalo dipake ke kartu enemy
//
//        // Jika aura digunakan pada character user
//        if (castOn == 1) {
//
//            for (SummonedMonster c : userMonsterOnField) {
//                if (c.getName().equals(cardName)) {
//                    c.setBuff(this.attack, this.defense);
//                }
//
//            }
//
//        }
//
//        // Jika aura digunakan pada character musuh
//
//        else if (castOn == 2) {
//
//            for (SummonedMonster c : enemyMonsterOnField) {
//                if (c.getName().equals(cardName)) {
//                    c.setBuff(this.attack, this.defense);
//                }
//
//            }
//
//        }
//
//        userSkillOnField.add(this);
//
//        // Next step update semua arrayList setelah penggunakan skill Aura
//
//
//        // Update the arrayList to user and enemy
//        if (castOn == 1) {
//            user.setMonsterOnField(userMonsterOnField);
//        }
//        else if (castOn == 2) {
//            enemy.setMonsterOnField(enemyMonsterOnField);
//        }

//    }
    

}
