package corn_snake.back_end;

public enum Tile {
    /**
     * Represents the default value of the field.
     */
    EMPTY("o"),

    /**
     * Represents the head of the snake.
     */
    SNAKE_HEAD("x"),

    /**
     * Represents any horizontal segment of the snake except for the head and last (using the movement's direction as reference)
     * segments of the snake.
     */
    SNAKE_HORIZONTAL_BODY(">"),

    /**
     * Represents any vertical segment of the snake except for the head and last (using the movement's direction as reference)
     * segments of the snake.
     */
    SNAKE_VERTICAL_BODY("^"),

    /**
     * Represents a segment that is between a segment on its right side and another segment under it.
     */
    SNAKE_CORNER_RIGHT_DOWN_BODY("|~"),

    /**
     * Represents a segment that is between a segment on its right side and another segment on top of it.
     */
    SNAKE_CORNER_RIGHT_UP_BODY("L"),

    /**
     * Represents a segment that is between a segment on its left side and another segment under it.
     */
    SNAKE_CORNER_LEFT_DOWN_BODY("7"),

    /**
     * Represents a segment that is between a segment on its left side and another segment on top of it.
     */
    SNAKE_CORNER_LEFT_UP_BODY("_|"),

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
