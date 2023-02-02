package modell;

public class Period {
    public static String[] getPeriodNames() {
        return periodNames;
    }

    public static String[] getPeriodNamesForeCast() {
        return periodNamesForeCast;
    }

    private static final String[] periodNames = new String[]{"Last 7 Days", "Last Month", "Last 3 Months"};
    private static final String[] periodNamesForeCast = new String[]{"Next 7 Days", "Next Month", "Next 3 Months"};
}
