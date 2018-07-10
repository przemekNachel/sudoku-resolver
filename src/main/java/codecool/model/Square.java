package codecool.model;

import java.util.ArrayList;

public class Square {

    int idOfSquare;
    ArrayList<Integer> listOfFieldsInThisSquare;

    public Square(int idOfSquare){
        this.idOfSquare = idOfSquare;
        listOfFieldsInThisSquare = new ArrayList<>();
    }

    public void addField(int field){
        listOfFieldsInThisSquare.add(field);
    }
}
