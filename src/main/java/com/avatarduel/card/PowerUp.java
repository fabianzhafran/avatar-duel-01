package com.avatarduel.card;

/**
 * PowerUp is a class that is used to store information
 * about a power up skill card in the Avatar Duel Game.
 * PowerUp is a skill that can a player's own monster
 * piercing abilities (inflict damage when attacking
 * defense monster).
 * This class inherits the Skill class.
 *
 * @author K01_01_IF2210
 */
public class PowerUp extends Skill {

    /**
     * Creates a new Destroy Card from given parameters using parent constructor
     * @param name name of the card
     * @param element element of the card
     * @param desc description of the card
     * @param path imagePath of the card
     * @param pow power point of the card
     */
    public PowerUp(String name, Element element, String desc, String path, int pow) {
        super(name, element, desc, path, pow);
    }

    /**
     * Override method from Skill class
     * @return "Power Up"
     */
    @Override
    public String getSkillType() {
        return "Power Up";
    }

}
