package com.avatarduel.card;

import java.util.ArrayList;
import com.avatarduel.Player;

/**
 * SummonedMonster is a Monster class that has been summoned
 * to the field. It implements the ISummoned interface.
 * It contains info about its current attack on the field and
 * its abilities that he gains from the skill linked to it.
 */
public class SummonedMonster implements ISummoned {
    /**
     * The monster that is summoned
     */
    private Monster monster;
    /**
     * true if monster is in attack position,
     * false if monster is in defense position
     */
    boolean isAttackPos;
    /**
     * true if monster has attack in the turn,
     * false if not
     */
    private boolean hasAttacked;
    /**
     * true if monster has been summoned in the same turn,
     * false if not
     */
    private boolean isJustSummoned;

    /**
     * The attack buff gain from skill
     */
    private int buffAtk;
    /**
     * The defense buff gain from skill
     */
    private int buffDef;
    /**
     * true if monster has a piercing ability
     */
    private boolean piercing;

    /**
     * List of integer in a Player class's list
     * of Skill that is on the field.
     */
    ArrayList<Integer> skillLinked;

    /**
     * @param mons
     * @param attackPos
     */
    public SummonedMonster(Monster mons, boolean attackPos) {
        this.monster = mons;
        this.isAttackPos = attackPos;
        resetBuff();
        piercing = false;
        skillLinked = new ArrayList<Integer>();
        buffDef = 0;
        buffAtk = 0;
        hasAttacked = false;
        isJustSummoned = true;
    }

    /**
     * @return monster without additional information from skill
     */
    public Monster getMonster() {
        return monster;
    }

    /**
     * @return Attack that has been given additional buff
     */
    public int getAttackValue() {
        return monster.getAttackValue() + buffAtk;
    }

    /**
     * @return Defense that has been given additional buff
     */
    public int getDefenseValue() {
        return monster.getDefenseValue() + buffDef;
    }

    /**
     * @return true if in attack position, false if in defense
     */
    public boolean getIsAttackPosition() {
        return isAttackPos;
    }

    /**
     * @return true if just summoned in this turn, false if not
     */
    public boolean getIsJustSummoned() {
        return isJustSummoned;
    }

    /**
     * set isJustSummoned to false
     */
    public void setIsJustSummonedToFalse() {
        isJustSummoned = false;
    }

    /**
     * Change the battle position of the monster
     */
    public void rotate() {
        this.isAttackPos = !(this.isAttackPos);
    }

    /**
     * @return total attack value if in attack position,
     * total defense value if in defense position
     */
    public int getPositionValue() {
        if (this.isAttackPos) {
            return this.getAttackValue();
        }
        else {
            return this.getDefenseValue();
        }
    }

    /**
     * Adds buff to monster
     * @param atk
     * @param def
     */
    public void addBuff(int atk, int def) {
        buffAtk += atk;
        buffDef += def;
    }

    /**
     * Subtract buff from monster
     * @param atk
     * @param def
     */
    public void subtractBuff(int atk, int def) {
        buffAtk -= atk;
        buffDef -= def;
    }

    /**
     * Set buff of monster
     * @param atk
     * @param def
     */
    public void setBuff(int atk, int def) {
        buffAtk = atk;
        buffDef = def;
    }

    /**
     * Reset monster's buff to 0
     */
    public void resetBuff() {
        buffAtk = 0;
        buffDef = 0;
    }

    /**
     * @return true if monster is equipped by a PowerUp Skill
     */
    public boolean getPierce() {
        return piercing;
    }

    /**
     * Give Monster piercing ability
     */
    public void setPierce() {
        piercing = true;
    }

    /**
     * Remove piercing ability
     */
    public void resetPiercing() {
        piercing = false;
    }

    /**
     * @return true if monster has attacked this turn
     */
    public boolean getHasAttacked() {
        return hasAttacked;
    }

    /**
     * set hasAttacked boolean
     * @param value
     */
    public void setHasAttacked(boolean value) {
        hasAttacked = value;
    }

    /**
     * Add a skill to equip to this card
     * @param skillOnFieldIndex index of List of Skill that is owned by a Player Class
     */
    public void registerSkill(int skillOnFieldIndex) {
        skillLinked.add(skillOnFieldIndex);
    }

    /**
     * @return Skills linked to the Monster
     */
    public ArrayList<Integer> getSkillLinked() {
        return skillLinked;
    }

    /**
     * Remove a skill that is equipped to a monster
     * @param skillOnFieldIndex
     */
    public void removeSkill(int skillOnFieldIndex) {
        for (int i = 0; i < skillLinked.size(); i++) {
            if (skillLinked.get(i) == skillOnFieldIndex) {
                skillLinked.remove(i);
                break;
            }
        }
    }
        
}
    