package logic.datapreloader;

import logic.consumer.Consumer;
import modell.Article;

public class DataPreLoader extends Thread{

    @Override
    public void run() {
        Consumer consumer = new Consumer();
        consumer.start();
    }
}
