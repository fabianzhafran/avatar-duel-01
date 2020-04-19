package com.avatarduel.card;

/**
 * Card is an abstract class that stores the info of cards
 * used in the Avatar Duel game.
 * This class is inherited by Monster, Skill, and Land class.
 *
 * @author K01_01_IF2210
 */
public abstract class Card {

    protected String name;
    protected Element element;
    protected String description;
    protected String imagePath;

    /**
     * Creates a new Card from given parameters
     * @param name name of the card
     * @param element element of the card
     * @param description description of the card
     * @param imagePath imagePath of the card
     */
    public Card(String name, Element element, String description, String imagePath) {
        this.name = name;
        this.element = element;
        this.description = description;
        this.imagePath = imagePath;
    }
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
