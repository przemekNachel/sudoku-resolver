package codecool.model;

import java.util.ArrayList;

public class Row {

    int idOfRow;
    ArrayList<Integer> listOfFieldsInThisRow;

    public Row(int idOfRow){
        this.idOfRow = idOfRow;
        listOfFieldsInThisRow = new ArrayList<>();
    }

    public void addField(int field){
        listOfFieldsInThisRow.add(field);
    }
}
