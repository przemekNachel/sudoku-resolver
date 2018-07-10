package codecool;


public class App {

    public static void main( String[] args )
    {
        Tools.printSudoku(Tools.tempSudoku);
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
