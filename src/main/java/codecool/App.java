package codecool;


import codecool.model.Collection;

import java.util.ArrayList;

public class App {

    static ArrayList<Field> allFields = new ArrayList<>();
    static Collection[] rows = new Collection[9];
    static Collection[] columns = new Collection[9];
    static Collection[] squares = new Collection[9];

    public static void main(String[] args) throws InterruptedException {
        int[][] tempSudoku = Tools.stringToArray(Tools.tempStringSudoku);
        divideBoard(tempSudoku);
        fullfillArraysWithEmptyCollections();
        addFieldsToCollections();
        System.out.println(Tools.isCorrect(tempSudoku));

//        addFieldsFromCollumns();
        while(true){
            addSureValues();
        }
        //Tools.printSudoku(Tools.fieldsToArray(allFields));

        /* W RAMACH TESTOWANIA */

        }

    public static void addSureValues() throws InterruptedException {
        boolean areAllCertainFound = false;
        while(!areAllCertainFound) {
            for (Field field : allFields) {
                findPossibleValue(field);
                if (field.getProbablyValues() != null && field.getProbablyValues().size() == 1 && field.getValue() == 0) {
                    System.out.println("PEWNIACZEK DLA FIELDA O WSPÓŁRZĘDNYCH " + field.getRowId()
                            + " " + field.getColumnId() + " TO > " + field.getProbablyValues().get(0));
                    field.setValue(field.getProbablyValues().get(0));
                    Tools.printSudoku(Tools.fieldsToArray(allFields));
                    Thread.sleep(100);
                }else if(field.getProbablyValues() != null && field.getProbablyValues().size() == 2){
                    System.out.println("TYMCZASOWE WARTOSCI DLA FIELDA O WSPÓŁRZĘDNYCH" + field.getRowId()
                            + " " + field.getColumnId() + " TO > " + field.getProbablyValues().get(0) + "i" +
                    field.getProbablyValues().get(1));
                    field.setTemporaryValues(field.getProbablyValues());
                    Tools.printSudoku(Tools.fieldsToArray(allFields));
                    Thread.sleep(100);
                }
                else {
                    areAllCertainFound = true;
                }
            }
        }
    }

    public static ArrayList<Integer> findPossibleValue(Field field){

        if(field.getValue()!=0 || field.getTemporaryValues().size() != 0){
            return null;
        }

        ArrayList<Integer> possibleValues = field.getProbablyValues();

        Collection row = rows[field.getRowId()];
        Collection column = columns[field.getColumnId()];
        Collection square = squares[field.getSquareId()];
        for(Field fieldToCheckInRow : row.getListOfFieldsInThisRow()) {
            if (possibleValues.contains(fieldToCheckInRow.getValue())) {
                possibleValues.remove(new Integer(fieldToCheckInRow.getValue()));
            }
            if(fieldToCheckInRow.getTemporaryValues().size() == 2 && possibleValues.contains(fieldToCheckInRow.getTemporaryValues().get(0)) && !field.hasTemporary()){
                possibleValues.remove(new Integer(fieldToCheckInRow.getTemporaryValues().get(0)));
            }
            if(fieldToCheckInRow.getTemporaryValues().size() == 2 && possibleValues.contains(fieldToCheckInRow.getTemporaryValues().get(1)) && !field.hasTemporary()){
                possibleValues.remove(new Integer(fieldToCheckInRow.getTemporaryValues().get(1)));
            }
        }

        for(Field fieldToCheckInColumn : column.getListOfFieldsInThisRow()){
            if(possibleValues.contains(fieldToCheckInColumn.getValue())){
                possibleValues.remove(new Integer(fieldToCheckInColumn.getValue()));
            }
            if(fieldToCheckInColumn.getTemporaryValues().size() == 2 && possibleValues.contains(fieldToCheckInColumn.getTemporaryValues().get(0)) && !field.hasTemporary()){
                possibleValues.remove(new Integer(fieldToCheckInColumn.getTemporaryValues().get(0)));
            }
            if(fieldToCheckInColumn.getTemporaryValues().size() == 2 && possibleValues.contains(fieldToCheckInColumn.getTemporaryValues().get(1)) && !field.hasTemporary()){
                possibleValues.remove(new Integer(fieldToCheckInColumn.getTemporaryValues().get(1)));
            }
        }

        for(Field fieldToCheckInSquare : square.getListOfFieldsInThisRow()){
            if(possibleValues.contains(fieldToCheckInSquare.getValue())){
                possibleValues.remove(new Integer(fieldToCheckInSquare.getValue()));
            }
            if(fieldToCheckInSquare.getTemporaryValues().size() == 2 && possibleValues.contains(fieldToCheckInSquare.getTemporaryValues().get(0)) && !field.hasTemporary()){
                possibleValues.remove(new Integer(fieldToCheckInSquare.getTemporaryValues().get(0)));
            }
            if(fieldToCheckInSquare.getTemporaryValues().size() == 2 && possibleValues.contains(fieldToCheckInSquare.getTemporaryValues().get(1)) && !field.hasTemporary()){
                possibleValues.remove(new Integer(fieldToCheckInSquare.getTemporaryValues().get(1)));
            }
        }

        field.setProbablyValues(possibleValues);

        return possibleValues;
    }

    public static void fullfillArraysWithEmptyCollections(){
        for(int i = 0 ; i<9 ;i++){
            rows[i] = new Collection(i);
            columns[i] = new Collection(i);
            squares[i] = new Collection(i);
        }
    }

//    public static void addFieldsFromCollumns() {
//        allFields.clear();
//
//        for (Collection column : columns) {
//            for (Field field : column.getListOfFieldsInThisRow()) {
//                allFields.add(field);
//            }
//        }
//    }


    public static void addFieldsToCollections(){
        for(Field field : allFields){

            rows[field.getRowId()].addField(field);
            columns[field.getColumnId()].addField(field);
            squares[field.getSquareId()].addField(field);
        }
    }

    public static void divideBoard(int[][] board){
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
