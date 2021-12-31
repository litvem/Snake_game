package corn_snake.facade.controllers;

import corn_snake.Main;
import corn_snake.util.IO;
import corn_snake.util.Images;
import corn_snake.util.Scale;
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

    // Images used in the title screen
    final static Image
            BACKGROUND = Images.BACKGROUND,
            TITLE = new Image(MenuController.class.getResource("menu/Title.png").toExternalForm()),
            PLAY = Images.SMALL_BOARD,
            CONTROLS = new Image(MenuController.class.getResource("menu/ControlsButton.png").toExternalForm()),
            LEADERBOARD = new Image(MenuController.class.getResource("menu/LeaderboardButton.png").toExternalForm()),
            CREDITS = Images.LOGO,
            EXIT = new Image(MenuController.class.getResource("menu/ExitButton.png").toExternalForm());

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

        load.setDelay(Duration.seconds(0.8));
        load.play();
    }

    @FXML
    public void onExitClick() {
        System.exit(0);
    }

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

    @FXML
    public void onCreditsClick() {
        try {
            IO.loadScene(getStage(), "credits/credits.fxml", CreditsController.class, "credits/CreditsStyle.css");
        } catch (IOException ignored) {

        }
    }

    @FXML
    public void onLeaderboardClick() {
        try {
            IO.loadScene(getStage(), "leaderboard/leaderboard.fxml", LeaderboardController.class, "leaderboard/LeaderboardStyle.css");
        } catch (IOException ignored) {

        }
    }

    @FXML
    public void onHowToPlayClick() {
        try {
            IO.loadScene(getStage(), "how_to_play/how_to_play.fxml", HowToPlayController.class, "how_to_play/HowToPlayStyle.css");
        } catch (IOException ignored) {

        }
    }

    @FXML
    public void onHover(MouseEvent event) {
        scale((Node) event.getTarget(), 1.1);
    }

    @FXML
    public void onUnHover(MouseEvent event) {
        scale((Node) event.getTarget(), 1);
    }

    private void scale(Node node, double value) {
        final String[] TEMP = {"playButton", "playLabel", "exitButton", "creditsButton", "leaderboardButton", "controlsButton"};
        final ArrayList<String> IDS = new ArrayList<>(Arrays.asList(TEMP));

        if (!IDS.contains(node.getId())) return;

        Scale.scale(node, value);
        if (node.getId().equals("playLabel")) {
            Scale.scale(playButton, value);
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
