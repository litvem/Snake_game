package corn_snake.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class FXDemo extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws IOException {
        // Starts directly in the game field
        FXMLLoader fxmlLoader = new FXMLLoader(
                DemoFieldController.class.getResource("game_field.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load());

        Image icon = new Image("file:///" + FXDemo.class.getResource("Icon.png").getFile());
        stage.getIcons().add(icon);

        stage.setResizable(false);

        stage.setTitle("Corn Snake");
        stage.setScene(scene);
        stage.show();
    }
}
