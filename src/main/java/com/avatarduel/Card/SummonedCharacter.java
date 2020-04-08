package com.avatarduel.card;

class SummonedCharacter implements ISummoned {
    
    Character character;
    boolean isFaceUp;
    boolean isAttackPos;


    public SummonedCharacter(Character chara, boolean faceUp, boolean attackPos) {
        this.character = chara;
        this.isFaceUp = faceUp;
        this.isAttackPos = attackPos;
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
 

}
