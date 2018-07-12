package codecool;

public class App {

    public static void main(String[] args) {
        int[][] sudoku = Tools.stringToArray(Tools.tempStringSudoku);
        Resolver.startTime = System.currentTimeMillis();
        new Resolver(sudoku).run();
    }
}
