package com.avatarduel.card;

/**
 * Aura is a class that is used to store information
 * about an aura skill card in the Avatar Duel Game.
 * Aura is a skill that boost the chosen monster atk/def
 * for a fixed amount.
 * This class inherits the Skill class.
 *
 * @author K01_01_IF2210
 */
public class Aura extends Skill {

    /**
     * The attack buff given
     */
    private int attack;
    /**
     * The defense buff given
     */
    private int defense;

    /**
     * Creates a new Aura Card from given parameters using parent constructor
     * @param name name of the card
     * @param element element of the card
     * @param desc description of the card
     * @param path imagePath of the card
     * @param pow power point of the card
     * @param atk attack buff of the card
     * @param def defense buff of the card
     */
    public Aura(String name, Element element, String desc, String path, int pow, int atk, int def) {
        super(name, element, desc, path, pow);
        this.attack = atk;
        this.defense = def;
    }

    /**
     * Override method from Skill class
     * @return "Aura"
     */
    @Override
    public String getSkillType() {
        return "Aura";
    }

    /**
     * Get the attack buff of the monster
     * @return Card's attack buff
     */
    public int getAttackValue() {
        return attack;
    }

    /**
     * Get the defense buff of the monster
     * @return Card's defense buff
     */
    public int getDefenseValue() {
        return defense;
    }

}
