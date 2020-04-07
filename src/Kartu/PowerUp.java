class PowerUp extends Skill {

    public PowerUp(String nama, Element element, String path, String desc, int pow) {
        this.nama = nama;
        this.element = element;
        this.pathGambar = path;
        this.description = desc;
        this.power = pow;
    }
    
    public void ActivateCardEff() {
        // PowerUp Skill Implementation here. Chosen Character on field has Piercing Attack.
    }

}