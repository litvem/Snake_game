package corn_snake.front_end.controllers;

import corn_snake.Main;
import corn_snake.back_end.Score;
import corn_snake.facade.Facade;
import corn_snake.util.FX;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    private ImageView background, board, homeButton, resetButton;

    private static final String[] COLUMNS = {"rank", "name", "score"};

    private static final Image
            BACKGROUND = FX.BACKGROUND,
            BOARD = FX.BOARD,
            HOME = FX.HOME,
            RESET = new Image(LeaderboardController.class.getResource("leaderboard/Reset.png").toExternalForm());

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

    /**
     * Called when the home button is clicked.
     * Returns to the title screen
     */
    @FXML
    public void onHomeClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) window.getScene().getWindow();
        FX.loadScene(stage, "menu/menu-view.fxml", MenuController.class, "menu/MenuStyle.css");
    }

    /**
     * Called when the reset button is clicked.
     * Wipes the leaderboard and reloads the leaderboard screen
     */
    @FXML
    public void onResetClick(MouseEvent event) {
        FACADE.resetLeaderboard();
        try {
            FX.loadScene(getStage(), "leaderboard/leaderboard.fxml", LeaderboardController.class, "leaderboard/LeaderboardStyle.css");
        } catch (Exception ignored) {

        }
    }

    /**
     * This method is implicitly called by JavaFX.
     * Initializes the leaderboard screen.
     * Two short {@link Timeline}s make the background and saved leaderboard data appear in quick succession.
     * If there are fewer than 10 registered scores, the rest of the rows will have "---" as their data
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        background.setImage(BACKGROUND);
        lb = FACADE.getLeaderboard();
        row = 1;

        Timeline loadBackground = new Timeline(
                new KeyFrame(Duration.seconds(0.1), (event) -> board.setImage(BOARD)),
                new KeyFrame(Duration.seconds(0.2), (event) -> homeButton.setImage(HOME)),
                new KeyFrame(Duration.seconds(0.3), (event) -> resetButton.setImage(RESET)),
                new KeyFrame(Duration.seconds(0.4), (event) -> title.setText("Top 10")),
                new KeyFrame(Duration.seconds(0.5), (event) -> {
                    rank.setText("Rank");
                    name.setText("Name");
                    score.setText("Score");
                })
        );
        loadBackground.setDelay(Duration.seconds(0.5));

        Timeline loadLeaderboard = new Timeline(
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
                            rankLabel.setText("---");
                            nameLabel.setText("---");
                            scoreLabel.setText("---");

                        }
                        row++;
                    }
                )
        );
        loadLeaderboard.setDelay(Duration.seconds(1));
        loadLeaderboard.setCycleCount(10);

        loadBackground.play();
        loadLeaderboard.play();
    }

    @FXML
    public void onHover(MouseEvent event) {
        Node node = (Node) event.getTarget();
        if (node.getId().equals("homeButton") || node.getId().equals("resetButton")) {
            FX.scale(node, 1.1);
        }
    }

    @FXML
    public void onUnHover(MouseEvent event) {
        Node node = (Node) event.getTarget();
        if (node.getId().equals("homeButton") || node.getId().equals("resetButton")) {
            FX.scale(node, 1);
        }
    }

    /**
     * Retrieves the current stage that's being used
     *
     * @return the current stage
     */
    private Stage getStage() {
        return (Stage) window.getScene().getWindow();
    }

    /**
     * Retrieves a label from the table of ranks, names, and scores of this controller.
     * @param row integer value of the row. Indexing starts at 1
     * @param col integer value of the row. Indexing starts at 0
     * @return the label that corresponds to the given row and column
     * @throws IndexOutOfBoundsException if an invalid row or column is given. 1 <= row <= 10, 0 <= col <= 2
     */
    private Label getLabel(int row, int col) throws IndexOutOfBoundsException {
        if (row < 1 || row > 10 ) {
            throw new IndexOutOfBoundsException("Row cannot be less than 1 or exceed 10.");
        }
        if (col < 0 || col > 2) {
            throw new IndexOutOfBoundsException("Column cannot be less than 0 or exceed 2.");
        }
        try {
            return (Label) getClass().getDeclaredField(
                    String.format("%s%d", COLUMNS[col], row)
            ).get(LeaderboardController.this);
        } catch (Exception e) {
            return null;
        }
    }
}
