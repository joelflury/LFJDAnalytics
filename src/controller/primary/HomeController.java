package controller.primary;

import application.LFJDAnalyticsApplication;
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
    protected Button btnReports;
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
    public void initialize(){
        lblDate.setText(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        lcAnalyse.setAnimated(false);
        lcTrend.setAnimated(false);
    }

    @FXML
    public void btnAnalyseClick() {
        Stage stage = (Stage)btnAnalyse.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.analyseScene);
    }
    @FXML
    public void btnTrendClick(){
        Stage stage = (Stage)btnTrend.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.trendScene);
    }

    public void populateAnalyseChart() {
        List<SalesPerDay> lastMonthData = getLastMonthData();
        List<XYChart.Series> seriesList = new ArrayList<>();
        for (Article article: Consumer.getArticles().getArticles()){
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

    public void populateChart(List<SalesPerDay> salesList, LineChart chart) {
        List<XYChart.Series> seriesList = new ArrayList<>();
        for (Article article: Consumer.getArticles().getArticles()){
            XYChart.Series serie = new XYChart.Series();
            serie.setName(article.getArticlename());
            for (SalesPerDay spd:salesList){
                if (spd.getArticleID() == article.getArticleID()){
                    serie.getData().add(new XYChart.Data(spd.getDate(), spd.getAmount()));
                }
            }
            seriesList.add(serie);
        }
        for (XYChart.Series serie:seriesList) {
            chart.getData().add(serie);
        }
    }

    private List<SalesPerDay> getLastMonthData(){
        List<SalesPerDay> lastMonthData = new ArrayList<>();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        int monthAmount = 0;
        double monthGross = 0;
        for (SalesPerDay spd: Consumer.getSales().getArticlePerDay()) {
            monthGross += spd.getPrice() * spd.getAmount();
            monthAmount += spd.getAmount();
            lastMonthData.add(spd);
        }
        lblAnalyzeGross.setText(String.valueOf(formatter.format(monthGross)));
        lblAnalyzeAmount.setText(monthAmount + " Articles");
        return lastMonthData;
    }

    public void start() {
        Consumer consumer = new Consumer();
        consumer.getSalesData(LocalDate.now().minusDays(31), LocalDate.now());
        consumer.getArticleData();
        //populateAnalyseChart();
        populateChart(getLastMonthData(), lcAnalyse);
        populateChart(SalesPerDay.getSalesForecastList(LocalDate.now(), LocalDate.now().plusDays(31)), lcTrend);
    }
}
