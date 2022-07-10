package com.example.ticktacktoe.controllers;

import com.example.ticktacktoe.SceneController;
import com.example.ticktacktoe.TicTacToeGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private GridPane gridBoard;
    private TicTacToeGame ticTacToeGame;
    private SceneController sceneController;

    @FXML
    protected void onButtonClick(ActionEvent event) throws IOException {


        if (event.getSource() instanceof Button) {
            Button button = (Button) event.getSource();
            if (button.getText().isEmpty() && ticTacToeGame.getGameMode()) {
//                ticTacToeGame.playerMove(button);
//                ticTacToeGame.setGameMode(true);
                ticTacToeGame.aiMode(button);
            } else if (button.getText().isEmpty() && !ticTacToeGame.getGameMode()){
                ticTacToeGame.versusMode(button);
            }

            if (ticTacToeGame.isGameEnded()){
                sceneController.switchToEndGameScene(event,ticTacToeGame);
//                sceneController.switchToEndGame(event);
            }

        }
    }
    public void initializeGameBoard(TicTacToeGame ticTacToeGame){
        this.ticTacToeGame = ticTacToeGame;
        ticTacToeGame.setGridBoard(gridBoard);
    }
//    @FXML
//    private void receiveData(ActionEvent actionEvent){
//        Node node = (Node) actionEvent.getSource();
//        Stage stage = (Stage) node.getScene().getWindow();
//        ticTacToeGame = (TicTacToeGame) stage.getUserData();
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        ticTacToeGame = new TicTacToeGame(new Alert(Alert.AlertType.INFORMATION));
        sceneController = new SceneController();
//        ticTacToeGame.setGridBoard(gridBoard);
    }
}