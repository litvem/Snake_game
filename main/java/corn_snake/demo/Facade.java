package corn_snake.demo;

import corn_snake.back_end.Field;
import corn_snake.back_end.GameOverException;
import corn_snake.back_end.Leaderboard;
import corn_snake.util.Json;

import java.io.IOException;
import java.util.Collections;

class Facade {

    private Leaderboard lb;
    private Field field;

    Facade() {
        try {
            // Tries to load leaderboard from JSON
            this.lb = Json.load(getClass().getResource("leaderboard.json").getFile(), Leaderboard.class);
        } catch (Exception e) {
            // Creates a new leaderboard if loading fails
            this.lb = new Leaderboard();
        }
        this.field = new Field();
    }

    void printField() {
        field.printField();
    }

    void moveSnake(String command) throws GameOverException {
        field.moveSnake(command);
    }
}
