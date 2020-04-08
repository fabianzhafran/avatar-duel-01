class SummonedCharacter implements ISummoned {
    
    Character character;
    boolean isFaceUp;
    boolean isAttackPos;


    public SummonedCharacter(Character kar, boolean faceUp, boolean attackPos) {
        this.Character = kar;
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
            return this.Character.getAttackValue();
        }
        else {
            return this.Character.getDefenseValue();
        }
    }
 

}