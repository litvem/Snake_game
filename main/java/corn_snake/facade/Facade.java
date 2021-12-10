package corn_snake.facade;

import corn_snake.back_end.*;
import java.util.List;
import corn_snake.util.Json;


public class Facade {

    private Leaderboard lb;
    private Field field;


    public void newField(){
        field= new Field();
    }

    public List<Score> getLeaderboard(){return lb.getTop10();}

    public void addScore(String name, int score){
        Score playerScore= new Score(name,score);
        lb.addScore(playerScore);
    }

    public Tile[][] getField(){
        return field.getMatrix();}

    public void updateField(String command) {
        updateField(command);
    }

    public int getScore(Score score){
        return score.getScore();
    }

    public void saveLeaderboard(){
        try {
            Json.dump(getLeaderboard(),"src/main/resources");
        }catch (Exception e){
            System.out.println("Directory Invalid");
        }
    }
}
