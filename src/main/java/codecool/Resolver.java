package codecool;

import codecool.model.Collection;
import codecool.model.Field;

import java.util.ArrayList;

public class Resolver {

    private ArrayList<Field> allFields = new ArrayList<>();
    private Collection[] rows = new Collection[9];
    private Collection[] columns = new Collection[9];
    private Collection[] squares = new Collection[9];

    public void resolve(int[][] sudoku) {
        divideBoard(sudoku);
        fullfillArraysWithEmptyCollections();
        addFieldsToCollections();
        System.out.println(Tools.isCorrect(sudoku));

//        addFieldsFromCollumns();
        Tools.printSudoku(Tools.fieldsToArray(allFields));

        /* W RAMACH TESTOWANIA */
        boolean areAllCertainFound = false;

        int numberOfInserted = 0;
        while(!areAllCertainFound) {
            areAllCertainFound = true;

            for (Field field : allFields) {
                if(field.getValue()!=0){
                    continue;
                }
                findPossibleValue(field);
                if (field.getProbablyValues() != null && field.getProbablyValues().size() == 1) {
                    System.out.println("PEWNIACZEK DLA FIELDA O WSPÓŁRZĘDNYCH " + field.getRowId()
                            + " " + field.getColumnId() + " TO > " + field.getProbablyValues().get(0));
                    field.setValue(field.getProbablyValues().get(0));
                    numberOfInserted++;
                    Tools.printSudoku(Tools.fieldsToArray(allFields));
                    System.out.println(numberOfInserted);
                    System.out.println(Tools.isCorrect(Tools.fieldsToArray(allFields)));
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    areAllCertainFound = false;
                }
            }
        }
    }

    private ArrayList<Integer> findPossibleValue(Field field){

        if(field.getValue()!=0){
            return null;
        }

        ArrayList<Integer> possibleValues = field.getProbablyValues();

        Collection row = rows[field.getRowId()];
        ArrayList<Field> fieldsInRow = row.getListOfFieldsInThisRow();
        Collection column = columns[field.getColumnId()];
        ArrayList<Field> fieldsInColumn = column.getListOfFieldsInThisRow();
        Collection square = squares[field.getSquareId()];
        ArrayList<Field> fieldsInSquare = square.getListOfFieldsInThisRow();

        ArrayList<Field> summaryFields = new ArrayList<>();
        summaryFields.addAll(fieldsInRow);
        summaryFields.addAll(fieldsInColumn);
        summaryFields.addAll(fieldsInSquare);

        for(Field fieldToCheckInRow : summaryFields) {
            if (possibleValues.contains(fieldToCheckInRow.getValue())) {
                possibleValues.remove(new Integer(fieldToCheckInRow.getValue()));
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
        for(Field field : allFields){

            rows[field.getRowId()].addField(field);
            columns[field.getColumnId()].addField(field);
            squares[field.getSquareId()].addField(field);
        }
    }

    private void divideBoard(int[][] board){
        int currentRowNo = 0;
        int currentColumnNo = 0;
        int currentSquareNo = -1;
        for(int[] row:board){
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
