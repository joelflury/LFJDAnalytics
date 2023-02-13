package modell;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateRangeDataObject {
    private final String FROMDATE;
    private final String TODATE;

    public String getFROMDATE() {
        return FROMDATE;
    }

    public String getTODATE() {
        return TODATE;
    }

    public DateRangeDataObject(String firstDate, String lastDate) {
        this.FROMDATE = firstDate;
        this.TODATE = lastDate;
    }

    public DateRangeDataObject() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.FROMDATE = dtf.format(LocalDate.now().minusDays(365));
        this.TODATE = dtf.format(LocalDate.now());
    }
}
