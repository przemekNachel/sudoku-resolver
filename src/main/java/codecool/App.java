package codecool;


import codecool.model.Collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {

    static ArrayList<Field> allFields = new ArrayList<>();
    static Collection[] rows = new Collection[9];
    static Collection[] columns = new Collection[9];
    static Collection[] squares = new Collection[9];

    public static void main( String[] args )

    {
        Tools.printSudoku(Tools.tempSudoku);
        devideBoard(Tools.tempSudoku);
        fullfillArraysWithEmptyCollections();
        addFieldsToCollections();
    }

    public static void fullfillArraysWithEmptyCollections(){
        for(int i = 0 ; i<9 ;i++){
            rows[i] = new Collection(i);
            columns[i] = new Collection(i);
            squares[i] = new Collection(i);
        }
    }

    public static void addFieldsToCollections(){
        for(Field field : allFields){

            rows[field.getRowId()].addField(field);
            columns[field.getColumnId()].addField(field);
            squares[field.getSquareId()].addField(field);
        }
    }

    public static void devideBoard(int[][] board){
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
