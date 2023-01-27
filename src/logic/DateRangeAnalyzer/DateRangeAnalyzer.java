package logic.DateRangeAnalyzer;

import logic.consumer.Consumer;
import modell.Article;
import modell.SalesPerDay;
import modell.SalesPerWeek;
import util.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateRangeAnalyzer {
    public static List<SalesPerWeek> analyze(List<SalesPerDay> salesList) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<SalesPerWeek> tempLSalesList = new ArrayList<>();

        LocalDate startDate = LocalDate.parse(salesList.get(0).getDate());
        LocalDate endDate = LocalDate.parse(salesList.get(salesList.size() - 1).getDate());

        if (Util.getWeekDay(startDate) > 1) {
//                startDate = startDate.minusDays(Util.getWeekDay(startDate) - 1);
        }
        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            int week = (int) (Math.floor(date.getDayOfYear() / 7) + 1);
            for (Article article : Consumer.getArticles().getArticles()) {
                tempLSalesList.add(new SalesPerWeek(article.getArticleID(), week, 0, 0));
                for (SalesPerDay sale : salesList) {
                    LocalDate saleDate = LocalDate.parse(sale.getDate(), dtf);
                    int saleWeek = (int) (Math.floor(saleDate.getDayOfYear() / 7) + 1);
                    if (saleWeek == week && article.getArticleID() == sale.getArticleID()) {
                        tempLSalesList.get(tempLSalesList.size() - 1).setAmount(tempLSalesList.get(tempLSalesList.size() - 1).getAmount() + sale.getAmount());
                        tempLSalesList.get(tempLSalesList.size() - 1).setPrice(tempLSalesList.get(tempLSalesList.size() - 1).getAmount() * article.getPrice());
                    }
                }
            }
        }
        return tempLSalesList;
    }
}
