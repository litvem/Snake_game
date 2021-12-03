package corn_snake.facade.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Label action;

    @FXML
    public void onExitClick() {
        System.exit(0);
    }

    @FXML
    public void onPlayClick(MouseEvent event) throws IOException {
        action.setText("Play clicked!");
        Stage stage = (Stage) action.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(
                FieldController.class.getResource("game_field.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load());

        stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void onCreditsClick(MouseEvent event) {
        action.setText("Credits clicked!");
    }

    @FXML
    public void onLeaderboardClick(MouseEvent event) {
        action.setText("Leaderboard clicked!");
    }

    @FXML
    public void onHowToPlayClick(MouseEvent event) {
        action.setText("How to play clicked!");
    }
}
