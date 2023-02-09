package controller.primary;

import application.LFJDAnalyticsApplication;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.consumer.Consumer;
import util.Util;

public class LoginController {
    @FXML
    protected HBox hBoxRoot;
    @FXML
    protected TextField tfUsername;
    @FXML
    protected PasswordField tfPassword;
    @FXML
    protected Label lblLoginError;
    @FXML
    protected ImageView ivLoginLogo;

    @FXML
    protected void handleKeyPressed(KeyEvent key) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.2), lblLoginError);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(3);
        if (key.getCode().equals(KeyCode.ENTER)) {
            if (!tfUsername.getText().equals("") && !tfPassword.getText().equals("")) {
                Consumer consumer = new Consumer();
                switch (consumer.getUserdata(tfUsername.getText(), Util.hashStringSHA515(tfPassword.getText()))) {
                    case 0, 1:
                        lblLoginError.setText("Wrong User Data");
                        tfPassword.setText("");
                        fadeTransition.play();
                        break;
                    case 2:
                        Stage stage = (Stage) hBoxRoot.getScene().getWindow();
                        stage.setScene(LFJDAnalyticsApplication.getHomeScene());
                        HomeController homeController = LFJDAnalyticsApplication.getHomeLoader().getController();
                        homeController.start();
                        break;
                    case 3:
                        lblLoginError.setText("Connection to Server failed");
                        tfPassword.setText("");
                        fadeTransition.play();
                        break;
                }
            } else {
                lblLoginError.setText("Please enter Username and Password");
                fadeTransition.play();
            }
        }
    }
}
