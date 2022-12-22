package controller;

import application.LFJDAnalyticsApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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
    protected Label lblFreeTimePeriod;
    @FXML
    protected Label lblArticles;

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
//
//    public void setTimePeriodLabelText(String text){
//        lblPeriod.setText(text);
//    }
//
//    public void setFreeTimePeriodLabelText(String text){
//        lblFreeTimePeriod.setText(text);
//    }
//
//    public void setArticleLabelText(String text){
//        lblArticles.setText(text);
//    }


    public void btnFreePeriodClick() {
        LFJDAnalyticsApplication.secondaryStage.setScene(LFJDAnalyticsApplication.datePickerScene);
        LFJDAnalyticsApplication.secondaryStage.show();
    }

    public void btnTemplatePeriodClick() {
        ((PeriodPickerController)LFJDAnalyticsApplication.periodPickerLoader.getController()).initializeComboboxData();
        LFJDAnalyticsApplication.secondaryStage.setScene(LFJDAnalyticsApplication.periodPickerScene);
        LFJDAnalyticsApplication.secondaryStage.show();
    }

    public void btnChooseArticlesClick() {
        ((ArticlePickerController)LFJDAnalyticsApplication.articlePickerLoader.getController()).createCheckBoxes();
        LFJDAnalyticsApplication.secondaryStage.setScene(LFJDAnalyticsApplication.articlePickerScene);
        LFJDAnalyticsApplication.secondaryStage.show();
    }
}
