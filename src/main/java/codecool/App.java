package codecool;


public class App {

    public static void main( String[] args )
    {
        int[][] tempSudoku = new int[][] {
                {1, 2, 3,   0, 0, 0,   0, 0, 0},
                {4, 0, 6,   0, 0, 0,   0, 0, 0},
                {7, 8, 9,   0, 0, 0,   0, 0, 0},

                {0, 0, 0,   0, 0, 0,   0, 0, 0},
                {0, 0, 0,   0, 0, 0,   0, 0, 0},
                {0, 0, 0,   0, 0, 0,   0, 0, 0},

                {0, 0, 0,   0, 0, 0,   0, 0, 0},
                {0, 0, 0,   0, 0, 0,   0, 0, 0},
                {0, 0, 0,   0, 0, 0,   0, 0, 9}};

        Tools tool = new Tools();
        tool.printSudoku(tempSudoku);
    }
}
