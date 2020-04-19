package com.avatarduel.Card;

import java.util.ArrayList;
import com.avatarduel.Player;

public class SummonedMonster implements ISummoned {

    private Monster monster;
    boolean isAttackPos;
    private boolean hasAttacked;
    private boolean isJustSummoned;

    int buffAtk;
    int buffDef;
    boolean piercieng;

    ArrayList<Integer> skillLinked;

    
    public SummonedMonster(Monster mons, boolean attackPos) {
        this.monster = mons;
        this.isAttackPos = attackPos;
        resetBuff();
        piercieng = false;
        skillLinked = new ArrayList<Integer>();
        buffDef = 0;
        buffAtk = 0;
        hasAttacked = false;
        isJustSummoned = true;
    }

    public Monster getMonster() {
        return monster;
    }

    public int getAttackValue() {
        return monster.getAttackValue() + buffAtk;
    }

    public int getDefenseValue() {
        return monster.getDefenseValue() + buffDef;
    }

    public boolean getIsAttackPosition() {
        return isAttackPos;
    }

    public boolean getIsJustSummoned() {
        return isJustSummoned;
    }

    public void setIsJustSummonedToFalse() {
        isJustSummoned = false;
    }
    
    public void rotate() {
        this.isAttackPos = !(this.isAttackPos);
    }
    
    public int getPositionValue() {
        if (this.isAttackPos) {
            return this.monster.getAttackValue();
        }
        else {
            return this.monster.getDefenseValue();
        }
    }
    
    public void addBuff(int atk, int def) {
        buffAtk += atk;
        buffDef += def;
    }

    public void subtractBuff(int atk, int def) {
        buffAtk -= atk;
        buffDef -= def;
    }
    
    public void setBuff(int atk, int def) {
        buffAtk = atk;
        buffDef = def;
    }
    
    public void resetBuff() {
        buffAtk = 0;
        buffDef = 0;
    }
    
    public boolean getPierce() {
        return piercieng;
    }

    public void setPierce() {
        piercieng = true;
    }
    
    public void resetPiercing() {
        piercieng = false;
    }

    public boolean getHasAttacked() {
        return hasAttacked;
    }

    public void setHasAttacked(boolean value) {
        hasAttacked = value;
    }

    public void registerSkill(int skillOnFieldIndex) {
        skillLinked.add(skillOnFieldIndex);
    }
    
    public ArrayList<Integer> getSkillLinked() {
        return skillLinked;
    }
    
    public void removeSkill(int skillOnFieldIndex) {
        for (int i = 0; i < skillLinked.size(); i++) {
            if (skillLinked.get(i) == skillOnFieldIndex) {
                skillLinked.remove(i);
                break;
            }
        }
    }
    
    public void remove(Player player) {
        // Remove this card and all skills linked to this monster card
        //        String targetCardName = this.getName();
        //
        //        ArrayList<SummonedMonster> monsterOnField = player.getMonsterOnField();
        //        monsterOnField.removeIf(SummonedMonster -> SummonedMonster.getName().equals(targetCardName));
        //        player.setMonsterOnField(monsterOnField);
        //
        //        // Remove Linked Skills
        //        for (Skill s : skillLinked) {
            //            s.remove(player);
            //        }
            
        }
        
    }
    