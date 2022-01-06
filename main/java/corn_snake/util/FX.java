package corn_snake.util;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class FX {

    public static final Image
            BACKGROUND = new Image(FX.class.getResource("common_images/TitleBG.png").toExternalForm()),
            HOME = new Image(FX.class.getResource("common_images/HomeButton.png").toExternalForm()),
            LOGO = new Image(FX.class.getResource("common_images/Logo.png").toExternalForm()),
            SMALL_BOARD = new Image(FX.class.getResource("common_images/SmallBoard.png").toExternalForm()),
            BOARD = new Image(FX.class.getResource("common_images/Board.png").toExternalForm());

    /**
     * Animates a node in the GUI using a {@link ScaleTransition}. Each animation takes 50 milliseconds to complete
     *
     * @param node the node to animate
     * @param value ratio to scale the node to. 1.0 translates to 100% its original size
     */
    public static void scale(Node node, double value) {
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(node);
        scale.setToY(value);
        scale.setToX(value);
        scale.setDuration(Duration.millis(50));
        scale.play();
    }

    /**
     * Loads a new JavaFX {@link Scene} using a CSS style sheet
     *
     * @param currentStage the current {@link Stage} that is used.
     *                     Bind the container to an FXID to access the stage, example:
     *                     {@code (Stage) <container>.getScene().getWindow()}
     * @param file FXML file to load scene from
     * @param clazz {@link Class} to get resources from
     * @param css CSS to style the scene with
     * @throws IOException if loading fails
     */
    public static void loadScene(Stage currentStage, String file, Class<?> clazz, String css) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                clazz.getResource(file)
        );
        Scene scene = new Scene(fxmlLoader.load());

        if (css != null) {
            scene.getStylesheets().add(clazz.getResource(css).toExternalForm());
        }

        currentStage.setResizable(false);

        currentStage.setScene(scene);
        currentStage.show();
    }
}
