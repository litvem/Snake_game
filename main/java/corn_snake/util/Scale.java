package corn_snake.util;

import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Scale {
    public static void scale(Node node, double value) {
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(node);
        scale.setToY(value);
        scale.setToX(value);
        scale.setDuration(Duration.millis(50));
        scale.play();
    }
}
