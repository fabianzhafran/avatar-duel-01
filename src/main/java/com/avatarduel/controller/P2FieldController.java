package com.avatarduel.controller;

public class P2FieldController extends FieldController {

    @Override
    public void init(GameplayController g) {
        super.init(g);
        this.arenaController.init(this, false);
        player.setNamePlayer("Player 2");
        playerLabel.setText(player.getNamePlayer());
    }

}
