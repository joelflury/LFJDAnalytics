package logic.DateRangeAnalyzer;

import modell.SalesPerDay;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateRangeAnalyzer {
    public static List<SalesPerDay> Analyzer(List<SalesPerDay> salesList){
        List<SalesPerDay> tempLSalesList = new ArrayList<>();

        LocalDate startDate = LocalDate.parse(salesList.get(0).getDate());
        LocalDate endDate = LocalDate.parse(salesList.get(salesList.size()).getDate());

        return tempLSalesList;
    }
}
