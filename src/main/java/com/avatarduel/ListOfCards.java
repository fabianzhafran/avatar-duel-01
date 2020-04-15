package com.avatarduel;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;

import com.avatarduel.Card.*;
import com.avatarduel.util.CsvReader;

public class ListOfCards {
    
    public ArrayList<String[]> listOfLandCards = ListOfCards.loadCards("land");
    public ArrayList<String[]> listOfMonsterCards = ListOfCards.loadCards("character");
    public ArrayList<String[]> listOfSkillAuraCards = ListOfCards.loadCards("skill_aura");

    public ListOfCards() {
        listOfLandCards = this.loadCards("land");
        listOfMonsterCards = this.loadCards("character");
        listOfSkillAuraCards = this.loadCards("skill_aura");
    }

    public static ArrayList<String[]> loadCards(String csvName) {

        ArrayList<String[]> cardRows = new ArrayList<String[]>();
        Path currentRelativePath = Paths.get("");
        String characterPath = currentRelativePath.toAbsolutePath().toString() + "/src/main/resources/com/avatarduel/card/data/" + csvName + ".csv";
        CsvReader cardReader = new CsvReader(characterPath);
        try {
            cardRows = cardReader.readCsv();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return cardRows;
        
    }

    public int getTotalLength() {
        return this.listOfLandCards.size() + this.listOfMonsterCards.size() + this.listOfSkillAuraCards.size();
    }

}