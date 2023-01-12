package controller;

import application.LFJDAnalyticsApplication;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logic.consumer.Consumer;
import modell.Article;
import modell.SalesPerDay;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @FXML
    protected LineChart lcAnalyse;
    @FXML
    protected Label lblAmount;
    @FXML
    protected Label lblGross;
    private static StringProperty lblArticlesTextProperty = new SimpleStringProperty("Choose\nProducts");
    private static StringProperty lblFreeTimePeriodTextProperty = new SimpleStringProperty("Choose a\nPeriod");
    private static LocalDate fromDate;
    private static LocalDate toDate;
    private static List<Article> chosenArticleList = new ArrayList<>();

    public static void setChosenArticleList(List<Article> articleListParam) {
        chosenArticleList = articleListParam;
    }

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

    public void checkIfAllDataPresent() {
        if (fromDate != null && toDate != null && chosenArticleList.size() !=0){
            Consumer consumer = new Consumer();
            consumer.getData(toDate, fromDate);
            populateAnalysisChart();
        }
    }

    private void populateAnalysisChart() {
        List<SalesPerDay> lastMonthData = getPeriodData();
        List<XYChart.Series> seriesList = new ArrayList<>();
        for (Article article: chosenArticleList){
            XYChart.Series serie = new XYChart.Series();
            serie.setName(article.getArticlename());
            for (SalesPerDay spd:lastMonthData){
                if (spd.getArticleID() == article.getArticleID()){
                    serie.getData().add(new XYChart.Data(spd.getDate(), spd.getAmount()));
                }
            }
            seriesList.add(serie);
        }
        for (XYChart.Series serie:seriesList) {
            lcAnalyse.getData().add(serie);
        }
    }

    private List<SalesPerDay> getPeriodData(){
        List<SalesPerDay> periodData = new ArrayList<>();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        int monthAmount = 0;
        double monthGross = 0;
        for (SalesPerDay spd: Consumer.getSales().getArticlePerDay()) {
            monthGross += spd.getPrice() * spd.getAmount();
            monthAmount += spd.getAmount();
            periodData.add(spd);
        }
        lblGross.setText(String.valueOf(formatter.format(monthGross)));
        lblAmount.setText(monthAmount + " Articles");
        return periodData;
    }

    public static void setLblArticlesTextProperty(String testString) {
        AnalyseController.lblArticlesTextProperty.set(testString);
    }

    public static void setLblFreeTimePeriodTextProperty(String lblFreeTimePeriodTextProperty) {
        AnalyseController.lblFreeTimePeriodTextProperty.set(lblFreeTimePeriodTextProperty);
    }

    public void btnFreePeriodClick() {
        LFJDAnalyticsApplication.secondaryStage.setScene(LFJDAnalyticsApplication.datePickerScene);
        LFJDAnalyticsApplication.secondaryStage.show();
        lblGross.setText("asdasd");
    }
    public void btnChooseArticlesClick() {
        ((ArticlePickerController)LFJDAnalyticsApplication.articlePickerLoader.getController()).createCheckBoxes();
        LFJDAnalyticsApplication.secondaryStage.setScene(LFJDAnalyticsApplication.articlePickerScene);
        LFJDAnalyticsApplication.secondaryStage.show();
    }

    public static void setFromDate(LocalDate fromDate) {
        AnalyseController.fromDate = fromDate;
    }

    public static void setToDate(LocalDate toDate) {
        AnalyseController.toDate = toDate;
    }
}
