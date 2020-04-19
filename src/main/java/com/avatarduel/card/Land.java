package com.avatarduel.card;

/**
 * Land is a class that is used to store
 * information about a land card in the
 * Avatar Duel Game. Land card is used to increase
 * an element power of a player that is used to
 * summon Monster or activate Skill.
 * This class inherits Card class.
 *
 * @author K01_01_IF2210
 */
public class Land extends Card {

    public Land(String name, Element element, String desc, String path) {
        super(name, element, desc, path);
    }

    public String getType() {
        return "Land";
    }

}
