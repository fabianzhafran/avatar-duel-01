package com.avatarduel.controller;

/** Player 1's Field GUI Controller. Inherits fieldController.
 * 
 * @author K01_01_IF2210
 */

public class P1FieldController extends FieldController {

    /** Initializes the arena and hand for player 1. 
     */
    @Override
    public void init(GameplayController g) {
        super.init(g);
        this.arenaController.init(this, true);
        player.setNamePlayer("Player 1");
        playerLabel.setText(player.getNamePlayer());
    }

}
