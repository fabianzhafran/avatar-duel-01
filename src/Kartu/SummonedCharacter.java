class SummonedCharacter implements ISummoned {
    
    Karakter karakter;
    boolean isFaceUp;
    boolean isAttackPos;


    public SummonedCharacter(Karakter kar, boolean faceUp, boolean attackPos) {
        this.karakter = kar;
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
            return this.karakter.getAttackValue();
        }
        else {
            return this.karakter.getDefenseValue();
        }
    }
 

}