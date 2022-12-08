package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LFJDAnalyticsApplication  extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader homeLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/Trend-view.fxml"));
        primaryStage.setScene(new Scene(homeLoader.load()));
        primaryStage.getIcons().add(new Image("file:src/resources/img/logo6.png"));



        primaryStage.show();
    }
}
