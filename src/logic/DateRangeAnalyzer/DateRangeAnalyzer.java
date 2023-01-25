package logic.DateRangeAnalyzer;

import modell.SalesPerDay;
import modell.SalesPerWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static java.time.temporal.ChronoUnit.DAYS;

public class DateRangeAnalyzer {
    public static List<SalesPerWeek> Analyzer(List<SalesPerDay> salesList){
        List<SalesPerWeek> tempLSalesList = new ArrayList<>();

        LocalDate startDate = LocalDate.parse(salesList.get(0).getDate());
        LocalDate endDate = LocalDate.parse(salesList.get(salesList.size()).getDate());

        if(DAYS.between(startDate, endDate) > 60){
            for (SalesPerDay sale:salesList) {

            }
        }

        return tempLSalesList;
    }
}
