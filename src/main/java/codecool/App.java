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
//        for (Field field : allFields) {
//            System.out.print(
//                    "v" + field.getValue() + " c" + field.getColumnId() + " r" + field.getRowId() + " s" + field.getSquareId() + "\n"
//            );
//        }
        new Resolver(columns, rows, squares).resolve();
        addFieldsFromCollumns();
        Tools.printSudoku(Tools.fieldsToArray(allFields));
    }

    public static void fullfillArraysWithEmptyCollections(){
        for(int i = 0 ; i<9 ;i++){
            rows[i] = new Collection(i);
            columns[i] = new Collection(i);
            squares[i] = new Collection(i);
        }
    }

    public static void addFieldsFromCollumns() {
        allFields.clear();

        for (Collection column : columns) {
            for (Field field : column.getListOfFieldsInThisRow()) {
                allFields.add(field);
            }
        }
    }


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
