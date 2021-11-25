package corn_snake.back_end;

public class Field {
    final private static Integer[][] INITIAL_COORDINATES = new Integer[][]{{1, 7}, {2, 7}, {3, 7}, {4, 7}};


    final private Tile[][] matrix;
    private Snake snake;

    /**
     * When called, the constructor creates a matrix of Tiles and set all elements to the EMPTY tile.
     * Then a function to add the snake to field is called.
     */
    public Field(){
        this.matrix = new Tile[17][17];
        for(int i = 0; i < this.matrix[0].length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                this.matrix[i][j] = Tile.EMPTY;
            }
        }
        this.addSnake();
    }

    /**
     * Creates a snake and sets its initial coordinates. Also sets the correct values of those coordinates in the field.
     */
    private void addSnake(){

        this.snake = new Snake(INITIAL_COORDINATES[0], INITIAL_COORDINATES[1], INITIAL_COORDINATES[2], INITIAL_COORDINATES[3]);

        for (int i = 0; i <= INITIAL_COORDINATES.length ; i++){
            if (i == 0){
                this.matrix[1 + i][7] = Tile.SNAKE_TAIL;
            } else if (i == INITIAL_COORDINATES.length) {
                this.matrix[1 + i][7] = Tile.SNAKE_HEAD;
            } else {
                this.matrix[1 + i][7] = Tile.SNAKE_BODY;
            }
        }
    }


    public Tile[][] getMatrix(){
        return this.matrix;
    }

    public void printField(){
        for (Tile[] row : matrix) {
            for (Tile value : row) {
                System.out.print(value.symbol + " ");
            }
            System.out.println();
        }
    }


    /**
     *
     * @param command set the snake's direction of movement.
     */
    public void moveSnake(String command){
        /*
        Creating a variable that will store the coordinates of the segment that will be updated so the next segment's coordinates
        can be updated to the location of the current segment.
         */
        Integer[] previousSegment = new Integer[]{0,0};

        //Iterating through the snake.
        for (int i = snake.getSize() - 1; i >= 0; i--){
            /*
            - If the current segment is the snake's head the program verifies if the next tile in the desired direction is
            the segment that precedes the snake's head. In that case nothing will happen.

            - If it is possible to move in that direction:
            the snake's head's coordinates will be changed to the coordinates of the next segment of the desired direction,
            the current coordinate will be set to Tile.SNAKE_BODY,
            the next segment in that direction will have the value updated to a Tile.SNAKE_HEAD.
             */

            if (i == snake.getSize() - 1){
                previousSegment = snake.getSegment(i);
                int row = snake.getSegment(i)[0];
                int column = snake.getSegment(i)[1];

                switch (command) {
                    case "d":

                        if (snake.getSegment(i - 1)[0] == row + 1 && snake.getSegment(i - 1)[1] == column) {
                            return;
                        } else {
                            snake.setSegment(i, row + 1, column);
                            matrix[row][column] = Tile.SNAKE_BODY;
                            matrix[row + 1][column] = Tile.SNAKE_HEAD;
                        }

                        break;
                    case "u":

                        if (snake.getSegment(i - 1)[0] == row - 1 && snake.getSegment(i - 1)[1] == column) {
                            return;
                        } else {
                            snake.setSegment(i, row - 1, column);
                            matrix[row][column] = Tile.SNAKE_BODY;
                            matrix[row - 1][column] = Tile.SNAKE_HEAD;
                        }

                        break;
                    case "r":

                        if (snake.getSegment(i - 1)[0] == row && snake.getSegment(i - 1)[1] == column + 1) {
                            return;
                        } else {
                            snake.setSegment(i, row, column + 1);
                            matrix[row][column] = Tile.SNAKE_BODY;
                            matrix[row][column + 1] = Tile.SNAKE_HEAD;
                        }

                        break;
                    case "l":

                        if (snake.getSegment(i - 1)[0] == row && snake.getSegment(i - 1)[1] == column - 1) {
                            return;
                        } else {
                            snake.setSegment(i, row, column - 1);
                            matrix[row][column] = Tile.SNAKE_BODY;
                            matrix[row][column - 1] = Tile.SNAKE_HEAD;
                        }
                        break;

                    default:
                        return;
                }

                /*
                If the current segment is the final segment of the snake's body:
                -the value of this tile in the field is changed to Tile.EMPTY.
                -This segment's coordinates is changed to the old coordinates of the segment that was updated directly before this segment.
                -the value of the updated coordinate is changed to Tile.SNAKE_TAIL
                 */
            } else if(i == 0){
                int row = snake.getSegment(0)[0];
                int column = snake.getSegment(0)[1];

                matrix[row][column] = Tile.EMPTY;
                matrix[previousSegment[0]][previousSegment[1]] = Tile.SNAKE_TAIL;

                snake.setSegment(0, previousSegment[0], previousSegment[1]);

                /*
                If the current segment is not the head neither the last segment of the snake:
                - The current segment will have its coordinates changed to the old coordinates of the segment that was updated directly before this segment.
                 */
            } else {
                int newRow = previousSegment[0];
                int newColumn = previousSegment[1];

                previousSegment = snake.getSegment(i);

                snake.setSegment(i, newRow, newColumn);

            }
        }
    }
}
