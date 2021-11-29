package corn_snake.back_end;

public enum Tile {
    /**
     * Represents the default value of the field.
     */
    EMPTY("o"),

    /**
     * Represents the head of the snake.
     */
    SNAKE_HEAD_UP("x"),
    SNAKE_HEAD_DOWN("x"),
    SNAKE_HEAD_LEFT("x"),
    SNAKE_HEAD_RIGHT("x"),

    /**
     * Represents any segment of the snake except for the head and last (using the movement's direction as reference)
     * segments of the snake.
     */
    SNAKE_BODY("a"),

    /**
     * Represents the last segment (using the movement's direction as reference) of the snake.
     */
    SNAKE_TAIL("w"),

    /**
     * Represents the obstacles in the field.
     */
    OBSTACLE("*");

    final String symbol;

    /**
     *
     * @param symbol is used only to print during the development of the backend and might be removed from the futures versions
     */
    Tile(String symbol){
        this.symbol = symbol;
    }

    @Override
    public String toString(){
        return this.symbol;
    }
}
