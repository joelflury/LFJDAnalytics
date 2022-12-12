package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LFJDAnalyticsApplication  extends Application {

    public static Scene startScene;
    public static Scene loginScene;
    public static Scene homeScene;
    public static Scene analyseScene;
    public static Scene trendScene;
    public static Scene reportScene;


    @Override
    public void start(Stage primaryStage) throws Exception {
        createScenes();
        loginScene.getRoot().requestFocus();
        primaryStage.setScene(startScene);
        primaryStage.getIcons().add(new Image("file:src/resources/img/logo6.png"));
        primaryStage.show();
    }

    private void createScenes() throws IOException {
        FXMLLoader startLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/Start-view.fxml"));
        FXMLLoader homeLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/Home-view.fxml"));
        FXMLLoader loginLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/Login-view.fxml"));
        FXMLLoader analyseLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/Analyse-view.fxml"));
        FXMLLoader trendLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/Trend-view.fxml"));
        FXMLLoader reportLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/Report-view.fxml"));
        startScene = new Scene(startLoader.load());
        homeScene = new Scene(homeLoader.load());
        loginScene = new Scene(loginLoader.load());
        analyseScene = new Scene(analyseLoader.load());
        trendScene = new Scene(trendLoader.load());
        reportScene = new Scene(reportLoader.load());
    }

}
