package controller.primary;

import application.LFJDAnalyticsApplication;
import controller.secondary.ArticlePickerController;
import controller.secondary.DatePickerController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import modell.Article;
import modell.SalesPerDay;
import util.Util;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrendController {

    @FXML
    protected Button btnAnalyse;
    @FXML
    protected Button btnHome;
    @FXML
    protected Button btnAboutUs;
    @FXML
    protected Label lblTimePeriod;
    @FXML
    protected Label lblArticles;
    @FXML
    protected LineChart lcTrend;
    @FXML
    protected Label lblAmount;
    @FXML
    protected Label lblGross;
    @FXML
    protected HBox hBoxLcTrend;
    private static StringProperty lblArticlesTextProperty = new SimpleStringProperty("Choose\nProducts");
    private static StringProperty lblFreeTimePeriodTextProperty = new SimpleStringProperty("Choose a\nPeriod");
    private static LocalDate fromDate;
    private static LocalDate toDate;
    private static List<Article> chosenArticleList = new ArrayList<>();
    private final PrintSaveChart printSaveChart = new PrintSaveChart();
    private final Stage stage = LFJDAnalyticsApplication.getMainStage();

    @FXML
    public void initialize() {
        lblArticles.textProperty().bind(lblArticlesTextProperty);
        lblTimePeriod.textProperty().bind(lblFreeTimePeriodTextProperty);
        lcTrend.setAnimated(false);
    }

    public static void setChosenArticleList(List<Article> articleListParam) {
        chosenArticleList = articleListParam;
    }

    /**
     * Calls the Analyze Scene
     */
    @FXML
    public void btnAnalyseClick() {
        Stage stage = (Stage) btnAnalyse.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.getAnalyseScene());
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
     * Calls the AboutUs Scene
     */
    @FXML
    public void btnAboutUsClick() {
        Stage stage = (Stage) btnAboutUs.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.getAboutUsScene());
    }

    /**
     * Calls the Print function from te Util Class
     */
    @FXML
    private void printChart() {
        printSaveChart.printFile(new ImageView(SwingFXUtils.toFXImage(printSaveChart.createPicture(hBoxLcTrend.snapshot(new SnapshotParameters(), null), hBoxLcTrend.getWidth(), hBoxLcTrend.getHeight()), null)));
    }

    /**
     * Calls the Save function from te Util Class
     */
    @FXML
    private void saveChart() {
        printSaveChart.saveFileAsImage(printSaveChart.createPicture(hBoxLcTrend.snapshot(new SnapshotParameters(), null), hBoxLcTrend.getWidth(), hBoxLcTrend.getHeight()), stage);
    }

    /**
     * Checks if all Data is present to fill the Chart
     */
    public void checkIfAllDataPresent() {
        if (fromDate != null && toDate != null && chosenArticleList.size() != 0) {
            getSalesDataForLabels();
            Util.populateChart(lcTrend, fromDate, toDate, chosenArticleList, SalesPerDay.getSalesForecastList(fromDate, toDate));
        }
    }

    /**
     * Gets the Amount of ordered articles and the gross of them to then present these values in the corresponding labels
     */
    private void getSalesDataForLabels() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        int monthAmount = 0;
        double monthGross = 0;
        for (Article article : chosenArticleList) {
            for (SalesPerDay spd : SalesPerDay.getSalesForecastList(fromDate, toDate)) {
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
        TrendController.lblArticlesTextProperty.set(testString);
    }

    /**
     * Sets the value for the time period Label after they have been chosen
     */
    public static void setLblFreeTimePeriodTextProperty(String lblFreeTimePeriodTextProperty) {
        TrendController.lblFreeTimePeriodTextProperty.set(lblFreeTimePeriodTextProperty);
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
        TrendController.fromDate = fromDate;
    }

    /**
     * setter for the variable toDate
     */
    public static void setToDate(LocalDate toDate) {
        TrendController.toDate = toDate;
    }

}
