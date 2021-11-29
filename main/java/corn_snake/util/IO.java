package corn_snake.util;

public class IO {

    public static final String EOL = System.lineSeparator();

    /**
     * Generates a pseudo-random integer with the given parameters as boundaries
     *
     * @param lowerBound lower boundary of the interval
     * @param upperBound upper boundary of the interval
     * @return a pseudo-randomly generated integer
     */
    public static int randInt(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound;
        int num = lowerBound + (int) (Math.random() * (range + 1));
        return num;
    }
}
