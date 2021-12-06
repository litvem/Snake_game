package corn_snake.facade.controllers;

import corn_snake.util.IO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private AnchorPane window;

    @FXML
    private ImageView titleView, playButton, controlsButton, leaderboardButton, creditsButton, exitButton;

    private Image title, play, controls, leaderboard, credits, exit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            title = new Image("file:///" + getClass().getResource("Title.png").getFile());
            play = new Image("file:///" + getClass().getResource("PlayButton.png").getFile());
            controls = new Image("file:///" + getClass().getResource("ControlsButton.png").getFile());
            leaderboard = new Image("file:///" + getClass().getResource("LeaderboardButton.png").getFile());
            credits = new Image("file:///" + getClass().getResource("CreditsButton.png").getFile());
            exit = new Image("file:///" + getClass().getResource("ExitButton.png").getFile());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }

        /*
        titleView.setImage(title);
        playButton.setImage(play);
        controlsButton.setImage(controls);
        leaderboardButton.setImage(leaderboard);
        creditsButton.setImage(credits);
        exitButton.setImage(exit);

         */

        LoadButtons loadButtons = new LoadButtons();
        Thread coro = new Thread(loadButtons);
        coro.start();
    }

    public void start() {

    }

    @FXML
    public void onExitClick() {
        System.exit(0);
    }

    @FXML
    public void onPlayClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) window.getScene().getWindow();

        IO.loadScene(stage, "game_field.fxml", FieldController.class);
    }

    @FXML
    public void onCreditsClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) window.getScene().getWindow();

        IO.loadScene(stage, "credits.fxml", CreditsController.class);
    }

    @FXML
    public void onLeaderboardClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) window.getScene().getWindow();

        IO.loadScene(stage, "leaderboard.fxml", LeaderboardController.class);
    }

    @FXML
    public void onHowToPlayClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) window.getScene().getWindow();

        IO.loadScene(stage, "how_to_play.fxml", HowToPlayController.class);
    }

    private class LoadButtons implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(1_000);
                titleView.setImage(title);
                Thread.sleep(500);
                playButton.setImage(play);
                Thread.sleep(500);
                controlsButton.setImage(controls);
                Thread.sleep(500);
                leaderboardButton.setImage(leaderboard);
                Thread.sleep(500);
                creditsButton.setImage(credits);
                Thread.sleep(500);
                exitButton.setImage(exit);
            } catch (InterruptedException e) {

            }
        }
    }
}
