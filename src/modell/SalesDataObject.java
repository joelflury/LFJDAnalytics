package modell;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalesDataObject {
    private final List<SalesPerDay> salesPerDay;

    public SalesDataObject(List<SalesPerDay> salesPerDay) {
        this.salesPerDay = salesPerDay;
    }

    public List<SalesPerDay> getArticlePerDay() {
        return salesPerDay;
    }

    public List<SalesPerDay> getArticlePerDay(LocalDate firstDate, LocalDate lastDate, Article article) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<SalesPerDay> selectedSalesList = new ArrayList<>();
        for (SalesPerDay sales : salesPerDay) {
            if (sales.getArticleID() == article.getArticleID()) {
                LocalDate date = LocalDate.parse(sales.getDate(), dtf);
                if (date.isAfter(firstDate.minusDays(1))) {
                    if (date.isBefore(lastDate.plusDays(1))) {
                        selectedSalesList.add(sales);
                    }
                }
            }
        }
        return selectedSalesList;
    }
}
