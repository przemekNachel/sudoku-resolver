package codecool;

import java.util.ArrayList;

public class ResolveAlgorithm {
    //Tools sudoku = new Tools();
    ArrayList<Integer> probablyValue = new ArrayList<>();
    int arraySize = 10;


    public void fillArray() {
        for (int i = 1; i < arraySize; i++) {
            probablyValue.add(i);
        }

        System.out.println("-----------------------\n probably value");
        for (int i=0; i<probablyValue.size(); i++){
            System.out.println(probablyValue.get(i));
        }
    }

    public void searchForProbablyValue(){
        fillArray();
        for(int i : Tools.kwadrat){
            if(i!=0 && probablyValue.contains(i)){
                probablyValue.remove(i);
            }
        }
        for(int i : Tools.row){
            if(i!=0 && probablyValue.contains(i)) {
                probablyValue.remove(i);
            }
        }
        for(int i : Tools.column){
            if(i!=0 && probablyValue.contains(i)){
                probablyValue.remove(i);
            }
        }

        System.out.println("-----------------------\n probably value");
        for (int i=0; i<probablyValue.size(); i++){
            System.out.println(probablyValue.get(i));
        }
    }
}
