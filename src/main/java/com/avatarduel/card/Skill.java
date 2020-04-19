package com.avatarduel.card;

/**
 * Skill is a class that is used to store
 * information about a skill card in the Avatar
 * Duel Game. Skill can be inherited to three type which
 * are Aura, Destroy, and Power Up.
 * This class inherits Card class.
 *
 * @author K01_01_IF2210
 */
public class Skill extends Card {

    protected int power;
    /**
     * Boolean that states after a skill is activated whether that skill
     * has finished using its effect or not. For example: an aura skill already
     * chose the monster for it to equip to.
     */
    protected boolean isUsed;

    /**
     * Creates a new Skill Card from given parameters using parent constructor
     * @param name name of the card
     * @param element element of the card
     * @param desc description of the card
     * @param path imagePath of the card
     * @param pow power point of the card
     */
    public Skill(String name, Element element, String desc, String path, int pow) {
        super(name, element, desc, path);
        this.power = pow;
        this.isUsed = false;
    }

    /**
     * Get boolean if the card already been used or not
     * @return true if already been used, false if haven't been used
     */
    public boolean getIsUsed() {
        return isUsed;
    }

    /**
     * set the isUsed boolean to true
     */
    public void setIsUsedToTrue() {
        isUsed = true;
    }

    /**
     * @return "Skill"
     */
    public String getType() {
        return "Skill";
    }

    /**
     * Get the power value of the monster
     * @return Monster's power
     */
    public int getPowerValue() {
        return power;
    }

    /**
     * @return "Nothing" This is method is going to be overridden
     * when inherited
     */
    public String getSkillType() {
        return "Nothing";
    }

}
