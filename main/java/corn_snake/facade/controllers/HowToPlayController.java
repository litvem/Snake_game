package corn_snake.facade.controllers;

import corn_snake.util.IO;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HowToPlayController {

    @FXML
    private AnchorPane window;

    @FXML
    public void onMainMenuClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) window.getScene().getWindow();
        IO.loadScene(stage, "menu/menu-view.fxml", MenuController.class, "menu/MenuStyle.css");
    }
}
