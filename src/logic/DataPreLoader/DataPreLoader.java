package logic.DataPreLoader;

import logic.algorithm.SalesForcecastAlgorithm;
import logic.consumer.Consumer;

import java.time.LocalDate;

import static java.time.temporal.TemporalAdjusters.firstDayOfYear;

public class DataPreLoader extends Thread {
    /**
     * Gets the Data for the Dashboard and runs the algorithm function for the forecast data. Runs in a thread so the GUI isn't blocked.
     */
    @Override
    public void run() {
        Consumer consumer = new Consumer();
        consumer.getSalesData(LocalDate.now().minusDays(735).with(firstDayOfYear()), LocalDate.now());
        consumer.getArticleData();
        SalesForcecastAlgorithm algorithm = new SalesForcecastAlgorithm(LocalDate.now().plusDays(365), consumer.getArticles().getArticles());
        algorithm.calculate();
    }
}
