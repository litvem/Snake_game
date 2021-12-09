package corn_snake.facade.controllers;

import corn_snake.util.IO;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LeaderboardController {
    @FXML
    private AnchorPane window;

    @FXML
    private ImageView mainMenuButton;

    @FXML
    public void onMainMenuClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) window.getScene().getWindow();
        IO.loadScene(stage, "menu-view.fxml",MenuController.class);
    }



}
