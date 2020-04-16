package com.avatarduel.Card;

public abstract class Card {

    protected String name;
    protected Element element;
    protected String description;
    protected String imagePath;

    public String getName() {
        return name;
    }

    public Element getElement() {
        return element;
    }

    public String getDeskripsi() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

}
