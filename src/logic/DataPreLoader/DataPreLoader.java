package logic.DataPreLoader;

import application.LFJDAnalyticsApplication;
import logic.consumer.Consumer;

import java.time.LocalDate;

public class DataPreLoader extends Thread{
    @Override
    public void run() {
        LFJDAnalyticsApplication.consumer.getSalesData(LocalDate.now().minusDays(365), LocalDate.now());
        LFJDAnalyticsApplication.consumer.getArticleData();
    }
}
