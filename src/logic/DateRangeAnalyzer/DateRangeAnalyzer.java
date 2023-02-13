package logic.DateRangeAnalyzer;

import modell.SalesPerDay;
import modell.SalesPerWeek;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateRangeAnalyzer {
    private final int AMOUNT_OF_DAYS_UNTIL_SWITCH_TO_WEEK = 62;

    /**
     * This function checks if the period is longer than 60 Days and if so blblalaal
     * @param salesList     The List of Data to be analyzed
     * @param articleID
     * @param articlePrice
     * @return
     */
    public List<SalesPerWeek> analyze(List<SalesPerDay> salesList, int articleID, float articlePrice) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<SalesPerWeek> tempLSalesList = new ArrayList<>();

        LocalDate fromDate = LocalDate.parse(salesList.get(0).getDATE());
        LocalDate toDate = LocalDate.parse(salesList.get(salesList.size() - 1).getDATE());

        for (LocalDate date = fromDate; date.isBefore(toDate.plusDays(1)); date = date.plusDays(1)) {
            int week = (int) (Math.floor(date.getDayOfYear() / 7.0) + 1);
            tempLSalesList.add(new SalesPerWeek(articleID, week, 0, 0));
            for (SalesPerDay sale : salesList) {
                LocalDate saleDate = LocalDate.parse(sale.getDATE(), dtf);
                int saleWeek = (int) (Math.floor(saleDate.getDayOfYear() / 7.0) + 1);
                if (saleWeek == week && articleID == sale.getARTICLEID()) {
                    tempLSalesList.get(tempLSalesList.size() - 1).setAMOUNT(tempLSalesList.get(tempLSalesList.size() - 1).getAMOUNT() + sale.getAMOUNT());
                    tempLSalesList.get(tempLSalesList.size() - 1).setPRICE(tempLSalesList.get(tempLSalesList.size() - 1).getAMOUNT() * articlePrice);
                }
            }
        }
        return tempLSalesList;
    }

    public int getAmountOfDaysUntilSwitchToWeek() {
        return AMOUNT_OF_DAYS_UNTIL_SWITCH_TO_WEEK;
    }
}
