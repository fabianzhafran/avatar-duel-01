package com.avatarduel.Card;

import java.util.ArrayList;
import com.avatarduel.Player;

public abstract class Skill extends Card {

    protected int power;

    public int getPowerValue() {
        return power;
    }

    public void remove(Player player) {

        String cardName = this.getName();
        ArrayList<Skill> skillOnField = player.getSkillOnField();

        skillOnField.removeIf(skill -> skill.getName().equals(cardName));

        player.setSkillOnField(skillOnField);
        

    }

    abstract void ActivateCardEff();

}
