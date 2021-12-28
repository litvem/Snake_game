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
                this.matrix[1 + i][7] = Tile.SNAKE_DOWNWARD_GOING_TAIL;
            } else if (i == INITIAL_COORDINATES.length - 1) {
                this.matrix[1 + i][7] = Tile.SNAKE_HEAD_DOWN;
            } else {
                this.matrix[1 + i][7] = Tile.SNAKE_VERTICAL_BODY;
            }
        }
    }

    public int getSnakeSize(){
        return this.snake.getSize();
    }

    /**
     * Creates and add fruit until random location of food is empty.
     */
    private void addFruit(){
        Random rand = new Random();

        Integer r = rand.nextInt(ROWS-1)+1;
        Integer c = rand.nextInt(COLS-1)+1;
        while(true){
            if(this.matrix[r][c] == Tile.EMPTY) {
                this.fruit = new Fruit(r, c);
                break;
            } else {
                r = rand.nextInt(ROWS-1)+1;
                c = rand.nextInt(COLS-1)+1;
            }
        }
        this.matrix[r][c] = Tile.FRUIT;
    }


    public Tile[][] getMatrix(){
        Tile[][] tempMatrix = new Tile[17][17];
        for (int i = 0; i < tempMatrix.length; i++){
            System.arraycopy(this.matrix[i], 0, tempMatrix[i], 0, tempMatrix.length);
        }
        return tempMatrix;
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
     * This method iterates through all the snake's segments. For each category of segment (head, body and tail) there are specific verifications
     * to update, if possible, each segment.
     *
     * After all the verifications are made and coordinates are updated the body tiles are updated.
     *
     * If the snake hits a wall ora body segment, a GameOverException is thrown
     */
    public void moveSnake(String command) throws GameOverException {
        /*
        Creating a variable that will store the coordinates of the segment that will be updated so the next segment's coordinates
        can be updated to the location of the current segment.
         */
        Integer[] previousSegment = new Integer[]{0, 0};

        //Iterating through the snake.
        for (int i = snake.getSize() - 1; i >= 0; i--) {

            if (i == snake.getSize() - 1) {
            /*
            If the current segment is the head, the moveHead() method is called and will verify if the movement is allowed.
            If the snake eats a fruit, moveHead() returns null, in that case moveSnake() is interrupted so the snake doesn't
            move and the growth can be correctly represented.
             */
                previousSegment = this.moveHead(command);
                if (previousSegment == null){
                    return;
                }

            } else if (i == 0) {
            /*
            If the current segment is the final segment of the snake's body (the tail):
            -the value of this tile in the field is changed to Tile.EMPTY.
            -This segment's coordinates is changed to the old coordinates of the segment that was updated directly before this segment.
            -the value of the updated coordinate is changed to Tile.SNAKE_TAIL
            */
                int row = snake.getSegment(0)[0];
                int column = snake.getSegment(0)[1];

                 /*
                Before changing the tail's old coordinates' value to Tile.EMPTY assure that the old coordinates' value
                is still a tail to assure that the head doesn't disappear in specific situations.
                 */
                if (matrix[row][column].equals(Tile.SNAKE_UPWARD_GOING_TAIL) ||
                        matrix[row][column].equals(Tile.SNAKE_DOWNWARD_GOING_TAIL) ||
                        matrix[row][column].equals(Tile.SNAKE_LEFTWARD_GOING_TAIL) ||
                        matrix[row][column].equals(Tile.SNAKE_RIGHTWARD_GOING_TAIL)){

                    matrix[row][column] = Tile.EMPTY;
                }
                snake.setSegment(0, previousSegment[0], previousSegment[1]);
                matrix[previousSegment[0]][previousSegment[1]] = setTailDirection(i);

            } else {
                /*
                If the current segment is not the head neither the last segment of the snake:
                - The current segment will have its coordinates changed to the old coordinates of the segment that was updated directly before this segment.
                 */
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

    private Tile setTailDirection (int tailIndex) {

        int tailRow = snake.getSegment(tailIndex)[0];
        int tailColumn = snake.getSegment(tailIndex)[1];

        int followingSegmentIndex = tailIndex + 1;
        int followingSegmentRow = snake.getSegment(followingSegmentIndex)[0];
        int followingSegmentColumn = snake.getSegment(followingSegmentIndex)[1];

        Tile tailTile;

        if (tailRow == followingSegmentRow + 1 && tailColumn == followingSegmentColumn){
            tailTile = Tile.SNAKE_UPWARD_GOING_TAIL;
        } else if (tailRow == followingSegmentRow - 1 && tailColumn == followingSegmentColumn) {
            tailTile = Tile.SNAKE_DOWNWARD_GOING_TAIL;
        } else if (tailRow == followingSegmentRow && tailColumn == followingSegmentColumn - 1) {
            tailTile = Tile.SNAKE_RIGHTWARD_GOING_TAIL;
        } else {
            tailTile = Tile.SNAKE_LEFTWARD_GOING_TAIL;
        } return tailTile;

    }

    /**
     *
     * @param command is the new direction inputted by the user
     * @return an Integer[] with the head's coordinates before moving, so it can be used as reference
     * to move the next segments.
     * @throws GameOverException in case the head hits a wall or itself
     */
    private Integer[] moveHead(String command) throws GameOverException {
        Integer[] previousSegment;

        previousSegment = snake.getSegment(snake.getSize() - 1);
        int row = snake.getSegment(snake.getSize() - 1)[0];
        int column = snake.getSegment(snake.getSize() - 1)[1];

        Integer[] precedingSegment = snake.getSegment(snake.getSize() - 2);
        Integer[] newHeadCoordinates;
        Tile newHeadTile;

        /*
        - If the current segment is the snake's head the program verifies if the next tile in the desired direction is
        the segment that precedes the snake's head. In that case, the head will continue to move in the previous direction
        ignoring the command.

        - If it is possible to move in that direction:
        the snake's head's new coordinates will be saved in the headNewCoordinates variable,
        the correct head tile value is saved in the newHeadTile variable.
        */
        switch (command) {
            case "d":

                //if the command is "down" but the snake is going up, the snake will move upwards
                if (precedingSegment[0] == row + 1 && precedingSegment[1] == column) {
                    newHeadCoordinates = new Integer[]{row - 1, column};
                    newHeadTile = Tile.SNAKE_HEAD_UP;
                } else {
                    newHeadCoordinates = new Integer[]{row + 1, column};
                    newHeadTile = Tile.SNAKE_HEAD_DOWN;
                }

                break;
            case "u":

                //if the command is "up" but the snake is going down, the snake will move downwards
                if (precedingSegment[0] == row - 1 && precedingSegment[1] == column) {
                    newHeadCoordinates = new Integer[]{row + 1, column};
                    newHeadTile = Tile.SNAKE_HEAD_DOWN;
                } else {
                    newHeadCoordinates = new Integer[]{row - 1, column};
                    newHeadTile = Tile.SNAKE_HEAD_UP;
                }

                break;
            case "r":

                //if the command is "right" but the snake is going left, the snake will move to the left
                if (precedingSegment[0] == row && precedingSegment[1] == column + 1) {
                    newHeadCoordinates = new Integer[]{row, column - 1};
                    newHeadTile = Tile.SNAKE_HEAD_LEFT;
                } else {
                    newHeadCoordinates = new Integer[]{row, column + 1};
                    newHeadTile = Tile.SNAKE_HEAD_RIGHT;
                }

                break;
            case "l":

                //if the command is "left" but the snake is going right, the snake will move to the right
                if (precedingSegment[0] == row && precedingSegment[1] == column - 1) {
                    newHeadCoordinates = new Integer[]{row, column + 1};
                    newHeadTile = Tile.SNAKE_HEAD_RIGHT;
                } else {
                    newHeadCoordinates = new Integer[]{row, column - 1};
                    newHeadTile = Tile.SNAKE_HEAD_LEFT;
                }

                break;

            default:
                return null;
        }


        if (matrix[newHeadCoordinates[0]][newHeadCoordinates[1]].equals(Tile.EMPTY) ||
                matrix[newHeadCoordinates[0]][newHeadCoordinates[1]].equals(Tile.SNAKE_UPWARD_GOING_TAIL) ||
                matrix[newHeadCoordinates[0]][newHeadCoordinates[1]].equals(Tile.SNAKE_DOWNWARD_GOING_TAIL) ||
                matrix[newHeadCoordinates[0]][newHeadCoordinates[1]].equals(Tile.SNAKE_LEFTWARD_GOING_TAIL) ||
                matrix[newHeadCoordinates[0]][newHeadCoordinates[1]].equals(Tile.SNAKE_RIGHTWARD_GOING_TAIL)) {
            //Updating the head segment's coordinates and the tile in the matrix.
            this.snake.setSegment(snake.getSize() - 1, newHeadCoordinates[0], newHeadCoordinates[1]);
            this.matrix[newHeadCoordinates[0]][newHeadCoordinates[1]] = newHeadTile;

        //If the head eats a fruit the eatFruit() method is called and moveHead() returns null so moveSnake doesn't move the rest of the body
        } else if (matrix[newHeadCoordinates[0]][newHeadCoordinates[1]].equals(Tile.FRUIT)) {
            this.eatFruit(newHeadCoordinates, newHeadTile);
            return null;

        //If the head hits a wall or its body the GameOverException is thrown.
        } else {
            throw new GameOverException("Game Over");
        }

        return previousSegment;
    }


    /**
     *
     * @param newHeadCoordinates will be added to the snake.
     * @param newHeadTile will be set on the matrix.
     */
    private void eatFruit(Integer[] newHeadCoordinates, Tile newHeadTile){
        this.snake.increaseSize(newHeadCoordinates[0], newHeadCoordinates[1]);
        this.matrix[newHeadCoordinates[0]][newHeadCoordinates[1]] = newHeadTile;

        //The new segment is the new head, so the old head must be set as a body tile.
        this.setBodyDirection(snake.getSize() - 2);

        //A new fruit must be generated.
        this.addFruit();
    }
}
