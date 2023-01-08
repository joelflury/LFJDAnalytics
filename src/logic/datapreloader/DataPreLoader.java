package logic.datapreloader;

import logic.consumer.Consumer;

import java.time.LocalDate;

public class DataPreLoader extends Thread{

    @Override
    public void run() {
        Consumer consumer = new Consumer();
        consumer.getData(LocalDate.now(), LocalDate.now().minusDays(365));
        //Trend algo
    }
}
