package util;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import logic.DateRangeAnalyzer.DateRangeAnalyzer;
import logic.consumer.Consumer;
import modell.Article;
import modell.SalesPerDay;
import modell.SalesPerWeek;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class Util {

    private static final String[] periodNames = new String[]{"Last 7 Days", "Last Month", "Last 3 Months"};
    private static final String[] periodNamesForeCast = new String[]{"Next 7 Days", "Next Month", "Next 3 Months"};

    /**
     * retunrs the Weekday from a String of the weekday
     * @param date
     * @return  the weekday as integer
     */
    public static int getWeekDay(LocalDate date) {
        String weekDayString = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK)).toString();
        int weekDayInt = 0;

        switch (weekDayString) {
            case "MONDAY":
                weekDayInt = 1;
                break;
            case "TUESDAY":
                weekDayInt = 2;
                break;
            case "WEDNESDAY":
                weekDayInt = 3;
                break;
            case "THURSDAY":
                weekDayInt = 4;
                break;
            case "FRIDAY":
                weekDayInt = 5;
                break;
            case "SATURDAY":
                weekDayInt = 6;
                break;
            case "SUNDAY":
                weekDayInt = 7;
                break;
        }
        return weekDayInt;
    }

    /**
     * Transforms a hash value out of the enterd password to check it in the DB
     * @param string
     * @return
     */
    public static String hashStringSHA515(String string) {
        StringBuffer hexString = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashedPassword = md.digest(string.getBytes(StandardCharsets.UTF_8));
            for (int i = 0; i < hashedPassword.length && i < 25; i++) {
                hexString.append(Integer.toHexString(0xFF & hashedPassword[i]));
            }
        } catch (Exception e) {
            hexString.append("0xFF");
        }
        return hexString.toString();

    }

    /**
     * This method populates the Chart given with the data given from the parameters
     * @param chart                 The chart to populate
     * @param fromDate              the fromDate to analyze the Date range in the DateRangeAnalayzer
     * @param toDate                the toDate to analyze the Date range in the DateRangeAnalayzer
     * @param chosenArticleList     All articles chosen from the suer
     * @param tempSalesPerDayList   a list with all the data to populate
     */
    public static void populateChart(LineChart chart, LocalDate fromDate, LocalDate toDate, List<Article> chosenArticleList, List<SalesPerDay> tempSalesPerDayList) {
        chart.getData().clear();
        List<XYChart.Series> seriesList = new ArrayList<>();
        for (Article article : chosenArticleList) {
            XYChart.Series serie = new XYChart.Series();
            serie.setName(article.getArticlename());
            DateRangeAnalyzer dateRangeAnalyzer = new DateRangeAnalyzer();
            if (DAYS.between(fromDate, toDate) > dateRangeAnalyzer.getAmountOfDaysUntilSwitchToWeek()) {
                List<SalesPerWeek> tempSalesPerWeekList = dateRangeAnalyzer.analyze(tempSalesPerDayList, article.getArticleID(), article.getPrice());
                for (SalesPerWeek spw : tempSalesPerWeekList) {
                    if (spw.getArticleID() == article.getArticleID()) {
                        serie.getData().add(new XYChart.Data("KW " + spw.getWeek(), spw.getAmount()));
                    }
                }
                seriesList.add(serie);

                for (XYChart.Series tempSerie : seriesList) {
                    chart.getData().add(tempSerie);
                }
                seriesList.clear();

            } else {
                for (SalesPerDay spd : tempSalesPerDayList) {
                    if (spd.getArticleID() == article.getArticleID()) {
                        serie.getData().add(new XYChart.Data(spd.getDate(), spd.getAmount()));
                    }
                }
                seriesList.add(serie);

                for (XYChart.Series tempSerie : seriesList) {
                    chart.getData().add(tempSerie);
                }
                seriesList.clear();
            }
        }
    }

    /**
     * Shows a dialog with the given params
     * @param alertType the type of alert: 1 for Erros dialogs and 2 for info dialogs
     * @param title     The Title text of the dialog
     * @param header    The header text of the dialog
     * @param content   The content text of the dialog
     */
    public static void showDialog(int alertType, String title, String header, String content) {
        Alert alert = null;
        if (alertType == 1) {
            alert = new Alert(Alert.AlertType.ERROR);
        } else if (alertType == 2) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /*
     * Following are all the setters and getters for this Class
     */
    public static String[] getPeriodNames() {
        return periodNames;
    }

    public static String[] getPeriodNamesForeCast() {
        return periodNamesForeCast;
    }

}


