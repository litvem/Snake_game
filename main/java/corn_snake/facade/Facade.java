package corn_snake.facade;

import corn_snake.back_end.*;
import java.util.List;
import corn_snake.util.Json;


public class Facade {

    private Leaderboard lb;
    private Field field;

    public Facade() {
        try {
            // Tries to load leaderboard from JSON
            this.lb = Json.load(getClass().getResource("leaderboard.json").getFile(), Leaderboard.class);
        } catch (Exception e) {
            // Creates a new leaderboard if loading fails
            this.lb = new Leaderboard();
        }
        this.field = new Field();
    }

    public void moveSnake(String command) throws GameOverException {
        field.moveSnake(command);
    }

    public void newField(){
        field= new Field();
    }

    public List<Score> getLeaderboard(){return lb.getTop10();}

    public void addScore(String name, int score){
        lb.addScore(name,score);
    }

    public Tile[][] getField(){
        return field.getMatrix();}

    public void updateField(String command) throws GameOverException {
        field.updateField(command);
    }

    public int getScore(Score score){
        return score.getScore();
    }

    public void saveLeaderboard(){
        try {
            Json.dump(getLeaderboard(),"src/main/resources");
        } catch (Exception e){
            System.out.println("Directory Invalid");
        }
    }
}
