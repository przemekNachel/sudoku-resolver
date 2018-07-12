package codecool;

import codecool.model.Collection;
import codecool.model.Field;

import java.util.ArrayList;

public class Resolver extends Thread{

    private ArrayList<Field> allFields = new ArrayList<>();
    private Collection[] rows = new Collection[9];
    private Collection[] columns = new Collection[9];
    private Collection[] squares = new Collection[9];
    private static ArrayList<Resolver> resolverThreads = new ArrayList<>();

    public Resolver(int[][] sudoku){
        divideBoard(sudoku);
        fullfillArraysWithEmptyCollections();
        addFieldsToCollections();
        System.out.println(Tools.isCorrect(sudoku));
    }

    public Resolver(ArrayList<Field> allFields, Collection[] rows, Collection[] columns, Collection[] squares){
        this.rows = rows;
        this.columns = columns;
        this.squares = squares;
        this.allFields = allFields;
    }

    public void run(){
        resolve();
        if(!isSolved()) {
            ArrayList<Field> fields = getFieldsWithTwoPossibilities();
            for(Field field: fields ) {
                field.setValue(field.getProbablyValues().get(0));
                Resolver resolver = new Resolver(allFields, rows, columns, squares);
                resolverThreads.add(resolver);
                resolver.start();
                field.setValue(field.getProbablyValues().get(1));
                Resolver nextResolver = new Resolver(allFields, rows, columns, squares);
                resolverThreads.add(nextResolver);
                nextResolver.start();
            }
        }
        else{
            Tools.printSudoku(Tools.fieldsToArray(allFields));
            System.out.println("Thread " + Thread.currentThread().getId() + " has solved the puzzle");
            for(Resolver resolver: resolverThreads){
                resolver.interrupt();
            }
        }
    }

    private boolean isSolved(){
        for(Field field: allFields){
            if(field.getValue() == 0){
                return false;
            }
        }
        return true;
    }


    private ArrayList<Field> getFieldsWithTwoPossibilities(){
        ArrayList<Field> fieldsWithTwoPossibilities = new ArrayList<>();
        for(Field field: allFields){
            if(field.getValue() == 0 && findPossibleValue(field).size() == 2) {
                fieldsWithTwoPossibilities.add(field);
            }
        }
        if(fieldsWithTwoPossibilities.isEmpty()){
            Thread.currentThread().interrupt();
        }
        return fieldsWithTwoPossibilities;
    }

    @Override
    public void interrupt(){
        System.out.println("Thread no. " + Thread.currentThread().getId() + " Has been interrupted");
    }

    public void resolve() {
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
                    System.out.println("\n\n\n" + Thread.currentThread().getId());
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
