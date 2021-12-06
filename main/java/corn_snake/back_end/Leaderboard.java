package corn_snake.back_end;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard {

    private ArrayList<Score> top10;

    public Leaderboard() {
        top10 = new ArrayList<Score>();
    }

    public ArrayList<Score> getTop10() {
        return top10;
    }

    public void setTop10(ArrayList<Score> top10) {
        // Only to be used by the Json class
        this.top10 = top10;
    }

    public String toString() {
        String res = "Top 10:\n";
        for (Score score : top10) {
            res += score.toString();
        }
        return res;
    }

    /**
     * addScore method will add the score if leaderboard has less than 10 scores and sort the list in ascending order.
     * If leaderboard has 10 scores already, playerScore will be added if it's higher than lowest score of leaderboard and sort list.
     * @param playerScore is the score which the current player of the game has when the game is over
     */
    public void addScore(Score playerScore) {

        if (top10.size() < 10) {
            top10.add(playerScore);
            Collections.sort(top10);
        } else if (playerScore.getScore() > top10.get(0).getScore()) {
            top10.remove(0);
            top10.add(playerScore);
            Collections.sort(top10);
        }
    }
}
