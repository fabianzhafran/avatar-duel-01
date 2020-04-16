package com.avatarduel.Card;

public class SummonedMonster implements ISummoned {
    
    Monster character;
    boolean isFaceUp;
    boolean isAttackPos;

    int buffAtk;
    int buffDef;
    boolean piercieng;

    public SummonedMonster(Monster chara, boolean faceUp, boolean attackPos) {
        this.character = chara;
        this.isFaceUp = faceUp;
        this.isAttackPos = attackPos;
        resetBuff();
        piercieng = false;
    }

    public boolean flip() {
        if (!(this.isFaceUp)) {
            this.isFaceUp = !(this.isFaceUp);
            return true;
        }
        else {
            return false;
        }
    }

    public void rotate() {
        this.isAttackPos = !(this.isAttackPos);
    }

    public int getPositionValue() {
        if (this.isAttackPos) {
            return this.character.getAttackValue();
        }
        else {
            return this.character.getDefenseValue();
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
