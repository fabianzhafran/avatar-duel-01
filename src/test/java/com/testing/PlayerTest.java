package com.testing;

import static org.junit.Assert.assertEquals;
import com.avatarduel.Player;
import com.avatarduel.Card.*;
import org.junit.Test;

import java.util.ArrayList;

public class PlayerTest {

    @Test
    public void testDraw() {

        Player dummy = new Player("Test");
        int cardInHandBeforeDraw=dummy.getHand().size();
        dummy.draw();
        assertEquals("card must be 1 after draw", cardInHandBeforeDraw+1, dummy.getHand().size());

        for (int i = 1; i <= 10; i++) {
            dummy.draw();
        }

        assertEquals("card max in hand is 9", 9, dummy.getHand().size());

    }

    @Test
    public void testPutToField() {
        Player dummy = new Player("Testtting");

        for (int i = 0; i <= 9; i++) {
            dummy.draw();
        }

        ArrayList<Card> hand = dummy.getHand();
        int i = 0;
        Card handGet = hand.get(i);
        while (!(handGet.getType().equals("Monster"))) {
            i++;
            handGet = hand.get(i);
        }

        dummy.putToField(i, true);

        assertEquals("there is 1 monster on field", 1, dummy.getNumberOfMonstersOnField());

    }

    @Test
    public void testRemoveMonsterOnField() {

        Player dummy = new Player("Testtting");

        for (int i = 0; i <= 9; i++) {
        dummy.draw();
        }

        ArrayList<Card> hand = dummy.getHand();
        int i = 0;
        Card handGet = hand.get(i);
        while (!(handGet.getType().equals("Monster"))) {
        i++;
        handGet = hand.get(i);
        }

        dummy.putToField(i, true);

        assertEquals("there is 1 monster on field", 1, dummy.getNumberOfMonstersOnField());

        dummy.removeMonsterOnField(0);

        assertEquals("must be no monster on field", 0, dummy.getNumberOfMonstersOnField());



    }

    @Test
    public void testRemoveSkillOnField(){

        Player dummy=new Player("Testtting");

        for(int i=0;i<=9;i++){
        dummy.draw();
        }

        ArrayList<Card> hand=dummy.getHand();
        int i=0;
        Card handGet=hand.get(i);
        while(!(handGet.getType().equals("Skill"))){
        i++;
        handGet=hand.get(i);
        }

        dummy.putToField(i,true);

        assertEquals("there is 1 skill on field",1,dummy.getNumberOfSkillsOnField());

        dummy.removeSkillOnField(0);

        assertEquals("must be no monster on field",0,dummy.getNumberOfSkillsOnField());
    }

    @Test
    public void testAttack() {

        Player dummy = new Player("Testtting");
        Player dummyEnemy = new Player("Testtting");

        for (int i = 0; i <= 9; i++) {
            dummy.draw();
            dummyEnemy.draw();
        }

        ArrayList<Card> hand = dummy.getHand();
        ArrayList<Card> handEnemy = dummyEnemy.getHand();

        // Summon Monster for player
        int i = 0;
        Card handGet = hand.get(i);
        while (!(handGet.getType().equals("Monster"))) {
            i++;
            handGet = hand.get(i);
        }

        // Summon Monster for enemy
        int j = 0;
        Card handEnemyGet = handEnemy.get(j);
        while (!(handEnemyGet.getType().equals("Monster"))) {
            j++;
            handEnemyGet = handEnemy.get(j);
        }

        dummy.putToField(i, true);
        dummyEnemy.putToField(j, true);

        SummonedMonster monster = (dummy.getMonsterOnField())[0];

        int atk = monster.getAttackValue();
        int atkEnemy = monster.getAttackValue();

        int diff = atk - atkEnemy;

        dummy.attack(0, 0, dummyEnemy);

        int hpEnemyAfterBattle = dummyEnemy.getHp();

        assertEquals("enemy hp must be reduced", (80 - diff), hpEnemyAfterBattle);


    }

    @Test
    public void testAura() {

        Player dummy = new Player("Testtting");

        for (int i = 0; i <= 9; i++) {
            dummy.draw();
        }

        ArrayList<Card> hand = dummy.getHand();
        int i = 0;
        Card handGet = hand.get(i);
        while (!(handGet.getType().equals("Monster"))) {
            i++;
            handGet = hand.get(i);
        }

        dummy.putToField(i, true);

        int j = 0;
        handGet = hand.get(j);
        while (!(handGet).getType().equals("Skill")) {
            if (((Skill)handGet).getSkillType().equals("Aura")) {
                handGet = hand.get(j);
            } else {
                j++;
            }
        }

        Aura cardAura = (Aura)handGet;
        int auraAtk = cardAura.getAttackValue();
        int auraDef = cardAura.getDefenseValue();

        dummy.putToField(j, true);

        SummonedMonster monster = (dummy.getMonsterOnField())[0];

        int atk = monster.getAttackValue();
        int def = monster.getDefenseValue();

        dummy.activateAuraSkill(0, 0);

        assertEquals("atk harusnya berubah sesuai dengan aura", (atk + auraAtk), monster.getAttackValue());
        assertEquals("def harusnya berubah sesuai dengan aura", (def + auraDef), monster.getDefenseValue());

    }

    @Test
    public void testDestroy() {

        Player dummy = new Player("Testtting");
        Player dummyEnemy = new Player("Testtting");

        for (int i = 0; i <= 9; i++) {
            dummy.draw();
            dummyEnemy.draw();
        }

        ArrayList<Card> handEnemy = dummyEnemy.getHand();
        int i = 0;
        Card handGetEnemy = handEnemy.get(i);
        while (!(handGetEnemy.getType().equals("Monster"))) {
            i++;
            handGetEnemy = handEnemy.get(i);
        }

        dummyEnemy.putToField(i, true);

        int j = 0;
        ArrayList<Card> hand = dummy.getHand();
        Card handGet = hand.get(j);
        while (!((Destroy)handGet).getSkillType().equals("Destroy")) {
            j++;
            handGet = hand.get(j);
        }

        dummy.putToField(j, true);

        dummy.activateDestroySkill(true, 0, 0, dummyEnemy);

        assertEquals("monster musuh ada 0", 0, dummyEnemy.getNumberOfMonstersOnField());


    }

    @Test
    public void testPowerUp() {
        Player dummy = new Player("Testtting");

        for (int i = 0; i <= 9; i++) {
            dummy.draw();
        }

        ArrayList<Card> hand = dummy.getHand();
        int i = 0;
        Card handGet = hand.get(i);
        while (!(handGet.getType().equals("Monster"))) {
            i++;
            handGet = hand.get(i);
        }

        dummy.putToField(i, true);

        int j = 0;
        handGet = hand.get(j);
        while (!((Aura)handGet).getSkillType().equals("Power Up")) {
            j++;
            handGet = hand.get(j);

        }

        dummy.putToField(j, true);

        SummonedMonster monster = (dummy.getMonsterOnField())[0];

        dummy.activatePowerUpSkill(0, 0);

        assertEquals("piercing harusnya aktif", true, monster.getPierce());

    }


}