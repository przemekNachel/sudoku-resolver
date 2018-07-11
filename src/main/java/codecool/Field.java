package codecool;

import java.util.ArrayList;

public class Field {
    private int value;
    private int rowId;
    private int columnId;
    private int squareId;
    private int emptyFieldValue = 0;
    private ArrayList<Integer> probablyValues;
    private boolean hasTemporary = false;
    private ArrayList<Integer> temporaryValues = new ArrayList<>();
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
        return probablyValues;
    }

    public void setTemporaryValues(ArrayList<Integer> temporaryValues){
        hasTemporary = true;
        this.temporaryValues = temporaryValues;
    }

    public boolean hasTemporary(){
        return hasTemporary;
    }

    public ArrayList<Integer> getTemporaryValues() {
        return temporaryValues;
    }

    public void setProbablyValues(ArrayList<Integer> probablyValues){
        this.probablyValues = probablyValues;
    }

    public void addProbablyValue(int value){
        probablyValues.add(value);
    }

    public void removeProbablyValue(int value){
        for (int i : probablyValues){
            if(i==value){
                probablyValues.remove(i);
            }
        }
    }
}