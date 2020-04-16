package com.avatarduel.Card;

import java.util.ArrayList;
import com.avatarduel;

public class Destroy extends Skill {
    
    public Destroy(String name, Element element, String path, String desc, int pow) {
        this.name = name;
        this.element = element;
        this.imagePath = path;
        this.description = desc;
        this.power = pow;
    }

    @Override
    public void ActivateCardEff(Player user, Player enemy) {
        
        // Destroy Skill Implementation here. Destroy chosen Character card on the field. 
        
        // Checking user's current power
        int userPower = user.getPowerByElement(element);
        
        // If user has not enough power to use skill
        if (this.power > userPower) {
            return;
        }

        ArrayList<SummonedMonster> enemyMonsterOnField = enemy.getCharacterOnField();
        ArrayList<Skill> enemySkillOnField = enemy.getSkillOnField();

        // Memilih kartu yang akan dihancurkan
        

        // Mungkin ini disambungin ke GUI ya, buat milih kartu apa yang mau dihancurin

        // Lets say
        String targetCardName; // Mungkin dipick lewat GUI, nama kartu yang maiu dihancurin
        int cardType;
        // cardType = 1 berarti destroy monster, cardType = 2 berarti destroy skill

        if (cardType == 1) {
            
            //enemyMonsterOnField.removeIf(SummonedMonster -> SummonedMonster.getName().equals(targetCardName));
            for (SummonedMonster mons : enemyMonsterOnField) {
                if (monst.getName().equals(targetCardName)) { // Masih matching by Name
                    mons.remove(enemy);
                    break;
                }
            }

        }
        else if (cardType == 2) {

            //enemySkillOnField.removeIf(Skill -> Skill.getName().equals(targetCardName));
            for (Skill s : enemySkillOnField) {
                if (s.getName().equals(targetCardName)) { // Masih matching by Name
                    s.remove(enemy);
                    break;
                }
            }

        }

        
        // Next step harusnya update isi arrayList setelah digunakan efek kartu


        // Update arrayList buat enemy
        if (cardType == 1) {
            enemy.setMonsterOnField(enemyMonsterOnField);
        }
        else if (cardType == 2) {
            enemy.setSkillOnField(enemySkillOnField);
        }


        

    }
    
}
