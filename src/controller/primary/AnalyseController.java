package controller.primary;

import application.LFJDAnalyticsApplication;
import controller.secondary.ArticlePickerController;
import controller.secondary.DatePickerController;
import javafx.beans.property.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.PrintSaveChart.PrintSaveChart;
import logic.consumer.Consumer;
import modell.Article;
import modell.SalesPerDay;
import util.Util;

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
    private static final StringProperty lblArticlesTextProperty = new SimpleStringProperty("Choose\nProducts");
    private static final StringProperty lblFreeTimePeriodTextProperty = new SimpleStringProperty("Choose a\nPeriod");
    private static LocalDate fromDate;
    private static LocalDate toDate;
    private static List<Article> chosenArticleList = new ArrayList<>();
    private final PrintSaveChart printSaveChart = new PrintSaveChart();
    private final Stage stage = LFJDAnalyticsApplication.getMainStage();

    public static void setChosenArticleList(List<Article> articleListParam) {
        chosenArticleList = articleListParam;
    }

    /**
     * Gets called when the Controller is being initialized
     */
    @FXML
    public void initialize() {
        lblArticles.textProperty().bind(lblArticlesTextProperty);
        lblTimePeriod.textProperty().bind(lblFreeTimePeriodTextProperty);
        lcAnalyse.setAnimated(false);
    }

    /**
     * Calls the Home Scene
     */
    @FXML
    public void btnHomeClick() {
        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.getHomeScene());
    }

    /**
     * Calls the Trend Scene
     */
    @FXML
    public void btnTrendClick() {
        Stage stage = (Stage) btnTrend.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.getTrendScene());
    }

    /**
     * Calls the AboutUs Scene
     */
    public void btnAboutUsClick() {
        Stage stage = (Stage) btnAboutUs.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.getAboutUsScene());
    }

    /**
     * Checks if all Data is present to fill the Chart
     */
    @FXML
    public void checkIfAllDataPresent() {
        if (fromDate != null && toDate != null && chosenArticleList.size() != 0) {
            Consumer consumer = new Consumer();
            consumer.getSalesData(fromDate, toDate);
            getSalesDataForLabels();
            Util.populateChart(lcAnalyse, fromDate, toDate, chosenArticleList, Consumer.getSales().getArticlePerDay());
        }
    }

    /**
     * Calls the function from Class Util to print the chart
     */
    @FXML
    private void printChart() {
        printSaveChart.printFile(new ImageView(SwingFXUtils.toFXImage(printSaveChart.createPicture(hBoxLcAnalyse.snapshot(new SnapshotParameters(), null), hBoxLcAnalyse.getWidth(), hBoxLcAnalyse.getHeight()), null)));
    }

    /**
     * Calls the function from Class Util to save the chart
     */
    @FXML
    private void saveChart() {
        printSaveChart.saveFileAsImage(printSaveChart.createPicture(hBoxLcAnalyse.snapshot(new SnapshotParameters(), null), hBoxLcAnalyse.getWidth(), hBoxLcAnalyse.getHeight()), stage);
    }

    /**
     * Gets the Amount of ordered articles and the gross of them to then present these values in the corresponding labels
     */
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

    /**
     * Sets the value for the Article Label after they have been chosen
     */
    public static void setLblArticlesTextProperty(String testString) {
        AnalyseController.lblArticlesTextProperty.set(testString);
    }

    /**
     * Sets the value for the time period Label after they have been chosen
     */
    public static void setLblFreeTimePeriodTextProperty(String lblFreeTimePeriodTextProperty) {
        AnalyseController.lblFreeTimePeriodTextProperty.set(lblFreeTimePeriodTextProperty);
    }

    /**
     * Calls the DatePicker Stage and also sets the Periods for the Choicebox
     */
    public void btnFreePeriodClick() {
        ((DatePickerController) LFJDAnalyticsApplication.getDatePickerLoader().getController()).setPeriodsForChoiceBox();
        LFJDAnalyticsApplication.getSecondaryStage().setScene(LFJDAnalyticsApplication.getDatePickerScene());
        LFJDAnalyticsApplication.getSecondaryStage().show();
    }

    /**
     * Calls the ArticlePicker Stage and also calls the createCheckBoxes Method
     */
    public void btnChooseArticlesClick() {
        ((ArticlePickerController) LFJDAnalyticsApplication.getArticlePickerLoader().getController()).createCheckBoxes();
        LFJDAnalyticsApplication.getSecondaryStage().setScene(LFJDAnalyticsApplication.getArticlePickerScene());
        LFJDAnalyticsApplication.getSecondaryStage().show();
    }

    /**
     * setter for the variable fromDate
     */
    public static void setFromDate(LocalDate fromDate) {
        AnalyseController.fromDate = fromDate;
    }

    /**
     * setter for the variable toDate
     */
    public static void setToDate(LocalDate toDate) {
        AnalyseController.toDate = toDate;
    }
}
