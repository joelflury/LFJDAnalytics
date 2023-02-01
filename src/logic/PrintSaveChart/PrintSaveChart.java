package logic.PrintSaveChart;

import javafx.embed.swing.SwingFXUtils;
import javafx.print.*;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Scale;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class PrintSaveChart {

    public BufferedImage createPicture(WritableImage snapShot, double width, double height){
        BufferedImage bufferedImage = new BufferedImage(550, 400, BufferedImage.TYPE_INT_ARGB);
        BufferedImage image = SwingFXUtils.fromFXImage(snapShot, bufferedImage);
        Graphics2D gd = (Graphics2D) image.getGraphics();
        gd.translate(width, height);
        return image;
    }
    public void saveFileAsImage(BufferedImage image, Stage stage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File outputFile = new File(directoryChooser.showDialog(stage).getAbsolutePath() + "\\chart" + LocalDate.now() + ".png");
        try {
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printFile(ImageView imageView) {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        Printer printer = getPrinter();
        printerJob.setPrinter(printer);
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
        scaleImageViewToPrintableSection(pageLayout, imageView);
        boolean success = printerJob.printPage(pageLayout, imageView);
        if(success){
            printerJob.endJob();
        }
    }

    private Printer getPrinter(){
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

    private void scaleImageViewToPrintableSection(PageLayout pageLayout, ImageView imageView) {
        double pWidth = pageLayout.getPrintableWidth();
        double pHeight = pageLayout.getPrintableHeight();

        double nWidth = imageView.getBoundsInParent().getWidth();
        double nHeight = imageView.getBoundsInParent().getHeight();

        double widthLeft = pWidth - nWidth;
        double heightLeft = pHeight - nHeight;

        double scale = 0;

        if (widthLeft < heightLeft) {
            scale = pWidth / nWidth;
        } else {
            scale = pHeight / nHeight;
        }

        imageView.getTransforms().add(new Scale(scale, scale));
    }
}
