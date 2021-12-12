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
        // Starts from the title screen
        FXMLLoader fxmlLoader = new FXMLLoader(
                DemoMenuController.class.getResource("menu-view.fxml")
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
