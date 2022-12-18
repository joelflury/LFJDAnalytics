package controller;

import application.LFJDAnalyticsApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class PeriodPickerController {

    @FXML
    protected ChoiceBox cobPeriodPicker;
    @FXML
    protected Button btnChoose;
    @FXML
    protected Button btnCancel;

    @FXML
    protected void btnCancelClick(){
        Stage stage = (Stage)btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void btnChooseClick(){
        Stage stage = (Stage)btnChoose.getScene().getWindow();
        stage.close();
        ((AnalyseController) LFJDAnalyticsApplication.analyseLoader.getController()).setFreeTimePeriodLabelText(cobPeriodPicker.getValue().toString());
    }

    public void initializeComboboxData(){
        String[] periods = {"Last 7 Days", "Last Month", "Last 3 Months"};
        cobPeriodPicker.setItems(FXCollections.observableArrayList(periods));
    }

}
