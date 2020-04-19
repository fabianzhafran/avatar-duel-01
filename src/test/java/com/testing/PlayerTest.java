package com.testing;

import com.avatarduel.Player;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

public class PlayerTest {

    private Player playerTest;

    @Test
    public void testPlayerDraw() {
        playerTest = new Player("GOBLOG");
        Assert.assertEquals(playerTest.getNumberOfMonstersOnField(), 0);
    }

}