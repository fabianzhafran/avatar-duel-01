package com.avatarduel.controller;

/** Player 2's Field GUI Controller. Inherits fieldController.
 * 
 * @author K01_01_IF2210
 */

public class P2FieldController extends FieldController {

    /** Initializes the arena and hand for player 2.
     */
    @Override
    public void init(GameplayController g) {
        super.init(g);
        this.arenaController.init(this, false);
        player.setNamePlayer("Player 2");
        playerLabel.setText(player.getNamePlayer());
    }

}
