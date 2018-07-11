package codecool.model;

import java.util.ArrayList;

public class Field {
    private static final int ARRAY_CAPACITY = 9;
    private static final int EMPTY_FIELD_VALUE = 0;
    private int value;
    private int rowId;
    private int columnId;
    private int squareId;
    private ArrayList<Integer> probablyValues;

    public Field(int value, int rowId, int columnId, int squareId){
        this.value = value;
        this.rowId = rowId;
        this.columnId = columnId;
        this.squareId = squareId;
        if(value == EMPTY_FIELD_VALUE) addInitialProbablyValues();
    }

    private void addInitialProbablyValues() {
        probablyValues = new ArrayList<>(ARRAY_CAPACITY);
        for(int i = 1 ; i <10 ; i++) probablyValues.add(i);
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

    public void setValue(int value) {
        this.value = value;
    }

    public ArrayList<Integer> getProbablyValues() {
        return probablyValues;
    }

    public void setProbablyValues(ArrayList<Integer> probablyValues){
        this.probablyValues = probablyValues;
    }

}