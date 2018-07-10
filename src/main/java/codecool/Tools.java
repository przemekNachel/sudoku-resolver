package codecool;

import java.util.HashSet;
import java.util.Set;

public class Tools {

    static int[][] tempSudoku = new int[][] {
            {0, 0, 0,   0, 0, 6,   4, 0, 9},
            {4, 0, 0,   0, 1, 9,   5, 8, 0},
            {5, 0, 0,   0, 0, 0,   0, 1, 7},

            {0, 0, 0,   0, 0, 0,   0, 0, 0},
            {3, 0, 0,   0, 0, 0,   0, 5, 8},
            {9, 0, 0,   0, 0, 0,   7, 0, 0},

            {0, 3, 0,   0, 6, 0,   0, 7, 0},
            {0, 5, 0,   7, 0, 0,   8, 0, 0},
            {1, 4, 0,   3, 0, 5,   0, 0, 6}};

    public static void printSudoku(int[][] sudoku) {
        for(int i=0; i<tempSudoku.length; i++) {
            for(int j=0; j<tempSudoku[i].length; j++) {
                System.out.print(tempSudoku[i][j] + " ");
                if (j == 2 || j == 5) System.out.print("| ");
            }
            System.out.println();
            if (i == 2 || i == 5) System.out.println("------+-------+------");
        }
    }

    public static boolean isCorrect(int[][] tempSudoku) {
        boolean isCorrect = true;
        isCorrect = checkSudokuLines(tempSudoku);
        return isCorrect;
    }

    private static boolean checkSudokuLines(int[][] tempSudoku) {
        boolean isCorrect = true;

        Set<Integer> digits = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j : tempSudoku[i]) {
                if (j != 0 && digits.contains(j)) isCorrect = false;
                digits.add(j);
            }
            digits.clear();
        }
        return isCorrect;
    }
}
