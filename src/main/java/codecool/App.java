package codecool;

public class App {

    public static void main( String[] args )
    {
        Tools.printSudoku(Tools.tempSudoku);
        System.out.println(Tools.isCorrect(Tools.tempSudoku));
        for (Field field : Tools.stringToFieldArrayList(Tools.n)) {
            System.out.print(field.getValue() + "  c" + field.getColumnId() + "  r" +
            field.getRowId() + "  s" + field.getSquareId() + "\n");
        }
        System.out.println(Tools.n);

        Tools.printSudoku(Tools.stringToArray(Tools.n));
    }

    public void devideBoard(int[][] board){
        int currentRowNo = 0;
        int currentColumnNo = 0;
        int currentSquareNo = 0;
        for(int[] row:board){
            for(int fieldValue:row){

                if(currentColumnNo%3==0){
                    currentSquareNo ++ ;
                }

                new Field(fieldValue, currentRowNo, currentColumnNo, currentSquareNo);
                currentColumnNo ++;

            }
            currentRowNo ++;
            currentSquareNo = 0;
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
