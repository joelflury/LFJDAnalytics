package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logic.consumer.Consumer;

import java.io.IOException;
import java.time.LocalDate;

public class LFJDAnalyticsApplication extends Application {

    private static Stage mainStage;
    public static Stage secondaryStage;
    public static Scene datePickerScene;
    public static FXMLLoader datePickerLoader;
    public static FXMLLoader articlePickerLoader;
    public static Scene articlePickerScene;
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

        Consumer consumer = new Consumer();
        consumer.getArticleData();
        consumer.getSalesData(LocalDate.now().minusDays(31), LocalDate.now());

        setMainStage(primaryStage);
        createScenes();
        createSecondaryStage(primaryStage);
        loginScene.getRoot().requestFocus();
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.setScene(homeScene);
        primaryStage.getIcons().add(new Image("file:src/resources/img/logo6.png"));
        primaryStage.show();
    }

    private void createScenes() throws IOException {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        loginLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Login-view.fxml"));
        loginScene = new Scene(loginLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        startLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Start-view.fxml"));
        startScene = new Scene(startLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        homeLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Home-view.fxml"));
        homeScene = new Scene(homeLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        analyseLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Analyse-view.fxml"));
        analyseScene = new Scene(analyseLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        trendLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Trend-view.fxml"));
        trendScene = new Scene(trendLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        reportLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Report-view.fxml"));
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
