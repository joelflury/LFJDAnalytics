package controller;

import application.LFJDAnalyticsApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class AnalyseController {
    @FXML
    protected Button btnHome;
    @FXML
    protected Button btnTrend;
    @FXML
    protected Button btnReports;
    @FXML
    protected Label lblPeriod;

    @FXML
    public void btnHomeClick() {
        Stage stage = (Stage)btnHome.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.homeScene);
    }

    @FXML
    public void btnTrendClick(){
        Stage stage = (Stage)btnTrend.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.trendScene);
    }

    @FXML
    public void btnReportsClick(){
        Stage stage = (Stage)btnReports.getScene().getWindow();
        stage.setScene(LFJDAnalyticsApplication.reportScene);
    }

    public void setTimePeriodLabel(String text){
        lblPeriod.setText(text);
    }

    public void btnFreePeriodClick(ActionEvent actionEvent) {
        LFJDAnalyticsApplication.secondaryStage.setScene(LFJDAnalyticsApplication.datePickerScene);
        LFJDAnalyticsApplication.secondaryStage.show();
    }

    public void btnTemplatePeriodClick(ActionEvent actionEvent) {
        ((PeriodPickerController)LFJDAnalyticsApplication.periodPickerLoader.getController().);
        LFJDAnalyticsApplication.secondaryStage.setScene(LFJDAnalyticsApplication.periodPickerScene);
        LFJDAnalyticsApplication.secondaryStage.show();

    }
}
