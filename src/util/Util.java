package util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class Util {
    public static void printChart(Image image){
        File outputFile = new File("C:\\LFJDTest\\");
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getWeekDay(LocalDate date){
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
}


