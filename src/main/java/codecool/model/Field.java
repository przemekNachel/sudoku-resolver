package codecool.model;

import java.util.ArrayList;

public class Field {
    private int value;
    private int rowId;
    private int columnId;
    private int squareId;
    private int emptyFieldValue = 0;
    private ArrayList<Integer> probablyValues;
    private int probablyListCapacity = 9;

    public Field(int value, int rowId, int columnId, int squareId){
        this.value = value;
        this.rowId = rowId;
        this.columnId = columnId;
        this.squareId = squareId;
        if(value==emptyFieldValue){
            probablyValues = new ArrayList<>(probablyListCapacity);
            for(int i = 1 ; i <10 ; i++){
                probablyValues.add(i);
            }
        }
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
        if (probablyValues != null && !probablyValues.isEmpty()){
            return probablyValues;
        }
        ArrayList<Integer> notNullList = new ArrayList<>();
        notNullList.add(null);
        return notNullList;
    }

    public void setProbablyValues(ArrayList<Integer> probablyValues){
        this.probablyValues = probablyValues;
    }
}