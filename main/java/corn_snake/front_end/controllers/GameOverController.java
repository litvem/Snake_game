package corn_snake.front_end.controllers;

import corn_snake.util.FX;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
            BACKGROUND = FX.BACKGROUND,
            BOARD = FX.SMALL_BOARD;

    /**
     * This method is implicitly called by JavaFX.
     * Initializes the "game over" screen.
     * A short {@link Timeline} makes a wooden board and different labels appear in quick succession
     */
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

    /**
     * Called when the screen is clicked.
     * Changes to the "save score" scene
     */
    @FXML
    public void onClick() {
        try {
            FX.loadScene(getStage(), "save_score/save_score.fxml", SaveScoreController.class, "save_score/SaveScoreStyle.css");
        } catch (Exception ignored) {

        }
    }

    /**
     * Retrieves the current stage that's being used
     *
     * @return the current stage
     */
    public Stage getStage() {
        return (Stage) window.getScene().getWindow();
    }
}
