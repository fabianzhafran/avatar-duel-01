package com.avatarduel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import com.avatarduel.Card.*;

public class AvatarDuel extends Application {
  private static final String LAND_CSV_FILE_PATH = "card/data/character.csv";

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

    
    try {
      System.out.println();
      System.out.println();
      System.out.println();
      System.out.println();
      text.setText("Avatar Duel!");
      Player player1 = new Player("Player 1");
      Player player2 = new Player("Player 2");
      ListOfCards temp = new ListOfCards();
      for (String[] s : temp.listOfLandCards) {
        System.out.println(s[0]);
      }
      player1.draw();
    } catch (Exception e) {
      text.setText("Failed to load cards: " + e);
    }
  }

  public static void main(String[] args) {
    launch();
  }
}