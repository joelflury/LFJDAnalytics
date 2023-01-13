package controller;

import application.LFJDAnalyticsApplication;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartController {

    @FXML
    protected ImageView lfjdLogo;

    @FXML
    public void initialize() {
        TranslateTransition translateTrans = new TranslateTransition(Duration.seconds(2), lfjdLogo);
        FadeTransition fadeTrans = new FadeTransition(Duration.seconds(2), lfjdLogo);

        fadeTrans.setOnFinished(event -> translateTrans.play());
        translateTrans.setOnFinished(event -> {
//            Stage stage = (Stage) lfjdLogo.getScene().getWindow();
//            stage.setScene(LFJDAnalyticsApplication.loginScene);
        });

        fadeTrans.setFromValue(0);
        fadeTrans.setToValue(1);

        translateTrans.setFromY(lfjdLogo.getY());
        translateTrans.setToY(-284);

        fadeTrans.setAutoReverse(true);
        fadeTrans.setCycleCount(3);

        // Play the Animation
        fadeTrans.play();
    }
}
