package com.testing;

import static org.junit.Assert.assertEquals;
import com.avatarduel.Player;
import com.avatarduel.Card.*;
import org.junit.Test;

public class CardTest {

    @Test
    public void testCreateMonster() {
        Monster dummy = new Monster("Fabian", ELEMENT.WATER, "Jon ketiduran", 10, 10, 10);
        assertEquals("Name must be Fabian", "Fabian", dummy.getName());
        assertEquals("Attack value must be 10", 10, dummy.getAttackValue());
        assertEquals("Defense value must be 10", 10, dummy.getDefenseValue());
        assertEquals("Power value must be 10", 10, dummy.getPowerValue());

    }

    @Test
    public void testCreateLand() {
        Land dummy = new Land("Bandung", ELEMENT.FIRE, "Bandung Lautan Api", "/usr/bin/local/python");
        assertEquals("Name must be Bandung", "Bandung", dummy.getName());
        assertEquals("Element must be Fire", ELEMENT.FIRE, dummy.getElement());
        assertEquals("Description must be Bandung Lautan Api", "Bandung Lautan Api", dummy.getDescription());
    }

    @Test
    public void testCreateAura() {
        Aura dummy = new Aura("Negative", ELEMENT.AIR, "Aura negatif gan", "/usr/bin/avatar", 10, 10, 10);
        assertEquals("Name must be Negative", "Negative", dummy.getName());
        assertEquals("Element must be Air", ELEMENT.AIR, dummy.getElement());
        assertEquals("Attack value must be 10", 10, dummy.getAttackValue());
        assertEquals("Power value must be 10", 10, dummy.getPowerValue());

    }

    @Test
    public void testCreateDestroy() {
        Destroy dummy = new Destroy("noob slayer", ELEMENT.FIRE, "noob slayer", "root", 10);
        assertEquals("Name must be noob slayer", "noob slayer", dummy.getName());
        assertEquals("Element must be Fire", ELEMENT.FIRE, dummy.getElement());
        assertEquals("Path must be root", "root", dummy.getImagePath());

    }

    @Test
    public void testCreatePowerUp() {
        PowerUp dummy = new PowerUp("nanda", ELEMENT.EARTH, "fakboi if", "anandayulizar", 10);
        assertEquals("Name must be nanda", "nanda", dummy.getName());
        assertEquals("desc must be fakboi if", "fakboi if", dummy.getDescription());
        assertEquals("Power must be 10", 10, dummy.getPowerValue());

    }
    
}