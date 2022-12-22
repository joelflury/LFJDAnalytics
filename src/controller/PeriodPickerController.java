package controller;

import application.LFJDAnalyticsApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import modell.Period;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;

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
        Period.periodFromTemplates(Arrays.asList(Period.periodNames).indexOf(cobPeriodPicker.getValue()));
        Stage stage = (Stage)btnChoose.getScene().getWindow();
        stage.close();
    }

    public void initializeComboboxData(){
        cobPeriodPicker.setItems(FXCollections.observableArrayList(Period.periodNames));
    }

}
