package controller;

import application.LFJDAnalyticsApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReportController {

    @FXML
    protected Button btnAnalyse;
    @FXML
    protected Button btnTrend;
    @FXML
    protected Button btnHome;

    @FXML
    public void btnAnalyseClick() {
        Stage stage = (Stage)btnAnalyse.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.analyseScene);
    }
    @FXML
    public void btnTrendClick(){
        Stage stage = (Stage)btnTrend.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.trendScene);
    }

    @FXML
    public void btnHomeClick(){
        Stage stage = (Stage)btnHome.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.homeScene);
    }

}
