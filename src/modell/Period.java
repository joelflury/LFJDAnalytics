package modell;

import java.time.LocalDate;

public class Period {
    public static LocalDate startDate;
    public static LocalDate endDate;
    public static String[] periodNames = new String[]{"Last 7 Days", "Last Month", "Last 3 Months"};
    public static int[] periodDays = new int[]{7, 30, 90};
    public static void periodFromTemplates(int index){
        endDate = LocalDate.now().minusDays(1);
        startDate = endDate.minusDays(periodDays[index]);
    }
}
