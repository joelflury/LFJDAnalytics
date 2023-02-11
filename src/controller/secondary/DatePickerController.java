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

    /**
     * Closes the Stage and resets the Stage values
     */
    @FXML
    protected void btnCancelClick() {
        resetStageValues();
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Gets triggered when the Choose button is clicked
     * 1. Checks which Period format was chosen and based on this sets the values for from date and to date
     * 2. Checks which Controller did call this Scene
     * 3. Checks if the chosen periods are valid
     * 4. Sends back the Data to the calling scene
     */
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
                    Util.showDialog(2,"Wrong Dates selected", "Datepicker Problem", "Please select a valid Date");
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
                    Util.showDialog(2,"Wrong Dates selected", "Datepicker Problem", "Please select a valid Date");
                }
            }
        } catch (NullPointerException e) {
            Util.showDialog(2,"Wrong Dates selected", "Datepicker Problem", "Please select a valid Date");
        } catch (Exception e) {
            Util.showDialog(2,"Unexpected Error", "An unexpected Error occurred", "Please try again");
        }
        resetStageValues();
    }

    /**
     * 1. Checks which Controller did call this Scene
     * Calls the function to populate the Choicebox with the correct data
     */
    public void setPeriodsForChoiceBox() {
        if (LFJDAnalyticsApplication.getMainStage().getScene() == LFJDAnalyticsApplication.getAnalyseScene()) {
            cbTemplatePeriods.setItems(FXCollections.observableArrayList(Util.getPeriodNames()));
        } else {
            cbTemplatePeriods.setItems(FXCollections.observableArrayList(Util.getPeriodNamesForeCast()));
        }
    }

    /**
     * If a template has been chosen from the Datepicker, this method stes the crrect values for from and to date
     */
    private void getDatesFromTemplate() {
        int index = cbTemplatePeriods.getSelectionModel().getSelectedIndex();
        if (LFJDAnalyticsApplication.getMainStage().getScene() == LFJDAnalyticsApplication.getAnalyseScene()) {
            switch (index) {
                case 0:
                    toDate = LocalDate.now();
                    fromDate = LocalDate.now().minusDays(7);
                    break;
                case 1:
                    toDate = LocalDate.now();
                    fromDate = LocalDate.now().minusDays(30);
                    break;
                case 2:
                    toDate = LocalDate.now();
                    fromDate = LocalDate.now().minusDays(90);
                    break;
            }
        } else {
            switch (index) {
                case 0:
                    toDate = LocalDate.now().plusDays(8);
                    fromDate = LocalDate.now().plusDays(1);
                    break;
                case 1:
                    toDate = LocalDate.now().plusDays(31);
                    fromDate = LocalDate.now().plusDays(1);
                    break;
                case 2:
                    toDate = LocalDate.now().plusDays(91);
                    fromDate = LocalDate.now().plusDays(1);
                    break;
            }
        }
    }

    /**
     * resets the satge values
     */
    private void resetStageValues() {
        cbTemplatePeriods.setValue(null);
        dpToDate.setValue(null);
        dpFromDate.setValue(null);
    }
}
