package modell;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateRangeDataObject {
    private final String fromDate;
    private final String toDate;

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public DateRangeDataObject(String firstDate, String lastDate) {
        this.fromDate = firstDate;
        this.toDate = lastDate;
    }

    public DateRangeDataObject() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.fromDate = dtf.format(LocalDate.now().minusDays(365));
        this.toDate = dtf.format(LocalDate.now());
    }
}
