class PowerUp extends Skill {

    public PowerUp(String name, Element element, String path, String desc, int pow) {
        this.name = name;
        this.element = element;
        this.pathGambar = path;
        this.description = desc;
        this.power = pow;
    }
    
    public void ActivateCardEff() {
        // PowerUp Skill Implementation here. Chosen Character on field has Piercing Attack.
    }

}