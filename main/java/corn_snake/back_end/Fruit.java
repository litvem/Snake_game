package corn_snake.back_end;

public class Fruit {

    private Integer _row;
    private Integer _col;
    public Fruit(Integer row, Integer column) {
        this._col = column;
        this._row = row;
    }

    private Integer getRows() {
        return _row;
    }

    private void setRows(Integer row) {
        this._row = row;
    }

    private Integer getColumn() {
        return _col;
    }

    private void setCols(Integer col) {
        this._col = col;
    }
}
