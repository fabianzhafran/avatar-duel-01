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
  }

  public static void main(String[] args) {
    launch();
  }
}