package corn_snake.front_end.controllers;

import corn_snake.Main;
import corn_snake.facade.Facade;
import corn_snake.util.IO;
import corn_snake.util.FX;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    static final Image
            BACKGROUND = FX.BACKGROUND,
            BOARD = FX.SMALL_BOARD,
            SAVE = new Image(SaveScoreController.class.getResource("save_score/SaveButton.png").toExternalForm()),
            HOME = FX.HOME;

    /**
     * This method is implicitly called by JavaFX.
     * Initializes the "save score" screen.
     * A short {@link Timeline} makes a wooden board and different components appear in quick succession
     */
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

    /**
     * Called when the home button is clicked.
     * Returns to the title screen without saving the player's score
     */
    @FXML
    public void onHomeClick() {
        try {
            FX.loadScene(getStage(), "menu/menu-view.fxml", MenuController.class, "menu/MenuStyle.css");
        } catch (IOException ignored) {

        }
    }

    /**
     * Called when the save button is clicked.
     * Saves the player's score with the name they entered.
     * The name and score gets checked by the Facade.
     */
    @FXML
    public void onSaveClick() {
        String name = nameField.getText();
        int score = FACADE.getScore();

        FACADE.addScore(name, score);
        FACADE.saveLeaderboard();
        try {
            FX.loadScene(getStage(), "leaderboard/leaderboard.fxml", LeaderboardController.class, "leaderboard/LeaderboardStyle.css");
        } catch (Exception ignored) {

        }
    }

    /**
     * Called whenever the cursor hovers over a button.
     * The button gets enlarged by 10%
     * @param event the {@link MouseEvent} used to fetch the button
     */
    @FXML
    public void onHover(MouseEvent event) {
        Node node = (Node) event.getTarget();
        if (node.getId().equals("homeButton") || node.getId().equals("saveButton")) {
            FX.scale(node, 1.1);
        }
    }

    /**
     * Called whenever the cursor stops hovering over a button.
     * The button reverts to its original size
     * @param event the {@link MouseEvent} used to fetch the button
     */
    @FXML
    public void onUnHover(MouseEvent event) {
        Node node = (Node) event.getTarget();
        if (node.getId().equals("homeButton") || node.getId().equals("saveButton")) {
            FX.scale(node, 1);
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
