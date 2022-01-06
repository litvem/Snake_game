package corn_snake.front_end.controllers;

import corn_snake.util.FX;
import corn_snake.util.IO;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
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
import java.util.ResourceBundle;

public class HowToPlayController implements Initializable {


    @FXML
    private AnchorPane window;
    @FXML
    private ImageView background, board, homeButton;
    @FXML
    private Label howToPlayLabel;

    static final Image
            BACKGROUND = FX.BACKGROUND,
            BOARD = FX.BOARD,
            HOME = FX.HOME;

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
     * This method is implicitly called by JavaFX.
     * Initializes the "how to play" screen.
     * A short {@link Timeline} makes the background and text explaining the game appear in quick succession
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        background.setImage(BACKGROUND);
        howToPlayLabel.setOpacity(0);
        howToPlayLabel.setText(IO.HOW_TO_PLAY);
        Timeline load = new Timeline(
                new KeyFrame(Duration.seconds(0.2), (event) -> board.setImage(BOARD)),
                new KeyFrame(Duration.seconds(0.4), (event) -> homeButton.setImage(HOME)),
                new KeyFrame(Duration.seconds(0.6), (event) -> {
                    TranslateTransition move = new TranslateTransition();
                    FadeTransition fade = new FadeTransition();

                    move.setNode(howToPlayLabel);
                    fade.setNode(howToPlayLabel);

                    move.setDuration(Duration.seconds(0.5));
                    fade.setDuration(Duration.seconds(0.5));

                    move.setFromY(30);
                    move.setByY(-30);
                    fade.setFromValue(0);
                    fade.setToValue(1);

                    move.play();
                    fade.play();
                })
        );

        load.setDelay(Duration.seconds(0.4));
        load.play();
    }
    /**
     * Called whenever the cursor hovers over a button.
     * The button gets enlarged by 10%
     * @param event the {@link MouseEvent} used to fetch the button
     */
    @FXML
    public void onHover(MouseEvent event) {
        Node node = (Node) event.getTarget();
        if (node.getId().equals("homeButton")) {
            FX.scale(node, 1.1);
        }
    }

    /**
     * Called whenever the cursor stops hovering over a button.
     * The button reverts to its original size
     * @param event the {@link MouseEvent} used to fetch the button
     */
    @FXML
    public void onUnHover(MouseEvent event) {
        Node node = (Node) event.getTarget();
        if (node.getId().equals("homeButton")) {
            FX.scale(node, 1);
        }
    }
}
