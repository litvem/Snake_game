package corn_snake.facade;

import corn_snake.back_end.*;
import java.util.List;
import corn_snake.util.Json;

public class Facade {

    private final static int INITIAL_LENGTH = 4;

    private Leaderboard lb;
    private Field field;
    private String command;
    private int score;

    public Facade() {
        try {
            // Tries to load leaderboard from JSON
            this.lb = Json.load(getClass().getResource("leaderboard.json").getPath(), Leaderboard.class);
        } catch (Exception e) {
            // Creates a new leaderboard if loading fails
            this.lb = new Leaderboard();
        }
        newField();
    }

    public void moveSnake() throws GameOverException {
        field.moveSnake(command);
        score = field.getSnakeSize() - INITIAL_LENGTH;
    }

    public void setCommand(String command) {
        if (command.equals("s") || command.equals("k")) {
            this.command = "d";
        } else if (command.equals("w") || command.equals("i")) {
            this.command="u";
        } else if(command.equals("a") || command.equals("j")) {
            this.command="l";
        } else if(command.equals("d") || command.equals("l")) {
            this.command="r";
        }
    }

    public void newField() {
        field = new Field();
        command = "d";
        score = 0;
    }

    public List<Score> getLeaderboard() {
        return lb.getTop10();
    }

    public void addScore(String name, int score) {
        if(!name.isBlank()) {
            lb.addScore(name.strip(), score);
        }
    }


    public Tile[][] getField() {
        return field.getMatrix();}

    public int getScore() {
        return score;
    }

    public void saveLeaderboard() {
        try {
            // Always writes to leaderboard.json
            // Creates a new file if it doesn't already exist
            Json.dump(lb, getClass().getResource("").getPath() + "leaderboard.json");
        } catch (Exception ignored) {

        }
    }
}
