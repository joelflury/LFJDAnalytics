package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LFJDAnalyticsApplication  extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader homeLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/Home-view.fxml"));
        primaryStage.setScene(new Scene(homeLoader.load()));



        primaryStage.show();
    }
}
