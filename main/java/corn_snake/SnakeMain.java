package corn_snake;

import corn_snake.facade.controllers.MenuController;
import corn_snake.util.IO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
        IO.loadScene(stage, "menu/menu-view.fxml", MenuController.class, "menu/MenuStyle.css");

        stage.setResizable(false);

        stage.setTitle("Corn Snake");
        stage.show();
    }
}