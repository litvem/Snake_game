package corn_snake.util;

import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class FX {
    public static final Image
            BACKGROUND = new Image(FX.class.getResource("common_images/TitleBG.png").toExternalForm()),
            HOME = new Image(FX.class.getResource("common_images/HomeButton.png").toExternalForm()),
            LOGO = new Image(FX.class.getResource("common_images/Logo.png").toExternalForm()),
            SMALL_BOARD = new Image(FX.class.getResource("common_images/SmallBoard.png").toExternalForm()),
            BOARD = new Image(FX.class.getResource("common_images/Board.png").toExternalForm());

    public static void scale(Node node, double value) {
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(node);
        scale.setToY(value);
        scale.setToX(value);
        scale.setDuration(Duration.millis(50));
        scale.play();
    }
}
