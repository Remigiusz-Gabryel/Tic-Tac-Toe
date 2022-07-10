package com.example.ticktacktoe;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class BackGroundImageViewApplier {
    private String file;
    private BackgroundImage backgroundImage;
    private Background background;

    public BackGroundImageViewApplier(String imagePath) {
        this.file = "file:" + imagePath;
        this.backgroundImage = new BackgroundImage(new Image(file),
                BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(690,690,false,false,false,false));
    }

    public Background getBackground() {
        return background = new Background(backgroundImage);
    }
}
