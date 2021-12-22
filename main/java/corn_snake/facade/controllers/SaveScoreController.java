package corn_snake.facade.controllers;

import corn_snake.Main;
import corn_snake.facade.Facade;
import corn_snake.util.IO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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

    final static String EOL = IO.EOL;

    @FXML
    private AnchorPane window;

    @FXML
    private ImageView background, homeButton, saveButton, board;

    @FXML
    private Label scoreText, playerScore, enterName;

    @FXML
    private TextField nameField;

    final static Image
            BACKGROUND = MenuController.BACKGROUND,
            BOARD = MenuController.PLAY,
            SAVE = new Image(SaveScoreController.class.getResource("save_score/SaveButton.png").toExternalForm()),
            HOME = new Image(SaveScoreController.class.getResource("save_score/HomeButton.png").toExternalForm());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        background.setImage(BACKGROUND);
        int score = FACADE.getScore();

        Timeline load = new Timeline(
                new KeyFrame(Duration.seconds(0.2), (event) -> board.setImage(BOARD)),
                new KeyFrame(Duration.seconds(0.4), (event) -> scoreText.setText("Your score:")),
                new KeyFrame(Duration.seconds(0.6), (event) -> playerScore.setText(String.valueOf(score))),
                new KeyFrame(Duration.seconds(0.8), (event) -> {
                    enterName.setText(
                            String.format(
                                    "Enter your name to save your score%sor click the home button to skip",
                                    EOL
                                    )
                    );
                }),
                new KeyFrame(Duration.seconds(1), (event) -> {
                    nameField.setVisible(true);
                    nameField.setPromptText("Max. 12 characters");
                }),
                new KeyFrame(Duration.seconds(1.2), (event) -> saveButton.setImage(SAVE)),
                new KeyFrame(Duration.seconds(1.4), (event) -> homeButton.setImage(HOME))
        );

        load.setDelay(Duration.seconds(0.4));
        load.play();
    }

    @FXML
    public void onHomeClick() throws IOException {
        IO.loadScene(getStage(), "menu/menu-view.fxml", MenuController.class, "menu/MenuStyle.css");
    }

    @FXML
    public void onSaveClick() {
        String name = nameField.getText();
        int score = FACADE.getScore();

        FACADE.addScore(name, score);
        FACADE.saveLeaderboard();
        try {
            IO.loadScene(getStage(), "leaderboard/leaderboard.fxml", LeaderboardController.class);
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
