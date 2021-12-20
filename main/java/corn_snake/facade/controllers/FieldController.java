package corn_snake.facade.controllers;

import corn_snake.Main;
import corn_snake.back_end.GameOverException;
import corn_snake.back_end.Tile;
import corn_snake.facade.Facade;
import corn_snake.util.IO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FieldController implements Initializable {

    // Unified facade
    final static Facade FACADE = Main.FACADE;

    // Tile grid
    // Borders either have a leading P or Q, or have the number 0 or 16
    @FXML
    private ImageView
            Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, Q12, Q13, Q14, Q15, Q16,
            A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16,
            B0, B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13, B14, B15, B16,
            C0, C1, C2, C3, C4, C5, C6, C7, C8, C9, C10, C11, C12, C13, C14, C15, C16,
            D0, D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12, D13, D14, D15, D16,
            E0, E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15, E16,
            F0, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12, F13, F14, F15, F16,
            G0, G1, G2, G3, G4, G5, G6, G7, G8, G9, G10, G11, G12, G13, G14, G15, G16,
            H0, H1, H2, H3, H4, H5, H6, H7, H8, H9, H10, H11, H12, H13, H14, H15, H16,
            I0, I1, I2, I3, I4, I5, I6, I7, I8, I9, I10, I11, I12, I13, I14, I15, I16,
            J0, J1, J2, J3, J4, J5, J6, J7, J8, J9, J10, J11, J12, J13, J14, J15, J16,
            K0, K1, K2, K3, K4, K5, K6, K7, K8, K9, K10, K11, K12, K13, K14, K15, K16,
            L0, L1, L2, L3, L4, L5, L6, L7, L8, L9, L10, L11, L12, L13, L14, L15, L16,
            M0, M1, M2, M3, M4, M5, M6, M7, M8, M9, M10, M11, M12, M13, M14, M15, M16,
            N0, N1, N2, N3, N4, N5, N6, N7, N8, N9, N10, N11, N12, N13, N14, N15, N16,
            O0, O1, O2, O3, O4, O5, O6, O7, O8, O9, O10, O11, O12, O13, O14, O15, O16,
            P0, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16;

    @FXML
    private AnchorPane window;

    @FXML
    private Label score, countdown;

    @FXML
    private ImageView backgroundView;

    // Images used in the game scene
    private final static Image
            BACKGROUND = new Image(
                    FieldController.class.getResource("field/GameBG.png").toExternalForm()
            ),
            EMPTY = new Image(
                    FieldController.class.getResource("field/tiles/Empty.png").toExternalForm()
            ),
            FRUIT = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            ),
            HEAD_U = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            ),
            HEAD_D = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            ),
            HEAD_R = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            ),
            HEAD_L = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            ),
            BODY_H = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            ),
            BODY_V = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            ),
            TURN_UR = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            ),
            TURN_UL = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            ),
            TURN_DR = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            ),
            TURN_DL = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            ),
            TAIL_U = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            ),
            TAIL_D = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            ),
            TAIL_R = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            ),
            TAIL_L = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            ),
            OBSTACLE = new Image(
                    FieldController.class.getResource("snowflake.jpeg").toExternalForm()
            );

    private static final char[] ROWS = {
            'Q', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'
    };

    // Iteration trackers
    private int cdNum, row, column;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cdNum = 3;
        row = 0;
        column = 0;
        backgroundView.setImage(BACKGROUND);
        countdown.setText(String.format("Starting%sin...", IO.EOL));

        // Sets a countdown before starting the game
        Timeline cd = new Timeline(
                new KeyFrame(
                        Duration.seconds(1), (event) -> {
                            countdown.setText(String.valueOf(cdNum));
                            cdNum--;
                        }
                )
        );
        cd.setDelay(Duration.seconds(1));
        cd.setCycleCount(4);
        cd.setOnFinished(
                (event) -> {
                    countdown.setText("");
                }
        );

        // Loads the field with an "animation"
        // Duration.millis(5) equivalent to Thread.sleep(5)
        Timeline load = new Timeline(
                new KeyFrame(
                        Duration.millis(5), (event) -> {
                            try {
                                // Gets all tiles
                                ImageView image = getTile(row, column);
                                image.setImage(EMPTY); // FIXME Add switch block
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                // Increments column from 0-16
                                // Increments row by 1 when column exceeds the number of columns in the field
                                column++;
                                if (column > 16) {
                                    column = 0;
                                    row++;
                                }
                            }
                        }
                )
        );

        load.setDelay(Duration.seconds(3.5));
        load.setCycleCount(289);

        // Loads and displays the actual game field
        Timeline game = new Timeline(
                new KeyFrame(
                        Duration.millis(5), (event) -> {
                            try {
                                FACADE.updateField(""); // FIXME
                            } catch (GameOverException e){
                                Stage stage = (Stage) window.getScene().getWindow();
                                try {
                                    IO.loadScene(stage, "game_over.fxml", GameOverController.class);
                                } catch (IOException g) {

                                }
                            }

                            Tile[][] field = FACADE.getField();

                            for (int i = 1; i < field.length - 1; i++){
                                Tile[] row = field[i];

                                for (int j = 1; j < row.length - 1; j++){
                                    Tile tile = row[j];
                                    try {
                                        // Retrieve the tile in question
                                        ImageView image = getTile(i, j);
                                        switch (tile) {
                                            case EMPTY:
                                                image.setImage(EMPTY);
                                                break;
                                            case SNAKE_HORIZONTAL_BODY:
                                                image.setImage(BODY_H);
                                                break;
                                            case SNAKE_VERTICAL_BODY:
                                                image.setImage(BODY_V);
                                                break;
                                            case SNAKE_CORNER_RIGHT_UP_BODY:
                                                image.setImage(TURN_UR);
                                                break;
                                            case SNAKE_CORNER_LEFT_UP_BODY:
                                                image.setImage(TURN_UL);
                                                break;
                                            case SNAKE_CORNER_RIGHT_DOWN_BODY:
                                                image.setImage(TURN_DR);
                                                break;
                                            case SNAKE_CORNER_LEFT_DOWN_BODY:
                                                image.setImage(TURN_DL);
                                                break;
                                            case SNAKE_HEAD_UP:
                                                image.setImage(HEAD_U);
                                                break;
                                            case SNAKE_HEAD_DOWN:
                                                image.setImage(HEAD_D);
                                                break;
                                            case SNAKE_HEAD_RIGHT:
                                                image.setImage(HEAD_R);
                                                break;
                                            case SNAKE_HEAD_LEFT:
                                                image.setImage(HEAD_L);
                                                break;
                                            case SNAKE_UPWARD_GOING_TAIL:
                                                image.setImage(TAIL_U);
                                                break;
                                            case SNAKE_DOWNWARD_GOING_TAIL:
                                                image.setImage(TAIL_D);
                                                break;
                                            case SNAKE_RIGHTWARD_GOING_TAIL:
                                                image.setImage(TAIL_R);
                                                break;
                                            case SNAKE_LEFTWARD_GOING_TAIL:
                                                image.setImage(TAIL_L);
                                                break;
                                            case FRUIT:
                                                image.setImage(FRUIT);
                                                break;
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                )
        );
        game.setDelay(Duration.seconds(5));

        load.play();
        cd.play();
        game.play();
    }

    /**
     * Gets one tile from the {@code FieldController}, given row and column
     * @param row row of the Tile matrix
     * @param col column of the Tile matrix
     * @return targeted ImageView in the FieldController
     * @throws IndexOutOfBoundsException if {@code row} or {@code col} are out of bounds of the matrix
     */
    private ImageView getTile(int row, int col) throws IndexOutOfBoundsException {
        if (row < 0 || row > 16 || col < 0 || col > 16) {
            throw new IndexOutOfBoundsException("Row and column cannot be less than 0 or exceed 16.");
        }
        try {
            return (ImageView) getClass().getDeclaredField(
                    String.format("%s%d", ROWS[row], col)
            ).get(FieldController.this);
        } catch (Exception e) {
            return null;
        }
    }
}
