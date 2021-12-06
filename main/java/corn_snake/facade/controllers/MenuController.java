package corn_snake.facade.controllers;

import corn_snake.util.IO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Label action;

    @FXML
    private AnchorPane window;

    @FXML
    public void onExitClick() {
        System.exit(0);
    }

    @FXML
    public void onPlayClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) window.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(
                FieldController.class.getResource("game_field.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load());

        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onCreditsClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) window.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(
                FieldController.class.getResource("credits.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load());

        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onLeaderboardClick(MouseEvent event) {
        action.setText("Leaderboard clicked!");
    }

    @FXML
    public void onHowToPlayClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) window.getScene().getWindow();

        IO.loadScene(stage, "how_to_play.fxml", HowToPlayController.class);

        /*
        FXMLLoader fxmlLoader = new FXMLLoader(
                FieldController.class.getResource("how_to_play.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load());

        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();

         */
    }
}
