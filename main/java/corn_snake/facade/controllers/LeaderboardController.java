package corn_snake.facade.controllers;

import corn_snake.Main;
import corn_snake.back_end.Score;
import corn_snake.facade.Facade;
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
import java.util.List;
import java.util.ResourceBundle;

public class LeaderboardController implements Initializable {

    private final static Facade FACADE = Main.FACADE;
    @FXML
    private AnchorPane window;

    @FXML
    private ImageView homeButton;

    private static final String[] COLUMNS = {"rank", "name", "score"};

    @FXML
    private Label
            title,
            rank, name, score,
            rank1, name1, score1,
            rank2, name2, score2,
            rank3, name3, score3,
            rank4, name4, score4,
            rank5, name5, score5,
            rank6, name6, score6,
            rank7, name7, score7,
            rank8, name8, score8,
            rank9, name9, score9,
            rank10, name10, score10;

    private int row, column;

    private List<Score> lb;

    @FXML
    public void onMainMenuClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) window.getScene().getWindow();
        IO.loadScene(stage, "menu/menu-view.fxml", MenuController.class, "menu/MenuStyle.css");
    }

    private Label getLabel(int row, int col) throws IndexOutOfBoundsException {
        if (row < 1 || row > 10 ) {
            throw new IndexOutOfBoundsException(" Row cannot be less than 1 or exceed 10.");
        }
        if (col < 0 || col > 2) {
            throw new IndexOutOfBoundsException(" Column cannot be less than 0 or exceed 2.");
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
        lb = FACADE.getLeaderboard();
        row = 1;

        Timeline load = new Timeline(
                new KeyFrame(Duration.millis(100), (event) -> {
                        try {
                            String rank = String.valueOf(row) + ".";
                            String name = lb.get(row - 1).getName();
                            int score = lb.get(row - 1).getScore();
                            Label rankLabel = getLabel(row, 0);
                            Label nameLabel = getLabel(row, 1);
                            Label scoreLabel = getLabel(row, 2);
                            rankLabel.setText(rank);
                            nameLabel.setText(name);
                            scoreLabel.setText(String.valueOf(score));
                        } catch (Exception e) {
                            Label rankLabel = getLabel(row, 0);
                            Label nameLabel = getLabel(row, 1);
                            Label scoreLabel = getLabel(row, 2);
                            rankLabel.setText("Foo");
                            nameLabel.setText("Bar");
                            scoreLabel.setText("5");

                        }
                        row++;
                    }
                )
        );
        load.setDelay(Duration.seconds(0.5));
        load.setCycleCount(10);
        load.play();
    }
}
