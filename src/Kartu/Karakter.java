class Karakter extends Kartu {

    protected int attack;
    protected int defense;
    protected int power;

    public Karakter(String nama, Element Element, String desc, String path, int atk, int def, int pow) {
        this.nama = nama;
        this.Element = Element;
        this.description = desc;
        this.pathGambar = path;
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

}