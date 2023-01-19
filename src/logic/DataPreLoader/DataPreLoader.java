package logic.DataPreLoader;

import application.LFJDAnalyticsApplication;
import logic.algorithm.SalesForcecastAlgorithm;
import logic.consumer.Consumer;
import modell.Article;
import modell.SalesPerDay;

import java.time.LocalDate;

public class DataPreLoader extends Thread{
    @Override
    public void run() {
        LFJDAnalyticsApplication.consumer.getSalesData(LocalDate.now().minusDays(365), LocalDate.now());
        LFJDAnalyticsApplication.consumer.getArticleData();
        SalesForcecastAlgorithm algorithm = new SalesForcecastAlgorithm(LocalDate.now().plusDays(365), Consumer.getArticles().getArticles());
        algorithm.calculate();
        System.out.println(SalesPerDay.getSalesForecastList());
    }
}
