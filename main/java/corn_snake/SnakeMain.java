package corn_snake;

import corn_snake.front_end.controllers.LoadController;
import corn_snake.util.FX;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Starts from the main menu
        FX.loadScene(stage, "load/load.fxml", LoadController.class, "load/LoadStyle.css");

        Image icon = new Image(getClass().getResource("Icon.png").toExternalForm());
        stage.getIcons().add(icon);

        stage.setTitle("Corn Snake");
        stage.show();
    }
}