package controller;

import application.LFJDAnalyticsApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import modell.Period;
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
    protected void btnCancelClick() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void btnChooseClick() {
        Stage stage = (Stage) btnChoose.getScene().getWindow();
        stage.close();
        AnalyseController.setLblFreeTimePeriodTextProperty(dpFromDate.getValue() + "\n-\n" + dpToDate.getValue());
    }

}
