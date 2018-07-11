package codecool;

import codecool.model.Field;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tools {

    static String tempStringSudoku = "8.4.59.....1....5......362.5..........2.....8.....4...98.6..3.1.2.89..4.1..3.....";

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
        for(int i = 0; i < sudoku.length; i++) {
            for(int j = 0; j < sudoku[i].length; j++) {
                if (sudoku[i][j] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(sudoku[i][j] + " ");
                }
                if (j == 2 || j == 5) System.out.print("| ");
            }
            System.out.println();
            if (i == 2 || i == 5) System.out.println("------+-------+------");
        }
    }

    public static boolean isCorrect(int[][] tempSudoku) {
        boolean linesAreCorrect = checkSudokuLines(tempSudoku);
        boolean columnsAreCorrect = checkSudokuColumns(tempSudoku);
        boolean squaresAreCorrect = checkSudokuSquares(tempSudoku);
        return linesAreCorrect && columnsAreCorrect && squaresAreCorrect;
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

    private static boolean checkSudokuColumns(int[][] tempSudoku) {
        boolean isCorrect = true;
        Set<Integer> digits = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int digit = tempSudoku[j][i];
                if (digit != 0 && digits.contains(digit)) isCorrect = false;
                digits.add(digit);
            }
            digits.clear();
        }
        return isCorrect;
    }

    private static boolean checkSudokuSquares(int[][] tempSudoku) {
        boolean isCorrect = true;
        Set<Integer> digits = new HashSet<>();

        for(int i = 0; i < 9; i += 3) {
            for(int j = 0; j < 9; j += 3) {
                for(int x = j; x < j + 3; x++) {
                    for(int y = i; y < i + 3; y++) {
                        int digit = tempSudoku[x][y];
                        if (digit != 0 && digits.contains(digit)) isCorrect = false;
                        digits.add(digit);
                    }
                }
                digits.clear();
            }
        }
        return isCorrect;
    }

    public static int[][] stringToArray(String string) {
        int[][] board = new int[9][9];
        int[] intArray = new int[81];
        for (int i = 0; i < 81; i ++) {
            char ch = string.charAt(i);
            int toPut = 0;
            if (ch != ".".charAt(0)) toPut = Character.getNumericValue(ch);
            intArray[i] = toPut;
        }

        int x = 0;
        int y = 0;
        for (int i : intArray) {
            board[y][x] = i;
            x++;
            if (x == 9) {
                y++;
                x = 0;
            }
        }
        return board;
    }

    public static int[][] fieldsToArray(List<Field> fields) {
        int[][] array = new int[9][9];
        for (Field field : fields) {
            array[field.getRowId()][field.getColumnId()] = field.getValue();
        }
        return array;
    }
}
