package logic.consumer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateRangeDataObject {
    private String firstDate;
    private String lastDate;

    public String getFirstDate() {
        return firstDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public DateRangeDataObject(String firstDate, String lastDate) {
        this.firstDate = firstDate;
        this.lastDate = lastDate;
    }

    public DateRangeDataObject() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.firstDate = dtf.format(LocalDate.now().minusDays(365));
        this.lastDate = dtf.format(LocalDate.now());
    }
}
