package controller.secondary;

import application.LFJDAnalyticsApplication;
import controller.primary.AnalyseController;
import controller.primary.TrendController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import util.Util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static java.time.temporal.ChronoUnit.DAYS;

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
    private LocalDate fromDate;
    private LocalDate toDate;

    @FXML
    public void initialize() {
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
        if (cbTemplatePeriods.isDisabled()) {
            toDate = dpToDate.getValue();
            fromDate = dpFromDate.getValue();
        } else {
            getDatesFromTemplate();
        }
        try {
            if (LFJDAnalyticsApplication.getMainStage().getScene() == LFJDAnalyticsApplication.getAnalyseScene()) {
                if (toDate.isAfter(fromDate) && DAYS.between(fromDate, toDate.plusDays(1)) <= 365 && fromDate.isBefore(LocalDate.now().plusDays(1)) && toDate.isBefore(LocalDate.now().plusDays(1))) {
                    AnalyseController.setLblFreeTimePeriodTextProperty(fromDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + "\nto\n" + toDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
                    AnalyseController.setToDate(toDate);
                    AnalyseController.setFromDate(fromDate);
                    AnalyseController analyseController = LFJDAnalyticsApplication.getAnalyseLoader().getController();
                    analyseController.checkIfAllDataPresent();
                    Stage stage = (Stage) btnChoose.getScene().getWindow();
                    stage.close();
                } else {
                    Util.showAlert(2,"Wrong Dates selected", "Datepicker Problem", "Please select a valid Date");
                }
            } else {
                if (toDate.isAfter(fromDate) && DAYS.between(fromDate, toDate.plusDays(1)) <= 365 && fromDate.isAfter(LocalDate.now())) {
                    TrendController.setLblFreeTimePeriodTextProperty(fromDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + "\nto\n" + toDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
                    TrendController.setToDate(toDate);
                    TrendController.setFromDate(fromDate);
                    TrendController trendController = LFJDAnalyticsApplication.getTrendLoader().getController();
                    trendController.checkIfAllDataPresent();
                    Stage stage = (Stage) btnChoose.getScene().getWindow();
                    stage.close();
                } else {
                    Util.showAlert(2,"Wrong Dates selected", "Datepicker Problem", "Please select a valid Date");
                }
            }
        } catch (NullPointerException e) {
            Util.showAlert(2,"Wrong Dates selected", "Datepicker Problem", "Please select a valid Date");
        } catch (Exception e) {
            Util.showAlert(2,"Unexpected Error", "An unexpected Error occurred", "Please try again");
        }
        resetStageValues();
    }

    public void setPeriodsForChoiceBox() {
        if (LFJDAnalyticsApplication.getMainStage().getScene() == LFJDAnalyticsApplication.getAnalyseScene()) {
            cbTemplatePeriods.setItems(FXCollections.observableArrayList(Util.getPeriodNames()));
        } else {
            cbTemplatePeriods.setItems(FXCollections.observableArrayList(Util.getPeriodNamesForeCast()));
        }
    }

    private void getDatesFromTemplate() {
        int index = cbTemplatePeriods.getSelectionModel().getSelectedIndex();
        if (LFJDAnalyticsApplication.getMainStage().getScene() == LFJDAnalyticsApplication.getAnalyseScene()) {
            switch (index) {
                case 0:
                    toDate = LocalDate.now().minusDays(1);
                    fromDate = LocalDate.now().minusDays(7);
                    break;
                case 1:
                    toDate = LocalDate.now().minusDays(1);
                    fromDate = LocalDate.now().minusDays(31);
                    break;
                case 2:
                    toDate = LocalDate.now().minusDays(1);
                    fromDate = LocalDate.now().minusDays(90);
                    break;
            }
        } else {
            switch (index) {
                case 0:
                    toDate = LocalDate.now().plusDays(7);
                    fromDate = LocalDate.now().plusDays(1);
                    break;
                case 1:
                    toDate = LocalDate.now().plusDays(31);
                    fromDate = LocalDate.now().plusDays(7);
                    break;
                case 2:
                    toDate = LocalDate.now().plusDays(90);
                    fromDate = LocalDate.now().plusDays(1);
                    break;
            }
        }
    }

    private void resetStageValues() {
        cbTemplatePeriods.setValue(null);
        dpToDate.setValue(null);
        dpFromDate.setValue(null);
    }
}
