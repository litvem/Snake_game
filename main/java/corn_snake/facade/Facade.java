package corn_snake.facade;

import corn_snake.back_end.Field;
import corn_snake.back_end.GameOverException;
import corn_snake.back_end.Leaderboard;
import corn_snake.back_end.Score;
import corn_snake.back_end.Tile;

import java.util.List;

import corn_snake.util.Json;

public class Facade {

    private final static int INITIAL_LENGTH = 4;

    private Leaderboard lb;
    private Field field;
    private String command;
    private int score;

    /**
     * The constructor creates a Facade object and initializes it with a field object and
     * loads the leaderboard with data from an existing "leaderboard.json" file.
     * The constructor creates a new leaderboard if the loading fails.
     */
    public Facade() {
        try {
            this.lb = Json.load("leaderboard.json", Leaderboard.class);
        } catch (Exception e) {
            this.lb = new Leaderboard();
        }
        newField();
    }

    /**
     * Creates a new game field
     * By default, the field object and Score attribute is initialized
     * with the "downward" Command and score 0 respectively.
     */
    public void newField() {
        field = new Field();
        command = "d";
        score = 0;
    }


    /**
     * setCommand method translates the predefined command from game-controls into the corresponding direction
     * the snake moves towards and assigns the value to the command attribute of facade.
     * It also validates the movement of the Snake.
     *
     * @param command is the command input from the keyboard when the User plays the game .
     */
    public void setCommand(String command) {
        switch (command) {
            case "S":
            case "K":
                if (this.command.equals("u")) return;
                this.command = "d";
                break;
            case "W":
            case "I":
                if (this.command.equals("d")) return;
                this.command = "u";
                break;
            case "A":
            case "J":
                if (this.command.equals("r")) return;
                this.command = "l";
                break;
            case "D":
            case "L":
                if (this.command.equals("l")) return;
                this.command = "r";
                break;
        }
    }

    /**
     * moveSnake method calls the moveSnake() method from the back end and passes
     * the command attribute of Facade and its value as an argument to it.
     *
     * The score Variable is updated each time moveSnake method is called .
     *
     * @throws GameOverException when the snake hits the wall or its own body.
     */
    public void moveSnake() throws GameOverException {
        field.moveSnake(command);
        score = field.getSnakeSize() - INITIAL_LENGTH;
    }

    /**
     * @return the Matrix of the field of type Tiles, by calling the getMatrix method from the back-end.
     */
    public Tile[][] getField() {
        return field.getMatrix();}

    /**
     * @return a list of 10 Score objects which contains name and score of the player.
     * The list is sorted by descending order of player score.
     */
    public List<Score> getLeaderboard() {
        return lb.getTop10();
    }

    /**
     * Saves the leaderboard object by serializing it into an existing json file or a newly created one.
     */
    public void saveLeaderboard() {
        try {
            // Always writes to leaderboard.json
            // Creates a new file if it doesn't already exist
            Json.dump(lb, "leaderboard.json");
        } catch (Exception ignored) {

        }
    }

    /**
     * Creates a new Leaderboard with no player data.
     * The empty leaderboard is saved and the previous one is deleted.
     */
    public void resetLeaderboard(){
        lb = new Leaderboard();
        saveLeaderboard();
    }

    /**
     * The method checks for valid name and passes it and the score to the addScore method.
     *
     * @param name input received from the user who plays the game.
     * @param score input scored by the user and passed as an argument to this method.
     */
    public void addScore(String name, int score) {
        if(!name.isBlank()) {
            lb.addScore(name.strip(), score);
        }
    }

    /**
     *
     * @return the total score of the user.
     */
    public int getScore() {
        return score;
    }
}
