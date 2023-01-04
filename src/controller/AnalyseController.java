package controller;

import application.LFJDAnalyticsApplication;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modell.Article;

import java.time.LocalDate;
import java.util.List;

public class AnalyseController {
    @FXML
    protected Button btnHome;
    @FXML
    protected Button btnTrend;
    @FXML
    protected Button btnReports;
    @FXML
    protected Label lblTimePeriod;
    @FXML
    protected Label lblArticles;
    private static StringProperty lblArticlesTextProperty = new SimpleStringProperty("Choose\nProducts");
    private static StringProperty lblFreeTimePeriodTextProperty = new SimpleStringProperty("Choose a\nPeriod");

    private static LocalDate fromDate;
    private static LocalDate toDate;
    private static List<Article> articleList;

    @FXML
    public void initialize(){
        lblArticles.textProperty().bind(lblArticlesTextProperty);
        lblTimePeriod.textProperty().bind(lblFreeTimePeriodTextProperty);
    }

    @FXML
    public void btnHomeClick() {
        Stage stage = (Stage)btnHome.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.homeScene);
    }

    @FXML
    public void btnTrendClick(){
        Stage stage = (Stage)btnTrend.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.trendScene);
    }

    @FXML
    public void btnReportsClick(){
        Stage stage = (Stage)btnReports.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.reportScene);
    }

    public static void setLblArticlesTextProperty(String testString) {
        AnalyseController.lblArticlesTextProperty.set(testString);
    }

    public static void setLblFreeTimePeriodTextProperty(String lblFreeTimePeriodTextProperty) {
        AnalyseController.lblFreeTimePeriodTextProperty.set(lblFreeTimePeriodTextProperty);
    }

    public static void checkIfAllDataPresent(){
        if (fromDate != null && toDate != null && articleList.size() != 0){
            fillAnalysisChart();
        }
    }

    private static void fillAnalysisChart() {
    }

    public static void setFromDate(LocalDate fromDate) {
        AnalyseController.fromDate = fromDate;
    }

    public static void setToDate(LocalDate toDate) {
        AnalyseController.toDate = toDate;
    }

    public static void setArticleList(List<Article> articleList) {
        AnalyseController.articleList = articleList;
    }

    public void btnFreePeriodClick() {
        LFJDAnalyticsApplication.secondaryStage.setScene(LFJDAnalyticsApplication.datePickerScene);
        LFJDAnalyticsApplication.secondaryStage.show();
    }
    public void btnChooseArticlesClick() {
        ((ArticlePickerController)LFJDAnalyticsApplication.articlePickerLoader.getController()).createCheckBoxes();
        LFJDAnalyticsApplication.secondaryStage.setScene(LFJDAnalyticsApplication.articlePickerScene);
        LFJDAnalyticsApplication.secondaryStage.show();
    }
}
