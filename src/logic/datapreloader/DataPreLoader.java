package logic.datapreloader;

import logic.consumer.Consumer;

public class DataPreLoader extends Thread{

    @Override
    public void run() {
        Consumer consumer = new Consumer();
        consumer.getData();
        //Trend algo
    }
}
