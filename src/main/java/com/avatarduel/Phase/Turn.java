package com.avatarduel.Phase;

import java.util.*;

import com.avatarduel.Player;

public class Turn {
    private Player activePlayer;
    private final List<Phase> phases = new ArrayList<Phase>();

    public Turn(Player aPlayer) {
        this.activePlayer = aPlayer;
        this.phases.add(new DrawPhase(this.activePlayer));
        this.phases.add(new MainPhase(this.activePlayer, 1));
        this.phases.add(new BattlePhase(this.activePlayer));
        this.phases.add(new MainPhase(this.activePlayer, 2));
        this.phases.add(new EndPhase(this.activePlayer));

    }

    public void playTurn(){
        for (Phase phase : phases) {
            phase.playPhase();
        }
    }
}