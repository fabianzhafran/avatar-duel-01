package com.avatarduel.controller;

public class P1FieldController extends FieldController {
    @Override
    public void init(GameplayController g) {
        super.init(g);
        this.arenaController.init(this, true);
        player.setNamePlayer("Player 1");
        playerLabel.setText(player.getNamePlayer());
    }
}
