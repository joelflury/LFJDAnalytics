package logic.PrintSaveChart;

import javafx.embed.swing.SwingFXUtils;
import javafx.print.*;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Scale;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import util.Util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.util.Optional;

public class PrintSaveChart {

    /**
     * Makes a picture of the snapshot from a Chart and sends back an image
     * @param snapShot  snapShot of the Chart
     * @param width     width of the Chart
     * @param height    height of the Chart
     * @return  the image
     */
    public BufferedImage createPicture(WritableImage snapShot, double width, double height) {
        BufferedImage bufferedImage = new BufferedImage(550, 400, BufferedImage.TYPE_INT_ARGB);
        BufferedImage image = SwingFXUtils.fromFXImage(snapShot, bufferedImage);
        Graphics2D gd = (Graphics2D) image.getGraphics();
        gd.translate(width, height);
        return image;
    }

    /**
     * Saves the files as an image file on a chosen Path
     * @param image
     * @param stage
     */
    public void saveFileAsImage(BufferedImage image, Stage stage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File outputFile = null;
        String fileName = "chart_" + LocalDate.now() + ".png";
        try {
            outputFile = new File(directoryChooser.showDialog(stage).getAbsolutePath() + "\\" + fileName);

        } catch (NullPointerException e){
            //Don nothing because it's ok if canceled
        }
        if (outputFile != null) {
            try {
                ImageIO.write(image, "png", outputFile);
                Util.showDialog(2, "Files saved", "Your file has been saved", "Your File " + fileName + " has been saved: \n" + outputFile);
            } catch (Exception e) {
                Util.showDialog(1, "Save Error", "The System was unable to save the file", "Please check your permissions");
            }
        }
    }

    /**
     * Prints the file on a chosen printer
     * @param imageView
     */
    public void printFile(ImageView imageView) {
        try {
            PrinterJob printerJob = PrinterJob.createPrinterJob();
            Printer printer = getPrinter();
            if (printer != null){
                printerJob.setPrinter(printer);
                PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
                scaleImageViewToPrintableSection(pageLayout, imageView);
                boolean success = printerJob.printPage(pageLayout, imageView);
                if (success) {
                    printerJob.endJob();
                    Util.showDialog(2, "Printing Successfully", "Printing Successfully", "Your file was successfully printed " );
                }
            }
        } catch (Exception e) {
            Util.showDialog(1, "Print Error", "The System was unable to print", "Please check the printer");
        }
    }

    /**
     * Gets all Printers from the system and shows a Dialog for the user to choose the desired printer
     * @return the chosen printer
     */
    private Printer getPrinter() {
        Printer printer = null;
        ChoiceDialog dialog = new ChoiceDialog(Printer.getDefaultPrinter(), Printer.getAllPrinters());
        dialog.setHeaderText("Choose the printer!");
        dialog.setContentText("Choose a printer from available printers");
        dialog.setTitle("Printer Choice");
        Optional<Printer> opt = dialog.showAndWait();
        if (opt.isPresent()) {
            printer = opt.get();
        }
        return printer;
    }

    /**
     * Scales a picture to the Printers A4 page size
     * @param pageLayout    The Layout of the printable page
     * @param imageView     The imageView to scale
     */
    private void scaleImageViewToPrintableSection(PageLayout pageLayout, ImageView imageView) {
        double pWidth = pageLayout.getPrintableWidth();
        double pHeight = pageLayout.getPrintableHeight();

        double nWidth = imageView.getBoundsInParent().getWidth();
        double nHeight = imageView.getBoundsInParent().getHeight();

        double widthLeft = pWidth - nWidth;
        double heightLeft = pHeight - nHeight;

        double scale;

        if (widthLeft < heightLeft) {
            scale = pWidth / nWidth;
        } else {
            scale = pHeight / nHeight;
        }

        imageView.getTransforms().add(new Scale(scale, scale));
    }
}
