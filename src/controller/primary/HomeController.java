package controller.primary;

import application.LFJDAnalyticsApplication;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import logic.DataPreLoader.DataPreLoader;
import logic.consumer.Consumer;
import modell.Article;
import modell.SalesPerDay;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    @FXML
    protected Button btnAnalyse;
    @FXML
    protected Button btnTrend;
    @FXML
    protected Button btnAboutUs;
    @FXML
    protected Label lblDate;
    @FXML
    protected LineChart lcAnalyse;
    @FXML
    protected LineChart lcTrend;
    @FXML
    protected Label lblAnalyzeGross;
    @FXML
    protected Label lblAnalyzeAmount;
    @FXML
    protected Label lblTrendGross;
    @FXML
    protected Label lblTrendAmount;

    /**
     * Gets called when the Controller is being initialized
     */
    @FXML
    public void initialize() {
        lblDate.setText(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        lcAnalyse.setAnimated(false);
        lcTrend.setAnimated(false);
    }

    /**
     * Calls the Analyse Scene
     */
    @FXML
    public void btnAnalyseClick() {
        Stage stage = (Stage) btnAnalyse.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.getAnalyseScene());
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
    @FXML
    public void btnAboutUsClick() {
        Stage stage = (Stage) btnAboutUs.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.getAboutUsScene());
    }

    /**
     * Populates the Chart
     * @param salesList The Data to be filled with
     * @param chart The Chart to be filled
     */
    public void populateChart(List<SalesPerDay> salesList, LineChart chart) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        int monthAmount = 0;
        double monthGross = 0;
        List<XYChart.Series> seriesList = new ArrayList<>();
        for (Article article : Consumer.getArticles().getArticles()) {
            XYChart.Series serie = new XYChart.Series();
            serie.setName(article.getArticlename());
            for (SalesPerDay spd : salesList) {
                if (spd.getArticleID() == article.getArticleID()) {
                    serie.getData().add(new XYChart.Data(spd.getDate(), spd.getAmount()));
                    monthGross += spd.getPrice();
                    monthAmount += spd.getAmount();
                }
            }
            seriesList.add(serie);
        }
        if (chart.getId().equals("lcTrend")) {
            lblTrendGross.setText(monthAmount + " Articles");
            lblTrendAmount.setText(String.valueOf(formatter.format(monthGross)));
        } else if (chart.getId().equals("lcAnalyse")) {
            lblAnalyzeGross.setText(String.valueOf(formatter.format(monthGross)));
            lblAnalyzeAmount.setText(monthAmount + " Articles");
        }
        for (XYChart.Series serie : seriesList) {
            chart.getData().add(serie);
        }
    }

    /**
     * Gets called from the LoginController. Gets Data for Forecast and Analisys and calls the populateChart Method
     */
    public void start() {
        try {
            Consumer consumer = new Consumer();
            //Start DataPreloader if not already done
            if (SalesPerDay.getSalesForecastList().size() == 0) {
                DataPreLoader dataPreLoader = new DataPreLoader();
                dataPreLoader.start();
                dataPreLoader.join();
            }
            consumer.getSalesData(LocalDate.now().minusDays(30), LocalDate.now());
            consumer.getArticleData();
            populateChart(Consumer.getSales().getArticlePerDay(), lcAnalyse);
            populateChart(SalesPerDay.getSalesForecastList(LocalDate.now().plusDays(1), LocalDate.now().plusDays(31)), lcTrend);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}