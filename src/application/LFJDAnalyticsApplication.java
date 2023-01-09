package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadListener;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logic.consumer.Consumer;
import logic.datapreloader.DataPreLoader;

import java.io.IOException;
import java.time.LocalDate;

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

//        Consumer consumer = new Consumer();
//        consumer.getData(LocalDate.now(), LocalDate.now().minusDays(365));
        DataPreLoader dataLoader = new DataPreLoader();
        dataLoader.getData();
        createScenes();
        createSecondaryStage(primaryStage);
        loginScene.getRoot().requestFocus();
        primaryStage.setMaximized(true);
        primaryStage.setScene(loginScene);
        primaryStage.getIcons().add(new Image("file:src/resources/img/logo6.png"));
        primaryStage.show();
    }

    private void createScenes() throws IOException {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

        startLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Start-view.fxml"));
        homeLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Home-view.fxml"));
        loginLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Login-view.fxml"));
        analyseLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Analyse-view.fxml"));
        trendLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Trend-view.fxml"));
        reportLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Report-view.fxml"));

        startScene = new Scene(startLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        homeScene = new Scene(homeLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        loginScene = new Scene(loginLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        analyseScene = new Scene(analyseLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        trendScene = new Scene(trendLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        reportScene = new Scene(reportLoader.load(), screenSize.getWidth(), screenSize.getHeight());
    }

    private void createSecondaryStage(Stage primaryStage) throws IOException {
        secondaryStage = new Stage();

        datePickerLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/secondary-stage/DatePicker-view.fxml"));
        articlePickerLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/secondary-stage/ArticlePicker-view.fxml"));

        datePickerScene = new Scene(datePickerLoader.load());
        articlePickerScene = new Scene(articlePickerLoader.load());

        secondaryStage.setResizable(false);
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
