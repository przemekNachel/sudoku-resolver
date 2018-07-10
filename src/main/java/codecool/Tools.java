package codecool;

public class Tools {

    static int[][] tempSudoku = new int[][] {
        {1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 9}};

    public static int[][] readSudokuFromFile(String filename) {
        return new int[9][9];
    }

    public static void printSudoku(int[][] sudoku) {
        for(int i=0; i<tempSudoku.length; i++) {
            for(int j=0; j<tempSudoku[i].length; j++) {
                System.out.print(tempSudoku[i][j] + " ");
                if (j == 2 || j == 5) System.out.print("| ");
            }
            System.out.println();
            if (i == 2 || i == 5) System.out.println("---------------------");
        }
    }
}
