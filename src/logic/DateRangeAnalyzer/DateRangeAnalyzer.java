package logic.DateRangeAnalyzer;

import modell.SalesPerDay;
import modell.SalesPerWeek;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateRangeAnalyzer {
    private int amountOfDaysUntilSwitchToWeek = 62;

    public List<SalesPerWeek> analyze(List<SalesPerDay> salesList, int articleID, float articlePrice) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<SalesPerWeek> tempLSalesList = new ArrayList<>();

        LocalDate fromDate = LocalDate.parse(salesList.get(0).getDate());
        LocalDate toDate = LocalDate.parse(salesList.get(salesList.size() - 1).getDate());

        for (LocalDate date = fromDate; date.isBefore(toDate.plusDays(1)); date = date.plusDays(1)) {
            int week = (int) (Math.floor(date.getDayOfYear() / 7.0) + 1);
            tempLSalesList.add(new SalesPerWeek(articleID, week, 0, 0));
            for (SalesPerDay sale : salesList) {
                LocalDate saleDate = LocalDate.parse(sale.getDate(), dtf);
                int saleWeek = (int) (Math.floor(saleDate.getDayOfYear() / 7.0) + 1);
                if (saleWeek == week && articleID == sale.getArticleID()) {
                    tempLSalesList.get(tempLSalesList.size() - 1).setAmount(tempLSalesList.get(tempLSalesList.size() - 1).getAmount() + sale.getAmount());
                    tempLSalesList.get(tempLSalesList.size() - 1).setPrice(tempLSalesList.get(tempLSalesList.size() - 1).getAmount() * articlePrice);
                }
            }
        }
        return tempLSalesList;
    }

    public int getAmountOfDaysUntilSwitchToWeek() {
        return amountOfDaysUntilSwitchToWeek;
    }
}
