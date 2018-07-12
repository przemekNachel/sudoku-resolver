package codecool;

import codecool.model.Field;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tools {
    private static ArrayList<Resolver> resolverThreads = new ArrayList<>();

    static String tempStringSudoku = ".....75..7..1...4.5.....2....139...83..786..48...417....8.....9.5...3..1..46.....";

    public static ArrayList<Resolver> getResolverThreads() {
        return resolverThreads;
    }

    public static void addResolverThread(Resolver resolver) {
        resolverThreads.add(resolver);
    }

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
