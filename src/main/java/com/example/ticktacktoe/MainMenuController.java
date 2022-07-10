package com.example.ticktacktoe;

import com.example.ticktacktoe.controllers.GameController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {


    @FXML
    AnchorPane anchorPane;
    SceneController sceneController;



    @FXML
    protected void playerVsAI(ActionEvent actionEvent) throws IOException {
        sceneController.switchToGameScene(actionEvent, true);
    }

    @FXML
    protected void versusMode(ActionEvent actionEvent) throws IOException {
        sceneController.switchToGameScene(actionEvent, false);
    }

    @FXML
    protected void exit() {
        Platform.exit();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BackgroundImage backgroundImage = new BackgroundImage(new Image("file:images/background.gif"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(690, 690, false, false, false, false));
        anchorPane.setBackground(new Background(backgroundImage));
//        imageView.setImage(new Image("file:background.gif"));
        sceneController = new SceneController();
    }


}
