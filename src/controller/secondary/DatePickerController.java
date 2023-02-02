package controller.secondary;

import application.LFJDAnalyticsApplication;
import controller.primary.AnalyseController;
import controller.primary.TrendController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import modell.Period;
import util.Util;

import java.net.DatagramSocket;
import java.time.Duration;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
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
//        btnChoose.disableProperty().bind(cbTemplatePeriods.valueProperty().isNull().orElse(!dpFromDate.disableProperty().getValue()));
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
        if (LFJDAnalyticsApplication.getMainStage().getScene() == LFJDAnalyticsApplication.analyseScene) {
            if (toDate.isAfter(fromDate) && DAYS.between(fromDate, toDate.plusDays(1)) <= 365 && fromDate.isBefore(LocalDate.now().plusDays(1)) && toDate.isBefore(LocalDate.now().plusDays(1))) {
                AnalyseController.setLblFreeTimePeriodTextProperty(fromDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + "\nto\n" + toDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
                AnalyseController.setToDate(toDate);
                AnalyseController.setFromDate(fromDate);
                AnalyseController analyseController = LFJDAnalyticsApplication.analyseLoader.getController();
                analyseController.checkIfAllDataPresent();
                Stage stage = (Stage) btnChoose.getScene().getWindow();
                stage.close();
            } else {
                Util.showAlert("Wrog Dates selected", "Datepicker Problem", "Please select a valid Date");
            }
        } else {
            if (toDate.isAfter(fromDate) && DAYS.between(fromDate, toDate.plusDays(1)) <= 365 && fromDate.isAfter(LocalDate.now())) {
                TrendController.setLblFreeTimePeriodTextProperty(fromDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + "\nto\n" + toDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
                TrendController.setToDate(toDate);
                TrendController.setFromDate(fromDate);
                TrendController trendController = LFJDAnalyticsApplication.trendLoader.getController();
                trendController.checkIfAllDataPresent();
                Stage stage = (Stage) btnChoose.getScene().getWindow();
                stage.close();
            } else {
                Util.showAlert("Wrog Dates selected", "Datepicker Problem", "Please select a valid Date");
            }
        }
        resetStageValues();
    }

    public void setPeriodsForChoiceBox() {
        if (LFJDAnalyticsApplication.getMainStage().getScene() == LFJDAnalyticsApplication.analyseScene) {
            cbTemplatePeriods.setItems(FXCollections.observableArrayList(Period.periodNames));
        } else {
            cbTemplatePeriods.setItems(FXCollections.observableArrayList(Period.periodNamesForeCast));
        }
    }

    private void getDatesFromTemplate() {
        int index = cbTemplatePeriods.getSelectionModel().getSelectedIndex();
        if (LFJDAnalyticsApplication.getMainStage().getScene() == LFJDAnalyticsApplication.analyseScene) {
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
