package controller.primary;

import application.LFJDAnalyticsApplication;
import controller.secondary.ArticlePickerController;
import controller.secondary.DatePickerController;
import javafx.beans.property.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.DateRangeAnalyzer.DateRangeAnalyzer;
import logic.PrintSaveChart.PrintSaveChart;
import logic.consumer.Consumer;
import modell.Article;
import modell.SalesPerDay;
import modell.SalesPerWeek;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class AnalyseController {
    @FXML
    protected Button btnHome;
    @FXML
    protected Button btnTrend;
    @FXML
    protected Button btnAboutUs;
    @FXML
    protected Button btnPrint;
    @FXML
    protected Button btnSave;
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
    @FXML
    protected HBox hBoxLcAnalyse;
    private static StringProperty lblArticlesTextProperty = new SimpleStringProperty("Choose\nProducts");
    private static StringProperty lblFreeTimePeriodTextProperty = new SimpleStringProperty("Choose a\nPeriod");
    private static LocalDate fromDate;
    private static LocalDate toDate;
    private static List<Article> chosenArticleList = new ArrayList<>();
    private PrintSaveChart printSaveChart = new PrintSaveChart();
    private Stage stage = LFJDAnalyticsApplication.getMainStage();


    public static void setChosenArticleList(List<Article> articleListParam) {
        chosenArticleList = articleListParam;
    }

    @FXML
    public void initialize() {
        lblArticles.textProperty().bind(lblArticlesTextProperty);
        lblTimePeriod.textProperty().bind(lblFreeTimePeriodTextProperty);
        lcAnalyse.setAnimated(false);
    }

    @FXML
    public void btnHomeClick() {
        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.homeScene);
    }

    @FXML
    public void btnTrendClick() {
        Stage stage = (Stage) btnTrend.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.trendScene);
    }
    public void btnAboutUsClick(){
        Stage stage = (Stage)btnAboutUs.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.aboutUsScene);
    }

    @FXML
    public void checkIfAllDataPresent() {
        if (fromDate != null && toDate != null && chosenArticleList.size() != 0) {
            Consumer consumer = new Consumer();
            consumer.getSalesData(fromDate, toDate);
            getSalesDataForLabels();
            populateAnalysisChart();
        }
    }
    @FXML
    private void printChart(){
        printSaveChart.printFile(new ImageView(SwingFXUtils.toFXImage(printSaveChart.createPicture(hBoxLcAnalyse.snapshot(new SnapshotParameters(), null), hBoxLcAnalyse.getWidth(), hBoxLcAnalyse.getHeight()), null)));
    }
    @FXML
    private void saveChart(){
        printSaveChart.saveFileAsImage(printSaveChart.createPicture(hBoxLcAnalyse.snapshot(new SnapshotParameters(), null), hBoxLcAnalyse.getWidth(), hBoxLcAnalyse.getHeight()), stage);
    }

    private void populateAnalysisChart() {
        lcAnalyse.getData().clear();
        List<XYChart.Series> seriesList = new ArrayList<>();
        for (Article article: chosenArticleList) {
            XYChart.Series serie = new XYChart.Series();
            serie.setName(article.getArticlename());
            List<SalesPerDay> tempSalesPerDayList = Consumer.getSales().getArticlePerDay();
            if (DAYS.between(fromDate, toDate) > 62) {
                List<SalesPerWeek> tempSalesPerWeekList = DateRangeAnalyzer.analyze(tempSalesPerDayList);
                for (SalesPerWeek spw : tempSalesPerWeekList) {
                    if (spw.getArticleID() == article.getArticleID()) {
                        serie.getData().add(new XYChart.Data("KW " +spw.getWeek(), spw.getAmount()));
                    }
                }
                seriesList.add(serie);

                for (XYChart.Series tempSerie : seriesList) {
                    lcAnalyse.getData().add(tempSerie);
                }
                seriesList.clear();

            } else {
                for (SalesPerDay spd : Consumer.getSales().getArticlePerDay()) {
                    if (spd.getArticleID() == article.getArticleID()) {
                        serie.getData().add(new XYChart.Data(spd.getDate(), spd.getAmount()));
                    }
                }
                seriesList.add(serie);

                for (XYChart.Series tempSerie : seriesList) {
                    lcAnalyse.getData().add(tempSerie);
                }
                seriesList.clear();
            }
        }
    }

    private void getSalesDataForLabels() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        int monthAmount = 0;
        double monthGross = 0;
         for (Article article : chosenArticleList) {
             for (SalesPerDay spd : Consumer.getSales().getArticlePerDay()) {
                 if (article.getArticleID() == spd.getArticleID()) {
                     monthGross += spd.getPrice();
                     monthAmount += spd.getAmount();
                 }
             }
         }
        lblGross.setText(String.valueOf(formatter.format(monthGross)));
        lblAmount.setText(monthAmount + " Articles");
    }

    public static void setLblArticlesTextProperty(String testString) {
        AnalyseController.lblArticlesTextProperty.set(testString);
    }

    public static void setLblFreeTimePeriodTextProperty(String lblFreeTimePeriodTextProperty) {
        AnalyseController.lblFreeTimePeriodTextProperty.set(lblFreeTimePeriodTextProperty);
    }

    public void btnFreePeriodClick() {
        ((DatePickerController) LFJDAnalyticsApplication.datePickerLoader.getController()).setPeriodsForChoiceBox();
        LFJDAnalyticsApplication.secondaryStage.setScene(LFJDAnalyticsApplication.datePickerScene);
        LFJDAnalyticsApplication.secondaryStage.show();
    }

    public void btnChooseArticlesClick() {
        ((ArticlePickerController) LFJDAnalyticsApplication.articlePickerLoader.getController()).createCheckBoxes();
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
