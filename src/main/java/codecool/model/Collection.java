package codecool.model;

import java.util.ArrayList;

public class Collection {

    int idOfColumn;
    ArrayList<Integer> listOfFieldsInThisRow;

    public Collection(int idOfColumn){
        this.idOfColumn = idOfColumn;
        listOfFieldsInThisRow = new ArrayList<>();
    }

    public void addField(int field){
        listOfFieldsInThisRow.add(field);
    }
}
