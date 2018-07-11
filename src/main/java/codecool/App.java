package codecool;

public class App {

    public static void main(String[] args) {

        int[][] sudoku = Tools.stringToArray(Tools.tempStringSudoku);
        Tools.printSudoku(sudoku);

        Resolver resolver = new Resolver(sudoku);

        int[][] resolved = resolver.resolve();
        Tools.printSudoku(resolved);
    }
}
