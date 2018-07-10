package codecool;


public class App {

    public static void main( String[] args ) {
        Tools.printSudoku(Tools.tempSudoku);
        System.out.println("\n" + Tools.isCorrect(Tools.tempSudoku));
    }
}
