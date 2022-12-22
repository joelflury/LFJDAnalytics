package controller;

import application.LFJDAnalyticsApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    public void btnChooseClick() {
        int articleAmount = 0;
        String returnText = "";
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        for (CheckBox cb:checkBoxList) {
            if (cb.isSelected()){
                articleAmount++;
            }
        }
        if (articleAmount == checkBoxList.size()){
            returnText = "All Articles";
        } else {
            returnText = articleAmount + " Articles";
        }

    }

    public void btnCancelClick() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void createCheckBoxes(){
        checkBoxList.clear();
        vBoxRight.getChildren().clear();
        vBoxLeft.getChildren().clear();
        String[] articles = {"Hose", "Schuhe", "Jacke", "Kappe"};
        for (int i = 0; i <articles.length; i++) {
            CheckBox cbArticle = new CheckBox(articles[i]);
            cbArticle.setStyle("-fx-text-fill: white");
            checkBoxList.add(cbArticle);
            VBox.setMargin(cbArticle, new Insets(0,0,5, 0));
            if (i%2!=0){
                vBoxLeft.getChildren().add(cbArticle);
            } else {
                vBoxRight.getChildren().add(cbArticle);
            }
        }
    }
}
