package codecool;


import codecool.model.Collection;

import java.util.ArrayList;

public class App {

    static ArrayList<Field> allFields = new ArrayList<>();
    static Collection[] rows = new Collection[9];
    static Collection[] columns = new Collection[9];
    static Collection[] squares = new Collection[9];

    public static void main(String[] args) {
        int[][] tempSudoku = Tools.stringToArray(Tools.tempStringSudoku);
        Tools.printSudoku(tempSudoku);
        divideBoard(tempSudoku);
        fullfillArraysWithEmptyCollections();
        addFieldsToCollections();
        System.out.println(Tools.isCorrect(tempSudoku));
        for (Field field : allFields) {
            System.out.print(
                    "v" + field.getValue() + " c" + field.getColumnId() + " r" + field.getRowId() + " s" + field.getSquareId() + "\n"
            );
        }
//        addFieldsFromCollumns();
        Tools.printSudoku(Tools.fieldsToArray(allFields));

        Field fieldToTest = allFields.get(6);
//        System.out.println(fieldToTest.getValue());

        /* W RAMACH TESTOWANIA */
        for(Field field : allFields){
            field.setProbablyValues(findPossibleValue(field));
            if(field.getProbablyValues() != null && field.getProbablyValues().size()==1){
                System.out.println("PEWNIACZEK DLA FIELDA O WSPÓŁRZĘDNYCH " + field.getRowId()
                + " " + field.getColumnId() + " TO > " + field.getProbablyValues().get(0));
                field.setValue(field.getProbablyValues().get(0)); 
            }
        }
    }

    public static ArrayList<Integer> findPossibleValue(Field field){

        if(field.getValue()!=0){
            System.out.println("This field is not empty");
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

        System.out.println(possibleValues.toString());

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
