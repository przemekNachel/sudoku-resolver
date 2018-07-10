package codecool;

import codecool.model.Collection;

import java.util.ArrayList;

public class Tools {

    ArrayList<Collection> rows;
    ArrayList<Collection> columns;
    ArrayList<Collection> squares;

    public static int[][] tempSudoku = new int[][] {
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
}
