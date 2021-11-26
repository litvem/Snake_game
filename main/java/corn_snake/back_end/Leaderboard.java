package corn_snake.back_end;

import java.util.List;

public class Leaderboard {

    private List<Score> top10;

    public List<Score> getTop10() {
        return top10;
    }

    public void setTop10(List<Score> top10) {
        // Only to be used by the Json class
        this.top10 = top10;
    }

    public void addScore(String name, int score) {
        top10.add(new Score(name, score));
    }

    public String toString() {
        String res = "Top 10:\n";
        for (Score score : top10) {
            res += score.toString();
        }

        return res;
    }
}
