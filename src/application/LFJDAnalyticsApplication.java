package application;

import controller.AnalyseController;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.consumer.Consumer;
import logic.datapreloader.DataPreLoader;

import java.io.IOException;

public class LFJDAnalyticsApplication extends Application {

    private static Stage mainStage;
    public static Stage secondaryStage;
    public static Scene datePickerScene;
    public static FXMLLoader periodPickerLoader;
    public static FXMLLoader datePickerLoader;
    public static FXMLLoader articlePickerLoader;
    public static Scene articlePickerScene;
    public static Scene periodPickerScene;
    public static Scene startScene;
    public static Scene loginScene;
    public static Scene homeScene;
    public static Scene analyseScene;
    public static Scene trendScene;
    public static Scene reportScene;
    public static FXMLLoader analyseLoader;
    public static FXMLLoader startLoader;
    public static FXMLLoader homeLoader;
    public static FXMLLoader loginLoader;
    public static FXMLLoader trendLoader;
    public static FXMLLoader reportLoader;

    @Override
    public void start(Stage primaryStage) throws Exception {
        setMainStage(primaryStage);
        Consumer consumer = new Consumer();
        consumer.start();
//        DataPreLoader dataLoader = new DataPreLoader();
//        dataLoader.start();
        createScenes();
        createSecondaryStage(primaryStage);
        loginScene.getRoot().requestFocus();
        primaryStage.setScene(loginScene);
        primaryStage.getIcons().add(new Image("file:src/resources/img/logo6.png"));
        primaryStage.show();
    }

    private void createScenes() throws IOException {
        startLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Start-view.fxml"));
        homeLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Home-view.fxml"));
        loginLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Login-view.fxml"));
        analyseLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Analyse-view.fxml"));
        trendLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Trend-view.fxml"));
        reportLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Report-view.fxml"));

        startScene = new Scene(startLoader.load());
        homeScene = new Scene(homeLoader.load());
        loginScene = new Scene(loginLoader.load());
        analyseScene = new Scene(analyseLoader.load());
        trendScene = new Scene(trendLoader.load());
        reportScene = new Scene(reportLoader.load());
    }

    private void createSecondaryStage(Stage primaryStage) throws IOException {
        secondaryStage = new Stage();

        datePickerLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/secondary-stage/DatePicker-view.fxml"));
        articlePickerLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/secondary-stage/ArticlePicker-view.fxml"));
        periodPickerLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/secondary-stage/PeriodPicker-view.fxml"));

        datePickerScene = new Scene(datePickerLoader.load());
        articlePickerScene = new Scene(articlePickerLoader.load());
        periodPickerScene = new Scene(periodPickerLoader.load());

        secondaryStage.initStyle(StageStyle.UNDECORATED);
        secondaryStage.initModality(Modality.WINDOW_MODAL);
        secondaryStage.initOwner(primaryStage);
    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage) {
        LFJDAnalyticsApplication.mainStage = mainStage;
    }
}
