package logic.DateRangeAnalyzer;

import modell.SalesPerDay;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateRangeAnalyzer {
    public static List<SalesPerDay> Analyzer(List<SalesPerDay> salesList){
        List<SalesPerDay> tempLSalesList = new ArrayList<>();

        LocalDate startDate = LocalDate.parse(salesList.get(0).getDate());
        LocalDate endDate = LocalDate.parse(salesList.get(salesList.size()).getDate());

        if(DAYS.between(startDate, endDate) > 60){

        }

        return tempLSalesList;
    }
}
