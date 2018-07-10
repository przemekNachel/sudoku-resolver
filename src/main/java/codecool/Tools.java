package codecool;

public class Tools {

    static String[][] tempSudoku = new String[][] {
            {" ", " ", " ",   " ", " ", "6",   "4", " ", "9"},
            {"4", " ", " ",   " ", "1", "9",   "5", "8", " "},
            {"5", " ", " ",   " ", " ", " ",   " ", "1", "7"},

            {" ", " ", " ",   " ", " ", " ",   " ", " ", " "},
            {"3", " ", " ",   " ", " ", " ",   " ", "5", "8"},
            {"9", " ", " ",   " ", " ", " ",   "7", " ", " "},

            {" ", "3", " ",   " ", "6", " ",   " ", "7", " "},
            {" ", "5", " ",   "7", " ", " ",   "8", " ", " "},
            {"1", "4", " ",   "3", " ", "5",   " ", " ", "6"}};

    public static void printSudoku(String[][] sudoku) {
        for(int i=0; i<tempSudoku.length; i++) {
            for(int j=0; j<tempSudoku[i].length; j++) {
                System.out.print(tempSudoku[i][j] + " ");
                if (j == 2 || j == 5) System.out.print("| ");
            }
            System.out.println();
            if (i == 2 || i == 5) System.out.println("------+-------+------");
        }
    }
}
