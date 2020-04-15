package com.avatarduel.Phase;

import com.avatarduel.Player;

public class DrawPhase extends Phase {
    
    public DrawPhase(Player aPlayer) {
        super(aPlayer, TurnPhase.DRAWPHASE);
    }

    @Override
    public void playPhase() {
        // TODO draw card

        // TODO Reset player's power

    }

}