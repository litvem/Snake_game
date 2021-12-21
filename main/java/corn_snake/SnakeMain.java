package corn_snake;

import corn_snake.facade.controllers.MenuController;
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
        FXMLLoader fxmlLoader = new FXMLLoader(
                MenuController.class.getResource("menu-view.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load());

        Image icon = new Image("file:///" + getClass().getResource("Icon.png").getFile());
        stage.getIcons().add(icon);

        stage.setResizable(false);

        stage.setTitle("Corn Snake");
        stage.setScene(scene);
        stage.show();
    }
}