package com.avatarduel.card;

/**
 * Monster is a class that is used to store
 * the information about a monster in the
 * Avatar Duel Game.
 * Monster class is used to battle in the Avatar Duel Game.
 * This class inherits Card class.
 *
 * @author K01_01_IF2210
 */

public class Monster extends Card {
    protected int attack;
    protected int defense;
    protected int power;

    /**
     * Creates a new Monster Card from given parameters using parent constructor
     * @param name name of the card
     * @param element element of the card
     * @param desc description of the card
     * @param path imagePath of the card
     * @param atk attack point of the card
     * @param def defense point of the card
     * @param pow power point of the card
     */
    public Monster(String name, Element element, String desc, String path, int atk, int def, int pow) {
        super(name, element, desc, path);
        this.attack = atk;
        this.defense = def;
        this.power = pow;
    }

    /**
     * @return "Monster"
     */
    public String getType() {
        return "Monster";
    }

    /**
     * Get the attack value of the monster
     * @return Monster's attack
     */
    public int getAttackValue() {
        return attack;
    }

    /**
     * Get the defense value of the monster
     * @return Monster's defense
     */
    public int getDefenseValue() {
        return defense;
    }

    /**
     * Get the power value of the monster
     * @return Monster's power
     */
    public int getPowerValue() {
        return power;
    }

}
