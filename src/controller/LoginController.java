package controller;

import application.LFJDAnalyticsApplication;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    HBox hBoxRoot;
    @FXML
    protected void handleKeyPressed(KeyEvent key){
        System.out.println("asad");
        if (key.getCode().equals(KeyCode.ENTER)){
            System.out.println("asf");
            Stage stage = (Stage)hBoxRoot.getScene().getWindow();
            stage.setScene(LFJDAnalyticsApplication.homeScene);
        }
    }
}
