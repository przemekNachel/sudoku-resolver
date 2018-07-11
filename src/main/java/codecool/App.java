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
                    Thread.sleep(100);
                    areAllCertainFound = false;
                }
            }
        }


//        System.out.println(rows[6].getListOfFieldsInThisRow().get(0).getProbablyValues());
//        System.out.println(rows[6].getListOfFieldsInThisRow().get(0).getValue());
//        System.out.println(rows[6].getListOfFieldsInThisRow().get(0).getColumnId());
//        System.out.println(rows[6].getListOfFieldsInThisRow().get(0).getRowId());

    }

    public static ArrayList<Integer> findPossibleValue(Field field){

        if(field.getValue()!=0){
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
        }

        for(Field fieldToCheckInColumn : column.getListOfFieldsInThisRow()){
            if(possibleValues.contains(fieldToCheckInColumn.getValue())){
                possibleValues.remove(new Integer(fieldToCheckInColumn.getValue()));
            }
        }

        for(Field fieldToCheckInSquare : square.getListOfFieldsInThisRow()){
            if(possibleValues.contains(fieldToCheckInSquare.getValue())){
                possibleValues.remove(new Integer(fieldToCheckInSquare.getValue()));
            }
        }

        field.setProbablyValues(possibleValues);

        return possibleValues;
    }

    public static void fullfillArraysWithEmptyCollections(){
        for(int i = 0 ; i<9 ;i++){
            rows[i] = new Collection();
            columns[i] = new Collection();
            squares[i] = new Collection();
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
