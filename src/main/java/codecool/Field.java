package codecool;

public class Field {
    private int value;
    private int rowId;
    private int columnId;
    private int squareId;

    public Field(int value, int rowId, int columnId, int squareId){
        this.value = value;
        this.rowId = rowId;
        this.columnId = columnId;
        this.squareId = squareId;
    }

    public int getRowId() {
        return rowId;
    }

    public int getColumnId() {
        return columnId;
    }

    public int getSquareId() {
        return squareId;
    }

    public int getValue() {
        return value;
    }
}
