abstract class Skill extends Kartu {

    protected int power;

    public int getPowerValue() {
        return power;
    }

    abstract void ActivateCardEff();

}