package corn_snake.back_end;

public enum Tile {
    /**
     * Represents the default value of the field.
     */
    EMPTY("o"),

    /**
     * Represents the head of the snake going up.
     */
    SNAKE_HEAD_UP("x"),
    /**
     * Represents the head of the snake going down
     */
    SNAKE_HEAD_DOWN("x"),
    /**
     * Represents the head of the snake going left
     */
    SNAKE_HEAD_LEFT("x"),
    /**
     * Represents the head of the snake going right
     */
    SNAKE_HEAD_RIGHT("x"),

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
     * Represents the tail of the snake going up
     */
    SNAKE_UPWARD_GOING_TAIL("u"),
    /**
     * Represents the tail of the snake going down
     */
    SNAKE_DOWNWARD_GOING_TAIL("n"),
    /**
     * Represents the tail of the snake going in left direction
     */
    SNAKE_LEFTWARD_GOING_TAIL("D"),
    /**
     * Represents the tail of the snake going in right direction
     */
    SNAKE_RIGHTWARD_GOING_TAIL("C"),

    /**
     * Represents the obstacles in the field.
     */
    OBSTACLE("*"),

    /**
     * Represents the fruit in the field.
     */
    FRUIT("y");

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
