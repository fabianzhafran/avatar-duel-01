package com.avatarduel.Card;

public abstract class Card {

    protected String name;
    protected Element element;
    protected String description;
    protected String imagePath;

    /**
     * Get the Name of the Card
     * @return Name of the Card
     */
    public String getName() {
        return name;
    }

    /**
     * Get the Element of the Card
     * @return Element of the Card
     */
    public Element getElement() {
        return element;
    }

    /**
     * Get the Description of the Card
     * @return Description of the Card
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the Image Path of the Card
     * @return Image Path of the Card
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Get the Type of the Card
     * @return Type of the Card (Monster, Skill, Land) in the form of String
     */
    abstract public String getType();

}
