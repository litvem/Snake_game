package corn_snake.facade.controllers;

import javafx.event.ActionEvent;
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
    public void onPlayClick(ActionEvent event) {
        action.setText("Play clicked!");
    }

    @FXML
    public void onCreditsClick(ActionEvent event) {
        action.setText("Credits clicked!");
    }

    @FXML
    public void onLeaderboardClick(ActionEvent event) {
        action.setText("Leaderboard clicked!");
    }

    @FXML
    public void onHowToPlayClick(ActionEvent event) {
        action.setText("How to play clicked!");
    }
}
