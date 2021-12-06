package corn_snake.back_end;

import java.util.Random;

public class Field {
    final private static Integer[][] INITIAL_COORDINATES = new Integer[][]{{1, 7}, {2, 7}, {3, 7}, {4, 7}};

    private static final Integer ROWS =  17;
    private static final Integer COLS = 17;
    final private Tile[][] matrix;
    private Snake snake;
    private Fruit fruit;

    /**
     * When called, the constructor creates a matrix of Tiles and set all elements to the EMPTY tile.
     * Then a function to add the snake to field is called.
     */
    public Field(){
        this.matrix = new Tile[ROWS][COLS];

        for(int i = 0; i < this.matrix[0].length ; i++){

            //Setting all tiles from the first and las rows to Tile.OBSTACLE
            if (i == 0 || i == this.matrix[0].length - 1){
                for (int j = 0; j < this.matrix[0].length; j++){
                    matrix[i][j] = Tile.OBSTACLE;
                }
            } else {
                for (int k = 0; k < matrix[0].length; k++){

                    //Setting the first and last tile of all the remaining rows to Tile.OBSTACLE
                    if (k == 0 || k == matrix[0].length - 1){
                        this.matrix[i][k] = Tile.OBSTACLE;

                        //Setting all the other tiles to Tile.EMPTY
                    } else {
                        this.matrix[i][k] = Tile.EMPTY;
                    }
                }
            }
        }
        this.addSnake();
        this.addFruit();
    }

    /**
     * Creates a snake and sets its initial coordinates. Also sets the correct values of those coordinates in the field.
     */
    private void addSnake() {

        this.snake = new Snake(INITIAL_COORDINATES[0], INITIAL_COORDINATES[1], INITIAL_COORDINATES[2], INITIAL_COORDINATES[3]);

        for (int i = 0; i <= INITIAL_COORDINATES.length - 1; i++) {
            if (i == 0) {
                this.matrix[1 + i][7] = Tile.SNAKE_TAIL;
            } else if (i == INITIAL_COORDINATES.length - 1) {
                this.matrix[1 + i][7] = Tile.SNAKE_HEAD_DOWN;
            } else {
                this.matrix[1 + i][7] = Tile.SNAKE_VERTICAL_BODY;
            }
        }
    }

    /**
     * Creates and add fruit until random location of food is empty.
     */

    private void addFruit(){
        Random rand = new Random();
        Integer c = 0;
        Integer r = 0;
        do{
             r = rand.nextInt(ROWS-1) + 1;
             c = rand.nextInt(COLS-1) + 1;
            this.fruit = new Fruit(r, c);
        } while(this.fruit.equals(Tile.SNAKE_HEAD_UP ));

        this.matrix[r][c] = Tile.FRUIT;
    }


    public void update(String command) {
        updateField(command);
        addFruit();

    }

    /**
     * In cose of snake addition, we need to update its body, head and tail accordingly
     */

