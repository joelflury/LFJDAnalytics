package logic.PrintSaveChart;

import javafx.embed.swing.SwingFXUtils;
import javafx.print.PrinterJob;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PrintSaveChart {
    public static void saveFileAsImage(WritableImage snapShot, double width, double height, Stage stage) {
        BufferedImage bufferedImage = new BufferedImage(550, 400, BufferedImage.TYPE_INT_ARGB);
        BufferedImage image;
        DirectoryChooser directoryChooser = new DirectoryChooser();
        image = SwingFXUtils.fromFXImage(snapShot, bufferedImage);
        File outputFile = new File(directoryChooser.showDialog(stage).toURI());
        try {
            Graphics2D gd = (Graphics2D) image.getGraphics();
            gd.translate(width, height);
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printFile(HBox hBox) {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null && printerJob.showPrintDialog(hBox.getScene().getWindow())) {
            boolean success = printerJob.printPage(hBox);
            if (success) {
                printerJob.endJob();
            }
        }
    }

}
