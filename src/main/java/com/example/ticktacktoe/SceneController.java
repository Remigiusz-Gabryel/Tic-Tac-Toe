package com.example.ticktacktoe;


import com.example.ticktacktoe.controllers.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Parent root;
    private Stage stage;
    private Scene scene;
    private TicTacToeGame ticTacToeGame;


    public void switchToGameScene(ActionEvent event, boolean soloGameMode) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game-view.fxml"));
        root = loader.load();
        GameController gameController = loader.getController();

        ticTacToeGame = new TicTacToeGame(new Alert(Alert.AlertType.INFORMATION));
        ticTacToeGame.setGameMode(soloGameMode);
        gameController.initializeGameBoard(ticTacToeGame);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
        var eventSource = (Node)event.getSource();
        var stage1 = (Stage) eventSource.getScene().getWindow();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEndGameScene(ActionEvent event,TicTacToeGame ticTacToeGame) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("end-game-view.fxml"));
        root = loader.load();

        EndGameController gameController = loader.getController();
        gameController.getCurrentGameSettings(ticTacToeGame);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEndGame(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("end-game-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
