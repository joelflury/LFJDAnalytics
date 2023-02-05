package logic.DataPreLoader;

import application.LFJDAnalyticsApplication;
import logic.algorithm.SalesForcecastAlgorithm;
import logic.consumer.Consumer;

import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

public class DataPreLoader extends Thread {
    @Override
    public void run() {
        LFJDAnalyticsApplication.getConsumer().getSalesData(LocalDate.now().minusDays(735).with(firstDayOfYear()), LocalDate.now());
        LFJDAnalyticsApplication.getConsumer().getArticleData();
        SalesForcecastAlgorithm algorithm = new SalesForcecastAlgorithm(LocalDate.now().plusDays(365), Consumer.getArticles().getArticles());
        algorithm.calculate();
    }
}
