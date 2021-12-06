package corn_snake.facade.controllers;

import corn_snake.util.IO;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOverController {

    @FXML
    private AnchorPane window;

    @FXML
    public void onClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) window.getScene().getWindow();
        IO.loadScene(stage, "save_score.fxml",SaveScoreController.class);
    }
}
