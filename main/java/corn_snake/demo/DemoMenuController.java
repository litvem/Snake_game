package corn_snake.demo;

import corn_snake.facade.controllers.CreditsController;
import corn_snake.facade.controllers.FieldController;
import corn_snake.facade.controllers.HowToPlayController;
import corn_snake.facade.controllers.LeaderboardController;
import corn_snake.util.IO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DemoMenuController implements Initializable {

    @FXML
    private AnchorPane window;

    @FXML
    private ImageView backgroundView, titleView, playButton, controlsButton, leaderboardButton, creditsButton, exitButton;

    private static Image background, title, play, controls, leaderboard, credits, exit;

    static {
        try {
            background = new Image("file:///" + DemoMenuController.class.getResource("TitleBG.png").getFile());
            title = new Image("file:///" + DemoMenuController.class.getResource("Title.png").getFile());
            play = new Image("file:///" + DemoMenuController.class.getResource("PlayButton.png").getFile());
            controls = new Image("file:///" + DemoMenuController.class.getResource("ControlsButton.png").getFile());
            leaderboard = new Image("file:///" + DemoMenuController.class.getResource("LeaderboardButton.png").getFile());
            credits = new Image("file:///" + DemoMenuController.class.getResource("CreditsButton.png").getFile());
            exit = new Image("file:///" + DemoMenuController.class.getResource("ExitButton.png").getFile());
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backgroundView.setImage(background);

        // Loads all visuals with a 0.2 seconds delay between each image/button
        Timeline load = new Timeline(
                new KeyFrame(Duration.seconds(0.2), (event) -> titleView.setImage(title)),
                new KeyFrame(Duration.seconds(0.4), (event) -> playButton.setImage(play)),
                new KeyFrame(Duration.seconds(0.6), (event) -> controlsButton.setImage(controls)),
                new KeyFrame(Duration.seconds(0.8), (event) -> leaderboardButton.setImage(leaderboard)),
                new KeyFrame(Duration.seconds(1), (event) -> creditsButton.setImage(credits)),
                new KeyFrame(Duration.seconds(1.2), (event) -> exitButton.setImage(exit))
        );
        load.setDelay(Duration.seconds(0.5));

        load.play();
    }

    @FXML
    public void onExitClick() {
        System.exit(0);
    }

    @FXML
    public void onPlayClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) window.getScene().getWindow();

        IO.loadScene(stage, "game_field.fxml", DemoFieldController.class, "FieldStyle.css");
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
}
