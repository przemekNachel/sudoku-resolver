package codecool;

import codecool.model.Collection;
import codecool.model.Field;

import java.util.ArrayList;

public class Resolver {

    private int[][] sudoku;
    private ArrayList<Field> allFields = new ArrayList<>();
    private Collection[] rows = new Collection[9];
    private Collection[] columns = new Collection[9];
    private Collection[] squares = new Collection[9];

    public Resolver(int[][] sudoku) {
        this.sudoku = sudoku;
        divideBoard();
        addFieldsToCollections();
    }

    public int[][] resolve() {
        boolean isSolved = false;

        while(!isSolved) {
            isSolved = true;
            for (Field field : allFields) {
                if(field.getValue()!=0) continue;
                findPossibleValue(field);
                if (field.getProbablyValues().size() == 1) {
                    field.setValue(field.getProbablyValues().get(0));
                    isSolved = false;
                }
            }
        }
        return Tools.fieldsToArray(allFields);
    }

    private ArrayList<Integer> findPossibleValue(Field field){
        if(field.getValue()!=0) return null;

        ArrayList<Integer> possibleValues = field.getProbablyValues();
        ArrayList<Field> summaryFields = new ArrayList<>();

        summaryFields.addAll(rows[field.getRowId()].getListOfFieldsInThisRow());
        summaryFields.addAll(columns[field.getColumnId()].getListOfFieldsInThisRow());
        summaryFields.addAll(squares[field.getSquareId()].getListOfFieldsInThisRow());

        for(Field fieldToCheck : summaryFields) {
            if (possibleValues.contains(fieldToCheck.getValue())) {
                possibleValues.remove(new Integer(fieldToCheck.getValue()));
            }
        }
        field.setProbablyValues(possibleValues);
        return possibleValues;
    }

    private void fullfillArraysWithEmptyCollections(){
        for(int i = 0 ; i<9 ;i++){
            rows[i] = new Collection();
            columns[i] = new Collection();
            squares[i] = new Collection();
        }
    }


    private void addFieldsToCollections(){
        fullfillArraysWithEmptyCollections();
        for(Field field : allFields){
            rows[field.getRowId()].addField(field);
            columns[field.getColumnId()].addField(field);
            squares[field.getSquareId()].addField(field);
        }
    }

    private void divideBoard(){
        int currentRowNo = 0;
        int currentColumnNo = 0;
        int currentSquareNo = -1;
        for(int[] row : sudoku){
            for(int fieldValue:row){

                if(currentColumnNo%3==0){
                    currentSquareNo ++ ;
                }

                Field fieldToAdd = new Field(fieldValue, currentRowNo, currentColumnNo, currentSquareNo);
                allFields.add(fieldToAdd);
                currentColumnNo ++;
            }

            currentRowNo ++;
            currentSquareNo = -1;
            currentColumnNo = 0;

            if(currentRowNo > 5){
                currentSquareNo += 6;
            }
            else if(currentRowNo > 2){
                currentSquareNo += 3;
            }
        }
    }
}
