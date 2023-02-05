package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.DataPreLoader.DataPreLoader;
import logic.consumer.Consumer;

import java.io.IOException;

public class LFJDAnalyticsApplication extends Application {

    public static Consumer consumer;
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
    public static Scene aboutUsScene;
    public static FXMLLoader analyseLoader;
    public static FXMLLoader startLoader;
    public static FXMLLoader homeLoader;
    public static FXMLLoader loginLoader;
    public static FXMLLoader trendLoader;
    public static FXMLLoader aboutUsLoader;

    @Override
    public void start(Stage primaryStage) throws Exception {
        DataPreLoader dataPreLoader = new DataPreLoader();
        dataPreLoader.start();

        setMainStage(primaryStage);
        createScenes();
        createSecondaryStage(primaryStage);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(false);
        primaryStage.setScene(startScene);

        primaryStage.getIcons().add(new Image("file:src/resources/img/logo6.png"));
        primaryStage.show();
    }


    private void createScenes() throws IOException {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        loginLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Login-view.fxml"));
        loginScene = new Scene(loginLoader.load(), screenSize.getWidth(), screenSize.getHeight() - 23);
        startLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Start-view.fxml"));
        startScene = new Scene(startLoader.load(), screenSize.getWidth(), screenSize.getHeight());
        homeLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Home-view.fxml"));
        homeScene = new Scene(homeLoader.load(), screenSize.getWidth(), screenSize.getHeight() - 23);
        analyseLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Analyse-view.fxml"));
        analyseScene = new Scene(analyseLoader.load(), screenSize.getWidth(), screenSize.getHeight() - 23);
        trendLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/Trend-view.fxml"));
        trendScene = new Scene(trendLoader.load(), screenSize.getWidth(), screenSize.getHeight() - 23);
        aboutUsLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/primary-stage/AboutUs-view.fxml"));
        aboutUsScene = new Scene(aboutUsLoader.load(), screenSize.getWidth(), screenSize.getHeight() - 23);
    }

    private void createSecondaryStage(Stage primaryStage) throws IOException {
        secondaryStage = new Stage();

        datePickerLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/secondary-stage/DatePicker-view.fxml"));
        articlePickerLoader = new FXMLLoader(LFJDAnalyticsApplication.class.getResource("/view/secondary-stage/ArticlePicker-view.fxml"));

        datePickerScene = new Scene(datePickerLoader.load());
        articlePickerScene = new Scene(articlePickerLoader.load());

        secondaryStage.setResizable(false);
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

    public static Consumer getConsumer() {
        return consumer;
    }

    public static Stage getSecondaryStage() {
        return secondaryStage;
    }

    public static Scene getDatePickerScene() {
        return datePickerScene;
    }

    public static FXMLLoader getDatePickerLoader() {
        return datePickerLoader;
    }

    public static FXMLLoader getArticlePickerLoader() {
        return articlePickerLoader;
    }

    public static Scene getArticlePickerScene() {
        return articlePickerScene;
    }

    public static Scene getStartScene() {
        return startScene;
    }

    public static Scene getLoginScene() {
        return loginScene;
    }

    public static Scene getHomeScene() {
        return homeScene;
    }

    public static Scene getAnalyseScene() {
        return analyseScene;
    }

    public static Scene getTrendScene() {
        return trendScene;
    }

    public static Scene getAboutUsScene() {
        return aboutUsScene;
    }

    public static FXMLLoader getAnalyseLoader() {
        return analyseLoader;
    }

    public static FXMLLoader getStartLoader() {
        return startLoader;
    }

    public static FXMLLoader getHomeLoader() {
        return homeLoader;
    }

    public static FXMLLoader getLoginLoader() {
        return loginLoader;
    }

    public static FXMLLoader getTrendLoader() {
        return trendLoader;
    }

    public static FXMLLoader getAboutUsLoader() {
        return aboutUsLoader;
    }
}

