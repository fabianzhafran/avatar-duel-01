package com.avatarduel.Card;

import java.util.ArrayList;
import com.avatarduel.Player;

public class SummonedMonster implements ISummoned {

    private Monster monster;
    boolean isAttackPos;

    int buffAtk;
    int buffDef;
    boolean piercieng;

    ArrayList<Skill> skillLinked;

    public Monster getMonster() {
        return monster;
    }

    public SummonedMonster(Monster mons, boolean attackPos) {
        this.monster = mons;
        this.isAttackPos = attackPos;
        resetBuff();
        piercieng = false;
        skillLinked = new ArrayList<Skill>();
    }

    public void rotate() {
        this.isAttackPos = !(this.isAttackPos);
    }

    public int getPositionValue() {
        if (this.isAttackPos) {
            return this.monster.getAttackValue();
        }
        else {
            return this.monster.getDefenseValue();
        }
    }
 
    public void setBuff(int atk, int def) {
        buffAtk = atk;
        buffDef = def;
    }

    public void resetBuff() {
        buffAtk = 0;
        buffDef = 0;
    }

    public void setPierce() {
        piercieng = true;
    }

    public void resetPiercing() {
        piercieng = false;
    }

    public void registerSkill(Skill skill) {
        skillLinked.add(skill);
    }

    public void remove(Player player) {
        // Remove this card and all skills linked to this monster card
//        String targetCardName = this.getName();
//
//        ArrayList<SummonedMonster> monsterOnField = player.getMonsterOnField();
//        monsterOnField.removeIf(SummonedMonster -> SummonedMonster.getName().equals(targetCardName));
//        player.setMonsterOnField(monsterOnField);
//
//        // Remove Linked Skills
//        for (Skill s : skillLinked) {
//            s.remove(player);
//        }

    }

}
