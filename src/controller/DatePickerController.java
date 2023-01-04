package controller;

import application.LFJDAnalyticsApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import modell.Period;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.IllegalFormatCodePointException;

public class DatePickerController {
    @FXML
    protected Button btnCancel;
    @FXML
    protected Button btnChoose;
    @FXML
    protected DatePicker dpFromDate;
    @FXML
    protected DatePicker dpToDate;
    @FXML
    protected ChoiceBox cbTemplatePeriods;

    @FXML
    public void initialize(){
        cbTemplatePeriods.setItems(FXCollections.observableArrayList(Period.periodNames));
        dpFromDate.disableProperty().bind(cbTemplatePeriods.valueProperty().isNotNull());
        dpToDate.disableProperty().bind(cbTemplatePeriods.valueProperty().isNotNull());
        cbTemplatePeriods.disableProperty().bind(dpFromDate.valueProperty().isNotNull().or(dpToDate.valueProperty().isNotNull()));
    }

    @FXML
    protected void btnCancelClick() {
        resetStageValues();
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void btnChooseClick() {
        Stage stage = (Stage) btnChoose.getScene().getWindow();
        stage.close();
        if (LFJDAnalyticsApplication.getMainStage().getScene() == LFJDAnalyticsApplication.analyseScene){
            if (cbTemplatePeriods.isDisabled()){
                AnalyseController.setLblFreeTimePeriodTextProperty(dpFromDate.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + "\nto\n" + dpToDate.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
            } else {
                AnalyseController.setLblFreeTimePeriodTextProperty(cbTemplatePeriods.getValue().toString());
            }
        } else {
            if (cbTemplatePeriods.isDisabled()){
                TrendController.setLblFreeTimePeriodTextProperty(dpFromDate.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + "\nto\n" + dpToDate.getValue().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
            } else {
                TrendController.setLblFreeTimePeriodTextProperty(cbTemplatePeriods.getValue().toString());
            }
        }
        resetStageValues();
    }

    private void resetStageValues() {
        cbTemplatePeriods.setValue(null);
        dpToDate.setValue(null);
        dpFromDate.setValue(null);
    }

}
