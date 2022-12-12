package controller;

import application.LFJDAnalyticsApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TrendController {

    @FXML
    protected Button btnAnalyse;
    @FXML
    protected Button btnHome;
    @FXML
    protected Button btnReports;

    @FXML
    public void btnAnalyseClick() {
        Stage stage = (Stage)btnAnalyse.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.analyseScene);
    }
    @FXML
    public void btnHomeClick(){
        Stage stage = (Stage)btnHome.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.homeScene);
    }

    @FXML
    public void btnReportsClick(){
        Stage stage = (Stage)btnReports.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.reportScene);
    }

}
