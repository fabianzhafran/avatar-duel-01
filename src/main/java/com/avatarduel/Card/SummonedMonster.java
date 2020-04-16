package com.avatarduel.Card;

public class SummonedMonster implements ISummoned {
    
    Monster monster;
    boolean isAttackPos;

    int buffAtk;
    int buffDef;
    boolean piercieng;

    public SummonedMonster(Monster mons, boolean attackPos) {
        this.monster = mons;
        this.isAttackPos = attackPos;
        resetBuff();
        piercieng = false;
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

}
