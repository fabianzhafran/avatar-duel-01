package com.avatarduel.Phase;

import com.avatarduel.Player;

public abstract class Phase {
    protected TurnPhase pType;
    protected Player activePlayer;

    public Phase(Player aPlayer, TurnPhase pType) {
        this.activePlayer = aPlayer;
        this.pType = pType;
    }

    public TurnPhase getType() {
        return this.pType;
    }

    abstract public void playPhase();
}