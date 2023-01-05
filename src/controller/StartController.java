package controller;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class StartController {

    @FXML
    protected ImageView lfjdLogo;
    @FXML
    public void initialize(){
        FadeTransition trans = new FadeTransition(Duration.seconds(2), lfjdLogo);

        trans.setFromValue(1.0);
        trans.setToValue(.20);

        // Let the animation run forever
        trans.setCycleCount(FadeTransition.INDEFINITE);

        // Reverse direction on alternating cycles
        trans.setAutoReverse(true);

        // Play the Animation
        trans.play();
    }

}
