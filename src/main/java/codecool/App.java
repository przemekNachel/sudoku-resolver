package codecool;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        int[][] sudoku = Tools.stringToArray(Tools.tempStringSudoku);
        new Resolver(sudoku).run();
    }
}
