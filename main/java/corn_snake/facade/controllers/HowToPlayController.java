package corn_snake.facade.controllers;

import corn_snake.util.IO;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private ImageView HowToPlay;
    @FXML
    private Label howToPlayLabel;

    private Image
    HOWTOPLAY = new Image(HowToPlayController.class.getResource("how_to_play/HowToPlay.png").toExternalForm());

    @FXML
    public void onMainMenuClick(MouseEvent event) throws IOException {
        Stage stage = (Stage) window.getScene().getWindow();
        IO.loadScene(stage, "menu/menu-view.fxml", MenuController.class, "menu/MenuStyle.css");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline load = new Timeline(
                new KeyFrame(Duration.seconds(0.1), (event) -> {
                    HowToPlay.setImage(HOWTOPLAY);
                    howToPlayLabel.setText(IO.HOW_TO_PLAY);

                    
                })
        );

        load.setDelay(Duration.seconds(0.1));
        load.play();

    }
}
