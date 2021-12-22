package corn_snake.facade.controllers;

import corn_snake.util.IO;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameOverController implements Initializable {

    @FXML
    private AnchorPane window;

    @FXML
    private ImageView background, board;

    @FXML
    private Label gameOver, clickPrompt;

    final static Image
            BACKGROUND = MenuController.BACKGROUND,
            BOARD = MenuController.PLAY;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        background.setImage(BACKGROUND);

        Timeline delay = new Timeline(
                new KeyFrame(Duration.seconds(0.2), (event) -> board.setImage(BOARD)),
                new KeyFrame(Duration.seconds(0.4), (event) -> gameOver.setText("GAME OVER")),
                new KeyFrame(Duration.seconds(0.6), (event) -> clickPrompt.setText("Click anywhere to continue"))
        );

        delay.setDelay(Duration.seconds(0.4));
        delay.play();
    }

    @FXML
    public void onClick(MouseEvent event) {
        try {
            IO.loadScene(getStage(), "save_score/save_score.fxml", SaveScoreController.class, "save_score/SaveScoreStyle.css");
        } catch (Exception ignored) {

        }
    }

    public Stage getStage() {
        return (Stage) window.getScene().getWindow();
    }
}
