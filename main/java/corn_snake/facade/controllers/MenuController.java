package corn_snake.facade.controllers;

import corn_snake.Main;
import corn_snake.util.IO;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
            BACKGROUND = new Image(MenuController.class.getResource("menu/TitleBG.png").toExternalForm()),
            TITLE = new Image(MenuController.class.getResource("menu/Title.png").toExternalForm()),
            PLAY = new Image(MenuController.class.getResource("menu/PlayButton.png").toExternalForm()),
            CONTROLS = new Image(MenuController.class.getResource("menu/ControlsButton.png").toExternalForm()),
            LEADERBOARD = new Image(MenuController.class.getResource("menu/LeaderboardButton.png").toExternalForm()),
            CREDITS = new Image(MenuController.class.getResource("menu/CreditsButton.png").toExternalForm()),
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
            ignored.printStackTrace();
        }
    }

    @FXML
    public void onCreditsClick() {
        try {
            IO.loadScene(getStage(), "credits/credits.fxml", CreditsController.class);
        } catch (IOException ignored) {

        }
    }

    @FXML
    public void onLeaderboardClick() {
        try {
            IO.loadScene(getStage(), "leaderboard/leaderboard.fxml", LeaderboardController.class);
        } catch (IOException ignored) {

        }
    }

    @FXML
    public void onHowToPlayClick() {
        try {
            IO.loadScene(getStage(), "how_to_play/how_to_play.fxml", HowToPlayController.class);
        } catch (IOException ignored) {

        }
    }

            //scale buttons up
    @FXML
        public void largeExitButton() {

        double x = 1.1;
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(exitButton);
        scale.setToY(x);
        scale.setToX(x);
        scale.setDuration(Duration.millis(50));
        scale.play();

    }
    @FXML
    public void largecontrolButton() {

        double x = 1.1;
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(controlsButton);
        scale.setToY(x);
        scale.setToX(x);
        scale.setDuration(Duration.millis(50));
        scale.play();

    }@FXML
    public void largeleaderboardButton() {

        double x = 1.1;
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(leaderboardButton);
        scale.setToY(x);
        scale.setToX(x);
        scale.setDuration(Duration.millis(50));
        scale.play();

    }

    @FXML
    public void largecreditsButton() {

        double x = 1.1;
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(creditsButton);
        scale.setToY(x);
        scale.setToX(x);
        scale.setDuration(Duration.millis(50));
        scale.play();

    }



        // scale buttons back
    @FXML
        public void smallexitButton() {
        double y = 1.0;
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(exitButton);
        scale.setToY(y);
        scale.setToX(y);
        scale.setDuration(Duration.millis(50));
        scale.play();

    }@FXML
    public void smallcontrolButton() {
        double y = 1.0;
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(controlsButton);
        scale.setToY(y);
        scale.setToX(y);
        scale.setDuration(Duration.millis(50));
        scale.play();

    }@FXML
    public void smallleaderboardButton() {
        double y = 1.0;
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(leaderboardButton);
        scale.setToY(y);
        scale.setToX(y);
        scale.setDuration(Duration.millis(50));
        scale.play();

    }@FXML
    public void smallcreditsButton() {
        double y = 1.0;
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(creditsButton);
        scale.setToY(y);
        scale.setToX(y);
        scale.setDuration(Duration.millis(50));
        scale.play();

    }

    /**
     * Retrieves the current stage that's being used
     *
     * @return the current stage
     */
    private Stage getStage() {
        return (Stage) window.getScene().getWindow();
    }
    @FXML
    public void largeplayLabel() {
        double x = 1.1;
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(playLabel);
        scale.setToY(x);
        scale.setToX(x);
        scale.setDuration(Duration.millis(50));
        scale.play();
    }@FXML
    public void smallplayLabel() {
        double y = 1.0;
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(playLabel);
        scale.setToY(y);
        scale.setToX(y);
        scale.setDuration(Duration.millis(50));
        scale.play();

    }


}
