package corn_snake.facade;

import corn_snake.back_end.*;
import java.util.List;
import corn_snake.util.Json;


public class Facade {

    private Leaderboard lb;
    private Field field;
    private String command;
    private String previousCommand;

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

    public String getCommand() {
        return command;
    }

    public void moveSnake() throws GameOverException {
        field.moveSnake(command);
    }

    public void setCommand(String command){
        if(command.equals("s") || command.equals("S")) {
            command = "d";
            previousCommand="d";
        }else if(command.equals("w") || command.equals("W")){
            command="u";
            previousCommand="u";
        }
        else if(command.equals("a") || command.equals("A")){
            command="l";
            previousCommand="l";
        }
        else if(command.equals("d") || command.equals("D")){
            command="r";
            previousCommand="r";
        }
        else{
            command=previousCommand;
        }
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
