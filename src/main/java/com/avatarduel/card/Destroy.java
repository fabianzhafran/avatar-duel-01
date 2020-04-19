package com.avatarduel.card;

/**
 * Destroy is a class that is used to store information
 * about a aura skill card in the Avatar Duel Game.
 * Destroy is a skill that can destroy one Monster
 * that the opponent owns.
 * This class inherits the Skill class.
 *
 * @author K01_01_IF2210
 */
public class Destroy extends Skill {

    /**
     * Creates a new Destroy Card from given parameters using parent constructor
     * @param name name of the card
     * @param element element of the card
     * @param desc description of the card
     * @param path imagePath of the card
     * @param pow power point of the card
     */
    public Destroy(String name, Element element, String desc, String path, int pow) {
        super(name, element, desc, path, pow);
    }

    /**
     * Override method from Skill class
     * @return "Destroy"
     */
    @Override
    public String getSkillType() {
        return "Destroy";
    }
}
