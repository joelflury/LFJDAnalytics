package controller.secondary;

import application.LFJDAnalyticsApplication;
import controller.primary.AnalyseController;
import controller.primary.TrendController;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.consumer.Consumer;
import modell.Article;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class ArticlePickerController {

    @FXML
    protected VBox vBoxLeft;
    @FXML
    protected VBox vBoxRight;
    @FXML
    protected Button btnCancel;
    @FXML
    protected Button btnChoose;
    public List<CheckBox> checkBoxList = new ArrayList<>();
    public List<Article> chosenArticleList = new ArrayList<>();

    public void btnChooseClick() {
        boolean isAnyBoxChecked = false;
        for (CheckBox cb : checkBoxList) {
            if (cb.isSelected()) {
                isAnyBoxChecked = true;
                break;
            }
        }
        if (isAnyBoxChecked) {
            chosenArticleList.clear();
            int articleAmount = 0;
            String returnText;
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
            List<Article> articleList = Consumer.getArticles().getArticles();
            for (CheckBox cb : checkBoxList) {
                if (cb.isSelected()) {
                    articleAmount++;
                    for (Article article : articleList) {
                        if (article.getArticlename().equals(cb.getText())) {
                            chosenArticleList.add(article);
                        }
                    }
                }
            }
            if (articleAmount == checkBoxList.size()) {
                returnText = "All Articles";
            } else {
                returnText = articleAmount + " Articles";
            }
            if (LFJDAnalyticsApplication.getMainStage().getScene() == LFJDAnalyticsApplication.analyseScene) {
                AnalyseController.setLblArticlesTextProperty(returnText);
                AnalyseController.setChosenArticleList(chosenArticleList);
                AnalyseController analyseController = LFJDAnalyticsApplication.analyseLoader.getController();
                analyseController.checkIfAllDataPresent();
            } else {
                TrendController.setLblArticlesTextProperty(returnText);
                TrendController.setChosenArticleList(chosenArticleList);
                TrendController trendController = LFJDAnalyticsApplication.trendLoader.getController();
                trendController.checkIfAllDataPresent();
            }
            resetStageValues();
        } else {
            Util.showAlert(2,"No Article selected", "Articlepicker Problem", "Please select at least one Article");
        }
    }

    public void btnCancelClick() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        resetStageValues();
    }

    public void createCheckBoxes() {
        checkBoxList.clear();
        vBoxRight.getChildren().clear();
        vBoxLeft.getChildren().clear();
        List<Article> articleList = Consumer.getArticles().getArticles();
        for (int i = 0; i < articleList.size(); i++) {
            CheckBox cbArticle = new CheckBox(articleList.get(i).getArticlename());
            cbArticle.setStyle("-fx-text-fill: white");
            checkBoxList.add(cbArticle);
            VBox.setMargin(cbArticle, new Insets(0, 0, 5, 0));
            if (i % 2 != 0) {
                vBoxLeft.getChildren().add(cbArticle);
            } else {
                vBoxRight.getChildren().add(cbArticle);
            }
        }
    }

    private void resetStageValues() {
        for (CheckBox cb : checkBoxList) {
            cb.setSelected(false);
        }
    }

}
