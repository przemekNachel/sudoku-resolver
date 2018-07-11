package codecool;

import codecool.model.Collection;
import codecool.model.Field;

import java.util.ArrayList;

public class Resolver {

    private static final boolean SHOW_STEPS = true;
    private static final int STEP_TIME = 0;
    private int step = 0;
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
        while(!Tools.isFullAndCorrect(Tools.fieldsToArray(allFields))) {
            checkLastPossibleValues();

        }
        return Tools.fieldsToArray(allFields);
    }

    private void checkLastPossibleValues() {
        boolean areAllCertainFound = false;
        while(!areAllCertainFound) {
            areAllCertainFound = true;
            for (Field field : allFields) {
                if(field.getValue() == 0) {
                    findPossibleValue(field);
                    if (field.getProbablyValues().size() == 1) {
                        field.setValue(field.getProbablyValues().get(0));
                        areAllCertainFound = false;
                        if (SHOW_STEPS) showStep();
                    }
                }
            }
        }
    }

    private void showStep() {
        Tools.printSudoku(Tools.fieldsToArray(allFields));
        System.out.println("step " + step++);
        try {
            Thread.sleep(STEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void findPossibleValue(Field field){
        if(field.getValue()!=0) return;

        ArrayList<Field> summaryFields = getSummaryFields(field);
        for(Field fieldToCheck : summaryFields) {
            if (field.getProbablyValues().contains(fieldToCheck.getValue())) {
                field.getProbablyValues().remove(new Integer(fieldToCheck.getValue()));
            }
        }
        field.setProbablyValues(field.getProbablyValues());
    }

    private ArrayList<Field> getSummaryFields(Field field) {
        ArrayList<Field> summaryFields = new ArrayList<>();
        summaryFields.addAll(rows[field.getRowId()].getListOfFieldsInThisRow());
        summaryFields.addAll(columns[field.getColumnId()].getListOfFieldsInThisRow());
        summaryFields.addAll(squares[field.getSquareId()].getListOfFieldsInThisRow());
        return summaryFields;
    }

    private void fillArraysWithEmptyCollections(){
        for(int i = 0 ; i<9 ;i++){
            rows[i] = new Collection();
            columns[i] = new Collection();
            squares[i] = new Collection();
        }
    }


    private void addFieldsToCollections(){
        fillArraysWithEmptyCollections();
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
                if(currentColumnNo%3==0) currentSquareNo ++ ;
                Field fieldToAdd = new Field(fieldValue, currentRowNo, currentColumnNo, currentSquareNo);
                allFields.add(fieldToAdd);
                currentColumnNo ++;
            }
            currentRowNo ++;
            currentSquareNo = -1;
            currentColumnNo = 0;
            if(currentRowNo > 5) currentSquareNo += 6;
            else if(currentRowNo > 2) currentSquareNo += 3;
        }
    }
}
