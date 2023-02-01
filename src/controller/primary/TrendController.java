package controller.primary;

import application.LFJDAnalyticsApplication;
import controller.secondary.ArticlePickerController;
import controller.secondary.DatePickerController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logic.DateRangeAnalyzer.DateRangeAnalyzer;
import logic.consumer.Consumer;
import modell.Article;
import modell.SalesPerDay;
import modell.SalesPerWeek;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class TrendController {

    @FXML
    protected Button btnAnalyse;
    @FXML
    protected Button btnHome;
    @FXML
    protected Button btnReports;
    @FXML
    protected Label lblTimePeriod;
    @FXML
    protected Label lblArticles;
    @FXML
    protected LineChart lcTrend;
    @FXML
    protected List<XYChart.Series> seriesList;
    @FXML
    protected Label lblAmount;
    @FXML
    protected Label lblGross;
    private static StringProperty lblArticlesTextProperty = new SimpleStringProperty("Choose\nProducts");
    private static StringProperty lblFreeTimePeriodTextProperty = new SimpleStringProperty("Choose a\nPeriod");
    private static LocalDate fromDate;
    private static LocalDate toDate;
    private static List<Article> chosenArticleList = new ArrayList<>();
    @FXML
    public void initialize(){
        lblArticles.textProperty().bind(lblArticlesTextProperty);
        lblTimePeriod.textProperty().bind(lblFreeTimePeriodTextProperty);
        lcTrend.setAnimated(false);
    }

    public static void setChosenArticleList(List<Article> articleListParam) {
        chosenArticleList = articleListParam;
    }

    @FXML
    public void btnAnalyseClick() {
        Stage stage = (Stage)btnAnalyse.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.analyseScene);
    }
    @FXML
    public void btnHomeClick(){
        Stage stage = (Stage)btnHome.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.homeScene);
    }

    public void checkIfAllDataPresent() {
        if (fromDate != null && toDate != null && chosenArticleList.size() != 0) {
            getSalesDataForLabels();
            populateTrendChart();
        }
    }

    private void populateTrendChart() {
        lcTrend.getData().clear();
        List<XYChart.Series> seriesList = new ArrayList<>();
        for (Article article: chosenArticleList) {
            XYChart.Series serie = new XYChart.Series();
            serie.setName(article.getArticlename());
            List<SalesPerDay> tempSalesPerDayList = SalesPerDay.getSalesForecastList(fromDate, toDate);
            if (DAYS.between(fromDate, toDate) > 62) {
                List<SalesPerWeek> tempSalesPerWeekList = DateRangeAnalyzer.analyze(tempSalesPerDayList);
                for (SalesPerWeek spw : tempSalesPerWeekList) {
                    if (spw.getArticleID() == article.getArticleID()) {
                        serie.getData().add(new XYChart.Data("KW " + spw.getWeek(), spw.getAmount()));
                    }
                }
                seriesList.add(serie);

                for (XYChart.Series tempSerie : seriesList) {
                    lcTrend.getData().add(tempSerie);
                }
                seriesList.clear();
            } else {
                for (SalesPerDay spd : SalesPerDay.getSalesForecastList(fromDate, toDate)) {
                    if (spd.getArticleID() == article.getArticleID()) {
                        serie.getData().add(new XYChart.Data(spd.getDate(), spd.getAmount()));
                    }
                }
                seriesList.add(serie);
                for (XYChart.Series tempSerie : seriesList) {
                    lcTrend.getData().add(tempSerie);
                }
                seriesList.clear();
            }
        }
    }

    private void getSalesDataForLabels() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        int monthAmount = 0;
        double monthGross = 0;
        for (Article article:chosenArticleList) {
            for (SalesPerDay spd : SalesPerDay.getSalesForecastList(fromDate, toDate)) {
                if (article.getArticleID() == spd.getArticleID()){
                    monthGross += spd.getPrice();
                    monthAmount += spd.getAmount();
                }
            }
        }
        lblGross.setText(String.valueOf(formatter.format(monthGross)));
        lblAmount.setText(monthAmount + " Articles");
    }

    public static void setLblArticlesTextProperty(String testString) {
        TrendController.lblArticlesTextProperty.set(testString);
    }

    public static void setLblFreeTimePeriodTextProperty(String lblFreeTimePeriodTextProperty) {
        TrendController.lblFreeTimePeriodTextProperty.set(lblFreeTimePeriodTextProperty);
    }

    public void btnFreePeriodClick() {
        ((DatePickerController) LFJDAnalyticsApplication.datePickerLoader.getController()).setPeriodsForChoiceBox();
        LFJDAnalyticsApplication.secondaryStage.setScene(LFJDAnalyticsApplication.datePickerScene);
        LFJDAnalyticsApplication.secondaryStage.show();
    }

    public void btnChooseArticlesClick() {
        ((ArticlePickerController)LFJDAnalyticsApplication.articlePickerLoader.getController()).createCheckBoxes();
        LFJDAnalyticsApplication.secondaryStage.setScene(LFJDAnalyticsApplication.articlePickerScene);
        LFJDAnalyticsApplication.secondaryStage.show();
    }

    public static void setFromDate(LocalDate fromDate) {
        TrendController.fromDate = fromDate;
    }

    public static void setToDate(LocalDate toDate) {
        TrendController.toDate = toDate;
    }

}
