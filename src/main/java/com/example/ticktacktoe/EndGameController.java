package com.example.ticktacktoe;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EndGameController implements Initializable {

    @FXML
    AnchorPane anchorPane;
    @FXML
    Label winnerLabel;
    @FXML
    VBox vBox;

    SceneController sceneController;
    private boolean gameMode;
    private BackGroundImageViewApplier applier;


    @FXML
    protected void onMainMenuButtonClick(ActionEvent event) throws IOException {
        sceneController.switchToMainMenu(event);
    }

    @FXML
    protected void onPlayAgainButtonClick(ActionEvent actionEvent) throws IOException {

        sceneController.switchToGameScene(actionEvent, gameMode);
//        sceneController.switchToGameScene(actionEvent);
    }

    @FXML
    protected void onExitButtonClick() {
        Platform.exit();
    }

    protected void getCurrentGameSettings(TicTacToeGame ticTacToeGame) {
        gameMode = ticTacToeGame.getGameMode();
        if (gameMode) {
            displayWinnerBackground(ticTacToeGame.getWinner());
        } else {
            applier = new BackGroundImageViewApplier("images/gg.gif");
            anchorPane.setBackground(applier.getBackground());
        }

    }
        // set new backgrounds, no need to create new object each time
    private void displayWinnerBackground(String winner) {

        vBox.setBackground((new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY))));
        if ("X".equals(winner)) {
            winnerLabel.setText(String.format("You won!"));
            applier = new BackGroundImageViewApplier("images/winner.gif");
            anchorPane.setBackground(applier.getBackground());
        } else if ("O".equals(winner)) {
            winnerLabel.setText(String.format("You Lost!"));
            applier = new BackGroundImageViewApplier("images/loser.gif");
            anchorPane.setBackground(applier.getBackground());
        } else {
            winnerLabel.setText(String.format("It's a Tie!"));
            applier = new BackGroundImageViewApplier("images/tie.gif");
            anchorPane.setBackground(applier.getBackground());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        applier = new BackGroundImageViewApplier("images/winner.gif");
//        anchorPane.setBackground(applier.getBackground());
        sceneController = new SceneController();
//        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:images/background.gif"),
//                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(690,690,false,false,false,false));
//        anchorPane.setBackground(new Background(backgroundImage));
    }
}
