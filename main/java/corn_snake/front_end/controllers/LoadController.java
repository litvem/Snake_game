package corn_snake.front_end.controllers;

import corn_snake.util.FX;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadController implements Initializable {

    @FXML
    private AnchorPane window;

    @FXML
    private Label label;

    @FXML
    private ImageView background, logo;

    static final Image
            BACKGROUND = FX.BACKGROUND,
            LOGO = FX.LOGO;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setOpacity(0);
        logo.setOpacity(0);
        background.setOpacity(0);
        label.setText("presents...");
        logo.setImage(LOGO);
        background.setImage(BACKGROUND);

        Timeline load = new Timeline(
                new KeyFrame(Duration.seconds(0), (event) -> {

                    FadeTransition fadeLabel = new FadeTransition();
                    FadeTransition fadeLogo = new FadeTransition();

                    fadeLabel.setNode(label);
                    fadeLogo .setNode(logo);

                    fadeLabel.setDuration(Duration.seconds(0.5));
                    fadeLogo .setDuration(Duration.seconds(0.5));

                    fadeLabel.setToValue(1);
                    fadeLogo .setToValue(1);

                    fadeLabel.play();
                    fadeLogo .play();
                }),
                new KeyFrame(Duration.seconds(2), (event) -> {

                    FadeTransition fadeLabel = new FadeTransition();
                    FadeTransition fadeLogo = new FadeTransition();

                    fadeLabel.setNode(label);
                    fadeLogo .setNode(logo);

                    fadeLabel.setDuration(Duration.seconds(0.5));
                    fadeLogo .setDuration(Duration.seconds(0.5));

                    fadeLabel.setToValue(0);
                    fadeLogo .setToValue(0);

                    fadeLabel.play();
                    fadeLogo .play();
                }),
                new KeyFrame(Duration.seconds(3), (event) -> {
                    FadeTransition fadeBackground = new FadeTransition();

                    fadeBackground.setNode(background);

                    fadeBackground.setDuration(Duration.seconds(0.5));

                    fadeBackground.setToValue(1);

                    fadeBackground.play();
                })
        );
        load.setDelay(Duration.seconds(1.2));

        PauseTransition loadTitle = new PauseTransition();
        loadTitle.setDelay(Duration.seconds(4.5));
        loadTitle.setOnFinished(
                (event) -> {
                    try {
                        FX.loadScene(getStage(), "menu/menu-view.fxml", MenuController.class, "menu/MenuStyle.css");
                    } catch (Exception ignored) {

                    }
                }
        );

        load.play();
        loadTitle.play();
    }

    /**
     * Retrieves the current stage that's being used
     *
     * @return the current stage
     */
    private Stage getStage() {
        return (Stage) window.getScene().getWindow();
    }
}
