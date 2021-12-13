package corn_snake.back_end;

public class Score implements Comparable<Score> {

    private String name;
    private int score;

    public Score() {
        // Default constructor is only to be used by the Json class
        name = "";
        score = 0;
    }

    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Only to be used by the Json class
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        // Only to be used by the Json class
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Score: %d", name, score);
    }

    /**
     * Compares all score in leaderboard and sorts them in descending order
     */
    @Override
    public int compareTo(Score otherScore) {
        return Integer.compare(otherScore.getScore(), getScore());
    }

}