    public void updateField(String command) {
        boolean isAdd = false;
        for(int i = 0; i < this.matrix[0].length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if(this.matrix[i][j].equals(Tile.FRUIT)) {
                    this.matrix[i][j] = Tile.EMPTY;
                }
                if(this.matrix[i][j].equals(Tile.FRUIT) && this.matrix[i][j].equals(Tile.SNAKE_HEAD_UP)) {
                    snake.increaseSize(i,j);
                    isAdd = true;

                }
                if(isAdd && this.matrix[i][j].equals(Tile.SNAKE_HEAD_UP)) {
                    this.matrix[i][j] = Tile.SNAKE_VERTICAL_BODY;
                    switch(command) {
                        case "d":
                            this.matrix[i+1][j] = Tile.SNAKE_HEAD_DOWN;
                        case "u":
                            this.matrix[i-1][j] = Tile.SNAKE_HEAD_UP;
                        case "l":
                            this.matrix[i][j-1] = Tile.SNAKE_HEAD_LEFT;
                        case "r":
                            this.matrix[i][j+1] = Tile.SNAKE_HEAD_RIGHT;
                    }
                }
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
     * @param command set the snake's direction of movement.
     */
    public void moveSnake(String command) throws GameOverException {
        /*
        Creating a variable that will store the coordinates of the segment that will be updated so the next segment's coordinates
        can be updated to the location of the current segment.
         */
        Integer[] previousSegment = new Integer[]{0, 0};

        //Iterating through the snake.
        for (int i = snake.getSize() - 1; i >= 0; i--) {
            /*
            - If the current segment is the snake's head the program verifies if the next tile in the desired direction is
            the segment that precedes the snake's head. In that case nothing will happen.

            - If it is possible to move in that direction:
            the snake's head's new coordinates will be saved in the headNewCoordinates variable,
            the correct head tile value is saved in the newHeadTile variable.
             */

            if (i == snake.getSize() - 1) {
                previousSegment = snake.getSegment(i);
                int row = snake.getSegment(i)[0];
                int column = snake.getSegment(i)[1];
                Integer[] newHeadCoordinates;
                Tile newHeadTile;


                switch (command) {
                    case "d":

                        if (snake.getSegment(i - 1)[0] == row + 1 && snake.getSegment(i - 1)[1] == column) {
                            return;
                        } else {
                            newHeadCoordinates = new Integer[]{row + 1, column};
                            newHeadTile = Tile.SNAKE_HEAD_DOWN;
                        }

                        break;
                    case "u":

                        if (snake.getSegment(i - 1)[0] == row - 1 && snake.getSegment(i - 1)[1] == column) {
                            return;
                        } else {
                            newHeadCoordinates = new Integer[]{row - 1, column};
                            newHeadTile = Tile.SNAKE_HEAD_UP;
                        }

                        break;
                    case "r":

                        if (snake.getSegment(i - 1)[0] == row && snake.getSegment(i - 1)[1] == column + 1) {
                            return;
                        } else {
                            newHeadCoordinates = new Integer[]{row, column + 1};
                            newHeadTile = Tile.SNAKE_HEAD_RIGHT;
                        }

                        break;
                    case "l":

                        if (snake.getSegment(i - 1)[0] == row && snake.getSegment(i - 1)[1] == column - 1) {
                            return;
                        } else {
                            newHeadCoordinates = new Integer[]{row, column - 1};
                            newHeadTile = Tile.SNAKE_HEAD_LEFT;
                        }

                        break;

                    default:
                        return;
                }

                //If the head hits a wall or its body the GameOverException is thrown.
                if (matrix[newHeadCoordinates[0]][newHeadCoordinates[1]].equals(Tile.EMPTY) || matrix[newHeadCoordinates[0]][newHeadCoordinates[1]].equals(Tile.SNAKE_TAIL)) {
                    //Updating the head segment's coordinates and the tile in the matrix.
                    this.snake.setSegment(i, newHeadCoordinates[0], newHeadCoordinates[1]);
                    this.matrix[newHeadCoordinates[0]][newHeadCoordinates[1]] = newHeadTile;

                //If the snake reaches a fruit, the snake eat the fruit and grows, after that a new fruit is added and the rest of the body doesn't move.
                } else if (matrix[newHeadCoordinates[0]][newHeadCoordinates[1]].equals(Tile.FRUIT)){
                    this.snake.increaseSize(newHeadCoordinates[0], newHeadCoordinates[1]);
                    this.matrix[newHeadCoordinates[0]][newHeadCoordinates[1]] = newHeadTile;
                    this.setBodyDirection(i);
                    this.addFruit();
                    return;

                }else {
                    throw new GameOverException("Game Over");
                }

                /*
                If the current segment is the final segment of the snake's body (the tail):
                -the value of this tile in the field is changed to Tile.EMPTY.
                -This segment's coordinates is changed to the old coordinates of the segment that was updated directly before this segment.
                -the value of the updated coordinate is changed to Tile.SNAKE_TAIL
                 */
            } else if (i == 0) {
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

        /*
        After all the snake's coordinates are updated the dody tiles are set to the correct values.
         */
        for(int i = 1; i < snake.getSize() - 1; i++){
            this.setBodyDirection(i);
        }
    }

    /**
     * Set the correct body tile to a specific segment based on the position of the preceding and following segments.
     * @param segmentIndex indicates which segment will have the value set.
     */
    private void setBodyDirection(int segmentIndex) {
        int segmentRow = snake.getSegment(segmentIndex)[0];
        int segmentColumn = snake.getSegment(segmentIndex)[1];

        int precedingSegmentIndex = segmentIndex - 1;
        int precedingSegmentRow = snake.getSegment(precedingSegmentIndex)[0];
        int precedingSegmentColumn = snake.getSegment(precedingSegmentIndex)[1];

        int followingSegmentIndex = segmentIndex + 1;
        int followingSegmentRow = snake.getSegment(followingSegmentIndex)[0];
        int followingSegmentColumn = snake.getSegment(followingSegmentIndex)[1];

        Tile bodyTile;

        if (segmentRow == precedingSegmentRow && segmentRow == followingSegmentRow) {
            bodyTile = Tile.SNAKE_HORIZONTAL_BODY;

        } else if (segmentColumn == precedingSegmentColumn && segmentColumn == followingSegmentColumn) {
            bodyTile = Tile.SNAKE_VERTICAL_BODY;

        } else if ((segmentRow == followingSegmentRow+1 && segmentColumn == precedingSegmentColumn-1) || (segmentRow == precedingSegmentRow+1 && segmentColumn == followingSegmentColumn-1)) {
            bodyTile = Tile.SNAKE_CORNER_RIGHT_UP_BODY;

        } else if ((segmentRow == followingSegmentRow-1 && segmentColumn == precedingSegmentColumn-1) || (segmentRow == precedingSegmentRow-1 && segmentColumn == followingSegmentColumn-1)) {
            bodyTile = Tile.SNAKE_CORNER_RIGHT_DOWN_BODY;

        } else if ((segmentRow == followingSegmentRow-1 && segmentColumn == precedingSegmentColumn+1) || (segmentRow == precedingSegmentRow-1 && segmentColumn == followingSegmentColumn+1)) {
            bodyTile = Tile.SNAKE_CORNER_LEFT_DOWN_BODY;

        } else {
            bodyTile = Tile.SNAKE_CORNER_LEFT_UP_BODY;
        }

        int row = snake.getSegment(segmentIndex)[0];
        int column = snake.getSegment(segmentIndex)[1];

        matrix[row][column] = bodyTile;
    }
}
