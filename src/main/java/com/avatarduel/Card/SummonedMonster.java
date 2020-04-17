package com.avatarduel.Card;

import java.util.ArrayList;
import com.avatarduel.Player;

public class SummonedMonster implements ISummoned {

    private Monster monster;
    boolean isAttackPos;

    int buffAtk;
    int buffDef;
    boolean piercieng;

    ArrayList<Integer> skillLinked;

    public Monster getMonster() {
        return monster;
    }

    public SummonedMonster(Monster mons, boolean attackPos) {
        this.monster = mons;
        this.isAttackPos = attackPos;
        resetBuff();
        piercieng = false;
        skillLinked = new ArrayList<Integer>();
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
    
    public void setPierce() {
        piercieng = true;
    }
    
    public void resetPiercing() {
        piercieng = false;
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
    