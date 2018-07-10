package codecool;

import java.util.ArrayList;

public class Tools {

    ResolveAlgorithm ra = new ResolveAlgorithm();
    public static ArrayList<Integer> row = new ArrayList<>(9);
    public static ArrayList<Integer> column = new ArrayList<>(9);
    public static ArrayList<Integer> kwadrat = new ArrayList<>(9);

    static int[][] tempSudoku = new int[][] {
        {1, 2, 3,   0, 0, 0,   0, 0, 0},
        {4, 0, 6,   0, 0, 0,   0, 0, 0},
        {7, 8, 9,   0, 0, 0,   0, 0, 0},

        {0, 0, 0,   0, 0, 0,   0, 0, 0},
        {0, 0, 0,   0, 0, 0,   0, 0, 0},
        {0, 0, 0,   0, 0, 0,   0, 0, 0},

        {0, 0, 0,   0, 0, 0,   0, 0, 0},
        {0, 0, 0,   0, 0, 0,   0, 0, 0},
        {0, 0, 0,   0, 0, 0,   0, 0, 9}};

    public static int[][] readSudokuFromFile(String filename) {
        return new int[9][9];
    }

    public void printSudoku(int[][] tempSudoku) {
        int counter = 0;
        for(int i=0; i<tempSudoku.length; i++) {
            for(int j=0; j<tempSudoku[i].length; j++) {
                if(row.size()<9)
                    row.add(tempSudoku[i][j]);
                if(counter<3){
                    if(kwadrat.size()<9) {
                        kwadrat.add(tempSudoku[i][j]);
                        counter++;
                    }
                }
                System.out.print(tempSudoku[i][j] + " ");
                if (j == 2 || j == 5) System.out.print("| ");
            }
            counter = 0;
            if(column.size()<9)
                column.add(tempSudoku[i][0]);
            System.out.println();
            if (i == 2 || i == 5) System.out.println("---------------------");
        }

        System.out.println("---------------------\n wiersz");
        for(int i : row){
            System.out.println(i);
        }
        System.out.println("----------------------\n kolumna");
        for(int i : column){
            System.out.println(i);
        }
        System.out.println("-----------------------\n kwadrat");
        for(int i : kwadrat){
            System.out.println(i);
        }

        ra.searchForProbablyValue();
    }
}
/*  1. sprawdz dla pola w kolekcji kwadratu jakie moze przyjac wartosci
    2. zapisz mozliwosci do kolekcji
    3. sprawdz mozliwe wartosci pola pod wzgledem powtorzen w wierszu
    4. usun z kolekcji mozliwosci pola wartosci powtarzajace sie w wierszu
    5. sprawdz mozliwe wartosci pola pod wzgledem powtorzen w kolumnie
    6. usun z kolekcji mozliwosci pola wartosci powtarzajace sie w kolumnie
    7. jesli kolekcja mozliwosci == 1, ustaw ta wartosc w polu
 */


