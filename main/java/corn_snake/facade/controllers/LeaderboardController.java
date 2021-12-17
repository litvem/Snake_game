package corn_snake.facade.controllers;

import corn_snake.util.IO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {
    @FXML
    private AnchorPane window;

    @FXML
    private ImageView mainMenuButton;

    private static final String[] COLUMNS = {"rank", "name", "score"};

    @FXML
    private Label
            rank1, name1, score1,
            rank2, name2, score2,
            rank3, name3, score3,
            rank4, name4, score,
            rank5, name5, score5,
            rank6, name6, score6,
            rank7, name7, score7,
            rank8, name8, score8,
            rank9, name9, score9,
            rank10, name10, score10;
    @FXML
    private int row, column;

    @FXML
    public void onMainMenuClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) window.getScene().getWindow();
        IO.loadScene(stage, "menu-view.fxml", MenuController.class);
    }

    private Label getLabel(int row, int col) throws IndexOutOfBoundsException {
        if (row < 0 || row > 2 ) {
            throw new IndexOutOfBoundsException(" Row cannot be less than 0 or exceed 2.");
        }
        if (col < 1 || col > 10) {
            throw new IndexOutOfBoundsException(" Column cannot be less than 0 or exceed 10.");
        }
        try {
            return (Label) getClass().getDeclaredField(
                    String.format("%s%d", COLUMNS[col], row)
            ).get(LeaderboardController.this);
        } catch (Exception e) {
            return null;
        }
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO


    }
}
