package corn_snake.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IO {

    public static final String EOL = System.lineSeparator();

    /**
     * Generates a pseudo-random integer with the given parameters as boundaries
     *
     * @param lowerBound lower boundary of the interval
     * @param upperBound upper boundary of the interval
     * @return a pseudo-randomly generated integer
     */
    public static int randInt(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound;
        int num = lowerBound + (int) (Math.random() * (range + 1));
        return num;
    }

    /**
     * Loads a new JavaFX {@link Scene}
     *
     * @param currentStage the current {@link Stage} that is used.
     *                     Bind the container to an FXID to access the stage, example:
     *                     {@code (Stage) <container>.getScene().getWindow()}
     * @param file FXML file to load scene from
     * @param clazz {@link Class} to get resources from
     * @throws IOException if loading fails
     */
    public static void loadScene(Stage currentStage, String file, Class<?> clazz) throws IOException {
        loadScene(currentStage, file, clazz, null);
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
