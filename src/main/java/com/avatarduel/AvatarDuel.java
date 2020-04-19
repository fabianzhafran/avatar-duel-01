package com.avatarduel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AvatarDuel extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    Text text = new Text();
    text.setText("Loading...");
    text.setX(50);
    text.setY(50);

    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("com.avatarduel.fxml/Gameplay.fxml")));

    Scene scene = new Scene(root, 1280, 720);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();
    System.out.println("\n\n\n\n\n\n ______                                             ");
    System.out.println(" _________        .---\"\"\"      \"\"\"---.              ");
    System.out.println(":______.-':      :  .--------------.  :             ");
    System.out.println("| ______  |      | :                : |             ");
    System.out.println("|:______B:|      | |  Debug  logs : | |             ");
    System.out.println("|:______B:|      | |  Debug  logs : | |             ");
    System.out.println("|:______B:|      | |  Debug  logs : | |             ");
    System.out.println("|         |      | |  Debug  logs : | |             ");
    System.out.println("|:_____:  |      | |                | |             ");
    System.out.println("|    ==   |      | :                : |             ");
    System.out.println("|       O |      :  '--------------'  :             ");
    System.out.println("|       o |      :'---...______...---'              ");
    System.out.println("|       o |-._.-i___/'             \\._              ");
    System.out.println("|'-.____o_|   '-.   '-...______...-'  `-._          ");
    System.out.println(":_________:      `.____________________   `-.___.-. ");
    System.out.println("                 .'.eeeeeeeeeeeeeeeeee.'.      :___:");
    System.out.println("    fsc        .'.eeeeeeeeeeeeeeeeeeeeee.'.         ");
    System.out.println("              :____________________________:        ");
    
    try {
      text.setText("Avatar Duel!");
      // Player player1 = new Player("Player 1");
      // player1.draw(); player1.draw(); player1.draw(); player1.draw(); player1.draw(); 
      // player1.printCardsOnHand();
      // player1.putToField(0, true);
      // player1.putToField(0, true);
      // player1.putToField(0, true);
      // player1.putToField(0, true);
      // player1.putToField(0, true);
      // player1.putToField(0, true);
      // player1.printCardsOnHand();
      // player1.printMonsterCardsOnField();
      // player1.printSkillCardsOnField();
    //  Player player2 = new Player("Player 2");
    //  ListOfCards temp = new ListOfCards();
    //  for (String[] s : temp.listOfLandCards) {
    //    System.out.println(s[0]);
    //  }
    //  System.out.println("Draw on main");
    //  player1.draw();
    } catch (Exception e) {
      text.setText("Failed to load cards: " + e);
    }
  }

  public static void main(String[] args) {
    launch();
  }
}