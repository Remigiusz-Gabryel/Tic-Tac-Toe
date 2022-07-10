package com.example.ticktacktoe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class TickTackToeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("game-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(TickTackToeApplication.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Tic Tack Toe");
        stage.getIcons().add(new Image("file:images/icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    }

    public static void start(String[] args) {
        launch();
    }
}