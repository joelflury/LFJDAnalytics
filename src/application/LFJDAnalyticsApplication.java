package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LFJDAnalyticsApplication extends Application {

    public static Stage datePickerStage;
    public static Scene startScene;
    public static Scene loginScene;
    public static Scene homeScene;
    public static Scene analyseScene;
    public static Scene trendScene;
    public static Scene reportScene;

    public static FXMLLoader analyseLoader;

    @Override
    public void start(Stage primaryStage) throws Exception {
        createScenes();
        createDatePicketStage(primaryStage);
        loginScene.getRoot().requestFocus();
        primaryStage.setScene(loginScene);
        primaryStage.getIcons().add(new Image("file:src/resources/img/logo6.png"));
        primaryStage.show();
    }

    private void createScenes() throws IOException {
        FXMLLoader startLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Start-view.fxml"));
        FXMLLoader homeLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Home-view.fxml"));
        FXMLLoader loginLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Login-view.fxml"));
        analyseLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Analyse-view.fxml"));
        FXMLLoader trendLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Trend-view.fxml"));
        FXMLLoader reportLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Report-view.fxml"));
        startScene = new Scene(startLoader.load());
        homeScene = new Scene(homeLoader.load());
        loginScene = new Scene(loginLoader.load());
        analyseScene = new Scene(analyseLoader.load());
        analyseScene.setUserData(analyseLoader);
        trendScene = new Scene(trendLoader.load());
        reportScene = new Scene(reportLoader.load());
    }

    private void createDatePicketStage(Stage primaryStage) throws IOException {
        FXMLLoader datePickerLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/datepicker-stage/DatePicker-view.fxml"));
        datePickerStage = new Stage();
        datePickerStage.setScene(new Scene(datePickerLoader.load()));
        datePickerStage.initStyle(StageStyle.UNDECORATED);
        datePickerStage.initModality(Modality.WINDOW_MODAL);
        datePickerStage.initOwner(primaryStage);
    }

}
