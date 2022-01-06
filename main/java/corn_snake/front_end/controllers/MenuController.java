package corn_snake.front_end.controllers;

import corn_snake.Main;
import corn_snake.util.FX;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private AnchorPane window;

    @FXML
    private ImageView backgroundView, titleView, playButton, controlsButton, leaderboardButton, creditsButton, exitButton;

    @FXML
    private Label playLabel, versionLabel;

    // FX used in the title screen
    final static Image
            BACKGROUND = FX.BACKGROUND,
            TITLE = new Image(MenuController.class.getResource("menu/Title.png").toExternalForm()),
            PLAY = FX.SMALL_BOARD,
            CONTROLS = new Image(MenuController.class.getResource("menu/ControlsButton.png").toExternalForm()),
            LEADERBOARD = new Image(MenuController.class.getResource("menu/LeaderboardButton.png").toExternalForm()),
            CREDITS = FX.LOGO,
            EXIT = new Image(MenuController.class.getResource("menu/ExitButton.png").toExternalForm());

    /**
     * This method is implicitly called by JavaFX.
     * Initializes the title screen.
     * A short {@link Timeline} makes the title and various buttons appear in quick succession
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundView.setImage(BACKGROUND);

        // Loads all visuals with a 0.2 seconds delay between each image/button
        Timeline load = new Timeline(
                new KeyFrame(Duration.seconds(0.1), (event) -> titleView.setImage(TITLE)),
                new KeyFrame(Duration.seconds(0.2), (event) -> {
                    playButton.setImage(PLAY);
                    playLabel.setText("PLAY");
                }),
                new KeyFrame(Duration.seconds(0.3), (event) -> versionLabel.setText(String.format("Version: %s", Main.VERSION))),
                new KeyFrame(Duration.seconds(0.4), (event) -> controlsButton.setImage(CONTROLS)),
                new KeyFrame(Duration.seconds(0.5), (event) -> leaderboardButton.setImage(LEADERBOARD)),
                new KeyFrame(Duration.seconds(0.6), (event) -> creditsButton.setImage(CREDITS)),
                new KeyFrame(Duration.seconds(0.7), (event) -> exitButton.setImage(EXIT))
        );

        load.setDelay(Duration.seconds(0.2));
        load.play();
    }

    /**
     * Called when the exit button is clicked. Exits the application
     */
    @FXML
    public void onExitClick() {
        System.exit(0);
    }

    /**
     * Called when the play button is clicked.
     * Sets an onKeyPressed {@link javafx.event.EventHandler} and changes scene to the game scene
     */
    @FXML
    public void onPlayClick() {
        try {
            Stage stage = getStage();
            Class<FieldController> clazz = FieldController.class;
            FXMLLoader fxmlLoader = new FXMLLoader(clazz.getResource("field/game_field.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            // Create anonymous event handler for the game field
            FieldController controller = fxmlLoader.getController();
            // onKeyPressed calls onKeyPressed(String command) in FieldController
            scene.setOnKeyPressed((event) -> controller.onKeyPressed(event.getCode().toString()));

            scene.getStylesheets().add(clazz.getResource("field/FieldStyle.css").toExternalForm());

            stage.setResizable(false);

            stage.setScene(scene);
            stage.show();
        } catch (Exception ignored) {

        }
    }

    /**
     * Called when the credits button is clicked.
     * Changes scene to the credits scene
     */
    @FXML
    public void onCreditsClick() {
        try {
            FX.loadScene(getStage(), "credits/credits.fxml", CreditsController.class, "credits/CreditsStyle.css");
        } catch (IOException ignored) {

        }
    }

    /**
     * Called when the leaderboard button is clicked.
     * Changes scene to the leaderboard scene
     */
    @FXML
    public void onLeaderboardClick() {
        try {
            FX.loadScene(getStage(), "leaderboard/leaderboard.fxml", LeaderboardController.class, "leaderboard/LeaderboardStyle.css");
        } catch (IOException ignored) {

        }
    }

    /**
     * Called when the "how to play" button is clicked.
     * Changes scene to the "how to play" scene
     */
    @FXML
    public void onHowToPlayClick() {
        try {
            FX.loadScene(getStage(), "how_to_play/how_to_play.fxml", HowToPlayController.class, "how_to_play/HowToPlayStyle.css");
        } catch (IOException ignored) {

        }
    }

    /**
     * Called whenever the cursor hovers over a button.
     * The button gets enlarged by 10%
     * @param event the {@link MouseEvent} used to fetch the button
     */
    @FXML
    public void onHover(MouseEvent event) {
        scale((Node) event.getTarget(), 1.1);
    }

    /**
     * Called whenever the cursor stops hovering over a button.
     * The button reverts to its original size
     * @param event the {@link MouseEvent} used to fetch the button
     */
    @FXML
    public void onUnHover(MouseEvent event) {
        scale((Node) event.getTarget(), 1);
    }

    /**
     * Checks if a {@link Node} can be scaled.
     * The {@code playButton} Node get scaled along with the {@code playLabel} Node
     * @param node Node to be scaled
     * @param value decimal value of the final scaling.
     *              1 translates to a Node's initial size while 1.1 translates to 110% its initial size
     */
    private void scale(Node node, double value) {
        final String[] TEMP = {"playButton", "playLabel", "exitButton", "creditsButton", "leaderboardButton", "controlsButton"};
        final ArrayList<String> IDS = new ArrayList<>(Arrays.asList(TEMP));

        if (!IDS.contains(node.getId())) return;

        FX.scale(node, value);
        if (node.getId().equals("playLabel")) {
            FX.scale(playButton, value);
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
