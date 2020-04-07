class Aura extends Skill {

    private int attack;
    private int defense;


    public Aura(String nama, Element elemen, String desc, String path, int pow, int atk, int def) {
        this.nama = nama;
        this.element = element;
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

    public void ActivateCardEff() {
        // Aura Skill Implementation here. Add Attack and Defense points to certain Character
    }
    

}