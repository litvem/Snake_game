package corn_snake.back_end;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard {

    private List<Score> top10;

    public Leaderboard() {
        top10 = new ArrayList<>();
    }

    /**
     * Getter for top 10 list
      * @return a copy of the top 10 leaderboard list
     */
    public List<Score> getTop10() {
        List<Score> copyOfTop10 = new ArrayList<>(top10);
        return copyOfTop10;
    }

    public void setTop10(List<Score> top10) {
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
     * addScore method will add the score if leaderboard has less than 10 scores and sort the list in descending order.
     * If leaderboard has 10 scores already, playerScore will be added if it's higher than lowest score of leaderboard and sort list.
     * @param name and score is the name of player and score of player when the game is over
     */
    public void addScore(String name, int score) {
        Score playerScore = new Score(name,score);

        if (top10.size() < 10) {
            top10.add(playerScore);
            Collections.sort(top10);
        } else if (playerScore.getScore() > top10.get(top10.size() - 1).getScore()) {
            top10.remove(top10.get(top10.size() - 1));
            top10.add(playerScore);
            Collections.sort(top10);
        }

    }
}
