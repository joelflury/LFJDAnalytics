package util;

import javafx.scene.control.Alert;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class Util {
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

    public static void showAlert(int alertType, String title, String header, String content) {
        Alert alert = null;
        if (alertType == 1){
            alert = new Alert(Alert.AlertType.ERROR);
        } else if (alertType == 2){
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

}


