package corn_snake.facade.controllers;

import corn_snake.Main;
import corn_snake.back_end.Score;
import corn_snake.facade.Facade;
import corn_snake.util.IO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SaveScoreController implements Initializable {

    final static Facade FACADE = Main.FACADE;

    @FXML
    private AnchorPane window;

    @FXML
    private ImageView background, homeButton, saveButton;

    @FXML
    private Label scoreText, playerScore, enterName;

    @FXML
    private TextField nameField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline load = new Timeline(
                new KeyFrame(Duration.seconds(0.1), (event) -> nameField.setPromptText("Max. 12 characters"))
        );

        load.setDelay(Duration.seconds(0.4));
        load.play();
    }

    @FXML
    public void onHomeClick(MouseEvent event) throws IOException {
        IO.loadScene(getStage(), "menu/menu-view.fxml", MenuController.class, "menu/MenuStyle.css");
    }

    @FXML
    public void onSaveClick(MouseEvent event) {
        String name = nameField.getText();
        int score = FACADE.getScore();

        FACADE.addScore(name, score);
        try {
            IO.loadScene(getStage(), "leaderboard.fxml", LeaderboardController.class);
        } catch (Exception ignored) {

        }
    }

    /**
     * Retrieves the current stage that's being used
     *
     * @return the current stage
     */
    private Stage getStage() {
        return (Stage) window.getScene().getWindow();
    }
}
