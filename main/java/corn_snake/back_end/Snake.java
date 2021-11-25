package corn_snake.back_end;

import java.util.ArrayList;
import java.util.Arrays;

public class Snake {
    private ArrayList<Integer[]> body;

    /**
     The constructor will always create a body with the same size.
     The body consists of an ArrayList of arrays of Integer (always two integer, the first represents the row and the second represents the column).
     */
    public Snake(Integer[] segment0, Integer[] segment1, Integer[] segment2, Integer[] segment3){
        this.body = new ArrayList<>(Arrays.asList(segment0, segment1, segment2, segment3));
    }

    /**
     *
     * @return Returns the size of the snake.
     */
    public int getSize(){
        return body.size();
    }

    /**
     *
     * @param segment is an integer corresponding to the index of a specific segment of the snake.
     * @return an array corresponding to the coordinates of the specific segment.
     */
    public Integer[] getSegment(int segment){
        return body.get(segment);
    }

    /**
     *
     * @param segment specifies which segment will be changed.
     * @param newRow will be the new row of the segment.
     * @param newColumn will be the new column of the segment.
     */
    public void setSegment(int segment, int newRow, int newColumn){
        Integer[] newPosition = new Integer[]{newRow, newColumn};
        body.set(segment, newPosition);
    }
}
