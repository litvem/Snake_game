package corn_snake.facade.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MenuController {

    @FXML
    private Label action;

    @FXML
    public void onExitClick() {
        System.exit(0);
    }

    @FXML
    public void onPlayClick(MouseEvent event) {
        action.setText("Play clicked!");
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
