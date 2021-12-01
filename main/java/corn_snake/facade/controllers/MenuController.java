package corn_snake.facade.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuController {

    @FXML
    private Label action;

    @FXML
    public void onExitClick() {
        System.exit(0);
    }

    @FXML
    public void onPlayClick() {
        action.setText("Play clicked!");
    }

    @FXML
    public void onCreditsClick() {
        action.setText("Credits clicked!");
    }

    @FXML
    public void onLeaderboardClick() {
        action.setText("Leaderboard clicked!");
    }

    @FXML
    public void onHowToPlayClick() {
        action.setText("How to play clicked!");
    }
}
