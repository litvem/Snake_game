package corn_snake.facade.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class FieldController {
    @FXML
    private ImageView A1;
    @FXML
    private ImageView A2;


    private ImageView[] field;

    @FXML
    private Label score;

    @FXML
    private ImageView gameOver;

    public FieldController() {
        field = new ImageView[225];
        field[0] = A1;
    }
}
