class Destroy extends Skill {
    
    public Destroy(String nama, Element element, String path, String desc, int pow) {
        this.nama = nama;
        this.element = element;
        this.pathGambar = path;
        this.description = desc;
        this.power = pow;
    }

    public void ActivateCardEff() {
        // Destroy Skill Implementation here. Destroy chosen Character card on the field. 
    }
    
}