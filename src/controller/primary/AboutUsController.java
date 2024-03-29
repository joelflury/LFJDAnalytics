package controller.primary;

import application.LFJDAnalyticsApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutUsController {
    @FXML
    protected Button btnHome;
    @FXML
    protected Button btnAnalyse;
    @FXML
    protected Button btnTrend;
    @FXML
    protected Label btnContact;

    /**
     * Calls the Home Scene
     */
    @FXML
    public void btnHomeClick() {
        Stage stage = (Stage) btnHome.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.getHomeScene());
    }

    /**
     * Calls the Analyze Scene
     */
    @FXML
    public void btnAnalyseClick() {
        Stage stage = (Stage) btnAnalyse.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.getAnalyseScene());
    }

    /**
     * Calls the Trend Scene
     */
    @FXML
    public void btnTrendClick() {
        Stage stage = (Stage) btnTrend.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.getTrendScene());
    }

    /**
     * Trys to Open the default email programm with info@lfjd-analytics.ch as sender
     */
    @FXML
    public void btnContact() {
        try {
            Desktop.getDesktop().mail(new URI("mailto:info@lfjd-analytics.ch"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
